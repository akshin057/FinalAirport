package com.example.finalairport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.finalairport.usersData.DispatcherInfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AccountantActivity extends AppCompatActivity {

    DatabaseReference myRef;
    FirebaseUser user;
    EditText dateET;
    EditText[] incomes = new EditText[3];
    Button sendDataBTN;
    Button returnBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountant);

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
                Intent intent = new Intent(AccountantActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private void collectInfo() {
        Map<String, Integer> accounterMap = new HashMap<>();
        DispatcherInfo accounterInfo = new DispatcherInfo(accounterMap);

        Integer integer = Integer.parseInt(incomes[0].getText().toString()) + Integer.parseInt(incomes[1].getText().toString())
                + Integer.parseInt(incomes[2].getText().toString());

        String first = "Выручка от диспетчерской";
        accounterInfo.addInfo(first, Integer.parseInt(incomes[0].getText().toString()));

        String second = "Выручка от арендаторов";
        accounterInfo.addInfo(second, Integer.parseInt(incomes[1].getText().toString()));

        String third = "Выручка от сервисов";
        accounterInfo.addInfo(third, Integer.parseInt(incomes[2].getText().toString()));

        String fourth = "Общая выручка";
        accounterInfo.addInfo(fourth, integer);

        myRef.child(dateET.getText().toString())
                .setValue(accounterInfo.getInfo());

    }

    private void init() {
        user = FirebaseAuth.getInstance().getCurrentUser();

        myRef = FirebaseDatabase.getInstance().getReference()
                .child("Accounter");

        dateET = findViewById(R.id.dateET);

        incomes[0] = findViewById(R.id.dispatcherIncomeET);
        incomes[1] = findViewById(R.id.rentIncomeET);
        incomes[2] = findViewById(R.id.planesIncomeET);

        sendDataBTN = findViewById(R.id.sendDataBTN);
        returnBTN = findViewById(R.id.returnBTN);
    }
}

