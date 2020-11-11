package views.console.gamestate;

import helpers.TextColors;
import models.character.heros.Hero;
import models.character.heros.HeroClass;
import models.gamestate.GameState;
import models.map.Map;

import java.util.ArrayList;

public class GameStateView {

    public void PrintStartOptions(){
        System.out.println(TextColors.ANSI_PURPLE + "\nWELCOME TO MY LITTLE RPG!\n" + TextColors.ANSI_RESET + "Enter an Option below:\n");
        System.out.println(TextColors.ANSI_BLUE + "[N]" + TextColors.ANSI_RESET + " - Start [N]ew Game");
        System.out.println(TextColors.ANSI_BLUE + "[L]" + TextColors.ANSI_RESET + " - [L]oad Saved Game");
        System.out.println(TextColors.ANSI_RED + "[Q]" + TextColors.ANSI_RESET + " - [Q]uit\n");
    }

    public void PrintNameRequest(){
        System.out.println(TextColors.ANSI_PURPLE + "\nYour adventure begins here...\n" + TextColors.ANSI_RESET + "Enter a name for your hero:\n");
    }

    public void PrintStartInstructions() {
        System.out.println("\nGet to one of the edges of the map to win the round.\nDefeating enemies will cause your XP to increase.\nLevelling up will increase the size of your map.");
        System.out.println(TextColors.ANSI_RED + "BE WARNED: If you encounter foe, you may try to run, but you will not always succeed.\nDying will force you to start again.");
    }

    public void PrintSavedGames(ArrayList<GameState> savedGames){
        System.out.println("\nSelect a Saved Game:\n");
        for (int i = 0; i < savedGames.size(); i++){
            GameState gameState = savedGames.get(i);
            System.out.println(TextColors.ANSI_GREEN + "[" + (i) + "]" + " " + gameState.get_heroName() + " - Level: " + gameState.get_heroLevel() + " - XP: " + gameState.get_heroXP() + TextColors.ANSI_RESET);
        }
    }

    public void PrintGameOptions(){
        System.out.println("\nEnter an Option below:\n");
        System.out.println(TextColors.ANSI_BLUE + "[W]" + TextColors.ANSI_RESET + " - UP");
        System.out.println(TextColors.ANSI_BLUE + "[S]" + TextColors.ANSI_RESET + " - DOWN");
        System.out.println(TextColors.ANSI_BLUE + "[A]" + TextColors.ANSI_RESET + " - LEFT");
        System.out.println(TextColors.ANSI_BLUE + "[D]" + TextColors.ANSI_RESET + " - RIGHT");
        System.out.println(TextColors.ANSI_PURPLE + "[P]" + TextColors.ANSI_RESET + " - [P]layer stats | " + TextColors.ANSI_GREEN + "[X]" + TextColors.ANSI_RESET + " - Save | " + TextColors.ANSI_RED + "[Q]" + TextColors.ANSI_RESET + " - [Q]uit\n");
    }

    public void PrintHeroClasses(){
        HeroClass arr[] = HeroClass.values();

        System.out.println("\nSelect a Hero Class:\n");
        for (HeroClass heroClass : arr)
        {
            System.out.println("[" + heroClass.ordinal() + "] " + heroClass);
        }
        System.out.println();
    }
}
