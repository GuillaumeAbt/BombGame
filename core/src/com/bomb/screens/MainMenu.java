package com.bomb.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.bomb.MyBombGame;

public class MainMenu implements Screen { //ApplicationAdapter


    SpriteBatch batch;
    Texture img;
    Texture fond;
    Texture titre;
    Sprite spriteFond;
    Sprite spriteTitle;
    MyBombGame game;
    private Stage stage;
    private Texture myTexturePlay;
    private TextureRegion myTextureRegionPlay;
    private TextureRegionDrawable myTextureRegionDrawablePlay;
    private Texture myTextureEdit;
    private TextureRegion myTextureRegionEdit;
    private TextureRegionDrawable myTextureRegionDrawableEdit;
    private Texture myTextureSettings;
    private TextureRegion myTextureRegionSettings;
    private TextureRegionDrawable myTextureRegionDrawableSettings;
    private Texture myTextureTrophy;
    private TextureRegion myTextureRegionTrophy;
    private TextureRegionDrawable myTextureRegionDrawableTrophy;

    public MainMenu(MyBombGame game) {
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

        createButtonPlay(col_width, row_height);
        createButtonEdit(col_width, row_height);
        createButtonSettings(col_width, row_height);
        createButtonTrophy(col_width, row_height);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderMenu();
    }


    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        fond.dispose();
        stage.dispose();
    }

    public void renderMenu() {
        batch.begin();
        spriteFond.draw(batch);
        spriteTitle.draw(batch);
        batch.end();
        stage.draw();
    }

    public void createButtonPlay(int col_width, int row_height) {
        myTexturePlay = new Texture(Gdx.files.internal("ui/playbutton.png"));
        myTextureRegionPlay = new TextureRegion(myTexturePlay);
        myTextureRegionDrawablePlay = new TextureRegionDrawable(myTextureRegionPlay);
        ImageButton buttonPlay = new ImageButton(myTextureRegionDrawablePlay);


        buttonPlay.setSize(col_width * 6, (float) (row_height * 3));
        buttonPlay.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("ui/playbutton.png"))));
        buttonPlay.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("ui/playbuttonclicked.png"))));
        buttonPlay.setPosition(col_width * 3, Gdx.graphics.getHeight() - (int) (row_height * 13.5));
        buttonPlay.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.playscreen.create();
                game.setScreen(game.playscreen);
            }
        });
        stage.addActor(buttonPlay);
    }

    public void createButtonEdit(int col_width, int row_height) {
        myTextureEdit = new Texture(Gdx.files.internal("ui/editbutton.png"));
        myTextureRegionEdit = new TextureRegion(myTextureEdit);
        myTextureRegionDrawableEdit = new TextureRegionDrawable(myTextureRegionEdit);
        ImageButton buttonEdit = new ImageButton(myTextureRegionDrawableEdit);


        buttonEdit.setSize(col_width * 2, (float) (row_height * 1.75));
        buttonEdit.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("ui/editbutton.png"))));
        buttonEdit.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("ui/editbuttonclicked.png"))));
        buttonEdit.setPosition(col_width * 1, Gdx.graphics.getHeight() - (int) (row_height * 16));
        buttonEdit.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.editscreen.create();
                game.setScreen(game.editscreen);
            }
        });
        stage.addActor(buttonEdit);

    }

    public void createButtonSettings(int col_width, int row_height) {
        myTextureSettings = new Texture(Gdx.files.internal("ui/settingsbutton.png"));
        myTextureRegionSettings = new TextureRegion(myTextureSettings);
        myTextureRegionDrawableSettings = new TextureRegionDrawable(myTextureRegionSettings);
        ImageButton buttonSettings = new ImageButton(myTextureRegionDrawableSettings);


        buttonSettings.setSize(col_width * 2, (float) (row_height * 1.75));
        buttonSettings.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("ui/settingsbutton.png"))));
        buttonSettings.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("ui/settingsbuttonclicked.png"))));
        buttonSettings.setPosition(col_width * 5, Gdx.graphics.getHeight() - (int) (row_height * 16));
        buttonSettings.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.settingsscreen.create();
                game.setScreen(game.settingsscreen);
            }
        });
        stage.addActor(buttonSettings);
    }

    public void createButtonTrophy(int col_width, int row_height) {
        myTextureTrophy = new Texture(Gdx.files.internal("ui/trophybutton.png"));
        myTextureRegionTrophy = new TextureRegion(myTextureTrophy);
        myTextureRegionDrawableTrophy = new TextureRegionDrawable(myTextureRegionTrophy);
        ImageButton buttonTrophy = new ImageButton(myTextureRegionDrawableTrophy);


        buttonTrophy.setSize(col_width * 2, (float) (row_height * 1.75));
        buttonTrophy.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("ui/trophybutton.png"))));
        buttonTrophy.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("ui/trophybuttonclicked.png"))));
        buttonTrophy.setPosition(col_width * 9, Gdx.graphics.getHeight() - (int) (row_height * 16));
        buttonTrophy.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.trophyscreen.create();
                game.setScreen(game.trophyscreen);
            }
        });
        stage.addActor(buttonTrophy);
    }

    @Override
    public void pause() {

    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int arg0, int arg1) {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {

    }
}
