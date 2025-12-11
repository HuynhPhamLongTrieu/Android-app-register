package com.example.login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.model.User;

public class RegisterActivity extends AppCompatActivity {

    private SQLiteConnector db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new SQLiteConnector(this);

        EditText edtUser = findViewById(R.id.edtUsername);
        EditText edtEmail = findViewById(R.id.edtEmail);
        EditText edtPass = findViewById(R.id.edtPassword);
        Button btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v -> {

            String username = edtUser.getText().toString().trim();
            String email = edtEmail.getText().toString().trim();
            String password = edtPass.getText().toString().trim();

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            // Check email tồn tại
            if (db.checkUser(email)) {
                Toast.makeText(this, "Email đã tồn tại!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Hash password
            String hashedPassword = HashUtil.sha256(password);

            // Tạo user mới
            User user = new User();
            user.setName(username);
            user.setEmail(email);
            user.setPassword(hashedPassword);

            db.addUser(user);

            Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
