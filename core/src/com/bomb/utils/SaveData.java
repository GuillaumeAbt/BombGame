package com.bomb.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Guillaume on 15/12/2017.
 */

public class SaveData {
    int a = 0;
    private Preferences prefs;
    private int[] deserializedInts;

    public SaveData() {
        prefs = Gdx.app.getPreferences("preferences");
        setSound("on");

     /*   prefs.putInteger("score1", 0);
        prefs.putInteger("score2", 0);
        prefs.putInteger("score3", 0);
        prefs.putInteger("score4", 0);
        prefs.putInteger("score5", 0);*/
        prefs.flush();

        //  System.out.println(deserializedInts[0]);
    }

    public void getPref() {
        System.out.println(prefs.getString("cc"));
    }

    public Array<Integer> getScoreList() {
        Array<Integer> list = new Array<Integer>();
        for (int i = 1; i <= 5; i++) {
            list.add(prefs.getInteger("score" + i));
        }
        return list;
    }

    public void insertOrNotScore(Array<Integer> list, int score) {
        for (int i = 0; i < 5; i++) {
            if (list.get(list.size - 1) <= score) {
                list.removeIndex(list.size - 1);
                list.add(score);
            }
        }
        list.sort();
        list.reverse();
        for (int j = 1; j <= 5; j++) {
            prefs.putInteger("score" + j, list.get(j - 1));
        }
        prefs.flush();
    }

    public void setDifficulty(int difficulty) {
        prefs.putInteger("difficulty", difficulty);

        prefs.flush();
    }

    public String getSpeed() {
        return prefs.getString("speed");
    }

    public void setSpeed(String speed) {
        prefs.putString("speed", speed);
        prefs.flush();
    }

    public String getSound() {
        return prefs.getString("sound");
    }

    public void setSound(String sound) {

        prefs.putString("sound", sound);

        prefs.flush();
    }
}
