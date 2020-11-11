package gameplay;

import controllers.ControllerGroup;
import helpers.Directions;
import helpers.GeneralHelpers;
import helpers.TextColors;

import java.util.ArrayList;
import java.util.Scanner;

public class Actions {
    public static void SelectMove(ControllerGroup controllerGroup, Scanner in, ArrayList<String> errors) {
        GeneralHelpers.ClearScreen();
        errors.forEach(System.out::println);
        errors.clear();
        controllerGroup.heroController.ShowPosition();
        controllerGroup.mapController.ShowMap();
        controllerGroup.gameStateController.ShowOptions();
        String selectMove = in.nextLine();

        try {
            if (selectMove.matches("w|W")) {
                controllerGroup.heroController.Move(Directions.Up);
            } else if (selectMove.matches("d|D")) {
                controllerGroup.heroController.Move(Directions.Right);
            } else if (selectMove.matches("s|S")) {
                controllerGroup.heroController.Move(Directions.Down);
            } else if (selectMove.matches("a|A")) {
                controllerGroup.heroController.Move(Directions.Left);
            } else if (selectMove.matches("x|X")) {
                //Write save
            } else if (selectMove.matches("p|P")) {
                controllerGroup.heroController.ShowHero();
                System.out.println(TextColors.ANSI_BLUE + "[B]" + TextColors.ANSI_RESET + "ack | " + TextColors.ANSI_RED + "[Q]" + TextColors.ANSI_RESET + "uit\n");
                String option = in.nextLine();

                while (!(option.matches("b|B|q|Q"))) {
                    System.out.println(TextColors.ANSI_RED + "\nPlease select a valid option!\n" + TextColors.ANSI_RESET);
                    System.out.println(TextColors.ANSI_BLUE + "[B]" + TextColors.ANSI_RESET + "ack | " + TextColors.ANSI_RED + "[Q]" + TextColors.ANSI_RESET + "uit\n");
                    option = in.nextLine();
                }

                if (option.matches("b|B")) {
                    SelectMove(controllerGroup, in, errors);
                } else if (option.matches("q|Q")) {
                    System.exit(0);
                }
            } else if (selectMove.matches("q|Q")) {
                System.exit(0);
            } else {
                GeneralHelpers.ClearScreen();
                errors.add(TextColors.ANSI_RED + "\nPlease select a valid option!\n" + TextColors.ANSI_RESET);
                SelectMove(controllerGroup, in, errors);
            }
        } catch (Exception e) {
            GeneralHelpers.ClearScreen();
            System.out.println(e);
            errors.add(TextColors.ANSI_RED + "\nPlease select a valid option!\n" + TextColors.ANSI_RESET);
            SelectMove(controllerGroup, in, errors);
        }
    }
}
