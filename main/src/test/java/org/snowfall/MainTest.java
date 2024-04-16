package org.snowfall;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MainTest {

        @Mock
        Obfuscating obfuscation;

        @Mock
        Unobfuscating unobfuscation;

        @InjectMocks
        Main main = new Main();

        @Test
        public void obfuscateTest() {
                String writeFile = "src/test/resources/test_obfusc_write.txt";
                String readFile = "src/test/resources/test_orig.xml";

                when(obfuscation.obfuscating(anyString(), anyString())).thenReturn("Successfull obfuscating XML");
                String result = main.obfuscatingData(readFile, writeFile);

                assertEquals(result, "Successfull obfuscating XML");
                List<String> data = FileReader.readFromFile(writeFile);
                assertEquals(
                                "dIsH6XirQ.EtcQHV\n" +
                                                "SiH6XirQ.qvW6i\n" +
                                                "St4i6ItU.jUBIi\n" +
                                                "IB.ooo",
                                String.join("\n", data));
        }

        @Test
        public void unObfuscateTest() {
                String readFile = "src/test/resources/test_obfusc_write.txt";
                String writeFile = "src/test/resources/test_unobfusc.xml";

                when(unobfuscation.unobfuscating(anyString(), anyString())).thenReturn("Successfull unobfuscating XML");
                String result = main.unObfuscatingData(readFile, writeFile);

                assertEquals(result, "Successfull unobfuscating XML");
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

        @Test
        public void testObfuscMainMethod() {

                Main.main(new String[] { "obfusc", "src/test/resources/test_orig.xml",
                                "src/test/resources/test_obfusc_write.txt" });
        }

        @Test
        public void testUnObfuscMainMethod() {

                Main.main(new String[] { "unobfusc", "src/test/resources/test_orig.xml",
                                "src/test/resources/test_obfusc_write.txt" });
        }
}
