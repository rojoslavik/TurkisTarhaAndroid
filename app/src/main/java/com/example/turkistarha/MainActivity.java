package com.example.turkistarha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity  implements ListView.OnItemClickListener {

    ArrayList<FoxThread> foxThreads = new ArrayList<>();
    ListView foxList = null;
    public FoxAdapter adapter;
    public int foxAmount;
    String[] status;
    public int deathAmount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        foxList = findViewById(R.id.foxList);

        Random rand = new Random();
        foxAmount = rand.nextInt(6) + 5;

        for(int i = 0; i < foxAmount; i++){
            FoxThread fox = new FoxThread();
            fox.setIndex(i);
            foxThreads.add(fox);
            foxThreads.get(i).setOnFoodGoneListener(new OnFoodGoneListener(){
                    @Override
                    public void onComplete(final FoxThread thread) {
                        MainActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        if(thread.isDead() == false) {
                            TextView foxy = thread.foxes.findViewById(R.id.foxList);
                            foxy.setText("Amount of food: " + thread.getFood());
                        }
                        else
                            {
                                deathAmount++;
                                TextView foxy = thread.foxes.findViewById(R.id.foxList);
                                foxy.setText("Dead");
                                thread.foxes.setBackgroundColor(0xFFFF0000);
                                if (deathAmount == foxAmount) {
                                    Toast.makeText(getBaseContext(), "Game over", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
                }
            });
            fox.start();
        }

        adapter = new FoxAdapter(this, foxThreads);
        foxList.setAdapter(adapter);
        foxList.setOnItemClickListener(this);

    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        foxThreads.get(i).fillFoodBowl();
    }
}
