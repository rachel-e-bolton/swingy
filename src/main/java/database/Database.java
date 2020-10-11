package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    public static void createMapTable() throws ClassNotFoundException {

        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:swingy.db";

        String sql = "CREATE TABLE IF NOT EXISTS maps (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	dimension integer NOT NULL\n"
                + ");";

        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);

         } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void createHeroTable() throws ClassNotFoundException {

        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:swingy.db";

        String sql = "CREATE TABLE IF NOT EXISTS heros (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	hero blob NOT NULL\n"
                + ");";

        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void createGameStateTable() throws ClassNotFoundException {

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

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
