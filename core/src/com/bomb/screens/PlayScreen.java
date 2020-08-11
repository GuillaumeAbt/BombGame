package com.bomb.screens;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.bomb.MyBombGame;
import com.bomb.objects.Bomb;
import com.bomb.objects.BoxColor;
import com.bomb.objects.Star;
import com.bomb.utils.SaveData;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


/**
 * Created by Guillaume on 04/12/2017.
 * <p>
 * TODO:
 * Optimiser le code avec les constantes
 * ajouter le bonus "timer"
 */

public class PlayScreen implements Screen, ApplicationListener {
    MyBombGame game;
    float stateTimeBlink;
    float stateTime;
    int score;
    int life;
    private Texture fond;
    private Sprite spriteFond;
    private SpriteBatch batch;
    private Stage stage;
    private Texture bombTextures;
    private TextureRegion[] bombTexture = new TextureRegion[12];
    private List<Bomb> Bombs;
    private long startTime;
    private int direction = -100;
    private BoxColor box;
    private Texture explosions;
    private TextureRegion[] explosion = new TextureRegion[10];
    private Animation explosionAnimation;
    private PlayScreenUI ui;
    private SaveData data;
    private Star star;
    // private Timer timer;
    // private long timeTimer; Surrended...
    private long stateTimeStar;
    private int countBomb;
    private long timeBetweenBomb;
    private Music music;
    private Sound sound;
    private boolean played;

    public PlayScreen(MyBombGame game) {
        this.game = game;
    }

    public void create() {
        Bombs = new ArrayList<Bomb>();
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        setBombTextures();
        fond = new Texture("fondbomb4.png");
        spriteFond = new Sprite(fond);
        spriteFond.setSize(1080, 2220); // pas adapter à tout les tels
        batch = new SpriteBatch();
        box = new BoxColor(stage, spriteFond);
        explosions = new Texture("explosion2.png");
        explosion[0] = new TextureRegion(explosions, 0, 0, 128, 128);
        explosion[1] = new TextureRegion(explosions, 128, 0, 128, 128);
        explosion[2] = new TextureRegion(explosions, 128 * 2, 0, 128, 128);
        explosion[3] = new TextureRegion(explosions, 128 * 3, 0, 128, 128);
        explosion[4] = new TextureRegion(explosions, 128 * 4, 0, 128, 128);
        explosion[5] = new TextureRegion(explosions, 128 * 5, 0, 128, 128);
        explosion[6] = new TextureRegion(explosions, 128 * 6, 0, 128, 128);
        explosion[7] = new TextureRegion(explosions, 128 * 7, 0, 128, 128);
        explosion[8] = new TextureRegion(explosions, 128 * 8, 0, 128, 128);
        explosion[9] = new TextureRegion(explosions, 128 * 9, 0, 128, 128);
        explosionAnimation = new Animation<TextureRegion>(0.1f, explosion);
        stateTime = 0f;
        stateTimeBlink = 0f;
        stateTimeStar = TimeUtils.millis();
        explosionAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        score = 0;
        life = 0;
        ui = new PlayScreenUI(stage);
        data = new SaveData();
        timeBetweenBomb = 2000;
        if (data.getSound() == "on") {
            System.out.println("ALUMER");
            music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
            music.setLooping(true);
            music.play();
            sound = Gdx.audio.newSound(Gdx.files.internal("explosion.mp3"));
        }

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batch.begin();
        spriteFond.draw(batch);
        stateTimeBlink += Gdx.graphics.getDeltaTime();

        batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        batch.begin();
        drawBombs(batch, delta, stateTimeBlink);
        ui.updateScore(score);
        ui.updateLife(life);
        batch.end();
        genStar();
        // genTime();
        genBomb();

    }

    private void genStar() {
        if (TimeUtils.timeSinceMillis(stateTimeStar) > 20000) {
            Random random = new Random();
            star = new Star(stage, life, random.nextInt(Gdx.graphics.getWidth()), random.nextInt(Gdx.graphics.getHeight()));
            stateTimeStar = TimeUtils.millis();
        }
        if (star != null) {
            if (Star.isClicked()) {
                star.setClicked();
                life += 1;
                star.remove();
            }
            if (TimeUtils.timeSinceMillis(stateTimeStar) > 2000) {
                star.remove();
            }
        }

    }

    private void setBombTextures() { // for...
        bombTextures = new Texture(Gdx.files.internal("bombs.png"));
        bombTexture[0] = new TextureRegion(bombTextures, 0, 0, 920, 996);
        bombTexture[1] = new TextureRegion(bombTextures, 0, 996, 920, 996);
        bombTexture[2] = new TextureRegion(bombTextures, 0, 996 * 2, 920, 996);
        bombTexture[3] = new TextureRegion(bombTextures, 0, 996 * 3, 920, 996);

        bombTexture[4] = new TextureRegion(bombTextures, 900, 0, 920, 996);
        bombTexture[5] = new TextureRegion(bombTextures, 900, 996, 920, 996);
        bombTexture[6] = new TextureRegion(bombTextures, 900, 996 * 2, 920, 996);
        bombTexture[7] = new TextureRegion(bombTextures, 900, 996 * 3, 920, 996);

        bombTexture[8] = new TextureRegion(bombTextures, 900 * 2, 0, 920, 996);
        bombTexture[9] = new TextureRegion(bombTextures, 900 * 2, 996, 920, 996);
        bombTexture[10] = new TextureRegion(bombTextures, 900 * 2, 996 * 2, 920, 996);
        bombTexture[11] = new TextureRegion(bombTextures, 900 * 2, 996 * 3, 920, 996);
    }

    private TextureRegion[] getBombTexture() {
        return bombTexture;
    }

    private void genBomb() {
        if (TimeUtils.timeSinceMillis(startTime) > timeBetweenBomb) {
            if (countBomb % 10 == 0) {
                timeBetweenBomb /= 1.2;
            }
            Bomb bomb = new Bomb(stage, getBombTexture(), direction);
            countBomb++;
            System.out.println(countBomb);
            Bombs.add(bomb);
            startTime = TimeUtils.millis();
            direction += 20;
            if (direction == 100) {
                direction = -100;
            }
        }
    }

    private void drawBombs(SpriteBatch batch, float delta, float stateTimeBlink) {

        for (Iterator<Bomb> IterBomb = Bombs.iterator(); IterBomb.hasNext(); ) {
            Bomb Bomb = IterBomb.next();
            float[] response = Bomb.drawBomb(box.getBox(), delta, spriteFond.getWidth(), spriteFond.getHeight(), bombTexture, stateTimeBlink);
            score += (int) response[3];
            if (response[0] == 1 && played == false && data.getSound() == "on") {
                sound.play(0.2f);
            }
            if (response[0] == 1) {
                stateTime += Gdx.graphics.getDeltaTime();

                if (!explosionAnimation.isAnimationFinished(stateTime)) {
                    batch.draw((TextureRegion) explosionAnimation.getKeyFrame(stateTime, true), response[1] - 210, response[2] - 180, 512, 512);
                    Bomb.setVisible(false);
                    played = true;
                } else {

                    IterBomb.remove();
                    life -= 1;
                    if (life < 0) {
                        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                        data.insertOrNotScore(data.getScoreList(), score);
                        System.out.println(data.getScoreList());
                        game.gameoverscreen.create(score);
                        game.setScreen(game.gameoverscreen);
                        played = false;
                    }
                }
            }


        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {

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
        stage.dispose();
        batch.dispose();
        explosions.dispose();
    }
}
