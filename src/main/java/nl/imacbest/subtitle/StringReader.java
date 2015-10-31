package nl.imacbest.subtitle;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import static java.lang.Character.isDigit;

/**
 * Created by Thomas on 31-10-2015.
 */
public class StringReader {

    private Logger log = Logger.getLogger(getClass().getName());
    private int episode;
    private int season;
    private Sort sort;

    public void stringReader(String string) {
        setFileSort(string);
        if(sort != Sort.UNKNOWN) {
            char[] chars = new char[string.length()];
            for (int i = 0; i < string.length(); i++) {
                chars[i] = string.charAt(i);
            }
            try {
                try {
                    this.season = getSeason(chars);
                } catch (IllegalArgumentException e) {
                    System.out.println(string);
                    log.info(e.getMessage());
                }
                try {
                    this.episode = getEpisode(chars);
                } catch (IllegalArgumentException e) {
                    System.out.println(string);
                    log.info(e.getMessage());
                }
            } catch (NullPointerException e) {
                log.severe("Nullpointer exeption");
            }
        }

//        System.out.println(string);
    }

    private int getEpisode(char[] chars) throws IllegalArgumentException, NullPointerException {
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'E' || chars[i] == 'e') {
                if ((i + 1) < chars.length && (i + 2) < chars.length) {
                    if (isDigit(chars[i + 1]) && isDigit(chars[i + 2])) {
                        return Integer.parseInt(chars[i + 1] + "" + chars[i + 2]);
                    }
                }
            }
            if(isDigit(chars[i]) && isDigit(chars[i+1]) && (chars[i+2]=='x' || chars[i+2]=='X') &&
                    isDigit(chars[i+3]) && isDigit(chars[i+4])){
                //System.out.println(chars[i] + "" + chars[i+1] + "" + chars[i+2] + "" + chars[i+3] + "" + chars[i+4]);
                return Integer.parseInt( chars[i+3] + "" + chars[i+4] );
            }
            if(chars[i] =='x' || chars[i]=='X'){
                if(isDigit(chars[i+1]) && isDigit(chars[i+2])){
                    return Integer.parseInt(chars[i+1] + "" + chars[i+2]);
                }if(isDigit(chars[i+1])){
                    return Integer.parseInt(chars[i+1]+"");
                }
            }
        }
        System.out.println(chars);
        throw new IllegalArgumentException("No episode found!");
    }

    private int getSeason(char[] chars) throws IllegalArgumentException, NullPointerException {
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'S' || chars[i] == 's') {
                if ((i + 1) < chars.length && (i + 2) < chars.length) {
                    if (isDigit(chars[i + 1]) && isDigit(chars[i + 2])) {
                        return Integer.parseInt(chars[i + 1] + "" + chars[i + 2]);
                    }
                }
            }
            if(isDigit(chars[i]) && isDigit(chars[i+1]) && (chars[i+2]=='x' || chars[i+2]=='X') &&
                    isDigit(chars[i+3]) && isDigit(chars[i+4])){
                //System.out.println(chars[i] + "" + chars[i+1] + "" + chars[i+2] + "" + chars[i+3] + "" + chars[i+4]);
                return Integer.parseInt(chars[i] + "" + chars[i+1] );
            }

            if(chars[i] =='x' || chars[i]=='X'){
                if(isDigit(chars[i-1]) && isDigit(chars[i-2])){
                    return Integer.parseInt(chars[i-1] + "" + chars[i-2]);
                }if(isDigit(chars[i-1])){
                    return Integer.parseInt(chars[i-1]+"");
                }
            }
        }
        System.out.println(chars);
        throw new IllegalArgumentException("No season found!");
    }

    public void setFileSort(File file){
        if(isVideo(file)){
            sort = Sort.VIDEO;
        }else if( isSubtitle(file)){
            sort = Sort.SUBTITLE;
        }else{
            sort = Sort.UNKNOWN;
        }
    }
    public void setFileSort(String string){
        if(isVideo(string)){
            sort = Sort.VIDEO;
        }else if( isSubtitle(string)){
            sort = Sort.SUBTITLE;
        }else{
            sort = Sort.UNKNOWN;
        }
    }

    public boolean isVideo(File file) {
        String extension = FilenameUtils.getExtension(file.getName());
        if("mvk".equals(extension) || "mp4".equals(extension) || "mkv".equals(extension)){
            this.sort = Sort.VIDEO;
            return true;
        }
        return false;
    }
    public boolean isVideo(String string) {
        String[] split = string.split(Pattern.quote("."));
        String extension = split[split.length-1];
        if("mvk".equals(extension) || "mp4".equals(extension) || "mkv".equals(extension)){
            this.sort = Sort.VIDEO;
            return true;
        }
        return false;
    }

    public boolean isSubtitle(File file) {
        String extension = FilenameUtils.getExtension(file.getName());
        if("srt".equals(extension)){
            this.sort = Sort.SUBTITLE;
            return true;
        }
        return false;
    }
    public boolean isSubtitle(String string) {
        String[] split = string.split(Pattern.quote("."));
        String extension = split[split.length-1];
        if("srt".equals(extension)){
            this.sort = Sort.SUBTITLE;
            return true;
        }
        return false;
    }

    public int getEpisode() {
        return episode;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }
}
