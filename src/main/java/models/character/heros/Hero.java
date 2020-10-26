package models.character.heros;

import controllers.database.Database;
import models.character.AbstractCharacter;

import java.io.Serializable;
import java.sql.SQLException;

public class Hero extends AbstractCharacter implements Serializable {

    private HeroClass _class;

    public Hero(String name, HeroClass heroClass) throws ClassNotFoundException, SQLException {
        super(name, "HERO", (Database.GetMaxHeroId() + 1));
        this._class = heroClass;

        switch (heroClass) {
            case Nurse:
                super.set_attack(25);
                super.set_defense(15);
                super.set_hp(10);
            case Judge:
                super.set_attack(10);
                super.set_defense(25);
                super.set_hp(15);
            case Teacher:
                super.set_attack(15);
                super.set_defense(10);
                super.set_hp(25);
        }
    }
}