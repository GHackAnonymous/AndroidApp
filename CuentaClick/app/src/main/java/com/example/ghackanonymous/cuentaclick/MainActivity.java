package com.example.ghackanonymous.cuentaclick;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button boton;
   //RadioGroup grupo;
    RadioButton mas5;
    RadioButton mas1;
    TextView tvContador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton = (Button) this.findViewById(R.id.button);
        mas5 = (RadioButton) this.findViewById(R.id.mas5);
        mas1 = (RadioButton) this.findViewById(R.id.mas1);
        tvContador = (TextView) this.findViewById(R.id.lTexto);

        boton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(mas1.isSelected())
            tvContador.setText(String.valueOf(Integer.parseInt(tvContador.getText().toString()) + 1));
        else if(mas5.isSelected())
            tvContador.setText(String.valueOf(Integer.parseInt(tvContador.getText().toString()) + 5));
    }
}
