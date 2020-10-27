package models.character;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;

public abstract class AbstractCharacter implements Serializable {

    @Min(value = 0)
    private int _level = 0;

    @Min(value = 0)
    private int _xp = 0;

    @Min(value = 0)
    private int _attack;

    @Min(value = 0)
    private int _defense;

    @Min(value = 0)
    private int _hp;

    @Min(value = 1)
    private int _rowPosition = 3;

    @Min(value = 1)
    private int _colPosition = 3;

    @Min(value=0)
    protected int _id;

    @Size(min=3,max=25)
    protected String _name;

    protected AbstractCharacter(String name, String type, int id) {
        this._name = name;
        if (type == "HERO") {
            this._id = id;
        }
    }

    public int get_id() {
        return _id;
    }

    public int get_level() {
        return _level;
    }

    public int get_xp() {
        return _xp;
    }

    public void set_xp(int _xp) {
        this._xp = _xp;
    }

    public int get_rowPosition() {
        return _rowPosition;
    }

    public void set_rowPosition(int _rowPosition) {
        this._rowPosition = _rowPosition;
    }

    public int get_colPosition() {
        return _colPosition;
    }

    public void set_colPosition(int _colPosition) {
        this._colPosition = _colPosition;
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

    public void checkLevelUp() {
        int maxXp = (int) (this._level * 1000 + Math.pow((this._level - 1), 2) * 450);
        if (this._xp >= maxXp) {
            this._level++;
        }
    }

    public String get_name() {
        return _name;
    }
}
