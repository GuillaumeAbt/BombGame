package com.bomb.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.Random;

/**
 * Created by Guillaume on 05/12/2017.
 */

public class Timer {
    public static boolean clicked;
    Random random = new Random();
    /* Not implanted in the game..*/
    private Image Timera;
    private Texture starTexture;

    public Timer(Stage stage, float x, float y) {
        starTexture = new Texture("timer.png");
        Timera = new Image(starTexture);
        Timera.setSize(150, 170);
        Timera.setPosition(x - Timera.getWidth(), y - Timera.getHeight());


        Timera.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                //  Star.setVisible(false);
                Timer.clicked = true;
            }
        });

        stage.addActor(Timera);

    }

    public static boolean isClicked() {

        return clicked;
    }

    public static void setClicked() {

        clicked = false;
    }

    public void remove() {
        Timera.remove();
    }

    public long getTime() {
        return 2000;
    }
}
