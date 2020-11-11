package controllers;

import controllers.character.HeroController;
import controllers.gamestate.GameStateController;
import controllers.map.MapController;

public class ControllerGroup {
    public MapController mapController;
    public HeroController heroController;
    public GameStateController gameStateController;

    public ControllerGroup(MapController mapController, HeroController heroController, GameStateController gameStateController) {
        this.mapController = mapController;
        this.gameStateController = gameStateController;
        this.heroController = heroController;
    }
}
