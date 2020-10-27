package swingy;

import controllers.database.Database;
import helpers.TextColors;
import models.gamestate.GameState;
import views.console.gamestate.GameStateView;

public class Swingy {

    public static void InitialiseDB() throws ClassNotFoundException {
        try {
            Database.CreateMapTable();
            Database.CreateHeroTable();
            Database.CreateGameStateTable();
        } catch (Exception e) {
            System.out.println("InitialiseDB Exception: " + e);
        }
    }

    public static void main(String[] args) throws Exception {

        if ((args.length > 0) && (args[0].equals("console") || args[0].equals("gui"))) {

            if (args[0].equals("console")) {
                InitialiseDB();

                GameState gameState = new GameState();
                GameStateView gameStateView = new GameStateView();


            } else {
                System.out.println(TextColors.ANSI_BLUE + "GUI mode is still under construction. Check back soon." + TextColors.ANSI_RESET);
            }

        } else {
            System.out.println(TextColors.ANSI_RED + "Please run the game with a valid mode specified: console or gui." + TextColors.ANSI_RESET);
        }

        //Validator instantiation
        //Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

//        Test test = new Test();

        //Error checking on specific class based on class level annotation
//        Set<ConstraintViolation<Test>> errors = validator.validate(test);
//        System.out.println(errors.size());
    }
}
