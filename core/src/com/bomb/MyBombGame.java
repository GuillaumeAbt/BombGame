package com.bomb;

import com.badlogic.gdx.Game;
import com.bomb.screens.EditScreen;
import com.bomb.screens.GameOverScreen;
import com.bomb.screens.MainMenu;
import com.bomb.screens.PlayScreen;
import com.bomb.screens.SettingsScreen;
import com.bomb.screens.TrophyScreen;

public class MyBombGame extends Game {
    public MainMenu mainmenu;
    public PlayScreen playscreen;
    public EditScreen editscreen;
    public TrophyScreen trophyscreen;
    public SettingsScreen settingsscreen;
    public GameOverScreen gameoverscreen;

    public void create() {

        mainmenu = new MainMenu(this);
        playscreen = new PlayScreen(this);
        editscreen = new EditScreen(this);
        trophyscreen = new TrophyScreen(this);
        settingsscreen = new SettingsScreen(this);
        gameoverscreen = new GameOverScreen(this);
        mainmenu.create();
        setScreen(mainmenu);
    }

}
