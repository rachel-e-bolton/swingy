package database;

import models.character.heros.Hero;
import models.gamestate.GameState;
import models.map.Map;

import java.io.*;
import java.sql.*;
import java.util.List;

public class Database {

    //MAP METHODS
    public static void CreateMapTable() throws ClassNotFoundException {

        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:swingy.db";

        String sql = "CREATE TABLE IF NOT EXISTS maps (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	map_object blob NOT NULL\n"
                + ");";

        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void CreateMap(Map map) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:swingy.db";

        String sql = "INSERT INTO maps(id,map_object) VALUES (?,?)";

        try {
            Connection conn = DriverManager.getConnection(url);
            PreparedStatement pstmt = conn.prepareStatement(sql);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oout = new ObjectOutputStream(baos);
            oout.writeObject(map);
            oout.close();

            pstmt.setInt(1, map.get_id());
            pstmt.setBytes(2, baos.toByteArray());
            pstmt.executeUpdate();
            conn.close();

        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Map GetMap(int id) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:swingy.db";

        String sql = "SELECT * FROM maps WHERE id = ?";

        try {
            Connection conn = DriverManager.getConnection(url);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            byte[] buf = rs.getBytes(2);
            if (buf != null) {
                ObjectInputStream objectIn = new ObjectInputStream(
                        new ByteArrayInputStream(buf));
                conn.close();
                return (Map) objectIn.readObject();
            }
            conn.close();

        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static int GetMaxMapId() throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:swingy.db";

        String sql = "SELECT MAX(id) FROM maps";

        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            conn.close();

            if (resultSet.getInt("MAX(id)")) {
                return resultSet.getInt("MAX(id)");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return -1;
    }

    //HERO METHODS
    public static void CreateHeroTable() throws ClassNotFoundException {

        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:swingy.db";

        String sql = "CREATE TABLE IF NOT EXISTS heros (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	hero_object blob NOT NULL\n"
                + ");";

        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void CreateHero(Hero hero) {

    }

    /*public static Hero GetHero(int id) {

    }*/

    public static void UpdateHero(Hero hero) {

    }

    //GAME STATE METHODS
    public static void CreateGameStateTable() throws ClassNotFoundException {

        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:swingy.db";

        String sql = "CREATE TABLE IF NOT EXISTS game_state (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	mapId integer NOT NULL,\n"
                + "	heroId integer NOT NULL,\n"
                + "	heroName varchar NOT NULL\n"
                + ");";

        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void CreateGameState(GameState gameState) {

    }

    /*public static List<GameState> GetSavedGames() {

    }*/

    public static void SaveGame(GameState gameState) {

    }
}
