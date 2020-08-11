package com.bomb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

/**
 * Created by Guillaume on 15/12/2017.
 */

public class PlayScreenUI {
    private Label scoreLabel;
    private Label lifeLabel;
    private Image gameOver;

    public PlayScreenUI(Stage stage) {
        Label.LabelStyle label1Style = new Label.LabelStyle();

        BitmapFont myFont = new BitmapFont(Gdx.files.internal("ui/birdy.fnt"));
        label1Style.font = myFont;
        myFont.getData().setScale((float) 2);
        label1Style.fontColor = Color.WHITE;


        scoreLabel = new Label("Score : ", label1Style);
        scoreLabel.setSize(100, 100);
        scoreLabel.setPosition(stage.getWidth() - 250, stage.getHeight() - 100);
        scoreLabel.setAlignment(Align.center);
        stage.addActor(scoreLabel);
        lifeLabel = new Label("Life :", label1Style);
        lifeLabel.setSize(100, 100);
        lifeLabel.setPosition(20, stage.getHeight() - 100);
        lifeLabel.setAlignment(Align.left);
        stage.addActor(lifeLabel);
    }

    public void updateScore(int score) {
        scoreLabel.setText("Score : " + score);

    }

    public void updateLife(int life) {
        lifeLabel.setText("Life : " + life);
    }

}
