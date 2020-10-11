package models.character;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;

public abstract class AbstractCharacter implements Serializable{

    private int _level = 0;
    private int _xp = 0;

    private int _rowPosition = 3;
    private int _colPosition = 3;

    @Min(value=0)
    protected long _id;

    @Size(min=3,max=25)
    protected String _name;

    private static long _idCounter = -1;

    protected AbstractCharacter(String name, String type) {
        this._name = name;
        if (type == "HERO") {
            this._id = nextId();
        }
    }

    private long nextId() {
        return (++_idCounter);
    }

    public int get_level() {
        return _level;
    }

    public void set_level(int _level) {
        this._level = _level;
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
}
