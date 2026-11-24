package com.example.mid_exam_olatanirah;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnLogin;

    private long lastBackPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setEnabled(false);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                validateInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };

        etEmail.addTextChangedListener(textWatcher);
        etPassword.addTextChangedListener(textWatcher);

        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this, ModeActivity.class);
            startActivity(intent);
        });
    }

    private void validateInputs() {

        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString();

        boolean validEmail =
                Patterns.EMAIL_ADDRESS.matcher(email).matches();

        boolean validPassword = password.length() >= 6;

        btnLogin.setEnabled(validEmail && validPassword);
    }

    @Override
    public void onBackPressed() {

        long currentTime = System.currentTimeMillis();

        if (currentTime - lastBackPressedTime < 2000) {
            finishAffinity();
        } else {
            lastBackPressedTime = currentTime;

            Snackbar.make(
                    findViewById(android.R.id.content),
                    "Press back again to exit",
                    Snackbar.LENGTH_SHORT
            ).show();
        }
    }
}
