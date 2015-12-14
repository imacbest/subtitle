package nl.imacbest.subtitle;

import java.util.ArrayList;

/**
 * Created by Thomas on 24-11-2015.
 */
public class Season {

    private int seasonNr;
    private ArrayList<Episode> episodes = new ArrayList<Episode>();

    public Season(int seasonNr, ArrayList<Episode> episodes) {
        this.seasonNr = seasonNr;
        this.episodes = episodes;
    }

    public Season(int seasonNr) {
        this.seasonNr = seasonNr;
    }

    public int getSeasonNr() {
        return seasonNr;
    }

    public void setSeasonNr(int seasonNr) {
        this.seasonNr = seasonNr;
    }

    public ArrayList<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(ArrayList<Episode> episodes) {
        this.episodes = episodes;
    }

    public Episode hasEpiside(int episodeNr) {
        for(Episode episode : episodes){
            if(episode.getEppisodeNr() == episodeNr){
                return episode;
            }
        }
        Episode episode = new Episode(episodeNr);
        episodes.add(episode);
        return episode;
    }
}
