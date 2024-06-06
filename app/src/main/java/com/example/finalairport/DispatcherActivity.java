package com.example.finalairport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.finalairport.usersData.DispatcherInfo;
import com.example.finalairport.usersData.PassengersInfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class DispatcherActivity extends AppCompatActivity {

    DatabaseReference myRef;
    FirebaseUser user;

    EditText dateET;
    EditText[] planes = new EditText[24];
    Button sendDataBTN;
    Button returnBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatcher);

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
                Intent intent = new Intent(DispatcherActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void init() {
        user = FirebaseAuth.getInstance().getCurrentUser();

        myRef = FirebaseDatabase.getInstance().getReference()
                .child("Dispatcher");

        dateET = findViewById(R.id.dateET);

        planes[0] = findViewById(R.id.zeroHourET);
        planes[1] = findViewById(R.id.firstHourET);
        planes[2] = findViewById(R.id.secondHourET);
        planes[3] = findViewById(R.id.thirdHourET);
        planes[4] = findViewById(R.id.fourthHourET);
        planes[5] = findViewById(R.id.fifthHourET);
        planes[6] = findViewById(R.id.sixthHourET);
        planes[7] = findViewById(R.id.seventhHourET);
        planes[8] = findViewById(R.id.eighthHourET);
        planes[9] = findViewById(R.id.ninthHourET);
        planes[10] = findViewById(R.id.tenthHourET);
        planes[11] = findViewById(R.id.eleventhHourET);
        planes[12] = findViewById(R.id.twelfthHourET);
        planes[13] = findViewById(R.id.thirteenthHourET);
        planes[14] = findViewById(R.id.fourteenthHourET);
        planes[15] = findViewById(R.id.fifteenthHourET);
        planes[16] = findViewById(R.id.sixteenthHourET);
        planes[17] = findViewById(R.id.seventeenthHourET);
        planes[18] = findViewById(R.id.eighteenthHourET);
        planes[19] = findViewById(R.id.nineteenthHourET);
        planes[20] = findViewById(R.id.twentiethHourET);
        planes[21] = findViewById(R.id.twentyFirstHourET);
        planes[22] = findViewById(R.id.twentySecondHourET);
        planes[23] = findViewById(R.id.twentyThirdHourET);

        sendDataBTN = findViewById(R.id.sendDataBTN);
        returnBTN = findViewById(R.id.returnBTN);
    }

    private void collectInfo() {
        Map<String, Integer> dispatcherMap = new HashMap<>();
        DispatcherInfo dispatcherInfo = new DispatcherInfo(dispatcherMap);
        int i = 0;
        for (EditText editText : planes) {
            if (!editText.getText().toString().isEmpty()) {
                String time;
                if (i < 10) {
                    time = "0" + String.valueOf(i) + ":00";
                } else {
                    time = String.valueOf(i) + ":00";
                }
                i++;
                dispatcherInfo.addInfo(time, Integer.parseInt(editText.getText().toString()));
            }
        }

        myRef.child(dateET.getText().toString())
                .setValue(dispatcherInfo.getInfo());
    }
}