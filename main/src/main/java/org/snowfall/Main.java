package org.snowfall;

import org.apache.log4j.Logger;

/**
 * 
 * Обфускация и деобфускация XML-файла, в зависимости от выбранного режима
 * (аргумента командной строки).
 * Бурков А. Э. 1к., 14 гр.
 * 
 * 
 */

public class Main {

    private static final Logger log = Logger.getLogger(Main.class);

    private Obfuscating obfuscation = new Obfuscating();
    private Unobfuscating unobfuscatiion = new Unobfuscating();

    public static void main(String[] args) {

        Main main = new Main();
        if (args[0].equals("obfusc")) {

            System.out.println(main.obfuscatingData(args[1], args[2]));
        } else if (args[0].equals("unobfusc")) {

            System.out.println(main.unObfuscatingData(args[1], args[2]));
        }
    }

    public String obfuscatingData(String readFrom, String writeTo) {

        String result = "";
        try {
            result = obfuscation.obfuscating(readFrom, writeTo);
            log.info(result);
            return result;
        } catch (Exception e) {
            log.info(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public String unObfuscatingData(String readFrom, String writeTo) {

        String result = "";
        try {
            result = unobfuscatiion.unobfuscating(readFrom, writeTo);
            log.info(result);
            return result;
        } catch (Exception e) {
            log.info(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}