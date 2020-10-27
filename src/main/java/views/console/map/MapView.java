package views.console.map;

import models.map.Map;

public class MapView {
    public void PrintMapDetails(Map currentMap){
        System.out.println("MAP SIZE: " + currentMap.get_dimension() + "x" + currentMap.get_dimension());
    }
}
