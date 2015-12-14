package nl.imacbest.subtitle;

import java.util.ArrayList;

/**
 * Created by Thomas on 24-11-2015.
 */
public class Serie {

    private String title;
    private String startYear;
    private String endYear;
    private ArrayList<Season> seasons = new ArrayList<Season>();

    public Serie(String title) {
        this.title = title;
    }

    public Serie(String title, String startYear, String endYear, ArrayList<Season> seasons) {
        this.title = title;
        this.startYear = startYear;
        this.endYear = endYear;
        this.seasons = seasons;
    }

    public Season hasSeason(int seasonNr){
        for(Season season : getSeasons()){
            if(season.getSeasonNr() == seasonNr){
                return season;
            }
        }
        Season season = new Season(seasonNr);
        seasons.add(season);
        return season;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(ArrayList<Season> seasons) {
        this.seasons = seasons;
    }
}
