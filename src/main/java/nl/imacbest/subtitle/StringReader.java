package nl.imacbest.subtitle;

import java.util.logging.Logger;

import static java.lang.Character.isDigit;

/**
 * Created by Thomas on 31-10-2015.
 */
public class StringReader {

    private Logger log = Logger.getLogger(getClass().getName());

    public void stringReader(String string) {
        char[] chars = new char[string.length()];
        for (int i = 0; i < string.length(); i++) {
            chars[i] = string.charAt(i);
        }
        try {
            try{
                int season = getSeason(chars);
                int epp = getEpisode(chars);
                System.out.println("S" + season);
                System.out.println("E" + epp);
            }catch (IllegalArgumentException e){
                log.info("No episode info found!");
            }
        } catch (NullPointerException e) {
            log.severe("Nullpointer exeption");
        }

//        System.out.println(string);
    }

    private int getEpisode(char[] chars) throws NullPointerException {
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'E' || chars[i] == 'e') {
                if ((i + 1) < chars.length && (i + 2) < chars.length) {
                    if (isDigit(chars[i + 1]) && isDigit(chars[i + 2])) {
                        return Integer.parseInt(chars[i + 1] + "" + chars[i + 2]);
                    }
                }
            }
        }
        throw new IllegalArgumentException("No episode found!");
    }

    private int getSeason(char[] chars) throws NullPointerException {
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'S' || chars[i] == 's') {
                if ((i + 1) < chars.length && (i + 2) < chars.length) {
                    if (isDigit(chars[i + 1]) && isDigit(chars[i + 2])) {
                        return Integer.parseInt(chars[i + 1] + "" + chars[i + 2]);
                    }
                }
            }
        }
        throw new IllegalArgumentException("No season found!");
    }
}
