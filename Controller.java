package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.beans.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;

import static sample.DBConnection.getConnection;
import static sun.audio.AudioPlayer.player;

public class Controller{



    @FXML
    private Button readPlayer;
    @FXML
    private Button deletePlayer;
    @FXML
    private Button editPlayer;
    @FXML
    private Button addPlayerButton;
    @FXML
    private Button addMatch;
    @FXML
    private Button editMatch;
    @FXML
    private Button deleteMatch;
    @FXML
    private Button readMatches;
    @FXML
    private Button tournamentButton;
    @FXML
    private Button addTeamButton;
    @FXML
    private Button editTeamButton;
    @FXML
    private Button readTeamButton;
    @FXML
    private Button deleteTeamButton;
    @FXML
    private Button plTab;
    @FXML
    private Button teamTab;
    @FXML
    private Button matchTab;
    @FXML
    private Button goBack;
    @FXML
    private Button editMatch1;



    //player`s table
    @FXML
    private TableColumn<Integer, Player> id;
    @FXML
    private TableColumn<String, Player> name;
    @FXML
    private TableColumn<String, Player> dateofbirth;
    @FXML
    private TableColumn<String, Player> email;
    @FXML
    private TableColumn<Integer, Player> rank;
    @FXML
    private TableView<Player> plTable;
    @FXML
    private TextField idTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField dateofbirthTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField rankTextField;
    @FXML
    private TextField editNameTextField;
    @FXML
    private TextField dateEditTextField;
    @FXML
    private TextField emailEditTextField;
    @FXML
    private TextField rankEditTextField;


    //match table
    @FXML
    private TextField team1TextField;
    @FXML
    private TextField team2TextField;
    @FXML
    private DatePicker matchDatePicker;
    @FXML
    private TextField scoreTextField;


    @FXML
    private TableView<Match> matchTable;

    @FXML
    private TableColumn<String, Team> team1Column;
    @FXML
    private TableColumn<String, Team> team2Column;
    @FXML
    private TableColumn<String, Team> dateColumn;
    @FXML
    private TableColumn<Integer, Team> scoreColumn;


    //team table
     @FXML
     private TableView<Team> teamTable;
     @FXML
     private TableColumn teamNameColumn;
     @FXML
     private TableColumn player1Column;
     @FXML
     private TableColumn player2Column;

     @FXML
     private TextField player1TextField;
     @FXML
     private TextField player2TextField;
     @FXML
     private TextField teamNameTextField;



    @FXML
    public void goToPlayersTab(javafx.event.ActionEvent event) throws IOException {


        Stage stage;
        Parent root;
        if(event.getSource()==plTab){
            //get reference to the button's stage
            stage=(Stage) plTab.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("Foosball.fxml"));
        }
        else{
            stage=(Stage) goBack.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("FirstWindow.fxml"));
        }
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();



    }


    @FXML
    public void goToTeamTab(javafx.event.ActionEvent event) throws Exception {

        Stage stage;
        Parent root;
        if(event.getSource()==teamTab){
            //get reference to the button's stage
            stage=(Stage) teamTab.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("TeamTab.fxml"));
        }
        else{
            stage=(Stage) goBack.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("FirstWindow.fxml"));
        }
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    public void goToMatchTab(javafx.event.ActionEvent event) throws Exception {

        Stage stage;
        Parent root;
        if(event.getSource()==matchTab){
            //get reference to the button's stage
            stage=(Stage) matchTab.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("MatchTab.fxml"));
        }
        else{
            stage=(Stage) goBack.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("FirstWindow.fxml"));
        }
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }




    //Players




    @FXML
    public void addPlayer(javafx.event.ActionEvent event) throws Exception {

        ObservableList<Player> plData = FXCollections.observableArrayList();

        int id = Integer.parseInt(idTextField.getText());
        String name = nameTextField.getText();
        String dateofbirth = dateofbirthTextField.getText();
        String email = emailTextField.getText();
        int rank = Integer.parseInt(rankTextField.getText());


        try {
            Connection con = DBConnection.getConnection();
            Statement myStmt = con.createStatement();
            myStmt.executeUpdate("INSERT INTO `Foosball system`.`Players` (`Id`, `Name`, `Date of birth`, `E-mail`, `Rank`) VALUES ('"+id+"', '"+name+"', '"+dateofbirth+"', '"+email+"', '"+rank+"');");



            con.close();

        }catch (Exception exc){
            exc.printStackTrace();
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Congratulations!");
        alert.setHeaderText(("You have just created a new player!\n You can push the \"Read\" button to see the updated list!"));
        alert.showAndWait();

    }


    @FXML
    public void updatePlayer(javafx.event.ActionEvent event) throws Exception {

        Stage stage;
        Parent root;
        if (event.getSource() == editPlayer) {
            //get reference to the button's stage
            stage = (Stage) editPlayer.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("editPlayer.fxml"));
        } else {
            stage = (Stage) goBack.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Foosball.fxml"));
        }
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    public void updatePlayerr(javafx.event.ActionEvent event) throws Exception {


        Player player = plTable.getSelectionModel().getSelectedItem();
        idTextField.setText(String.valueOf(player.getId()));
        nameTextField.setText(player.getName());
        dateofbirthTextField.setText(player.getDateofbirth());
        emailTextField.setText(player.getEmail());
        rankTextField.setText(String.valueOf(player.getRank()));

    }

      @FXML
    public void savePlayer(javafx.event.ActionEvent event) throws Exception{

    Player player = plTable.getSelectionModel().getSelectedItem();
    int x = player.getId();

        try {

            Connection con = DBConnection.getConnection();
            Statement myStmt = con.createStatement();
            myStmt.executeUpdate("UPDATE `Players` SET `Name` = '"+nameTextField.getText()+"', " +
                    "`Date of birth` = '"+dateofbirthTextField.getText()+"', " +
                    "`E-mail` = '"+emailTextField.getText()+"', " +
                    "`Rank` = '"+rankTextField.getText()+"'" +
                    " WHERE Id = '"+x+"' ");

            con.close();
            myStmt.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Congratulations!");
            alert.setHeaderText("You have just edited a player!" +
                    "\n Click the \"Read\" button again to see the updated list!");
            alert.showAndWait();




        }catch (Exception exc){
            exc.printStackTrace();
        }


    }



    @FXML
    public void readPlayers(javafx.event.ActionEvent event) throws Exception {

        ObservableList<Player> plData = FXCollections.observableArrayList();

        //model
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateofbirth.setCellValueFactory(new PropertyValueFactory<>("dateofbirth"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        rank.setCellValueFactory(new PropertyValueFactory<>("rank"));


        try {
            Connection con = DBConnection.getConnection();
            Statement myStmt = con.createStatement();
            ResultSet myRs = myStmt.executeQuery("select * from Players");

            while(myRs.next()) {
                plData.add(new Player(
                        myRs.getInt("Id"),
                        myRs.getString("Name"),
                        myRs.getString("Date of birth"),
                        myRs.getString("E-mail"),
                        myRs.getInt("Rank")
                ));
                plTable.setItems(plData);
            }

            con.close();



        }catch (Exception exc){
            exc.printStackTrace();
        }


    };


    @FXML
    public void deletePlayer(javafx.event.ActionEvent event) throws Exception {

        try {
            Player player = plTable.getSelectionModel().getSelectedItem();
            Connection con = DBConnection.getConnection();
            Statement myStmt = con.createStatement();
            myStmt.executeUpdate("DELETE FROM `Players` WHERE `Name` = '"+player+"', " +
                    "`Date of birth` = '"+player+"', " +
                    "`E-mail` = '"+player+"', " +
                    "`Rank` = '"+player+"'," +
                    " 'Id' = '"+player+"' ");

            con.close();
            myStmt.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Congratulations!");
            alert.setHeaderText("You have just deleted a player!" +
                    "\n Click the \"Read\" button again to see the updated list!");
            alert.showAndWait();




        }catch (Exception exc){
            exc.printStackTrace();
        }
    }


    //Match
    @FXML
    public void addMatch(javafx.event.ActionEvent event) throws Exception {


        ObservableList<Match> matchData = FXCollections.observableArrayList();

        String team1 = team1TextField.getText();
        String team2 = team2TextField.getText();
        LocalDate date = matchDatePicker.getValue();
        String score = scoreTextField.getText();



        try {
            Connection con = DBConnection.getConnection();
            Statement myStmt = con.createStatement();
            myStmt.executeUpdate("INSERT INTO `Foosball system`.`Matches` (`Team 1`, `Team 2`, `Score`, `Date`) VALUES ('"+team1+"', '"+team2+"', '"+score+"', '"+date+"');");

            con.close();
            myStmt.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Congratulations!");
            alert.setHeaderText("You have just created a new player!" +
                    "\n You can push the \"Read\" button again to see the updated list!");
            alert.showAndWait();




        }catch (Exception exc){
            exc.printStackTrace();
        }


    }


    @FXML
    public void editMatch(javafx.event.ActionEvent event) throws Exception {

        Stage stage;
        Parent root;
        if(event.getSource()==editMatch){
            //get reference to the button's stage
            stage=(Stage) editMatch1.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("editMatch.fxml"));
        }
        else{
            stage=(Stage) goBack.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("MatchTab.fxml"));
        }
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }

        @FXML
        public void updateMatch(javafx.event.ActionEvent event) throws Exception {


            Match match =  matchTable.getSelectionModel().getSelectedItem();
            team1TextField.setText(match.getTeam1());
            team2TextField.setText(match.getTeam2());
            scoreTextField.setText(match.getScore());
            matchDatePicker.setEditable(Boolean.parseBoolean(match.getDate()));
        }

    @FXML
    public void saveMatch(javafx.event.ActionEvent event) throws Exception{

        Match match =  matchTable.getSelectionModel().getSelectedItem();
        String y = match.getScore();

        try {


            Connection con = DBConnection.getConnection();
            Statement myStmt = con.createStatement();
            myStmt.executeUpdate("UPDATE `Matches` SET `Team 1` = '"+team1TextField.getText()+"', "+
                    "`Team 2` = '"+team2TextField.getText()+"', " +
                    "`Date` = '"+matchDatePicker.getPromptText()+"' " +
                    " WHERE Score = '"+y+"' ");

            con.close();
            myStmt.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Congratulations!");
            alert.setHeaderText("You have just edited a match!" +
                    "\n Click the \"Read\" button again to see the updated list!");
            alert.showAndWait();

        }catch (Exception exc){
            exc.printStackTrace();
        }


    }


    @FXML
    public void deleteMatch(javafx.event.ActionEvent event) throws Exception {

        Match match = matchTable.getSelectionModel().getSelectedItem();

        String y = match.getScore();
        try {


            Connection con = DBConnection.getConnection();
            Statement myStmt = con.createStatement();
            myStmt.executeUpdate("DELETE FROM `Matches` WHERE `Team 1` = '"+team1TextField.getText()+"', " +
                    "`Team 2` = '"+team2TextField.getText()+"', " +
                    "`Date` = '"+matchDatePicker.getEditor()+"', " +
                    " 'Score' = '"+y+"' ");

            con.close();
            myStmt.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Congratulations!");
            alert.setHeaderText("You have just deleted a match!" +
                    "\n Click the \"Read\" button again to see the updated list!");
            alert.showAndWait();

        }catch (Exception exc){
            exc.printStackTrace();
        }

    }

    @FXML
    public void readAllMatches(javafx.event.ActionEvent event) throws Exception {

        ObservableList<Match> matchData = FXCollections.observableArrayList();

        team1Column.setCellValueFactory(new PropertyValueFactory<>("Team1"));
        team2Column.setCellValueFactory(new PropertyValueFactory<>("Team2"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("Score"));




            try {
                Connection con = DBConnection.getConnection();
                Statement myStmt = con.createStatement();
                ResultSet myRs = myStmt.executeQuery("select * from Matches");

                while(myRs.next()) {
                    matchData.add(new Match(
                            myRs.getString("Team 1"),
                            myRs.getString("Team 2"),
                            myRs.getString("Score"),
                            myRs.getString("Date")
                    ));
                    matchTable.setItems(matchData);
                }

                con.close();



            }catch (Exception exc){
                exc.printStackTrace();
            }


        };


    @FXML
    public void tournamentMethod(javafx.event.ActionEvent event) throws Exception {

        System.out.println("go to tournaments scene");
    }


    //Team
    @FXML
    public void addTeam(javafx.event.ActionEvent event) throws Exception {

        ObservableList<Team> teamData = FXCollections.observableArrayList();

        String player1 = player1TextField.getText();
        String player2 = player2Column.getText();
        String teamName = teamNameTextField.getText();




        try {
            Connection con = DBConnection.getConnection();
            Statement myStmt = con.createStatement();
            myStmt.executeUpdate("INSERT INTO `Foosball system`.`Teams` (`Player 1`, `Player 2`, `Name`) VALUES ('"+player1+"', '"+player2+"', '"+teamName+"');");

            con.close();
            myStmt.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Congratulations!");
            alert.setHeaderText("You have just created a new team!" +
                    "\n You can push the \"Read\" button to see the updated list!");
            alert.showAndWait();




        }catch (Exception exc){
            exc.printStackTrace();
        }
    }

    @FXML
    public void editTeam(javafx.event.ActionEvent event) throws Exception {

            Team team = teamTable.getSelectionModel().getSelectedItem();
            player1TextField.setText(team.getPlayer1());
            player2TextField.setText(team.getPlayer2());
            teamNameTextField.setText(team.getTeamName());

    }

    @FXML
    public void saveTeam(javafx.event.ActionEvent event) throws Exception {

        Team team = teamTable.getSelectionModel().getSelectedItem();


        try {

            Connection con = DBConnection.getConnection();
            Statement myStmt = con.createStatement();
            myStmt.executeUpdate("UPDATE `Teams` SET `Name` = '"+teamNameTextField.getText()+"', `Player 1` = '"+player1TextField.getText()+"' WHERE Player 2 = '"+player2TextField.getText()+"'' ");

            con.close();
            myStmt.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Congratulations!");
            alert.setHeaderText("You have just edited a team!" +
                    "\n Click the \"Read\" button again to see the updated list!");
            alert.showAndWait();




        }catch (Exception exc){
            exc.printStackTrace();
        }
    }

    @FXML
    public void deleteTeam(javafx.event.ActionEvent event) throws Exception {

        System.out.println("Team deleted");
    }

    @FXML
    public void readTeam(javafx.event.ActionEvent event) throws Exception {


        ObservableList<Team> teamData = FXCollections.observableArrayList();

        teamNameColumn.setCellValueFactory(new PropertyValueFactory<>("teamName"));
        player1Column.setCellValueFactory(new PropertyValueFactory<>("player1"));
        player2Column.setCellValueFactory(new PropertyValueFactory<>("player2"));


           try {
                Connection con = DBConnection.getConnection();
                Statement myStmt = con.createStatement();
                ResultSet myRs = myStmt.executeQuery("select * from Teams");

                while(myRs.next()) {
                    teamData.add(new Team(
                            myRs.getString("Name"),
                            myRs.getString("Player 1"),
                            myRs.getString("Player 2")
                            ));
                    teamTable.setItems(teamData);
                }

                con.close();



            }catch (Exception exc){
                exc.printStackTrace();
            }


        };


}













