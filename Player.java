package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Created by Ersan on 4/23/2017.
 */
public class Player {

    private int id;
    private String name;
    private String dateofbirth;
    private String email;
    private int rank;



        public Player(int id, String name, String dateofbirth, String email, int rank){

            this.id = id;
            this.name = name;
            this.dateofbirth = dateofbirth;
            this.email = email;
            this.rank = rank;
        }




    public Integer getId(){
            return id;
        }



        public String getName() {
            return name;
        }



        public String getDateofbirth() {
            return dateofbirth;
        }



        public String getEmail() {
            return email;
        }



        public Integer getRank() {
            return rank;
        }



    public void setRank(int rank){
        this.rank = rank;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id){
        this.id = id;
    }




}
