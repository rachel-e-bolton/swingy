package swingy;

import controllers.database.Database;
import helpers.TextColors;
import models.character.heros.Hero;
import models.character.heros.HeroClass;
import models.map.Map;

import javax.xml.crypto.Data;

public class Swingy {

    public static void InitialiseDB() throws ClassNotFoundException {
        try {
            Database.CreateMapTable();
            Database.CreateHeroTable();
            Database.CreateGameStateTable();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {

        if ((args.length > 0) && (args[0].equals("console") || args[0].equals("gui"))) {

            if (args[0].equals("console")) {
                InitialiseDB();

                Hero hero = new Hero("Francine", HeroClass.Nurse);
                System.out.println(hero.get_attack());
                System.out.println(hero.get_id());
                Database.CreateHero(hero);
                Hero firstFetch = Database.GetHero(hero.get_id());
                System.out.println(firstFetch.get_attack());
                firstFetch.set_attack(firstFetch.get_attack()+25);
                System.out.println(firstFetch.get_attack());
                System.out.println(firstFetch.get_id());
                Database.UpdateHero(firstFetch);
                Hero secondFetch = Database.GetHero(firstFetch.get_id());
                System.out.println(secondFetch.get_attack());

            } else if (args[0].equals("gui")) {
                System.out.println(TextColors.ANSI_BLUE + "GUI mode is still under construction. Check back soon." + TextColors.ANSI_RESET);
            }

        } else {
            System.out.println(TextColors.ANSI_RED + "Please run the game with a valid mode specified: console or gui." + TextColors.ANSI_RESET);
        }

        //Don't log to the stdout
        //java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

        //Setting controllers.database connection
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
