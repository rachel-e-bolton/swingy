package models.gamestate;

import models.map.Map;

import java.io.Serializable;

public class GameState {
    private int _mapId;
    private int _heroId;
    private String _heroName;
    private int _heroLevel;
    private int _heroXP;

    public int get_mapId() {
        return _mapId;
    }

    public void set_mapId(int _mapId) {
        this._mapId = _mapId;
    }

    public int get_heroId() {
        return _heroId;
    }

    public void set_heroId(int _heroId) {
        this._heroId = _heroId;
    }

    public String get_heroName() {
        return _heroName;
    }

    public void set_heroName(String _heroName) {
        this._heroName = _heroName;
    }

    public int get_heroLevel() {
        return _heroLevel;
    }

    public void set_heroLevel(int _heroLevel) {
        this._heroLevel = _heroLevel;
    }

    public int get_heroXP() {
        return _heroXP;
    }

    public void set_heroXP(int _heroXP) {
        this._heroXP = _heroXP;
    }
}
