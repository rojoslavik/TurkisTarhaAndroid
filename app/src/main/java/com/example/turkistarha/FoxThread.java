package com.example.turkistarha;

import android.view.View;

public class FoxThread extends Thread {

    private boolean dead = false;
    private int food = 10;
    private int index;
    private boolean firstLoop = true;
    public View foxes;

    private OnFoodGoneListener OnFoodGoneListener;

    public void setOnFoodGoneListener(OnFoodGoneListener OnFoodGoneListener) {
        this.OnFoodGoneListener = OnFoodGoneListener;
    }

    public void setView(View view){
        this.foxes = view;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public int getIndex() {
        return this.index;
    }

    public int getFood() {
        return this.food;
    }

    public boolean isDead(){
        return this.dead;
    }

    public void fillFoodBowl() {
        this.food += 10;
    }

    @Override
    public void run() {
        try {
            while(this.dead == false) {
                if(firstLoop == true) {
                    Thread.sleep(1000*this.index);
                    firstLoop = false;
                }
                Thread.sleep(5000);
                this.food--;
                if(food <= 0 || food >= 20)
                    this.dead = true;
                OnFoodGoneListener.onComplete(this);
            }
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}
