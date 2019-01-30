package com.example.app_demo_testing;

import android.graphics.Point;

import java.util.Random;

public class Grid {

    private boolean[][] grid;
    public int moves;

    public Grid(){
        grid = new boolean[3][3];
        moves =0;
    }

    public boolean isOn(Point x){
        return grid[x.y][x.x];
    }

    public void setUp(){
        Random randy = new Random();
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                grid[i][j] = randy.nextBoolean();
            }
        }
        moves = 0;
    }

    public String getState(){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                sb.append(grid[i][j]?'T':'F');
            }
        }
        return sb.toString();
    }

    public void restoreState(String input){
        int char_index = 0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(input.charAt(char_index)=='T')
                    grid[i][j] = true;
                else
                    grid[i][j] = false;
                char_index++;
            }
        }
    }


    private final Point[] steps = {new Point(0,1) , new Point(1,0), new Point(-1,0) , new Point(0,-1),new Point(0,0)};

    public void Toggle(Point x) {

        for (Point pivot : steps) {
            Point temp = new Point(x.x + pivot.x, x.y + pivot.y);
            if (temp.x >= 3 || temp.x < 0 || temp.y >= 3 || temp.y < 0)
                continue;
            grid[temp.y][temp.x] = !grid[temp.y][temp.x];
        }
        moves++;
    }

    public boolean isGameOver(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(!grid[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

}
