package personal;

import java.sql.*;
import java.util.logging.Logger;

public class Bank {
    private static final Logger logger = Logger.getLogger(Bank.class.getName());
    public void createDatabase(){
        try{
            Connection conn = connect();
            String sql = initCard();
            Statement statement = conn.createStatement();
            statement.execute(sql);
        }catch (SQLException | ClassNotFoundException e){
            logger.info("Cannot connect the database");
        }

    }
    public void insert(String number,String pin,int balance){
        String sql = "INSERT INTO card(number,pin,balance) VALUES(?,?,?)";
        try{
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, number);
            pstmt.setString(2, pin);
            pstmt.setInt(3,balance);
            pstmt.executeUpdate();
        }catch (SQLException | ClassNotFoundException e){
            logger.info("Cannot insert data ");
        }
    }
    private Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:D:/Work/sqlite/db/card.s3db";
        return DriverManager.getConnection(url);
    }

    private  String initCard(){
        return  "CREATE TABLE IF NOT EXISTS card (\n"
                + " id integer PRIMARY KEY AUTOINCREMENT,\n"
                + " number text not null,\n"
                + " pin text not null,\n"
                + "balance integer default 0)";
    }
}
/*    public static void main(String[] args) {
        createDatabase();
    }*/