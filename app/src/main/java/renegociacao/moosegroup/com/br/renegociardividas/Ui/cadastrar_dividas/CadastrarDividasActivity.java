package renegociacao.moosegroup.com.br.renegociardividas.Ui.cadastrar_dividas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import renegociacao.moosegroup.com.br.renegociardividas.R;

public class CadastrarDividasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_dividas);

        getSupportActionBar().hide();
    }
}
