package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProjectActivity extends AppCompatActivity {

    ImageView projectImage;
    TextView projectName, projectBudget, projectDeadline, projectStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        projectImage = findViewById(R.id.projectImage);
        projectName = findViewById(R.id.projectName);
        projectBudget = findViewById(R.id.projectBudget);
        projectDeadline = findViewById(R.id.projectDeadline);
        projectStatus = findViewById(R.id.projectStatus);

        // Пример заполнения информации (здесь может быть передача данных через Intent)
        projectImage.setImageResource(R.drawable.img_1);  // Сюда подставьте реальный ресурс
        projectName.setText("Название проекта: Новый офис");
        projectBudget.setText("Бюджет: 500,000");
        projectDeadline.setText("Срок: 31.12.2024");
        projectStatus.setText("Статус: В процессе");
    }
}
