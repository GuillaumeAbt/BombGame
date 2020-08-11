package com.bomb.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.TimeUtils;
import com.bomb.MyBombGame;
import com.bomb.utils.SaveData;

import java.util.Random;

/**
 * Created by Guillaume on 05/12/2017.
 */

public class Bomb {
    MyBombGame game;
    private Image Bomb;
    private char collision = 'n';
    private char ys;
    private char xs;
    private float x;
    private float y;
    private int changeDirection;
    private int randDirection;
    private int randTopOrBottom;
    private TextureRegion[] blinkAnimationTexture = new TextureRegion[2];


    private int color = -2;
    private int catched = -1;
    private int scoreBomb = 1;

    private SaveData data;
    private float speed;
    private long timeBomb;
    private Animation blinkAnimation;

    public Bomb(Stage stage, TextureRegion[] bombTexture, int direction) {
        Random random = new Random();
        color = random.nextInt(4);
        Bomb = new Image(bombTexture[color]);
        Bomb.setSize(150, 160);


        Bomb.addListener(new DragListener() {
            public void drag(InputEvent event, float x, float y, int pointer) {
                if (catched < 0) {
                    Bomb.moveBy(x - Bomb.getWidth() / 2, y - Bomb.getHeight() / 2);
                }
            }
        });
        changeDirection = direction;
        randDirection = random.nextInt(2);

        randTopOrBottom = random.nextInt(2);
        data = new SaveData();
        if (data.getSpeed() == "fast") {
            speed = (float) 2.2;
        } else {
            speed = (float) 1.5;
        }
        if (randTopOrBottom == 1) {
            Bomb.setPosition((int) (getColWidth() * 5.25), Gdx.graphics.getHeight() - (getRowHeight() * 2));
            ys = '-';
            xs = '+';
        } else {
            Bomb.setPosition((int) (getColWidth() * 5.25), (getRowHeight() * 2));
            ys = '+';
            xs = '+';
        }

        stage.addActor(Bomb);
        timeBomb = TimeUtils.millis();
        blinkAnimationTexture[0] = bombTexture[color + 4];
        blinkAnimationTexture[1] = bombTexture[color + 8];
        blinkAnimation = new Animation<TextureRegion>(0.25f, blinkAnimationTexture);
    }

    private int getColor() {
        return color;
    }

    public int getColWidth() {
        return Gdx.graphics.getWidth() / 12;
    }

    public int getRowHeight() {
        return Gdx.graphics.getWidth() / 12;
    }

    public void setVisible(boolean t) {
        this.Bomb.setVisible(t);
    }

    private void moveBomb(float delta, float widthScreen, float heightScreen, float limitx, float limity) {

        if (Bomb.getX() > limitx + widthScreen - Bomb.getWidth()) {
            collision = 'd';
        } else if (Bomb.getY() > limity + heightScreen - Bomb.getHeight()) {
            collision = 'h';
        } else if (Bomb.getX() < limitx) {
            collision = 'g';
        } else if (Bomb.getY() < limity) {
            collision = 'b';
        }
        switch (collision) {
            case 'h':
                if (xs == '+') {
                    x = Bomb.getX() + speed * delta * (100 + changeDirection);
                    y = Bomb.getY() - speed * delta * (100 - changeDirection);
                    ys = '-';
                } else {
                    x = Bomb.getX() - speed * delta * (100 + changeDirection);
                    y = Bomb.getY() - speed * delta * (100 - changeDirection);
                    ys = '-';
                }
                break;
            case 'b':
                if (xs == '+') {
                    x = Bomb.getX() + speed * delta * (100 + changeDirection);
                    y = Bomb.getY() + speed * delta * (100 - changeDirection);
                    ys = '+';
                } else {
                    x = Bomb.getX() - speed * delta * (100 + changeDirection);
                    y = Bomb.getY() + speed * delta * (100 - changeDirection);
                    ys = '+';
                }
                break;
            case 'd':
                if (ys == '+') {
                    x = Bomb.getX() - speed * delta * (100 + changeDirection);
                    y = Bomb.getY() + speed * delta * (100 - changeDirection);
                    xs = '-';
                } else {
                    x = Bomb.getX() - speed * delta * (100 + changeDirection);
                    y = Bomb.getY() - speed * delta * (100 - changeDirection);
                    xs = '-';
                }
                break;
            case 'g':
                if (ys == '+') {
                    x = Bomb.getX() + speed * delta * (100 + changeDirection);
                    y = Bomb.getY() + speed * delta * (100 - changeDirection);
                    xs = '+';
                } else {
                    x = Bomb.getX() + speed * delta * (100 + changeDirection);
                    y = Bomb.getY() - speed * delta * (100 - changeDirection);
                    xs = '-';
                }
                break;
            default:

                if (randDirection == 0) {

                    x = Bomb.getX() + (float) 1.5 * delta * (100 + changeDirection);
                    xs = '+';
                } else {
                    x = Bomb.getX() - (float) 1.5 * delta * (100 + changeDirection);
                    xs = '-';
                }
                if (randTopOrBottom == 1) {
                    y = Bomb.getY() - (float) 1.5 * delta * (100 - changeDirection);
                    ys = '-';
                } else {
                    y = Bomb.getY() + (float) 1.5 * delta * (100 - changeDirection);
                    ys = '+';
                }

        }
        Bomb.setPosition(x, y);
    }

    public long getTimeAlive() {
        return TimeUtils.timeSinceMillis(timeBomb);
    }

    public float[] drawBomb(Image[] Box, float delta, float widthScreen, float heightScreen, TextureRegion[] bombTexture, float stateTime) {

        if (TimeUtils.timeSinceMillis(timeBomb) > 3000 && catched < 0) {

            Bomb.setDrawable(new SpriteDrawable(new Sprite((TextureRegion) blinkAnimation.getKeyFrame(stateTime, true))));

        }
        if (TimeUtils.timeSinceMillis(timeBomb) > 5500 && catched < 0) {

            return new float[]{1, Bomb.getX(), Bomb.getY(), 0};
        }

        if (((Bomb.getX() >= Box[0].getX() && Bomb.getX() <= Box[0].getX() + Box[0].getWidth() - Bomb.getWidth() && Bomb.getY() > Box[0].getY()) && Bomb.getY() < Box[0].getY() + Box[0].getHeight() - Bomb.getHeight())) { // jaune
            catched = 0;


        }
        if (((Bomb.getX() >= Box[1].getX() && Bomb.getX() <= Box[1].getX() + Box[1].getWidth() - Bomb.getWidth() && Bomb.getY() > Box[1].getY()) && Bomb.getY() < Box[1].getY() + Box[1].getHeight() - Bomb.getHeight())) { // violet
            catched = 1;

        }
        if (((Bomb.getX() >= Box[2].getX() && Bomb.getX() <= Box[2].getX() + Box[2].getWidth() - Bomb.getWidth() && Bomb.getY() > Box[2].getY()) && Bomb.getY() < Box[2].getY() + Box[2].getHeight() - Bomb.getHeight())) { // violet
            catched = 2;

        }
        if (Bomb.getX() >= Box[3].getX() && Bomb.getY() < Box[3].getY() + Box[3].getHeight() && Bomb.getY() > Box[3].getY() - Bomb.getHeight()) { // rose
            catched = 3;

        }
        if (catched >= 0) {

            moveBomb(delta, Box[catched].getWidth(), Box[catched].getHeight(), Box[catched].getX(), Box[catched].getY());

        } else {
            moveBomb(delta, widthScreen, heightScreen, 0, 0);
        }
        if (getColor() != catched && (catched >= 0 && getColor() >= 0)) {


            return new float[]{1, Bomb.getX(), Bomb.getY(), 0}; // mort d'ou le 1 au debut
        }
        if (scoreBomb == 1 && catched >= 0) {
            scoreBomb = 0;
            return new float[]{0, Bomb.getX() - Bomb.getWidth(), Bomb.getY() - Bomb.getHeight(), 1}; // comme jamais compté, score 1
        } else {
            return new float[]{0, Bomb.getX() - Bomb.getWidth(), Bomb.getY() - Bomb.getHeight(), 0}; // score 0 a la fin

        }

    }

}
