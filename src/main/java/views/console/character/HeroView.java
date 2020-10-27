package views.console.character;

import helpers.TextColors;
import models.character.heros.Hero;

public class HeroView {
    public void PrintPosition(Hero hero){
        System.out.println("You are at position: [" + TextColors.ANSI_CYAN + hero.get_rowPosition() + TextColors.ANSI_RESET + "," + TextColors.ANSI_CYAN + hero.get_colPosition() + TextColors.ANSI_RESET + "]\n");
    }

    public void PrintHeroStats(Hero hero){
        System.out.println("Hello, " + TextColors.ANSI_PURPLE + hero.get_name() + TextColors.ANSI_RESET + "!");
        System.out.println("Your stats are as follows: \n");
        System.out.println("Level: " + hero.get_level());
        System.out.println("XP: " + hero.get_xp());
        System.out.println("Attack: " + hero.get_attack());
        System.out.println("Defense: " + hero.get_defense());
        System.out.println("HP: " + "\n" + hero.get_hp());
    }
}