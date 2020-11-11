package controllers.character;

import controllers.database.Database;
import models.character.heros.Hero;
import org.omg.DynamicAny.DynArray;
import views.console.character.HeroView;

import java.sql.SQLException;

public class HeroController {
    private Hero _hero;
    private HeroView _heroView;

    public HeroController (Hero hero, HeroView heroView) {
        this._hero = hero;
        this._heroView = heroView;
    }

    public void SaveHero() throws SQLException, ClassNotFoundException {
        Database.CreateHero(_hero);
    }

    public int GetLevel() {
        return _hero.get_level();
    }

    public void ShowHero() {
        this._heroView.PrintHeroStats(this._hero);
    }

    public void ShowPosition() {
        this._heroView.PrintPosition(this._hero);
    }

    public Hero GetHero() {
        return this._hero;
    }
}
