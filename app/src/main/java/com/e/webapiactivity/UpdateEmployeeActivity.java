package com.e.webapiactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import API.EmployeeAPI;
import model.Employee;
import model.EmployeeCUD;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateEmployeeActivity extends AppCompatActivity {

    private final static String BASE_URL="http://dummy.restapiexample.com/api/v1/";
    private Button btnSearch1, btnUpdate,btnDelete;
    private EditText etName1, etSalary1, etAge1,etEmpNo;
    Retrofit retrofit;
    EmployeeAPI employeeAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_employee);

        btnSearch1=findViewById(R.id.btnSearch1);
        btnUpdate=findViewById(R.id.btnUpdate);
        btnDelete=findViewById(R.id.btnDelete);
        etName1=findViewById(R.id.etName1);
        etSalary1=findViewById(R.id.etSalary1);
        etAge1=findViewById(R.id.etAge1);
        etEmpNo=findViewById(R.id.etEmpNo);

        btnSearch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEmployee();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteEmployee();
            }
        });
    }
    private void CreateInstance(){
        retrofit =new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        employeeAPI=retrofit.create(EmployeeAPI.class);


    }
    private void loadData(){
        CreateInstance();
        Call<Employee>listCall =employeeAPI.getEmployeeByID(Integer.parseInt(etEmpNo.getText().toString()));
        listCall.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                etName1.setText(response.body().getEmployee_name());
                etSalary1.setText(Float.toString(response.body().getEmployee_salary()));
                etAge1.setText(Integer.toString(response.body().getEmployee_age()));
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Toast.makeText(UpdateEmployeeActivity.this,"Error" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void updateEmployee(){
        CreateInstance();
        EmployeeCUD employee= new EmployeeCUD(
                etName1.getText().toString(),
                Float.parseFloat(etSalary1.getText().toString()),
                Integer.parseInt(etAge1.getText().toString())
        );
        Call<Void> voidCall=employeeAPI.updateEmployee(Integer.parseInt(etEmpNo.getText().toString()),employee);
        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(UpdateEmployeeActivity.this,"Updated",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(UpdateEmployeeActivity.this,"Error" + t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });


    }
    private void deleteEmployee(){
        CreateInstance();
        Call<Void> voidCall=employeeAPI.deleteEmployee(Integer.parseInt(etEmpNo.getText().toString()));
        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(UpdateEmployeeActivity.this,"Successfully Deleted",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(UpdateEmployeeActivity.this,"Error" + t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }
}
