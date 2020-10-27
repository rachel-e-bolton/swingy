package controllers.gamestate;

import controllers.database.Database;
import controllers.map.MapController;
import models.gamestate.GameState;
import models.map.Map;
import views.console.gamestate.GameStateView;

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

    public void showSavedGames() throws ClassNotFoundException {
        if (_savedGames.size() > 0){
            _gameStateView.PrintSavedGames(_savedGames);
        } else {
            System.out.println("No Saved Games Found.");
            _gameStateView.PrintStartOptions();
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

    public void CreateGame() throws ClassNotFoundException {

    }

    public void SaveGame() throws ClassNotFoundException {
        Database.UpdateGameState(_gameState);
    }
}
