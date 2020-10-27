package views.console.gamestate;

import helpers.TextColors;
import models.character.heros.Hero;
import models.gamestate.GameState;
import models.map.Map;

import java.util.ArrayList;

public class GameStateView {

    public void PrintStartOptions(){
        System.out.println("WELCOME TO MY LITTLE RPG!\nSELECT AN OPTION BELOW:\n");
        System.out.println(TextColors.ANSI_BLUE + "[N]" + TextColors.ANSI_RESET + " - Start [N]ew Game");
        System.out.println(TextColors.ANSI_BLUE + "[L]" + TextColors.ANSI_RESET + " - [L]oad Saved Game");
    }

    public void PrintStartInstructions(Map currentMap, Hero hero) {
        System.out.println("Get to one of the edges to win the round. Defeating enemies will cause your XP to increase. Levelling up will increase the size of your map.");
        System.out.println(TextColors.ANSI_RED + "BE WARNED: You can try to run, but you will not always succeed.\n Dying will force you to start again.");
    }



    public void PrintSavedGames(ArrayList<GameState> savedGames){
        for (int i = 0; i < savedGames.size(); i++){
            GameState gameState = savedGames.get(i);
            System.out.println(TextColors.ANSI_GREEN + "[" + (i) + "]" + " " + gameState.get_heroName() + " - Level: " + gameState.get_heroLevel() + " - XP: " + gameState.get_heroXP() + TextColors.ANSI_RESET);
        }
    }

    public void PrintGameOptions(){
        System.out.println("\nSELECT AN OPTION BELOW:\n");
        System.out.println(TextColors.ANSI_BLUE + "[W]" + TextColors.ANSI_RESET + " - UP");
        System.out.println(TextColors.ANSI_BLUE + "[S]" + TextColors.ANSI_RESET + " - DOWN");
        System.out.println(TextColors.ANSI_BLUE + "[A]" + TextColors.ANSI_RESET + " - LEFT");
        System.out.println(TextColors.ANSI_BLUE + "[D]" + TextColors.ANSI_RESET + " - RIGHT");
        System.out.println(TextColors.ANSI_PURPLE + "[P]" + TextColors.ANSI_RESET + " - [P]layer stats");
        System.out.println(TextColors.ANSI_GREEN + "[S]" + TextColors.ANSI_RESET + " - [S]ave");
        System.out.println(TextColors.ANSI_RED + "[Q]" + TextColors.ANSI_RESET + " - [Q]uit");
    }

    public void PrintFightOptions(){
        System.out.println(TextColors.ANSI_BLUE + "[F]" + TextColors.ANSI_RESET + " - [F]ight");
        System.out.println(TextColors.ANSI_BLUE + "[R]" + TextColors.ANSI_RESET + " - [R]un");
    }
}
