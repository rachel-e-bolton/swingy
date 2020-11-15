package controllers.character;

import controllers.database.Database;
import helpers.Directions;
import models.character.heros.Hero;
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

    public void UpdateHero() throws ClassNotFoundException {
        Database.UpdateHero(_hero);
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

    public void Move(Directions direction){
        switch (direction) {
            case Up:
                this._hero.set_colPosition((this._hero.get_colPosition()-1));
                break;
            case Down:
                this._hero.set_colPosition((this._hero.get_colPosition()+1));
                break;
            case Left:
                this._hero.set_rowPosition((this._hero.get_rowPosition()-1));
                break;
            case Right:
                this._hero.set_rowPosition((this._hero.get_rowPosition()+1));
                break;
        }
    }

    public int GetX() {
        return this._hero.get_rowPosition();
    }

    public int GetY() {
        return this._hero.get_colPosition();
    }

    public void SetX(int rowPos) {
        this._hero.set_rowPosition(rowPos);
    }

    public void SetY(int colPos) {
        this._hero.set_colPosition(colPos);
    }

    public void SetXP(int XP) {
        this._hero.set_xp((this._hero.get_xp()+XP));
    }

    public void ShowMapWin() {
        this._heroView.PrintWinMap(this._hero);
    }

    public void LevelCheck() {
        this._hero.checkLevelUp();
    }

    public void ResetPosition(int mapDimensions) {
        this._hero.set_rowPosition((mapDimensions/2 + 1));
        this._hero.set_colPosition((mapDimensions/2 + 1));
    }

    public String GetHeroName() {
        return _hero.get_name();
    }

    public void SetHP(int hp) {
        this._hero.set_hp(this._hero.get_hp()+hp);
    }

    public void SetAttack(int attack) {
        this._hero.set_attack(this._hero.get_attack()+attack);
    }

    public void SetDefense(int defense) {
        this._hero.set_defense(this._hero.get_defense()+defense);
    }
}
