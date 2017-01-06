package com.app.ybpick;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.app.ybpick.dialog.DialogChooseDate;

public class MainActivity2 extends AppCompatActivity {
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button2 = (Button) findViewById(R.id.bt_2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogChooseDate dialogChoosePick = new DialogChooseDate(MainActivity2.this, 1, new DialogChooseDate.Dialogcallback() {
                    @Override
                    public void pickWeightResult(String date) {
                        Toast.makeText(MainActivity2.this, date + "", Toast.LENGTH_SHORT).show();
                    }
                });
                dialogChoosePick.show();
            }
        });
    }

}