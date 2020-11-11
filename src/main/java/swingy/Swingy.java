package swingy;

import controllers.ControllerGroup;
import controllers.character.HeroController;
import controllers.database.Database;
import controllers.gamestate.GameStateController;
import controllers.map.MapController;
import helpers.GeneralHelpers;
import helpers.TextColors;
import models.character.heros.Hero;
import models.character.heros.HeroClass;
import models.gamestate.GameState;
import models.map.Map;
import views.console.character.HeroView;
import views.console.gamestate.GameStateView;
import views.console.map.MapView;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;

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

    public static HeroClass SelectClass(GameStateController gameStateController, Scanner in, ArrayList<String> errors) {
        GeneralHelpers.ClearScreen();
        errors.forEach(System.out::println);
        errors.clear();
        gameStateController.RequestClass();
        HeroClass arr[] = HeroClass.values();
        String classChoice = in.nextLine();

        try {
            if (classChoice.matches("q|Q")){
                System.exit(0);
            }

            int classChoiceInt = Integer.parseInt(classChoice);

            while (classChoiceInt < 0 || classChoiceInt >= arr.length){
                GeneralHelpers.ClearScreen();
                errors.add(TextColors.ANSI_RED + "\nPlease select a valid option!\n" + TextColors.ANSI_RESET);
                return SelectClass(gameStateController, in, errors);
            }

            return HeroClass.valueOf(arr[classChoiceInt].toString());
        } catch (InputMismatchException | NumberFormatException exception) {
            GeneralHelpers.ClearScreen();
            errors.add(TextColors.ANSI_RED + "\nPlease select a valid option!\n" + TextColors.ANSI_RESET);
            return SelectClass(gameStateController, in, errors);
        }
    }

    public static HeroController CreateHero(GameStateController gameStateController, Scanner in, ArrayList<String> errors) {
        GeneralHelpers.ClearScreen();
        errors.forEach(System.out::println);
        errors.clear();
        gameStateController.RequestName();
        String heroName = in.nextLine();
        HeroClass heroClass = SelectClass(gameStateController, in, errors);
        while (heroClass == null) {
            GeneralHelpers.ClearScreen();
            errors.add(TextColors.ANSI_RED + "\nSelection error. Please try again.\n" + TextColors.ANSI_RESET);
            heroClass = SelectClass(gameStateController, in, errors);
        }

        try {
            if (heroName.matches("q|Q")) {
                System.exit(0);
            }
            //Validator instantiation - for annotation based validation
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

            Hero hero = new Hero(heroName, heroClass);

            Set<ConstraintViolation<Hero>> modelErrors = validator.validate(hero);

            while (modelErrors.size() > 0){
                GeneralHelpers.ClearScreen();
                errors.add(TextColors.ANSI_RED + "\nPlease check that the length of your hero's name is between 3 and 25 characters.\n" + TextColors.ANSI_RESET);
                modelErrors.clear();
                return CreateHero(gameStateController, in, errors);
            }

            HeroView heroView = new HeroView();
            HeroController heroController = new HeroController(hero, heroView);
            heroController.SaveHero();

            return heroController;
        } catch (InputMismatchException inputMismatchException) {
            GeneralHelpers.ClearScreen();
            errors.add(TextColors.ANSI_RED + "\nPlease check that the length of your hero's name is between 3 and 25 characters.\n" + TextColors.ANSI_RESET);
            return CreateHero(gameStateController, in, errors);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static MapController CreateMap(HeroController heroController){
        int level = heroController.GetLevel();

        try {
            int mapId = Database.GetMaxMapId();
            Map map = new Map(mapId+1);
            map.set_dimension(((level-1)*5+10-(level%2)));

            MapView mapView = new MapView();
            MapController mapController = new MapController(map, mapView);
            mapController.SaveMap();

            return mapController;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ControllerGroup StartGame(GameStateController gameStateController, Scanner in, ArrayList<String> errors) {
        errors.forEach(System.out::println);
        errors.clear();
        gameStateController.StartGame();

        try {
            String startOption = in.nextLine();

            while (!(startOption.matches("l|L|n|N|q|Q"))){
                GeneralHelpers.ClearScreen();
                errors.add(TextColors.ANSI_RED + "\nPlease select a valid option!\n" + TextColors.ANSI_RESET);
                StartGame(gameStateController, in, errors);
            }

            if (startOption.matches("n|N")){
                HeroController heroController = CreateHero(gameStateController, in, errors);

                MapController mapController = CreateMap(heroController);

                gameStateController.CreateGameState(mapController.GetMap(), heroController.GetHero());

                ControllerGroup controllerGroup = new ControllerGroup(mapController, heroController, gameStateController);

                return controllerGroup;
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
        } catch (InputMismatchException | ClassNotFoundException | SQLException inputMismatchException) {
            GeneralHelpers.ClearScreen();
            errors.add(TextColors.ANSI_RED + "\nPlease select a valid option!\n" + TextColors.ANSI_RESET);
            StartGame(gameStateController, in, errors);
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        ArrayList<String> errors = new ArrayList<String>();

        if ((args.length > 0) && (args[0].equals("console") || args[0].equals("gui"))) {
            java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
            GeneralHelpers.ClearScreen();

            if (args[0].equals("console")) {
                InitialiseDB();

                Scanner in = new Scanner(System.in);
                GameState gameState = new GameState();
                GameStateView gameStateView = new GameStateView();
                GameStateController gameStateController = new GameStateController(gameState, gameStateView);

                ControllerGroup controllerGroup = StartGame(gameStateController, in, errors);

                controllerGroup.heroController.ShowHero();

            } else {
                System.out.println(TextColors.ANSI_BLUE + "GUI mode is still under construction. Check back soon." + TextColors.ANSI_RESET);
            }

        } else {
            System.out.println(TextColors.ANSI_RED + "Please run the game with a valid mode specified: console or gui." + TextColors.ANSI_RESET);
        }
    }
}
