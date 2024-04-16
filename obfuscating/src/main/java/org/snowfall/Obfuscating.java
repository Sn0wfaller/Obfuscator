package org.snowfall;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static org.snowfall.FileReader.writeToFile;

public class Obfuscating {

    public static final String source = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public static final String target = "A17yCzqfjKnE3XkwebphaYl8P5i04BQdTVIGcSrUtWgsH6vLuZRDxo2NFJOmM9";

    public String obfuscating(String fromFile, String toFile) {

        String res = "";
        try {
            Employees employees = readFromXML(fromFile);
            res = obfuscateData(employees);
            writeToFile(res, toFile);
            return "Successfull obfuscating XML";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Unsuccessfull obfuscating XML";
    }

    private Employees readFromXML(String filePath) throws Exception {
        Employees employees = new Employees();
        try {
            XmlMapper xmlMapper = new XmlMapper();

            // read file and put contents into the string
            String readContent = new String(Files.readAllBytes(Paths.get(filePath)));

            // deserialize from the XML into a Employees object
            employees = xmlMapper.readValue(readContent, Employees.class);
        } catch (IOException e) {
            System.err.println("Error in deserializing XML");
            e.printStackTrace();
        }
        return employees;
    }

    private String obfuscateData(Employees employees) throws Exception {
        // create map where
        // key - sign from XML
        // List - encoded data from read data
        Map<String, List<String>> map = new HashMap<>();
        for (String key : Employee.getValues()) {
            map.put(key, new ArrayList<>());
        }

        // put encoded sign value in list head
        for (String key : map.keySet()) {
            map.get(key).add(encodeString(key));
        }

        for (Employee e : employees.getEmployeeList()) {
            for (String key : Employee.getValues()) {
                String encodedValue = encodeString(e.getValue(key));
                map.get(key).add(encodedValue);
            }
        }

        // collect all values
        StringBuilder stringBuilder = new StringBuilder();
        for (List<String> value : map.values()) {
            stringBuilder.append(String.join(".", value)).append('\n');
        }
        return stringBuilder.toString();
    }

    private String encodeString(String s) {
        return s.chars()
                .mapToObj(c -> encodeChar((char) c))
                .map(Objects::toString)
                .collect(Collectors.joining());
    }

    private char encodeChar(char c) {
        int index = source.indexOf(c);
        return target.charAt(index);
    }
}
