package com.e.webapiactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashBoardActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnAllEMp,btnRegEmp,btnSeaEmp,btnUpdEmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        btnAllEMp=findViewById(R.id.btnAllEMp);
        btnRegEmp=findViewById(R.id.btnRegEmp);
        btnSeaEmp=findViewById(R.id.btnSeaEmp);
        btnUpdEmp=findViewById(R.id.btnUpdEmp);

        btnAllEMp.setOnClickListener(this);
        btnRegEmp.setOnClickListener(this);
        btnSeaEmp.setOnClickListener(this);
        btnUpdEmp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnAllEMp){
            Intent intent = new Intent(DashBoardActivity.this, MainActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.btnRegEmp){
            Intent intent=new Intent(DashBoardActivity.this, RegisterEmployeeActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.btnSeaEmp){
            Intent intent=new Intent(DashBoardActivity.this,SearchEmployeeActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.btnUpdEmp){
            Intent intent=new Intent(DashBoardActivity.this,UpdateEmployeeActivity.class);
            startActivity(intent);
        }

    }
}
