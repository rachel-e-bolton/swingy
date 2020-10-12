package models.map;

import models.character.heros.Hero;

import javax.validation.constraints.Min;
import java.io.Serializable;

public class Map implements Serializable {
    @Min(value = 0)
    protected int _id;

    @Min(value = 5)
    private int _dimension;


    public Map(Hero hero, int startId) {
        this._id = startId;
        this._dimension = (hero.get_level() - 1) * 5 + 10 - (hero.get_level() % 2);
    }

    public int get_id() {
        return _id;
    }

    public int get_dimension() {
        return _dimension;
    }
}
