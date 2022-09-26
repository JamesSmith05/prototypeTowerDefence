package database;

import gameFolder.GamePanel;
import logic.AssetSetter;

import java.sql.*;

public class DBaccess{

    private final String dbConnectionUrl = "jdbc:ucanaccess://theBigDataSheet.accdb";

    private Connection dbConnection = null;

    public DBaccess(){
    }

    private Statement getSqlStatement() throws SQLException {

        if (dbConnection == null) {
            dbConnection = DriverManager.getConnection(dbConnectionUrl, "", ""); // connects to db
        }

        return dbConnection.createStatement();
    }

    private boolean executeUpdateSql(String sqlQuery) {
        try {
            Statement stmt = getSqlStatement();
            stmt.executeUpdate(sqlQuery);
            stmt.close(); // to save resources

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void loadGameData(int gameSaveID , GamePanel gp) {
        String sqlQuery = "SELECT GameID, TowerID from GameTowerRelation";
        int towerID;
        int gameID;
        try {

            Statement stmt = getSqlStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);

            while (rs.next()) {
                gameID = rs.getInt("GameID");
                towerID = rs.getInt("TowerID");
                if (gameID == gameSaveID){
                    loadSingleTower(towerID,gp);
                }
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void loadSingleTower(int towerSavedID,GamePanel gp){

        String sqlQuery = "SELECT * from Tower";

        int towerID, xCoord,yCoord,elementID;
        boolean u1A,u1B,u1C,u2A,u2B,u2C;
        String towerName;
        try {
            Statement stmt = getSqlStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            while (rs.next()) {
                towerID = rs.getInt("TowerID");
                if (towerID == towerSavedID){
                    towerName = rs.getString("TowerName");
                    xCoord = rs.getInt("xCoord");
                    yCoord = rs.getInt("xCoord");
                    elementID = rs.getInt("ElementID");
                    u1A = rs.getBoolean("upgrade1A");
                    u1B = rs.getBoolean("upgrade1B");
                    u1C = rs.getBoolean("upgrade1C");
                    u2A = rs.getBoolean("upgrade2A");
                    u2B = rs.getBoolean("upgrade2B");
                    u2C = rs.getBoolean("upgrade2C");
                    gp.aSetter.loadTowerFromSave(xCoord,yCoord,towerName,elementID,u1A,u1B,u1C,u2A,u2B,u2C);
                }
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


//    private String getTopPlayers (int topCount, String dbColumnName){
//        String sqlQuery = String.format("SELECT TOP %s %s, Username FROM Users WHERE %s > 0 ORDER BY %s DESC", topCount, dbColumnName, dbColumnName, dbColumnName);
//
//        String topScores = "";
//
//        try {
//            Statement stmt = getSqlStatement();
//            ResultSet rs = stmt.executeQuery(sqlQuery);
//
//            while (rs.next()) { // for each top scorer
//                topScores = topScores + rs.getInt(dbColumnName)+ " - " + rs.getString("Username") + "\n";
//            }
//
//            stmt.close();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return topScores;
//    } // either HighestScore OR HighestRally
//
//    public boolean createUser(String username, String password) {
//
//        String passwordHashValue = HashGenerator.getHashValue(password);
//
//        String sqlQuery = String.format("INSERT INTO Users VALUES ('%s', '%s', 0, 0)", username, passwordHashValue);
//        return executeUpdateSql(sqlQuery);
//    }
//
//    public Boolean loginUser(String username, String password) {
//
//        String passwordHashValue = HashGenerator.getHashValue(password);
//
//        String sqlQuery = String.format("SELECT * FROM Users WHERE Username ='%s' AND Password = '%s'", username, passwordHashValue);
//
//        Boolean isValidUser = false;
//
//        try {
//            Statement stmt = getSqlStatement();
//            ResultSet rs = stmt.executeQuery(sqlQuery);
//
//            isValidUser = rs.next();
//
//            stmt.close();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return isValidUser;
//    }
//
//    public void updateHighScore (String username, int lastScore) {
//        String sqlQuery = String.format("UPDATE Users SET HighestScore = %s WHERE Username = '%s' AND HighestScore < %s", lastScore, username, lastScore);
//        executeUpdateSql(sqlQuery);
//    }
//
//    public String getTopScores(int topCount){
//        return getTopPlayers(topCount, "HighestScore");
//    }
//
//    public void updateHighestRally (String username, int lastRally) {
//        String sqlQuery = String.format("UPDATE Users SET HighestRally = %s WHERE Username = '%s' AND HighestRally < %s", lastRally, username, lastRally);
//        executeUpdateSql(sqlQuery);
//    }
//
//    public String getTopRallies(int topCount){
//        return getTopPlayers(topCount, "HighestRally");
//    }
}