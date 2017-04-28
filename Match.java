package sample;

import java.util.Date;

/**
 * Created by Ersan on 4/23/2017.
 */
public class Match {


    private String team1;
    private String team2;
    private String score;
    private String date;


    public Match(){
        this.team1 = "";
        this.team2 = "";
        this.score = "";
        this.date = "";

    }


    public Match(String team1, String team2, String score, String date){
       this.team1 = team1;
       this.team2 = team2;
       this.score = score;
       this.date = date;

    }

    public String getTeam1(){
        return team1;
    }

    public void setTeam1(String team1){
        this.team1 = team1;
    }

    public String getTeam2(){
        return team2;
    }

    public void setTeam2(String team2){
        this.team2 = team2;
    }

    public String getScore(){
        return score;
    }

    public void setScore(String score){
        this.score = score;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }



}
