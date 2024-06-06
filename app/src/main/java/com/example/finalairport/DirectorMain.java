package com.example.finalairport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class DirectorMain extends AppCompatActivity {

    EditText dateET;
    RadioGroup radioGroupRG;
    Button showBTN;
    Button returnBTN;
    Class<?> cls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_director_main);

        init();

        showBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DirectorMain.this, cls);
                intent.putExtra("date", dateET.getText().toString());

                startActivity(intent);
                finish();
            }
        });

        returnBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DirectorMain.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    public void init(){
        ConstraintLayout main = findViewById(R.id.main);
        Snackbar.make(main, "Добро пожаловать, господин директор", Snackbar.LENGTH_SHORT).show();

        dateET = findViewById(R.id.dateET);
        radioGroupRG = findViewById(R.id.radioGroupRG);

        showBTN = findViewById(R.id.showBTN);
        returnBTN = findViewById(R.id.returnBTN);

        radioGroupRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.accountantRB:
                        cls = AccountantReport.class;
                        break;
                    case R.id.dispatcherRB:
                        cls = DispatcherReport.class;
                        break;
                    case R.id.passengerRB:
                        cls = PassengersReport.class;
                        break;
                }
            }
        });



    }
}