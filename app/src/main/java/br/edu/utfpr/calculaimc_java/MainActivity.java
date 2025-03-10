package br.edu.utfpr.calculaimc_java;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etPeso;
    private EditText etAltura;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        etPeso = findViewById(R.id.etPeso);
        etAltura = findViewById(R.id.etAltura);
        tvResultado = findViewById(R.id.tvResultado);
        Button btnCalcular = findViewById(R.id.btnCalcular);
        Button btnLimpar = findViewById(R.id.btnLimpar);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularIMC();
            }
        });

        btnCalcular.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(MainActivity.this, "Botão de calcular", Toast.LENGTH_SHORT).show();
                return true; // usando true para nao caracterizar o evento de click na sequencia
            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearField();
            }
        });
    }

    public void calcularIMC(){
        String pesoStr = etPeso.getText().toString();
        String alturaStr = etAltura.getText().toString();

        if (alturaStr.isEmpty()) {
            etAltura.setError("Altura deve ser preenchida!!");
            return;
        }

        if(pesoStr.isEmpty()) {
            etPeso.setError("Peso deve ser preenchiso!");
            return;
        }
        double peso = Double.parseDouble(pesoStr);
        double altura = Double.parseDouble(alturaStr);
        double imc = peso / (altura * altura);

        String classificacao;
        if (imc < 18.5) {
            classificacao = "Abaixo do peso";
        } else if (imc < 24.9) {
            classificacao = "Peso normal";
        } else if (imc < 29.9) {
            classificacao = "Sobrepeso";
        } else {
            classificacao = "Obesidade";
        }

        String resultado = String.format("%.2f\n%s", imc, classificacao);
        tvResultado.setText(resultado);
    }

    public void clearField(){
        etPeso.setText("");
        etAltura.setText("");
        tvResultado.setText("0.0");
    }
}