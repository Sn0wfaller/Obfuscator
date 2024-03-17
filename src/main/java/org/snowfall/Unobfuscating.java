package org.snowfall;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.snowfall.FileReader.readFromFile;

public class Unobfuscating {

    public static final String source = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public static final String target = "A17yCzqfjKnE3XkwebphaYl8P5i04BQdTVIGcSrUtWgsH6vLuZRDxo2NFJOmM9";

    public void unobfuscating() {
        unobfuscating("src\\\\main\\\\resources\\\\obfusc.xml", "src\\\\main\\\\resources\\\\unobfusc.xml");
    }

    public void unobfuscating(String fromFile, String toFile) {
        List<String> lines = readFromFile(fromFile);
        Employees employees = new Employees();
        try {
            employees = parseObfuscatedData(lines);
        } catch (Exception e) {
            e.printStackTrace();
        }
        serializeToXML(employees, toFile);
    }

    private Employees parseObfuscatedData(List<String> lines) throws Exception {
        int employeeAmount = 0;

        Map<String, String[]> obfuscatedData = new HashMap<>();
        for (String line : lines) {
            String[] items = line.split("\\.");
            if (employeeAmount == 0) {
                employeeAmount = items.length - 1;
            } else if (employeeAmount != items.length - 1) {
                throw new Exception("Number of elements in lines are not the same");
            }
            obfuscatedData.put(decodeString(items[0]), Arrays.copyOfRange(items, 1, items.length));
        }

        List<Employee> employeeList = new ArrayList<>();
        for (int i = 0; i < employeeAmount; i++) {
            Employee employee = new Employee();
            for (String key : Employee.getValues()) {
                String decodedValue = decodeString(obfuscatedData.get(key)[i]);
                employee.setValue(key, decodedValue);
            }
            employeeList.add(employee);
        }

        return new Employees(employeeList);
    }

    private void serializeToXML(Employees employees, String file) {
        try {
            ObjectMapper xmlMapper = new XmlMapper().enable(SerializationFeature.INDENT_OUTPUT);

            // serialize our Object into XML string
            String xmlString = xmlMapper.writeValueAsString(employees);

            // write XML string to file
            File xmlOutput = new File(file);
            FileWriter fileWriter = new FileWriter(xmlOutput);
            fileWriter.write(xmlString);
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("Error in serializing XML");
            e.printStackTrace();
        }
    }

    private String decodeString(String s) {
        return s.chars()
                .mapToObj(this::decodeChar)
                .map(Objects::toString)
                .collect(Collectors.joining());
    }

    private char decodeChar(int c) {
        int index = target.indexOf(c);
        return source.charAt(index);
    }
}
