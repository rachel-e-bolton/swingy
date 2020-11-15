package gameplay;

import controllers.ControllerGroup;
import controllers.character.VillainController;
import helpers.Directions;
import helpers.GeneralHelpers;
import helpers.TextColors;
import models.character.villains.Villain;
import models.character.villains.VillainClass;
import models.character.villains.VillainNames;
import views.console.character.VillainView;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public final class Actions {
    public static void SelectMove(ControllerGroup controllerGroup, Scanner in, ArrayList<String> errors) {
        GeneralHelpers.ClearScreen();
        errors.forEach(System.out::println);
        errors.clear();
        controllerGroup.heroController.ShowPosition();
        controllerGroup.mapController.ShowMap();
        controllerGroup.gameStateController.ShowOptions();
        String selectMove = in.nextLine();

        try {
            if (((controllerGroup.heroController.GetX() == 1) || (controllerGroup.heroController.GetY() == 1) || (controllerGroup.heroController.GetX() == controllerGroup.mapController.GetDimensions()) || (controllerGroup.heroController.GetY() == controllerGroup.mapController.GetDimensions()))) {
                WinMap(controllerGroup, in, errors);
            } else {
                if (selectMove.matches("w|W|a|A|s|S|d|D")) {
                    int fightChance = ThreadLocalRandom.current().nextInt(1,4);

                    if (fightChance == 1) {
                        Villain villain = new Villain(VillainNames.getRandomVillainName(), VillainClass.getVillainRandomClass(), controllerGroup.heroController.GetHero());

                        if (controllerGroup.getVillainController() == null) {
                            VillainView villainView = new VillainView();
                            VillainController villainController = new VillainController (villain, villainView);
                            controllerGroup.setVillainController(villainController);
                        } else {
                            controllerGroup.villainController.NewVillain(villain);
                        }
                        FightOrFlight(controllerGroup, in, errors);
                    }

                    if (selectMove.matches("w|W")) {
                        controllerGroup.heroController.Move(Directions.Up);
                    } else if (selectMove.matches("d|D")) {
                        controllerGroup.heroController.Move(Directions.Right);
                    } else if (selectMove.matches("s|S")) {
                        controllerGroup.heroController.Move(Directions.Down);
                    } else if (selectMove.matches("a|A")) {
                        controllerGroup.heroController.Move(Directions.Left);
                    }

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
                }

                SelectMove(controllerGroup, in, errors);
            }
        } catch (Exception e) {
            GeneralHelpers.ClearScreen();
            errors.add(TextColors.ANSI_RED + "\nPlease select a valid option!\n" + TextColors.ANSI_RESET);
            SelectMove(controllerGroup, in, errors);
        }
    }

    public static void WinMap(ControllerGroup controllerGroup, Scanner in, ArrayList<String> errors) throws ClassNotFoundException {
        GeneralHelpers.ClearScreen();
        controllerGroup.heroController.ShowMapWin();

        int level = controllerGroup.heroController.GetLevel();

        controllerGroup.heroController.SetXP((level-1)*5+9-(level%3));
        controllerGroup.heroController.LevelCheck();

        controllerGroup.mapController.UpdateMapDimensions(((level-1)*5+10-(level%2)));
        controllerGroup.heroController.ResetPosition(controllerGroup.mapController.GetDimensions());
        controllerGroup.heroController.UpdateHero();
        try
        {
            Thread.sleep(2500);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        SelectMove(controllerGroup, in, errors);
    }

    public static void FightOrFlight(ControllerGroup controllerGroup, Scanner in, ArrayList<String> errors) {
        GeneralHelpers.ClearScreen();
        controllerGroup.villainController.VillainEncounter();
        errors.forEach(System.out::println);
        errors.clear();
        String fightOrFlight = in.nextLine();

        try {
            if (fightOrFlight.matches("f|F")) {
                System.out.println("You've chosen to Fight!"); // Must go in gamestate view
            } else if (fightOrFlight.matches("r|R")) {
                System.out.println("Successfully Ran."); // Must go in gamestate view
            } else {
                GeneralHelpers.ClearScreen();
                errors.add(TextColors.ANSI_RED + "\nPlease select a valid option!\n" + TextColors.ANSI_RESET);
                FightOrFlight(controllerGroup, in, errors);
            }
        } catch (NumberFormatException exception) {
            GeneralHelpers.ClearScreen();
            errors.add(TextColors.ANSI_RED + "\nPlease select a valid option!\n" + TextColors.ANSI_RESET);
            FightOrFlight(controllerGroup, in, errors);
        }
    }

    public static void WinArtefact(ControllerGroup controllerGroup, Scanner in, ArrayList<String> errors) {

    }
}
