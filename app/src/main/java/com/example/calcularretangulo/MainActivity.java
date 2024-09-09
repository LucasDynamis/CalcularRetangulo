package com.example.calcularretangulo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText editTextLargura, editTextAltura;
    private Button buttonCalcular;
    private TextView textViewResultado;
    private CheckBox checkBoxArea, checkBoxPerimetro, checkBoxDiagonal;
    private RadioButton radioButtonMetros, radioButtonCentimetros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextLargura = findViewById(R.id.editTextLargura);
        editTextAltura = findViewById(R.id.editTextAltura);
        buttonCalcular = findViewById(R.id.buttonCalcular);
        textViewResultado = findViewById(R.id.textViewResultado);
        checkBoxArea = findViewById(R.id.checkBoxArea);
        checkBoxPerimetro = findViewById(R.id.checkBoxPerimetro);
        checkBoxDiagonal = findViewById(R.id.checkBoxDiagonal);
        radioButtonMetros = findViewById(R.id.radioButtonMetros);
        radioButtonCentimetros = findViewById(R.id.radioButtonCentimetros);

        textViewResultado.setVisibility(View.INVISIBLE); // Inicialmente invisível

        buttonCalcular.setOnClickListener(v -> calcular());
    }

    private void calcular() {
        if (editTextLargura.getText().toString().isEmpty() || editTextAltura.getText().toString().isEmpty()) {
            Toast.makeText(this, "Por favor, insira todos os valores necessários.", Toast.LENGTH_SHORT).show();
            return;
        }

        double largura = Double.parseDouble(editTextLargura.getText().toString());
        double altura = Double.parseDouble(editTextAltura.getText().toString());
        StringBuilder resultado = new StringBuilder();

        if (checkBoxArea.isChecked()) {
            double area = Retangulo.calcularArea(largura, altura);
            area = converterParaUnidadeSelecionada(area);
            resultado.append("Área: ").append(String.format("%.2f", area)).append(" ").append(obterUnidadeSelecionada()).append("²\n");
        }
        if (checkBoxPerimetro.isChecked()) {
            double perimetro = Retangulo.calcularPerimetro(largura, altura);
            perimetro = converterParaUnidadeSelecionada(perimetro);
            resultado.append("Perímetro: ").append(String.format("%.2f", perimetro)).append(" ").append(obterUnidadeSelecionada()).append("\n");
        }
        if (checkBoxDiagonal.isChecked()) {
            double diagonal = Retangulo.calcularDiagonal(largura, altura);
            diagonal = converterParaUnidadeSelecionada(diagonal);
            resultado.append("Diagonal: ").append(String.format("%.2f", diagonal)).append(" ").append(obterUnidadeSelecionada()).append("\n");
        }

        textViewResultado.setText(resultado.toString());
        textViewResultado.setVisibility(View.VISIBLE); // Torna visível após calcular
    }

    private double converterParaUnidadeSelecionada(double valor) {
        if (radioButtonCentimetros.isChecked()) {
            return valor * 100; // Convertendo de metros para centímetros
        }
        return valor; // Mantendo em metros
    }

    private String obterUnidadeSelecionada() {
        return radioButtonCentimetros.isChecked() ? "cm" : "m";
    }
}