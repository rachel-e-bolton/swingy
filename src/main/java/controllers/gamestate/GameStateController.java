package controllers.gamestate;

import controllers.database.Database;
import controllers.map.MapController;
import helpers.GeneralHelpers;
import models.character.heros.Hero;
import models.gamestate.GameState;
import models.map.Map;
import views.console.gamestate.GameStateView;

import java.sql.SQLException;
import java.util.ArrayList;

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
}
