package models.map;

import database.Database;
import helpers.TextColors;
import models.character.heros.Hero;

import javax.validation.constraints.Min;
import java.io.Serializable;

public class Map implements Serializable {
    @Min(value = 0)
    protected int _id;

    @Min(value = 5)
    private int _dimension;

    private static int _idCounter;

    static {
        try {
            _idCounter = Database.GetMaxMapId();
        } catch (ClassNotFoundException e) {
            System.out.println(TextColors.ANSI_RED + e.getMessage() + TextColors.ANSI_RESET);
        }
    }

    public Map(Hero hero) {
        this._id = nextId();
        this._dimension = (hero.get_level() - 1) * 5 + 10 - (hero.get_level() % 2);
    }

    private int nextId() {
        return (++_idCounter);
    }

    public int get_id() {
        return _id;
    }

    public int get_dimension() {
        return _dimension;
    }
}
