package com.example.todoapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        LinearLayout linearLayout = findViewById(R.id.main);

        Button button = findViewById(R.id.btn_add);
        button.setOnClickListener(click -> {
            Dialog dialog = new Dialog(this);
            dialog.setTitle("Adicionar item");

            LinearLayout view = new LinearLayout(this);
            view.setOrientation(LinearLayout.VERTICAL);
            EditText editText = new EditText(this);
            editText.setHint("Coloque o nome da tarefa");
            editText.setOnClickListener(v -> {
                // Open DatePickerDialog
                openDatePickerDialog(editText);
            });

            EditText date = new EditText(this);
            date.setHint("Clique para adicionar uma data");
            date.setFocusable(false); // To prevent manual editing
            date.setOnClickListener(v -> {
                // Open DatePickerDialog
                openDatePickerDialog(date);
            });

            Button btnAdicionar = new Button(this);
            btnAdicionar.setText("Adicionar tarefa");
            btnAdicionar.setOnClickListener(click2 -> {
                dialog.cancel();
            });

            view.addView(editText);
            view.addView(date);
            view.addView(btnAdicionar);
            dialog.setContentView(view);

            dialog.show();
        });

    }

    private void openDatePickerDialog(EditText editText) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year1, monthOfYear, dayOfMonth1) -> {
            // Set selected date to EditText
            editText.setText(String.format(Locale.getDefault(), "%d-%02d-%02d", year1, monthOfYear + 1, dayOfMonth1));
        }, year, month, dayOfMonth);

        datePickerDialog.show();
    }
}