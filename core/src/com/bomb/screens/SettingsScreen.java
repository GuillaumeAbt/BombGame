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
import com.bomb.utils.SaveData;

/**
 * Created by Guillaume on 04/12/2017.
 */

public class SettingsScreen implements Screen {
    SpriteBatch batch;
    Stage stage;
    Texture fond;
    Texture titre;
    MyBombGame game;
    Sprite spriteFond;
    Sprite spriteTitle;
    private int changeDifficulty = 2;
    private String changeSpeed = "medium";
    private String changeSound = "on";

    private Texture myTextureReturn;
    private TextureRegion myTextureRegionReturn;
    private TextureRegionDrawable myTextureRegionDrawableReturn;
    private ImageButton buttonDifficulty;
    private Texture myTextureDifficulty;
    private TextureRegion myTextureRegionDifficulty;
    private TextureRegionDrawable myTextureRegionDrawableDifficulty;
    private ImageButton buttonSpeed;
    private Texture myTextureSpeed;
    private TextureRegion myTextureRegionSpeed;
    private TextureRegionDrawable myTextureRegionDrawableSpeed;
    private ImageButton buttonSound;
    private Texture myTextureSound;
    private TextureRegion myTextureRegionSound;
    private TextureRegionDrawable myTextureRegionDrawableSound;
    private Label soundLabel;
    private Label speedLabel;
    private Label difficultyLabel;
    private SaveData data;

    public SettingsScreen(MyBombGame game) {
        this.game = game;
    }

    public void create() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        int row_height = Gdx.graphics.getWidth() / 12;
        int col_width = Gdx.graphics.getWidth() / 12;

        batch = new SpriteBatch();

        fond = new Texture("fondbomb4.png");
        spriteFond = new Sprite(fond);
        spriteFond.setSize(1080, 2220); // pas adapter à tous les tels

        titre = new Texture("ui/bombtitle.png");
        spriteTitle = new Sprite(titre);
        spriteTitle.setSize((int) (1273 * 0.75), (int) (380 * 0.75));
        spriteTitle.setPosition(80, 1800);

        createButtonReturn(col_width, row_height);
        createButtonDifficulty(col_width, row_height);
        createButtonSpeed(col_width, row_height);
        createButtonSound(col_width, row_height);
        createLabel(col_width, row_height);
        data = new SaveData();

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        batch.begin();
        spriteFond.draw(batch);
        spriteTitle.draw(batch);
        updateDifficulty(buttonDifficulty, changeDifficulty);
        updateSpeed(buttonSpeed, changeSpeed);
        updateSound(buttonSound, changeSound);

        batch.end();
        stage.draw();
    }

    public void createLabel(int col_width, int row_height) {
        Label.LabelStyle label1Style = new Label.LabelStyle();

        BitmapFont myFont = new BitmapFont(Gdx.files.internal("ui/birdy.fnt"));
        label1Style.font = myFont;
        myFont.getData().setScale((float) 2);
        label1Style.fontColor = Color.WHITE;

        soundLabel = new Label("Sound :", label1Style);
        soundLabel.setSize(100, 100);
        soundLabel.setPosition((int) (col_width * 2.5) - 75, Gdx.graphics.getHeight() - (int) (row_height * 10.5) + 100);
        soundLabel.setAlignment(Align.center);
        stage.addActor(soundLabel);
        speedLabel = new Label("Speed :", label1Style);
        speedLabel.setSize(100, 100);
        speedLabel.setPosition((int) (col_width * 2.5) - 75, Gdx.graphics.getHeight() - (int) (row_height * 12.5) + 100);
        speedLabel.setAlignment(Align.center);
        stage.addActor(speedLabel);
        difficultyLabel = new Label("Difficulty :", label1Style);
        difficultyLabel.setSize(100, 100);
        difficultyLabel.setPosition((int) (col_width * 2.5) - 75, Gdx.graphics.getHeight() - (int) (row_height * 14.5) + 100);
        difficultyLabel.setAlignment(Align.center);
        stage.addActor(difficultyLabel);
    }

    public void updateSound(ImageButton buttonSound, String j) {
        buttonSound.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("ui/sound" + j + ".png"))));
        data.setSound(j);
    }

    public void createButtonSound(int col_width, int row_height) {
        myTextureSound = new Texture(Gdx.files.internal("ui/soundoff.png"));
        myTextureRegionSound = new TextureRegion(myTextureReturn);
        myTextureRegionDrawableSound = new TextureRegionDrawable(myTextureRegionReturn);
        buttonSound = new ImageButton(myTextureRegionDrawableReturn);


        buttonSound.setSize(col_width * 7, (float) (row_height * 3));
        buttonSound.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("ui/soundoff.png"))));
        buttonSound.setPosition((int) (col_width * 2.5) + 125, Gdx.graphics.getHeight() - (int) (row_height * 10.5));
        buttonSound.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }


            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (changeSound == "on") {
                    changeSound = "off";
                } else {
                    changeSound = "on";
                }
            }
        });
        stage.addActor(buttonSound);
    }

    public void updateSpeed(ImageButton buttonDifficulty, String j) {
        buttonDifficulty.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("ui/speed" + j + ".png"))));
        data.setSpeed(j);
    }

    public void createButtonSpeed(int col_width, int row_height) {
        myTextureSpeed = new Texture(Gdx.files.internal("ui/speedmedium.png"));
        myTextureRegionSpeed = new TextureRegion(myTextureReturn);
        myTextureRegionDrawableSpeed = new TextureRegionDrawable(myTextureRegionReturn);
        buttonSpeed = new ImageButton(myTextureRegionDrawableReturn);


        buttonSpeed.setSize(col_width * 7, (float) (row_height * 3));
        buttonSpeed.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("ui/speedmedium.png"))));
        //  buttonReturn.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("ui/difficulty4.png"))));
        buttonSpeed.setPosition((int) (col_width * 2.5) + 125, Gdx.graphics.getHeight() - (int) (row_height * 12.5));
        buttonSpeed.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }


            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (changeSpeed == "medium") {
                    changeSpeed = "fast";
                } else {
                    changeSpeed = "medium";
                }

            }
        });
        stage.addActor(buttonSpeed);
    }

    public void updateDifficulty(ImageButton buttonDifficulty, int i) {
        buttonDifficulty.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("ui/difficulty" + i + ".png"))));
        data.setDifficulty(i);
    }

    public void createButtonDifficulty(int col_width, int row_height) {
        myTextureDifficulty = new Texture(Gdx.files.internal("ui/difficulty4.png"));
        myTextureRegionDifficulty = new TextureRegion(myTextureReturn);
        myTextureRegionDrawableDifficulty = new TextureRegionDrawable(myTextureRegionReturn);
        buttonDifficulty = new ImageButton(myTextureRegionDrawableReturn);


        buttonDifficulty.setSize(col_width * 7, (float) (row_height * 3));
        buttonDifficulty.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("ui/difficulty" + changeDifficulty + ".png"))));
        //  buttonReturn.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("ui/difficulty4.png"))));
        buttonDifficulty.setPosition((int) (col_width * 2.5) + 125, Gdx.graphics.getHeight() - (int) (row_height * 14.5));
        buttonDifficulty.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }


            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (changeDifficulty == 4) {
                    changeDifficulty = 2;
                } else {
                    changeDifficulty = 4;
                }
            }
        });
        stage.addActor(buttonDifficulty);
    }

    public void createButtonReturn(int col_width, int row_height) {
        myTextureReturn = new Texture(Gdx.files.internal("ui/returnbutton.png"));
        myTextureRegionReturn = new TextureRegion(myTextureReturn);
        myTextureRegionDrawableReturn = new TextureRegionDrawable(myTextureRegionReturn);
        ImageButton buttonReturn = new ImageButton(myTextureRegionDrawableReturn);


        buttonReturn.setSize(col_width * 7, (float) (row_height * 3));
        buttonReturn.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("ui/returnbutton.png"))));
        buttonReturn.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("ui/returnbuttonclicked.png"))));
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
