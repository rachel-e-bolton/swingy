package controllers.database;

import models.character.heros.Hero;
import models.gamestate.GameState;
import models.map.Map;

import java.io.*;
import java.sql.*;

public class Database {

    //MAP METHODS
    public static void CreateMapTable() throws ClassNotFoundException {

        String sql = "CREATE TABLE IF NOT EXISTS maps (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	map_object blob NOT NULL\n"
                + ");";

        try {
            Connection conn = SQLiteHelper.getConn();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void CreateMap(Map map) throws ClassNotFoundException {

        String sql = "INSERT INTO maps(id,map_object) VALUES (?,?)";

        try {
            Connection conn = SQLiteHelper.getConn();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oout = new ObjectOutputStream(baos);
            oout.writeObject(map);
            oout.close();

            pstmt.setInt(1, map.get_id());
            pstmt.setBytes(2, baos.toByteArray());
            pstmt.executeUpdate();

        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Map GetMap(int id) throws ClassNotFoundException {

        String sql = "SELECT * FROM maps WHERE id = ?";

        try {
            Connection conn = SQLiteHelper.getConn();
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

        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static int GetMaxMapId() throws ClassNotFoundException {

        String sql = "SELECT MAX(id) FROM maps";

        try {
            Connection conn = SQLiteHelper.getConn();
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            while (resultSet.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    String columnValue = resultSet.getString(i);
                    if (columnValue == null) {
                        return -1;
                    } else {
                        return Integer.parseInt(columnValue);
                    }
                }
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        return -1;
    }

    //HERO METHODS
    public static void CreateHeroTable() throws ClassNotFoundException, SQLException {

        String sql = "CREATE TABLE IF NOT EXISTS heros (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	hero_object blob NOT NULL\n"
                + ");";

        Connection conn = SQLiteHelper.getConn();

        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conn.close();
        }

    }

    public static void CreateHero(Hero hero) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO heros(id,hero_object) VALUES (?,?)";
        Connection conn = SQLiteHelper.getConn();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oout = new ObjectOutputStream(baos);
            oout.writeObject(hero);
            oout.close();

            pstmt.setInt(1, hero.get_id());
            pstmt.setBytes(2, baos.toByteArray());
            pstmt.executeUpdate();

        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        } finally {
            conn.close();
        }
    }

    public static Hero GetHero(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM heros WHERE id = ?";
        Connection conn = SQLiteHelper.getConn();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            byte[] buf = rs.getBytes(2);
            if (buf != null) {
                ObjectInputStream objectIn = new ObjectInputStream(
                        new ByteArrayInputStream(buf));
                conn.close();
                return (Hero) objectIn.readObject();
            }

        } catch (SQLException | IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            conn.close();
        }
        return null;
    }

    public static void UpdateHero(Hero hero) {

        String sql = "UPDATE heros SET hero_object = ? WHERE id = ?";

        try {
            Connection conn = SQLiteHelper.getConn();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oout = new ObjectOutputStream(baos);
            oout.writeObject(hero);
            oout.close();

            pstmt.setBytes(1, baos.toByteArray());
            pstmt.setInt(2, hero.get_id());
            pstmt.executeUpdate();

        } catch (SQLException | IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    public static int GetMaxHeroId() throws ClassNotFoundException {

        String sql = "SELECT MAX(id) FROM heros";

        try {
            Connection conn = SQLiteHelper.getConn();
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            while (resultSet.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    String columnValue = resultSet.getString(i);
                    if (columnValue == null) {
                        return -1;
                    } else {
                        return Integer.parseInt(columnValue);
                    }
                }
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        return -1;
    }

    //GAME STATE METHODS
    public static void CreateGameStateTable() throws ClassNotFoundException {

        String sql = "CREATE TABLE IF NOT EXISTS game_state (\n"
                + "	mapId integer NOT NULL,\n"
                + "	heroId integer NOT NULL,\n"
                + "	heroName varchar NOT NULL\n"
                + ");";

        try {
            Connection conn = SQLiteHelper.getConn();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);

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
