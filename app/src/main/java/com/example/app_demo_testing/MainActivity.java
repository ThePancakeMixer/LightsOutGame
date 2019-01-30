package com.example.app_demo_testing;

import android.content.Intent;
import android.graphics.Point;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Grid gameGrid;
    GridLayout grid;
    TextView score;

    Button[][] grid_buttons;

    int cur_color;

    final int REQUEST_CODE_COLOR = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameGrid = new  Grid();
        grid =  findViewById(R.id.light_grid);
        score = findViewById(R.id.Score);
        grid_buttons = new Button[3][3];
        int index = 0;

        cur_color = R.color.Yellow;

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                grid_buttons[i][j] = (Button)grid.getChildAt(index);
                index++;
            }
        }

        if(savedInstanceState!=null)
            Log.v("state","exists");
        else
            Log.v("state","no exists");

        if(savedInstanceState==null){
            gameGrid.setUp();
        }
        else {
            String state = savedInstanceState.getString("state");
            gameGrid.restoreState(state);
        }

        PaintGrid();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("state",gameGrid.getState());

    }


    public void PaintGrid(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(gameGrid.isOn(new Point(i,j)))
                    grid_buttons[j][i].setBackgroundColor(ContextCompat.getColor(this,cur_color));
                else
                    grid_buttons[j][i].setBackgroundColor(ContextCompat.getColor(this,R.color.colorOff));

            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==REQUEST_CODE_COLOR){
            cur_color = data.getIntExtra("color",R.id.Yellow);
            PaintGrid();
        }
    }

    public void Toggle(View view) {
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(view==grid_buttons[j][i])
                    gameGrid.Toggle(new Point(i,j));
            }
        }

        PaintGrid();
        if(gameGrid.isGameOver())
            Toast.makeText(this, "You Win!", Toast.LENGTH_SHORT).show();
    }

    public void restartGame(View view) {
        gameGrid.setUp();
        PaintGrid();
    }

    public void onSetColors(View view) {
        Intent intent = new Intent(this,HelpActivity.class);
        intent.putExtra("curr",cur_color);
        startActivityForResult(intent,REQUEST_CODE_COLOR);
    }
}
