package sample;

import com.mysql.jdbc.Statement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Ersan on 4/28/2017.
 */
 class PlayerDAO {

    private Connection con;

    public PlayerDAO() throws Exception {

        Properties props = new Properties();

        con = DriverManager.getConnection("jdbc:mysql://35.161.234.133:3306/",
                "Ersan", "pass");
    }

    private static void close(Connection con, Statement myStmt, ResultSet myRs){}

    public List<Player> getAllPlayers() throws Exception{
        List<Player> list = new ArrayList<>();

        Statement myStmt = null;
        ResultSet myRs = null;

        try{
            myStmt = (Statement) con.createStatement();
            myRs = myStmt.executeQuery("select * from Players");

            while(myRs.next()){
                Player tempPlayer = (Player) convertRowToPlayer(myRs);
                list.add(tempPlayer);
            }

            return list;

        }
        finally{
            close(myStmt,myRs);
        }

    }




    public List<Player> searchPlayers(String name) throws Exception{
        List<Player> list= new ArrayList<>();

        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            name += "%";
            myStmt = con.prepareStatement("select * from Players where name like ?");

            myStmt.setString(2, name);

            myRs = myStmt.executeQuery();

            while (myRs.next()) {
                Player tempPlayer = convertRowToPlayer(myRs);
                list.add(tempPlayer);
            }
            return list;
        }
        finally{
            close((Statement) myStmt,myRs);
        }
    }



    private Player convertRowToPlayer(ResultSet myRs) throws SQLException{
        int id = myRs.getInt("Id");
        String name = myRs.getString("Name");
        String dateofbirth = myRs.getString("Date of birth");
        String email = myRs.getString("E-mail");
        int rank = myRs.getInt("Rank");

        Player tempPlayer = new Player(id, name, dateofbirth, email, rank);

        return tempPlayer;
    }

    private void close(Connection con, PreparedStatement myStmt, ResultSet myRs) throws SQLException {

        if (myRs != null) {
            myRs.close();
        }

        if (myStmt != null) {

        }

        if (con != null) {
            con.close();
        }
    }

    private void close(Statement myStmt, ResultSet myRs) throws SQLException {
        close(null, (PreparedStatement) myStmt, myRs);
    }


    public static void main(String[] args) throws Exception{
        PlayerDAO dao = new PlayerDAO();

    }



    private void close(PreparedStatement myStmt) {
    }


    public void addPlayer(Player tempPlayer) {
    }
}




