package models.character.heros;

import models.character.AbstractCharacter;

import java.io.Serializable;

public class KhutulunTheWrestlerPrincess extends AbstractCharacter implements Serializable {

    private int _attack = 25;
    private int _defense = 15;
    private int _hp = 10;

    public KhutulunTheWrestlerPrincess(String name) {
        super(name, "HERO");
    }

    public int get_attack() {
        return _attack;
    }

    public void set_attack(int _attack) {
        this._attack = _attack;
    }

    public int get_defense() {
        return _defense;
    }

    public void set_defense(int _defense) {
        this._defense = _defense;
    }

    public int get_hp() {
        return _hp;
    }

    public void set_hp(int _hp) {
        this._hp = _hp;
    }
}
