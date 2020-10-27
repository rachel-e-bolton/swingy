package swingy;

import controllers.character.HeroController;
import controllers.database.Database;
import controllers.gamestate.GameStateController;
import controllers.map.MapController;
import helpers.GeneralHelpers;
import helpers.TextColors;
import javafx.util.Pair;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import models.character.heros.Hero;
import models.character.heros.HeroClass;
import models.gamestate.GameState;
import models.map.Map;
import sun.java2d.loops.GeneralRenderer;
import views.console.gamestate.GameStateView;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

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

    public static HeroClass SelectClass(GameStateController gameStateController, Scanner in) {
        GeneralHelpers.ClearScreen();
        gameStateController.RequestClass();
        HeroClass arr[] = HeroClass.values();

        try {
            int classChoice = in.nextInt();

            while (classChoice < 0 || classChoice > arr.length){
                GeneralHelpers.ClearScreen();
                System.out.println(TextColors.ANSI_RED + "\nPlease select a valid option!\n" + TextColors.ANSI_RESET);
                SelectClass(gameStateController, in);
            }

            return HeroClass.valueOf(arr[in.nextInt()].toString());
        } catch (InputMismatchException inputMismatchException) {
            GeneralHelpers.ClearScreen();
            System.out.println(TextColors.ANSI_RED + "\nPlease select a valid option!\n" + TextColors.ANSI_RESET);
            SelectClass(gameStateController, in);
        }
        return null;
    }



    public static Pair<HeroController, MapController> StartGame(GameStateController gameStateController, Scanner in) {
        gameStateController.StartGame();

        try {
            String startOption = in.nextLine();

            while (!(startOption.matches("l|L|n|N|q|Q"))){
                GeneralHelpers.ClearScreen();
                System.out.println(TextColors.ANSI_RED + "\nPlease select a valid option!\n" + TextColors.ANSI_RESET);
                StartGame(gameStateController, in);
            }

            if (startOption.matches("n|N")){
                GeneralHelpers.ClearScreen();
                gameStateController.RequestName();
                String heroName = in.nextLine();
                SelectClass(gameStateController, in);
                //create Hero
                //create HeroController
                //save Hero
                //create Map
                //create Map Controller
                //save Map
                //create Game State
                //save Game State
                //return Pair<HeroController, MapController>
            } else if (startOption.matches("l|L")){
                gameStateController.ShowSavedGames();
                //create Hero from Saved Hero
                //create HeroController
                //create Map from Saved Map
                //create MapController
                //return Pair<HeroController, MapController>
            } else {
                return null;
            }
        } catch (InputMismatchException | ClassNotFoundException inputMismatchException) {
            GeneralHelpers.ClearScreen();
            System.out.println(TextColors.ANSI_RED + "\nPlease select a valid option!\n" + TextColors.ANSI_RESET);
            StartGame(gameStateController, in);
        }
        return null;
    }





    public static void main(String[] args) throws Exception {

        if ((args.length > 0) && (args[0].equals("console") || args[0].equals("gui"))) {

            if (args[0].equals("console")) {
                InitialiseDB();

                Scanner in = new Scanner(System.in);
                GameState gameState = new GameState();
                GameStateView gameStateView = new GameStateView();
                GameStateController gameStateController = new GameStateController(gameState, gameStateView);

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
