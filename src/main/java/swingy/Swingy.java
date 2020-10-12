package swingy;

import database.Database;
import helpers.TextColors;
import models.character.heros.Hero;
import models.character.heros.HeroClass;
import models.character.villains.VillainClass;
import models.character.villains.VillainNames;
import models.map.Map;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Set;
import java.util.logging.Level;

public class Swingy {

    public static void InitialiseDB() throws ClassNotFoundException {
        Database.CreateMapTable();
        Database.CreateHeroTable();
        Database.CreateGameStateTable();
    }

    public static void main(String[] args) throws Exception {

        if ((args.length > 0) && (args[0].equals("console") || args[0].equals("gui"))) {

            if (args[0].equals("console")) {
                InitialiseDB();

                Hero hero = new Hero("Francine", HeroClass.Judge);
                Map map = new Map(hero);
                Database.CreateMap(map);
                Map retrvMap = Database.GetMap(map.get_id());
                System.out.println(map.get_id());
                System.out.println(map.get_dimension());
                System.out.println(retrvMap.get_id());
                System.out.println(retrvMap.get_dimension());

            } else if (args[0].equals("gui")) {
                System.out.println(TextColors.ANSI_BLUE + "GUI mode is still under construction. Check back soon." + TextColors.ANSI_RESET);
            }

        } else {
            System.out.println(TextColors.ANSI_RED + "Please run the game with a valid mode specified: console or gui." + TextColors.ANSI_RESET);
        }

        //Don't log to the stdout
        //java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

        //Setting database connection
        //Class.forName("org.sqlite.JDBC");
        //String url = "jdbc:sqlite:swingy.db";
        //try {
        //    Connection connection = DriverManager.getConnection(url);
        // } catch (Exception e) {
        //    System.out.println(e.getMessage());
        //}

        //Validator instantiation
        //Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

//        Test test = new Test();

        //Error checking on specific class based on class level annotation
//        Set<ConstraintViolation<Test>> errors = validator.validate(test);
//        System.out.println(errors.size());
    }
}
