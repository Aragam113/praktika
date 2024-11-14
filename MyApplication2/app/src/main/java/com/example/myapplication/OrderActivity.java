package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class OrderActivity extends AppCompatActivity {

    TextView orderDate, orderBudget, orderDeadline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        orderDate = findViewById(R.id.orderDate);
        orderBudget = findViewById(R.id.orderBudget);
        orderDeadline = findViewById(R.id.orderDeadline);

        // Пример заполнения информации
        orderDate.setText("Дата заказа: 01.11.2024");
        orderBudget.setText("Бюджет: 100,000 руб.");
        orderDeadline.setText("Срок выполнения: 30.11.2024");
    }
}
