package controllers.gamestate;

import controllers.database.Database;
import helpers.GeneralHelpers;
import models.artefact.Artefact;
import models.character.heros.Hero;
import models.character.villains.Villain;
import models.gamestate.GameState;
import models.map.Map;
import views.console.gamestate.GameStateView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class GameStateController {
    private GameState _gameState;
    private GameStateView _gameStateView;
    private ArrayList<GameState> _savedGames;

    public GameStateController(GameState gameState, GameStateView gameStateView) throws ClassNotFoundException {
        this._gameState = gameState;
        this._gameStateView = gameStateView;
        this._savedGames = Database.GetSavedGames();
    }

    public void ShowSavedGames() throws ClassNotFoundException {
        GeneralHelpers.ClearScreen();
        if (_savedGames.size() > 0){
            _gameStateView.PrintSavedGames(_savedGames);
        } else {
            System.out.println("No Saved Games Found.\nExiting...\n");
            try
            {
                Thread.sleep(2200);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
            System.exit(0);
        }
    }

    public void LoadSavedGame(int index){
        GameState loadGame = _savedGames.get(index);

        _gameState.set_mapId(loadGame.get_mapId());
        _gameState.set_heroId(loadGame.get_heroId());
        _gameState.set_heroName(loadGame.get_heroName());
        _gameState.set_heroLevel(loadGame.get_heroLevel());
        _gameState.set_heroXP(loadGame.get_heroXP());
    }

    public void CreateGameState(Map map, Hero hero) throws ClassNotFoundException, SQLException {
        _gameState.set_mapId(map.get_id());
        _gameState.set_heroId(hero.get_id());
        _gameState.set_heroName(hero.get_name());
        _gameState.set_heroLevel(hero.get_level());
        _gameState.set_heroXP(hero.get_xp());

        Database.CreateGameState(_gameState);
    }

    public void UpdateGameState(Hero hero) throws ClassNotFoundException {
        _gameState.set_heroLevel(hero.get_level());
        _gameState.set_heroXP(hero.get_xp());

        Database.UpdateGameState(_gameState);
    }

    public void StartGame(){
        _gameStateView.PrintStartOptions();
    }

    public void ShowOptions(){
        _gameStateView.PrintGameOptions();
    }

    public void RequestName(){
        _gameStateView.PrintNameRequest();
    }

    public void RequestClass(){
        _gameStateView.PrintHeroClasses();
    }

    public ArrayList<GameState> GetList() {
        return _savedGames;
    }

    public Map LoadSavedMap() throws ClassNotFoundException {
        return Database.GetMap(this._gameState.get_mapId());
    }

    public Hero LoadSavedHero() throws SQLException, ClassNotFoundException {
        return Database.GetHero(this._gameState.get_heroId());
    }

    public boolean Fight(Hero hero, Villain villain, String message, boolean upperHand) {
        GeneralHelpers.ClearScreen();
        _gameStateView.PrintFightStart(message);
        boolean heroTurn = upperHand;

        String heroName = hero.get_name();
        int heroLevel = hero.get_level();
        int heroXP = hero.get_xp();
        int heroAttack = hero.get_attack();
        int heroDefense = hero.get_defense();
        int heroHP = hero.get_hp();

        String villainName = villain.get_name();
        int villainLevel = villain.get_level();
        int villainXP = villain.get_xp();
        int villainAttack = villain.get_attack();
        int villainDefense = villain.get_defense();
        int villainHP = villain.get_hp();

        while(villainHP > 0 && heroHP > 0) {
            if (heroTurn) {
                int villainDamage = (((((((2*heroLevel)/5) + 2)*heroXP*(heroAttack/heroDefense))/50)+2)*(heroHP/6))/2;
                villainDamage = (villainDamage > 0) ? villainDamage : 1;
                _gameStateView.PrintAttack(heroName, villainName, villainDamage);
                villainHP -= Math.abs(villainDamage);
                System.out.println(villainName + " HP: " + villainHP);
                heroTurn = false;
            } else {
                int heroDamage = (((((((2*villainLevel)/5) + 2)*villainXP*(villainAttack/villainDefense))/50)+2)*(villainHP/6))/2;
                heroDamage = (heroDamage > 0) ? heroDamage : 1;
                _gameStateView.PrintAttack(villainName, heroName, heroDamage);
                heroHP -= Math.abs(heroDamage);
                System.out.println(heroName + " HP: " + heroHP);
                heroTurn = true;
            }
        }

        try
        {
            Thread.sleep((heroLevel == 0) ? 1000 : (1000/heroLevel));
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }

        if (villainHP <= 0 && heroHP > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void Lose() {
        _gameStateView.PrintLose();
        try
        {
            Thread.sleep(1500);
            System.exit(0);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    public int GetArtefactValue(Artefact artefact, Villain villain) {
        Random random = new Random();
        int artefactValue = 0;

        switch (artefact) {
            case Helm:
                artefactValue = random.ints((villain.get_hp() - 2), (villain.get_hp() + 2)).findFirst().getAsInt();
                break;
            case Armor:
                artefactValue = random.ints((villain.get_defense() - 2), (villain.get_defense() + 2)).findFirst().getAsInt();
                break;
            case Weapon:
                artefactValue = random.ints((villain.get_attack() - 2), (villain.get_attack() + 2)).findFirst().getAsInt();
                break;
        }
        return artefactValue;
    }

    public void ShowArtefactValue(Artefact artefact, int artefactValue) {
        _gameStateView.PrintArtefact(artefact, artefactValue);
    }
}
