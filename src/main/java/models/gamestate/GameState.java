package models.gamestate;

import models.map.Map;

import java.io.Serializable;

public class GameState {
    private int _mapId;
    private int _heroId;
    private String _heroName;

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
}
