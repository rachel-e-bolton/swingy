package swingy;

import controllers.ControllerGroup;
import controllers.gamestate.GameStateController;
import gameplay.Actions;
import gameplay.Setup;
import helpers.GeneralHelpers;
import helpers.TextColors;
import models.gamestate.GameState;
import views.console.gamestate.GameStateView;
import java.util.*;
import java.util.logging.Level;

public class Swingy {
    public static void main(String[] args) throws Exception {
        ArrayList<String> errors = new ArrayList<String>();

        if ((args.length > 0) && (args[0].equals("console") || args[0].equals("gui"))) {
            java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
            GeneralHelpers.ClearScreen();

            Scanner in = new Scanner(System.in);
            GameState gameState = new GameState();
            GameStateView gameStateView = new GameStateView();
            GameStateController gameStateController = new GameStateController(gameState, gameStateView);

            if (args[0].equals("console")) {
                Setup.InitialiseDB();
                ControllerGroup controllerGroup = Setup.StartGame(gameStateController, in, errors);
                while (controllerGroup.heroController.GetX() > 1 || controllerGroup.heroController.GetY() > 1 || controllerGroup.heroController.GetX() < controllerGroup.mapController.GetDimensions() || controllerGroup.heroController.GetY() < controllerGroup.mapController.GetDimensions()) {
                    Actions.SelectMove(controllerGroup, in, errors);
                }
            } else {
                System.out.println(TextColors.ANSI_BLUE + "GUI mode is still under construction. Check back soon." + TextColors.ANSI_RESET);
            }

        } else {
            System.out.println(TextColors.ANSI_RED + "Please run the game with a valid mode specified: console or gui." + TextColors.ANSI_RESET);
        }
    }
}
