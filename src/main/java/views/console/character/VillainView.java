package views.console.character;

import helpers.TextColors;
import models.character.villains.Villain;

public class VillainView {
    public void PrintVillainStats(Villain villain){
        System.out.println(TextColors.ANSI_RED + "A Villain has appeared!" + TextColors.ANSI_RESET);
        System.out.println(villain.get_name());
        System.out.println("Level: " + villain.get_level());
        System.out.println("XP: " + villain.get_xp());
        System.out.println("Attack: " + villain.get_attack());
        System.out.println("Defense: " + villain.get_defense());
        System.out.println("HP: " + "\n" + villain.get_hp());
    }
}