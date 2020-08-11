package com.bomb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.bomb.MyBombGame;

/**
 * Created by Guillaume on 04/12/2017.
 */

public class GameOverScreen implements Screen {

    SpriteBatch batch;

    Texture fond;
    Texture gameOver;
    Sprite spriteFond;
    Sprite spriteGameOver;
    MyBombGame game;
    Stage stage;
    private Label scoreLabel;
    private Texture myTextureReturn;
    private TextureRegion myTextureRegionReturn;
    private TextureRegionDrawable myTextureRegionDrawableReturn;

    public GameOverScreen(MyBombGame game) {
        this.game = game;
    }

    public void create(int score) {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        int row_height = Gdx.graphics.getWidth() / 12;
        int col_width = Gdx.graphics.getWidth() / 12;

        batch = new SpriteBatch();

        fond = new Texture("fondbomb4.png");
        spriteFond = new Sprite(fond);
        spriteFond.setSize(1080, 2220); // pas adapter à tous les tels

        gameOver = new Texture("ui/gameover.png");
        spriteGameOver = new Sprite(gameOver);
        // spriteTitle.setSize((int) (1273 * 0.75), (int) (380 * 0.75));
        spriteGameOver.setPosition(100, 1000);
        createButtonMenu(col_width, row_height);
        createButtonReplay(col_width, row_height);
        Label.LabelStyle label1Style = new Label.LabelStyle();

        BitmapFont myFont = new BitmapFont(Gdx.files.internal("ui/birdy.fnt"));
        label1Style.font = myFont;
        myFont.getData().setScale((float) 2);
        label1Style.fontColor = Color.WHITE;

        scoreLabel = new Label("Score : " + score, label1Style);
        scoreLabel.setSize(100, 100);
        scoreLabel.setPosition(stage.getWidth() / 2 - 50, stage.getHeight() - 1300);
        scoreLabel.setAlignment(Align.center);
        stage.addActor(scoreLabel);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        spriteFond.draw(batch);
        spriteGameOver.draw(batch);
        batch.end();
        stage.draw();

    }

    public void createButtonMenu(int col_width, int row_height) {
        myTextureReturn = new Texture(Gdx.files.internal("ui/menu.png"));
        myTextureRegionReturn = new TextureRegion(myTextureReturn);
        myTextureRegionDrawableReturn = new TextureRegionDrawable(myTextureRegionReturn);
        ImageButton buttonReturn = new ImageButton(myTextureRegionDrawableReturn);


        buttonReturn.setSize(col_width * 7, (float) (row_height * 3));
        buttonReturn.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("ui/menu.png"))));
        buttonReturn.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("ui/menuclicked.png"))));
        buttonReturn.setPosition((int) (col_width * 2.5), Gdx.graphics.getHeight() - (int) (row_height * 17.5));
        buttonReturn.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.mainmenu.create();
                game.setScreen(game.mainmenu);
            }
        });
        stage.addActor(buttonReturn);
    }

    public void createButtonReplay(int col_width, int row_height) {
        myTextureReturn = new Texture(Gdx.files.internal("ui/replay.png"));
        myTextureRegionReturn = new TextureRegion(myTextureReturn);
        myTextureRegionDrawableReturn = new TextureRegionDrawable(myTextureRegionReturn);
        ImageButton buttonReturn = new ImageButton(myTextureRegionDrawableReturn);


        buttonReturn.setSize(col_width * 7, (float) (row_height * 3));
        buttonReturn.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("ui/replay.png"))));
        buttonReturn.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("ui/replayclicked.png"))));
        buttonReturn.setPosition((int) (col_width * 2.5), Gdx.graphics.getHeight() - (int) (row_height * 21.5));
        buttonReturn.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.playscreen.create();
                game.setScreen(game.playscreen);
            }
        });
        stage.addActor(buttonReturn);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
