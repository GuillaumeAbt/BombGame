package com.bomb.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Guillaume on 07/12/2017.
 */

public class BoxColor {
    float width = 0;
    float height = 0;
    private Image[] Box = new Image[4];

    public BoxColor(Stage stage, Sprite spriteFond) {
        Texture[] boxColor = new Texture[4];
        boxColor[0] = new Texture("jaune.png");
        boxColor[1] = new Texture("violet.png");
        boxColor[2] = new Texture("bleu.png");
        boxColor[3] = new Texture("rose.png");

        Box[0] = new Image(boxColor[0]);
        Box[0].setSize((float) (0.75 * 389), (float) (0.75 * 854));
        Box[0].setPosition(0, 1290);

        stage.addActor(Box[0]);

        Box[2] = new Image(boxColor[1]);
        Box[2].setSize((float) (0.75 * 389), (float) (0.75 * 854));
        Box[2].setPosition(0, 225);

        stage.addActor(Box[2]);
        Box[1] = new Image(boxColor[2]);
        Box[1].setSize((float) (0.75 * 389), (float) (0.75 * 854));
        Box[1].setPosition(spriteFond.getWidth() - Box[2].getWidth(), 1290);

        stage.addActor(Box[1]);
        Box[3] = new Image(boxColor[3]);
        Box[3].setSize((float) (0.75 * 389), (float) (0.75 * 854));
        Box[3].setPosition(spriteFond.getWidth() - Box[3].getWidth(), 225);
        stage.addActor(Box[3]);

    }

    public Image[] getBox() {
        return Box;
    }
}