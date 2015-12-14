package nl.imacbest.subtitle;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Thomas on 24-11-2015.
 */
public class Episode {

    private int eppisodeNr;
    private ArrayList<Subtitle> subtitles = new ArrayList<Subtitle>();
    private ArrayList<VideoFile> videoFiles = new ArrayList<VideoFile>();


    public Episode(int nr) {
        this.eppisodeNr = nr;
    }

    public Episode(int eppisodeNr, ArrayList<Subtitle> subtitles, ArrayList<VideoFile> videoFiles) {
        this.eppisodeNr = eppisodeNr;
        this.subtitles = subtitles;
        this.videoFiles = videoFiles;
    }

    public int getEppisodeNr() {
        return eppisodeNr;
    }

    public void setEppisodeNr(int eppisodeNr) {
        this.eppisodeNr = eppisodeNr;
    }

    public ArrayList<Subtitle> getSubtitles() {
        return subtitles;
    }

    public void setSubtitles(ArrayList<Subtitle> subtitles) {
        this.subtitles = subtitles;
    }

    public ArrayList<VideoFile> getVideoFiles() {
        return videoFiles;
    }

    public void setVideoFiles(ArrayList<VideoFile> videoFiles) {
        this.videoFiles = videoFiles;
    }

    public void addFile(File file, Sort sort) {
        if(sort == Sort.SUBTITLE){
            subtitles.add(new Subtitle(file));
        }else if(sort == Sort.VIDEO){
            videoFiles.add(new VideoFile(file));
        }
    }
}
