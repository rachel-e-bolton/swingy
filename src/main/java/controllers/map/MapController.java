package controllers.map;

import controllers.database.Database;
import models.map.Map;
import views.console.map.MapView;

public class MapController {
    private Map _map;
    private MapView _mapView;
    private boolean _initialised = false;

    public MapController(Map map, MapView mapView){
        this._map = map;
        this._mapView = mapView;
    }

    public void SetMapDimensions(int dimension) throws ClassNotFoundException {
        _map.set_dimension(dimension);
        if (_initialised) {
            this.UpdateMap();
        } else {
            this.SaveMap();
            this._initialised = true;
        }
    }

    public void UpdateMapDimensions(int dimension) throws ClassNotFoundException {
        this._map.set_dimension(dimension);
        Database.UpdateMap(this._map);
    }

    public void SaveMap() throws ClassNotFoundException {
        Database.CreateMap(_map);
    }

    public void UpdateMap() throws ClassNotFoundException {
        Database.UpdateMap(_map);
    }

    public void ShowMap() {
        _mapView.PrintMapDetails(_map);
    }

    public Map GetMap() {
        return this._map;
    }

    public int GetDimensions() {
        return this._map.get_dimension();
    }
}
