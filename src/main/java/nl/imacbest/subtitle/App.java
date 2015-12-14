package nl.imacbest.subtitle;

import java.io.File;

import java.util.logging.Logger;


/**
 * Hello world!
 */
public class App {
    private Logger log = Logger.getLogger(getClass().getName());
    private StringReader reader = new StringReader();

    private Serie serie;

    public static void main(String[] args) {
        App ap = new App();
    }

    public App() {
        String path = "Path to directive";
        serie = new Serie("Serie name");
        readDirective(path);
        //showAllFiles();

        System.out.println("Serie: " + serie.getTitle());
        for(Season season : serie.getSeasons()){
            System.out.println("SEASON: "+String.format("%02d", season.getSeasonNr()));

//            (new File(path + "\\S"+ String.format("%02d", season.getSeasonNr()))).mkdir();

            for(Episode episode : season.getEpisodes()){
                System.out.println("\tEpisode: " + episode.getEppisodeNr());

//                    (new File(path + "\\S"+ String.format("%02d", season.getSeasonNr())+ "\\S"+ String.format("%02d", season.getSeasonNr())
//                            +"E"+ String.format("%02d", episode.getEppisodeNr()) )).mkdir();

                    System.out.println("\t\tVideo files:");
                    for(VideoFile videoFile : episode.getVideoFiles()){
                        System.out.println("\t\t\t" + videoFile.getFile().getName());
                        System.out.println(videoFile.getFile().getParent());
                    }
                    System.out.println("\t\tSRT files:");
                    for(Subtitle subtitle : episode.getSubtitles()){
                        System.out.println("\t\t\t" + subtitle.getFile().getName());
                    }
            }

        }
    }
    private void readDirective(String path) {
        File[] files = new File(path).listFiles();
        showFiles(files);
    }

    private void showFiles(File[] files) {
        for (File file : files) {
            reader.setFileSort(file);
            reader.stringReader(file.getName());
            if (file.isFile()) {
                if (reader.getSort() == Sort.SUBTITLE || reader.getSort() == Sort.VIDEO) {
                    Season season = serie.hasSeason(reader.getSeason());
                    Episode episode = season.hasEpiside(reader.getEpisode());
                    episode.addFile(file, reader.getSort());
                }
            } else if (file.isDirectory()) {
                showFiles(file.listFiles());
            }
        }
    }


}
