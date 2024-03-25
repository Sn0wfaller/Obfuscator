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

public class Main {
    public static void main(String[] args) {
        if (Objects.equals(args[0], "obfusc")) {

            Obfuscating obfuscation = new Obfuscating();
            obfuscation.obfuscating(args[1], args[2]);
        }

        else if (Objects.equals(args[0], "unobfusc")) {

            Unobfuscating unobfuscatiion = new Unobfuscating();
            unobfuscatiion.unobfuscating(args[1], args[2]);
        }
    }
}