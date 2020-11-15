package controllers;

import controllers.character.HeroController;
import controllers.character.VillainController;
import controllers.gamestate.GameStateController;
import controllers.map.MapController;

public class ControllerGroup {
    public MapController mapController;
    public HeroController heroController;
    public GameStateController gameStateController;
    public VillainController villainController = null;

    public ControllerGroup(MapController mapController, HeroController heroController, GameStateController gameStateController) {
        this.mapController = mapController;
        this.gameStateController = gameStateController;
        this.heroController = heroController;
    }

    public VillainController getVillainController() {
        return villainController;
    }

    public void setVillainController(VillainController villainController) {
        this.villainController = villainController;
    }
}
