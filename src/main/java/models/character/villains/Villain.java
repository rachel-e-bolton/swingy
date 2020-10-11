package models.character.villains;

import models.character.AbstractCharacter;

public class Villain extends AbstractCharacter {

    private int _attack;
    private int _defense;
    private int _hp;

    public Villain(String name) {
        super(name, "VILLAIN");
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
