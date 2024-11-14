package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class EmployeeActivity extends AppCompatActivity {

    ImageView employeeImage;
    TextView employeeName, employeePosition, employeeSalary, employeeExperience, employeeContractInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        employeeImage = findViewById(R.id.employeeImage);
        employeeName = findViewById(R.id.employeeName);
        employeePosition = findViewById(R.id.employeePosition);
        employeeSalary = findViewById(R.id.employeeSalary);
        employeeExperience = findViewById(R.id.employeeExperience);
        employeeContractInfo = findViewById(R.id.employeeContractInfo);

        // Пример заполнения информации
        employeeImage.setImageResource(R.drawable.img);  // Сюда подставьте реальный ресурс
        employeeName.setText("ФИО: Иван Иванов");
        employeePosition.setText("Позиция: Менеджер");
        employeeSalary.setText("Зарплата: 50,000 руб.");
        employeeExperience.setText("Опыт: 5 лет");
        employeeContractInfo.setText("Информация о контракте: Постоянный контракт");
    }
}
