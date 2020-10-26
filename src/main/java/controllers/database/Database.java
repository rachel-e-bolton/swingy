package controllers.database;

import models.character.heros.Hero;
import models.gamestate.GameState;
import models.map.Map;
import java.io.*;
import java.sql.*;
import java.util.List;

public class Database {

    //MAP METHODS
    public static void CreateMapTable() throws ClassNotFoundException, SQLException {

        String sql = "CREATE TABLE IF NOT EXISTS maps (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	map_object blob NOT NULL\n"
                + ");";
        Connection conn = SQLiteHelper.GetInstance();

        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Create Map Table Exception: " + e.getMessage());
        } finally {
            SQLiteHelper.CloseConnection(conn);
        }

    }

    public static void CreateMap(Map map) throws ClassNotFoundException {

        String sql = "INSERT INTO maps(id,map_object) VALUES (?,?)";
        Connection conn = SQLiteHelper.GetInstance();

        try {
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
        } finally {
            SQLiteHelper.CloseConnection(conn);
        }
    }

    public static Map GetMap(int id) throws ClassNotFoundException {

        String sql = "SELECT * FROM maps WHERE id = ?";
        Connection conn = SQLiteHelper.GetInstance();

        try {

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
        } finally {
            SQLiteHelper.CloseConnection(conn);
        }
        return null;
    }

    public static int GetMaxMapId() throws ClassNotFoundException {

        String sql = "SELECT MAX(id) FROM maps";
        Connection conn = SQLiteHelper.GetInstance();

        try {

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
            System.out.println(e.getMessage());
        } finally {
            SQLiteHelper.CloseConnection(conn);
        }

        return -1;
    }

    //HERO METHODS
    public static void CreateHeroTable() throws ClassNotFoundException, SQLException {

        String sql = "CREATE TABLE IF NOT EXISTS heros (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	hero_object blob NOT NULL\n"
                + ");";
        Connection conn = SQLiteHelper.GetInstance();

        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);

        } catch (SQLException e) {
            System.out.println("Create Hero Table Exception: " + e.getMessage());
        } finally {
            SQLiteHelper.CloseConnection(conn);
        }

    }

    public static void CreateHero(Hero hero) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO heros(id,hero_object) VALUES (?,?)";
        Connection conn = SQLiteHelper.GetInstance();

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
            SQLiteHelper.CloseConnection(conn);
        }
    }

    public static Hero GetHero(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM heros WHERE id = ?";
        Connection conn = SQLiteHelper.GetInstance();

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
            SQLiteHelper.CloseConnection(conn);
        }
        return null;
    }

    public static void UpdateHero(Hero hero) throws ClassNotFoundException {

        String sql = "UPDATE heros SET hero_object = ? WHERE id = ?";
        Connection conn = SQLiteHelper.GetInstance();

        try {

            PreparedStatement pstmt = conn.prepareStatement(sql);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oout = new ObjectOutputStream(baos);
            oout.writeObject(hero);
            oout.close();

            pstmt.setBytes(1, baos.toByteArray());
            pstmt.setInt(2, hero.get_id());
            pstmt.executeUpdate();

        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        } finally {
            SQLiteHelper.CloseConnection(conn);
        }

    }

    public static int GetMaxHeroId() throws ClassNotFoundException, SQLException {

        String sql = "SELECT MAX(id) FROM heros";

        Connection conn = SQLiteHelper.GetInstance();

        try {
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
        } finally {
            SQLiteHelper.CloseConnection(conn);
        }

        return -1;
    }

    //GAME STATE METHODS
    public static void CreateGameStateTable() throws ClassNotFoundException, SQLException {

        String sql = "CREATE TABLE IF NOT EXISTS game_state (\n"
                + "	mapId integer NOT NULL,\n"
                + "	heroId integer NOT NULL,\n"
                + "	heroName varchar NOT NULL,\n"
                + " heroLevel integer,\n"
                + " heroXP integer\n"
                + ");";

        Connection conn = SQLiteHelper.GetInstance();

        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Create Game State Table Exception: " + e.getMessage());
        } finally {
            SQLiteHelper.CloseConnection(conn);
        }

    }

    public static void CreateGameState(GameState gameState) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO game_state(mapId,heroId,heroName,heroLevel,heroXp) VALUES (?,?,?,?,?)";
        Connection conn = SQLiteHelper.GetInstance();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, gameState.get_mapId());
            pstmt.setInt(2,gameState.get_heroId());
            pstmt.setString(3, gameState.get_heroName());
            pstmt.setInt(4, gameState.get_heroLevel());
            pstmt.setInt(5, gameState.get_heroXP());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            SQLiteHelper.CloseConnection(conn);
        }
    }

    /*public static List<GameState> GetSavedGames() {

    }*/

    public static void UpdateGameState(GameState gameState) throws ClassNotFoundException {
        String sql = "UPDATE game_state\n"
                + "	SET mapId = ?,\n"
                + "	heroName = ?,\n"
                + " heroLevel = ?,\n"
                + "	heroXP = ?,\n"
                + " WHERE heroId = ?";
        Connection conn = SQLiteHelper.GetInstance();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, gameState.get_mapId());
            pstmt.setString(2, gameState.get_heroName());
            pstmt.setInt(3, gameState.get_heroLevel());
            pstmt.setInt(4, gameState.get_heroXP());
            pstmt.setInt(5,gameState.get_heroId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            SQLiteHelper.CloseConnection(conn);
        }

    }
}
