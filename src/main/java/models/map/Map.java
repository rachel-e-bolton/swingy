package models.map;

import models.character.heros.Hero;

import javax.validation.constraints.Min;
import java.io.Serializable;

public class Map implements Serializable {
    @Min(value = 0)
    protected int _id;

    @Min(value = 5)
    private int _dimension;


    public Map(int startId) {
        this._id = startId;
    }

    public void set_dimension(int _dimension) {
        this._dimension = _dimension;
    }

    public int get_id() {
        return _id;
    }

    public int get_dimension() {
        return _dimension;
    }
}
