package com.example.finalairport;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button registerBTN;
    Button signBTN;
    ConstraintLayout main;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        registerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegisterWindow();
            }
        });

        signBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSignWindow();
            }
        });

    }

    private void init() {
        main = findViewById(R.id.main);
        registerBTN = findViewById(R.id.registerBTN);
        signBTN = findViewById(R.id.signBTN);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void showSignWindow() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Войти");
        dialog.setMessage("Для авторизации введите email и пароль");

        LayoutInflater inflater = LayoutInflater.from(this);
        View signWindow = inflater.inflate(R.layout.activity_sign, null);
        dialog.setView(signWindow);

        final String[] post = new String[1];
        RadioGroup postRG = signWindow.findViewById(R.id.postRG);
        RadioButton directorRB = signWindow.findViewById(R.id.directorRB);
        RadioButton chiefAccountantRB = signWindow.findViewById(R.id.chiefAccountantRB);
        RadioButton chiefDispatcherRB = signWindow.findViewById(R.id.chiefDispatcherRB);
        RadioButton chiefPassengerRegistrationRB = signWindow.findViewById(R.id.chiefPassengerRegistrationRB);
        EditText passwordET = signWindow.findViewById(R.id.passwordET);

        postRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.directorRB:
                        post[0] = "director";
                        break;
                    case R.id.chiefAccountantRB:
                        post[0] = "chiefAccountant";
                        break;
                    case R.id.chiefDispatcherRB:
                        post[0] = "chiefDispatcher";
                        break;
                    case R.id.chiefPassengerRegistrationRB:
                        post[0] = "chiefPassengerRegistration";
                        break;
                }
            }
        });

        dialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });

        dialog.setPositiveButton("Войти", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                if (TextUtils.isEmpty(post[0])) {
                    Snackbar.make(main, "Укажите вашу должность", Snackbar.LENGTH_SHORT).show();
                    return;
                } else if (passwordET.getText().length() < 5) {
                    Snackbar.make(main, "Введите пароль, который имеет более 5 символов", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                String email = post[0] + "@gmail.com";

                firebaseAuth.signInWithEmailAndPassword(email, passwordET.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Авторизация прошла успешно", Toast.LENGTH_LONG).show();
                                    if (post[0].equals("director")) {
                                        Intent intent = new Intent(MainActivity.this, DirectorMain.class);
                                        startActivity(intent);
                                        finish();
                                    } else if (post[0].equals("chiefAccountant")) {
                                        Intent intent = new Intent(MainActivity.this, AccountantActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else if (post[0].equals("chiefPassengerRegistration")) {
                                        Intent intent = new Intent(MainActivity.this, PassengersActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else if (post[0].equals("chiefDispatcher")) {
                                        Intent intent = new Intent(MainActivity.this, DispatcherActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }

                                }
                            }
                        });


            }
        });

        dialog.show();
    }

    private void showRegisterWindow() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Зарегистрироваться");
        dialog.setMessage("Введите данные для регистрации");
        CardView cardView = findViewById(R.id.register);

        LayoutInflater inflater = LayoutInflater.from(this);
        View registerWindow = inflater.inflate(R.layout.activity_register, null);
        dialog.setView(registerWindow);

        RadioGroup postRG = registerWindow.findViewById(R.id.postRG);

        EditText nameET = registerWindow.findViewById(R.id.nameET);
        EditText passwordET = registerWindow.findViewById(R.id.passwordET);

        final String[] post = new String[1];

        postRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.directorRB:
                        post[0] = "director";
                        break;
                    case R.id.chiefAccountantRB:
                        post[0] = "chiefAccountant";
                        break;
                    case R.id.chiefDispatcherRB:
                        post[0] = "chiefDispatcher";
                        break;
                    case R.id.chiefPassengerRegistrationRB:
                        post[0] = "chiefPassengerRegistration";
                        break;
                }
            }
        });

        dialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });

        dialog.setPositiveButton("Добавить", null);

        final AlertDialog alertDialog = dialog.create();
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(post[0])) {
                    Snackbar.make(findViewById(android.R.id.content), "Введите вашу должность", Snackbar.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(nameET.getText().toString())) {
                    Snackbar.make(findViewById(android.R.id.content), "Введите ваше имя", Snackbar.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(passwordET.getText().toString())) {
                    Snackbar.make(findViewById(android.R.id.content), "Введите ваш пароль", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                String email = post[0] + "@gmail.com";

                firebaseAuth.createUserWithEmailAndPassword(email, passwordET.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                alertDialog.dismiss();
                                if (task.isSuccessful()) {
                                    Snackbar.make(main,"Пользователь успешно зарегистрирован", Snackbar.LENGTH_SHORT ).show();
                                } else {
                                    Snackbar.make(main,"Ошибка регистрации, данная должность уже зарегистрирована", Snackbar.LENGTH_SHORT ).show();
                                }
                            }
                        });

            }

        });

    }

}