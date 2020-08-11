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

public class Star {
    public static boolean clicked;
    Random random = new Random();
    private Image Stara;
    private Texture starTexture;

    public Star(Stage stage, int life, float x, float y) {
        starTexture = new Texture("star.png");
        Stara = new Image(starTexture);
        Stara.setSize(150, 150);
        Stara.setPosition(x - Stara.getWidth(), y - Stara.getHeight());


        Stara.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                //  Star.setVisible(false);
                Star.clicked = true;
            }
        });

        stage.addActor(Stara);

    }

    public static boolean isClicked() {

        return clicked;
    }

    public static void setClicked() {

        clicked = false;
    }

    public void remove() {
        Stara.remove();
    }
}
