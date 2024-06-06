package com.example.finalairport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalairport.usersData.PassengersInfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class PassengersActivity extends AppCompatActivity {
    DatabaseReference myRef;
    FirebaseUser user;

    EditText dateET;
    EditText[] passengers = new EditText[24];
    Button sendDataBTN;
    Button returnBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passengers);

        init();

        sendDataBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectInfo();
            }
        });

        returnBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PassengersActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void init() {
        user = FirebaseAuth.getInstance().getCurrentUser();

        myRef = FirebaseDatabase.getInstance().getReference()
                .child("Passengers");


        dateET = findViewById(R.id.dateET);

        passengers[0] = findViewById(R.id.zeroHourET);
        passengers[1] = findViewById(R.id.firstHourET);
        passengers[2] = findViewById(R.id.secondHourET);
        passengers[3] = findViewById(R.id.thirdHourET);
        passengers[4] = findViewById(R.id.fourthHourET);
        passengers[5] = findViewById(R.id.fifthHourET);
        passengers[6] = findViewById(R.id.sixthHourET);
        passengers[7] = findViewById(R.id.seventhHourET);
        passengers[8] = findViewById(R.id.eighthHourET);
        passengers[9] = findViewById(R.id.ninthHourET);
        passengers[10] = findViewById(R.id.tenthHourET);
        passengers[11] = findViewById(R.id.eleventhHourET);
        passengers[12] = findViewById(R.id.twelfthHourET);
        passengers[13] = findViewById(R.id.thirteenthHourET);
        passengers[14] = findViewById(R.id.fourteenthHourET);
        passengers[15] = findViewById(R.id.fifteenthHourET);
        passengers[16] = findViewById(R.id.sixteenthHourET);
        passengers[17] = findViewById(R.id.seventeenthHourET);
        passengers[18] = findViewById(R.id.eighteenthHourET);
        passengers[19] = findViewById(R.id.nineteenthHourET);
        passengers[20] = findViewById(R.id.twentiethHourET);
        passengers[21] = findViewById(R.id.twentyFirstHourET);
        passengers[22] = findViewById(R.id.twentySecondHourET);
        passengers[23] = findViewById(R.id.twentyThirdHourET);

        sendDataBTN = findViewById(R.id.sendDataBTN);
        returnBTN = findViewById(R.id.returnBTN);
    }

    private void collectInfo() {
        Map<String, Integer> passengersMap = new HashMap<>();
        PassengersInfo passengersInfo = new PassengersInfo(passengersMap);
        int i = 0;
        for (EditText editText : passengers) {
            if (!editText.getText().toString().isEmpty()) {
                String time;
                if (i < 10) {
                    time = "0" + String.valueOf(i) + ":00";
                } else {
                    time = String.valueOf(i) + ":00";
                }
                i++;
                passengersInfo.addInfo(time, Integer.parseInt(editText.getText().toString()));
            }
        }

        myRef.child(dateET.getText().toString())
                .setValue(passengersInfo.getInfo());

    }
}