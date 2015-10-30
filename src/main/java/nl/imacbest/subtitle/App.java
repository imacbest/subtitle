package nl.imacbest.subtitle;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.logging.Logger;

import static java.lang.Character.isDigit;

/**
 * Hello world!
 */
public class App {
    private Logger log = Logger.getLogger(getClass().getName());
    private StringReader reader = new StringReader();

    public static void main(String[] args) {
        (new App()).readDirective("");

    }

    private void readDirective(String path) {
        File[] files = new File(path).listFiles();
        showFiles(files);
    }

    private void showFiles(File[] files) {
        for (File file : files) {
            if(file.isFile()){
                reader.stringReader(file.getName());
                System.out.println(FilenameUtils.getExtension(file.getName()));
                System.out.println("File: " + file.getName());
            }
//            if (file.isDirectory()) {
//                System.out.println("Directory: " + file.getName());
//                showFiles(file.listFiles()); // Calls same method again.
//            } else {
//                System.out.println("File: " + file.getName());
//            }
        }
    }


}
