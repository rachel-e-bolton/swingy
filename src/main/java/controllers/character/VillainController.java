package controllers.character;

import models.character.villains.Villain;
import views.console.character.VillainView;

public class VillainController {
    private Villain _villain;
    private VillainView _villainView;

    public VillainController(Villain villain, VillainView villainView) {
        this._villain = villain;
        this._villainView = villainView;
    }

    public void VillainEncounter() {
        this._villainView.PrintVillainStats(this._villain);
        this._villainView.PrintFightOptions();
    }

    public void NewVillain(Villain villain) {
        this._villain = villain;
    }

    public Villain GetVillain() {
        return this._villain;
    }

    public int GetXP() {
        return this._villain.get_xp();
    }
}
