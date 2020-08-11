package com.bomb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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

/**
 * Created by Guillaume on 04/12/2017.
 */

public class EditScreen implements Screen {

    SpriteBatch batch;

    Texture fond;
    Texture titre;
    Texture about;
    Sprite spriteFond;
    Sprite spriteTitle;
    Sprite spriteAbout;
    MyBombGame game;
    Stage stage;
    private Texture myTextureReturn;
    private TextureRegion myTextureRegionReturn;
    private TextureRegionDrawable myTextureRegionDrawableReturn;

    public EditScreen(MyBombGame game) {
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

        about = new Texture("about.png");
        spriteAbout = new Sprite(about);
        spriteAbout.setPosition((stage.getWidth() - about.getWidth()) / 2, 500);
        createButtonReturn(col_width, row_height);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        spriteFond.draw(batch);
        spriteTitle.draw(batch);
        spriteAbout.draw(batch);
        batch.end();
        stage.draw();

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
