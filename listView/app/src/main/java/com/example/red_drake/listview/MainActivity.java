package com.example.red_drake.listview;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class MainActivity extends ListActivity implements AdapterView.OnItemClickListener {
    String lista[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lista = this.getResources().getStringArray(R.array.categoriascontactos);
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                R.layout.activity_main, R.id.tvValor, lista);

        this.getListView().setAdapter(adaptador);
        this.getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, this.getString(R.string.prompt)+" " + lista[position], Toast.LENGTH_SHORT).show();
    }
}
