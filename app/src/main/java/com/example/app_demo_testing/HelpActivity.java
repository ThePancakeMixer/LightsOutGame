package com.example.app_demo_testing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Intent intent = getIntent();

        int color = intent.getIntExtra("curr",R.color.Yellow);
        String curr_color = "Yellow";
        switch(color){
            case R.color.Red:
                curr_color = "Red";
                break;
            case R.color.Black:
                curr_color = "Black";
                break;
            case R.color.Green:
                curr_color = "Green";
                break;
            case R.color.Yellow:
                curr_color = "Yellow";
                break;
            case R.color.Brown:
                curr_color = "Brown";
                break;
        }
        RadioGroup radioGroup = findViewById(R.id.ColorPicker);
        for(int i=0;i<radioGroup.getChildCount();i++){
            RadioButton rb = (RadioButton)radioGroup.getChildAt(i);
            if(rb.getText().equals(curr_color)){
                rb.toggle();
                break;
            }
        }
    }

    public void colorPicked(View view) {
        RadioGroup radioGroup = findViewById(R.id.ColorPicker);
        int r_id = radioGroup.getCheckedRadioButtonId();
        RadioButton rb = findViewById(r_id);

        Intent intent = new Intent();

        int r_color = 0;
        switch(rb.getText().toString()){
            case "Red":
                r_color = R.color.Red;
                break;
            case "Black":
                r_color = R.color.Black;
                break;
            case "Green":
                r_color = R.color.Green;
                break;
            case "Yellow":
                r_color = R.color.Yellow;
                break;
            case "Brown":
                r_color = R.color.Brown;
                break;

        }

        intent.putExtra("color",r_color);
        setResult(RESULT_OK,intent);
        finish();
    }
}
