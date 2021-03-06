package views.console.character;

import helpers.TextColors;
import models.character.villains.Villain;

public class VillainView {
    public void PrintVillainStats(Villain villain){
        System.out.println(TextColors.ANSI_RED + "\nA Villain has appeared!\n" + TextColors.ANSI_RESET);
        System.out.println(villain.get_name() + " - " + villain.get_class());
        System.out.println("Level: " + villain.get_level());
        System.out.println("XP: " + villain.get_xp());
        System.out.println("Attack: " + villain.get_attack());
        System.out.println("Defense: " + villain.get_defense());
        System.out.println("HP: " + villain.get_hp() + "\n");
    }

    public void PrintFightOptions(){
        System.out.println(TextColors.ANSI_RED + "[F]" + TextColors.ANSI_RESET + " - [F]ight" + " | " + TextColors.ANSI_BLUE + "[R]" + TextColors.ANSI_RESET + " - [R]un\n");
    }
}