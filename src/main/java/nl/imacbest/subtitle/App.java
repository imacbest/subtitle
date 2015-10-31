package nl.imacbest.subtitle;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Logger;
import java.util.regex.Pattern;


/**
 * Hello world!
 */
public class App {
    private Logger log = Logger.getLogger(getClass().getName());
    private StringReader reader = new StringReader();

    private HashMap<Integer, HashMap<Integer, HashMap<Sort, ArrayList<File>>>> seasons = new HashMap<Integer, HashMap<Integer, HashMap<Sort, ArrayList<File>>>>();


    public static void main(String[] args) {
        App ap = new App();
    }

    public App() {
        readDirective("");
        showAllFiles();

    }

    private void showAllFiles() {
        Iterator<Integer> SEASONSITERATOR = seasons.keySet().iterator();
        while (SEASONSITERATOR.hasNext()) {
            Integer key = SEASONSITERATOR.next();
            System.out.println("=== SEASON #" + key + " ===");

            HashMap<Integer, HashMap<Sort, ArrayList<File>>> season = seasons.get(key);
            Iterator<Integer> EPISODEITERATOR = season.keySet().iterator();
            while (EPISODEITERATOR.hasNext()) {
                Integer eppisodeKey = EPISODEITERATOR.next();
                System.out.println("\t=== EPISODE #" + eppisodeKey + " ===");

                HashMap<Sort, ArrayList<File>> episode = season.get(eppisodeKey);

                ArrayList<File> vid = episode.get(Sort.VIDEO);
                System.out.println("\t\t==== VID =====");
                for (File temp : vid) {
                    System.out.println("\t\t\"" + temp.getAbsolutePath()+ '"');
                    //System.out.println(temp.getParent());
                }

                System.out.println("\t\t==== SRT =====");
                ArrayList<File> srt = episode.get(Sort.SUBTITLE);
                for (File temp : srt) {
                    System.out.println("\t\t\"" + temp.getAbsolutePath()+ '"');

                }

            }

        }
    }

    private void putFileInEpisode(File file) {
        reader.setFileSort(file);
        reader.stringReader(file.getName());
        if (reader.getSort() != Sort.UNKNOWN) {
            if (!seasons.containsKey(reader.getSeason())) {
                seasons.put(reader.getSeason(), new HashMap<Integer, HashMap<Sort, ArrayList<File>>>());
            }
            HashMap<Integer, HashMap<Sort, ArrayList<File>>> season = seasons.get(reader.getSeason());

            if (!season.containsKey(reader.getEpisode())) {
                season.put(reader.getEpisode(), new HashMap<Sort, ArrayList<File>>());
            }
            HashMap<Sort, ArrayList<File>> episode = season.get(reader.getEpisode());

            if (!episode.containsKey(Sort.SUBTITLE)) {
                episode.put(Sort.SUBTITLE, new ArrayList<File>());
            }
            if (!episode.containsKey(Sort.VIDEO)) {
                episode.put(Sort.VIDEO, new ArrayList<File>());
            }


            ArrayList<File> files = episode.get(reader.getSort());
            files.add(file);
        } else {
            log.info("File type was unknown. File is:" + file.getName());
        }
    }


    private void readDirective(String path) {
        File[] files = new File(path).listFiles();
        showFiles(files);
    }

    private void showFiles(File[] files) {
        for (File file : files) {
            if (file.isFile()) {
                reader.stringReader(file.getName());
                if (reader.getSort() != Sort.UNKNOWN) {
                    putFileInEpisode(file);
                }
            } else if (file.isDirectory()) {
                showFiles(file.listFiles());
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
