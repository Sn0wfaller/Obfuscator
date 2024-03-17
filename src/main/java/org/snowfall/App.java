package org.snowfall;

import java.util.Objects;

/**
 * 
 * Обфускация и деобфускация XML-файла, в зависимости от выбранного режима
 * (аргумента командной строки).
 * Бурков А. Э. 1к., 14 гр.
 * 
 * 
 */

public class App {
    public static void main(String[] args) {
        if (Objects.equals(args[0], "obfusc")) {

            Obfuscating obfuscation = new Obfuscating();
            obfuscation.obfuscating();
        }

        else if (Objects.equals(args[0], "unobfusc")) {

            Unobfuscating unobfuscatiion = new Unobfuscating();
            unobfuscatiion.unobfuscating();
        }
    }
}
