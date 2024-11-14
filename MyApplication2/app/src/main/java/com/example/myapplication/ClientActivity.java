package com.example.myapplication;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ClientActivity extends AppCompatActivity {

    ImageView clientImage;
    TextView clientCompanyName, clientContactPerson, clientPhone, clientEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        clientImage = findViewById(R.id.clientImage);
        clientCompanyName = findViewById(R.id.clientCompanyName);
        clientContactPerson = findViewById(R.id.clientContactPerson);
        clientPhone = findViewById(R.id.clientPhone);
        clientEmail = findViewById(R.id.clientEmail);

        // Пример заполнения информации
        clientImage.setImageResource(R.drawable.img_2);  // Сюда подставьте реальный ресурс
        clientCompanyName.setText("Компания: ABC Ltd.");
        clientContactPerson.setText("Контактное лицо: Иван Иванов");
        clientPhone.setText("Телефон: +123456789");
        clientEmail.setText("Email: ivan@abc.com");
    }
}
