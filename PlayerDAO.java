package sample;

import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Ersan on 4/28/2017.
 */
public class PlayerDAO {

    private Connection con;

    public PlayerDAO() throws Exception {

        Properties props = new Properties();

        con = DriverManager.getConnection("jdbc:mysql://35.161.234.133:3306/", "Ersan", "pass");
    }

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
                myRs.close();
                myStmt.close();
            }

        }

    private ResultSet convertRowToPlayer(ResultSet myRs) {
        return myRs;
    }


}

