package org.snowfall;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ObfuscatingTest {

        @Test
        public void obfuscateData() {
                String writeFile = "src/test/resources/test_obfusc_write.txt";
                Obfuscating obfuscation = new Obfuscating();
                obfuscation.obfuscating(
                                "src/test/resources/test_orig.xml",
                                writeFile);
                List<String> data = FileReader.readFromFile(writeFile);
                assertEquals(
                                "dIsH6XirQ.EtcQHV\n" +
                                                "SiH6XirQ.qvW6i\n" +
                                                "St4i6ItU.jUBIi\n" +
                                                "IB.ooo",
                                String.join("\n", data));
        }

        @Test
        public void unobfuscateData() {
                String writeFile = "src/test/resources/test_unobfusc.xml";
                Unobfuscating unobfuscation = new Unobfuscating();
                unobfuscation.unobfuscating(
                                "src/test/resources/test_obfusc_write.txt",
                                writeFile);
                List<String> data = FileReader.readFromFile(writeFile);
                assertEquals(
                                "<Employees>\n" +
                                                "  <employee id=\"111\">\n" +
                                                "    <firstName>Lokesh</firstName>\n" +
                                                "    <lastName>Gupta</lastName>\n" +
                                                "    <location>India</location>\n" +
                                                "  </employee>\n" +
                                                "</Employees>",
                                String.join("\n", data));
        }
}
