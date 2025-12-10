package com.example.login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private UserDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper = new UserDatabaseHelper(this);

        EditText edtUser = findViewById(R.id.edtUsername);
        EditText edtPass = findViewById(R.id.edtPassword);
        EditText edtEmail = findViewById(R.id.edtEmail);   // <--- THÊM VÀO
        Button btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v -> {

            String username = edtUser.getText().toString().trim();
            String password = edtPass.getText().toString().trim();
            String email = edtEmail.getText().toString().trim();  // <--- LẤY EMAIL

            if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                Toast.makeText(RegisterActivity.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean success = dbHelper.registerUser(username, password, email); // <--- GỌI HÀM MỚI

            if (success) {
                Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(RegisterActivity.this, "Username hoặc Email đã tồn tại", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
