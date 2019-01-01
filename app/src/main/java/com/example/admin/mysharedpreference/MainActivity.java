package com.example.admin.mysharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_userName,et_userPassword;
    private Button btn_Save,btn_Display;
    private TextView tv_Display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeObj();
        btn_Save.setOnClickListener(this);
        btn_Display.setOnClickListener(this);
    }

    private void makeObj() {
        et_userName = (EditText)findViewById(R.id.et_userName);
        et_userPassword = (EditText)findViewById(R.id.et_userPassword);
        btn_Save = (Button)findViewById(R.id.btn_Save);
        btn_Display = (Button)findViewById(R.id.btn_Display);
        tv_Display = (TextView)findViewById(R.id.tv_Display);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_Save){
            String name = et_userName.getText().toString();
            String password = et_userPassword.getText().toString();

            if (name.equals("") && password.equals("")){
                Toast.makeText(this, "Please Enter Some Data", Toast.LENGTH_SHORT).show();
            }
            else {
                //Writing Data into SharedPreference
                SharedPreferences sharedPreferences = getSharedPreferences("userDetails",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("usernameKey",name);
                editor.putString("passwordKey",password);
                editor.commit();
                et_userName.setText("");
                et_userPassword.setText("");
                Toast.makeText(this, "Data is Stored Successfully!!!", Toast.LENGTH_SHORT).show();
            }

        }
        else if (v.getId() == R.id.btn_Display){
            // to read data from SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("userDetails",Context.MODE_PRIVATE);
            if (sharedPreferences.contains("usernameKey") && sharedPreferences.contains("passwordKey")){
                String name = sharedPreferences.getString("usernameKey","Data not found");
                String password = sharedPreferences.getString("passwordKey","Data not found");
                tv_Display.setText(name+"\n"+password);
            }

        }
    }
}
