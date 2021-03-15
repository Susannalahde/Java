package com.example.viikkko8t1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStreamWriter;


public class MainActivity extends AppCompatActivity {


    EditText bottleChoice;
    TextView showItems, showMoney, showAddAmount;
    BottleDispenser bd = BottleDispenser.getInstance();
    Toast toast;
    SeekBar seekBar;
    int addAmount = 0;
    Context context;
    String file = "output.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        toast = Toast.makeText(MainActivity.this," ",Toast.LENGTH_SHORT);
        bottleChoice = findViewById(R.id.selectBottle);
        bottleChoice.setText("");
        showMoney = findViewById(R.id.moneyAmount);
        showItems = findViewById(R.id.showBottles);
        showItems.setText(bd.listBottles());
        showAddAmount = findViewById(R.id.addAmount);
        //seekbar slideri, jolla voidaan vaihtaa syötettävän rahan määrää
        seekBar= findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //progress välillä 0-100, vaihdetaan välille 0-5
                addAmount = progress/20;
                showAddAmount.setText(addAmount+"$");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void addMoney(View v){
        toast.setText(bd.addMoney(addAmount));
        toast.show();
        showMoney.setText(bd.moneyInDispenser()+"$");
        showItems.setText(bd.listBottles());

    }

    public void buyBottle(View v) {
        if (bottleChoice.getText().toString().matches("")){
            toast.setText("Choose a bottle");
            toast.show();
        }
        else {
            int choice = Integer.parseInt(bottleChoice.getText().toString());
            toast.setText(bd.buyBottle(choice));
            toast.show();
            showMoney.setText(bd.moneyInDispenser() + "$");
            showItems.setText(bd.listBottles());
            bottleChoice.setText("");
        }

    }

    public void returnMoney(View v){
        toast.setText(bd.returnMoney());
        toast.show();
        showMoney.setText(bd.moneyInDispenser()+"$");
        showItems.setText(bd.listBottles());

    }

    public void writeFile(View v){
        try {
            //Path on: data/user/0/com.example.viikko8t1/files
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput(file, Context.MODE_APPEND));
            osw.append(bd.getReceipt());
            osw.close();
            toast.setText("Receipt printed");
            toast.show();
        } catch (IOException e) {
            Log.e("IOExpection", "Error occurred writing file ");
        }
    }
}