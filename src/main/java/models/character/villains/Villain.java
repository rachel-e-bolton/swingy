package models.character.villains;

import models.character.AbstractCharacter;
import models.character.heros.Hero;

import java.util.Random;

public class Villain extends AbstractCharacter {

    private VillainClass _class;

    public Villain(String name, VillainClass villainClass, Hero hero) {
        super(name, "VILLAIN", -1);
        this._class = villainClass;

        Random random = new Random();

        int xp = Math.abs(random.ints((hero.get_xp() - 50), (hero.get_xp() + 50)).findFirst().getAsInt());
        int attack = random.ints((hero.get_attack() - 5), (hero.get_attack() + 5)).findFirst().getAsInt();
        int defense = random.ints((hero.get_defense() - 5), (hero.get_defense() + 5)).findFirst().getAsInt();
        int hp = random.ints((hero.get_hp() - 5), (hero.get_hp() + 5)).findFirst().getAsInt();

        super.set_xp(xp);
        super.checkLevelUp();
        super.set_colPosition(hero.get_colPosition());
        super.set_rowPosition(hero.get_rowPosition());

        switch (villainClass) {
            case NaughtyBoy:
                super.set_attack(attack);
                super.set_defense(defense);
                super.set_hp(hp - 5);
            case Executioner:
                super.set_attack(attack);
                super.set_defense(defense - 5);
                super.set_hp(hp);
            case PsychoticPatient:
                super.set_attack(attack - 5);
                super.set_defense(defense);
                super.set_hp(hp);
        }
    }

    public VillainClass get_class() {
        return _class;
    }
}
