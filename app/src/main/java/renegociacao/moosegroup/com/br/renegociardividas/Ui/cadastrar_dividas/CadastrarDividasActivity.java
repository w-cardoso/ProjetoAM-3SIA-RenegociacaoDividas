package renegociacao.moosegroup.com.br.renegociardividas.Ui.cadastrar_dividas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import renegociacao.moosegroup.com.br.renegociardividas.R;
import renegociacao.moosegroup.com.br.renegociardividas.Ui.dividas.DividasActivity;


public class CadastrarDividasActivity extends AppCompatActivity {

    private TextView txtTitulo, txtDescricao, txtValor, txtEmpresa;
    private Button btnCadastrar, btnCancelar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_dividas);

        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadComponents();

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CadastrarDividasActivity.this, DividasActivity.class);
                startActivity(intent);
            }
        });

    }

    private void loadComponents() {
        txtTitulo = (TextView) findViewById(R.id.cadastrarDividas_edt_titulo);
        txtDescricao = (TextView) findViewById(R.id.cadastrarDividas_edt_descricao);
        txtValor = (TextView) findViewById(R.id.cadastrarDividas_edt_valor);
        txtEmpresa = (TextView) findViewById(R.id.cadastrarDividas_edt_empresa);
        btnCadastrar = (Button) findViewById(R.id.cadastrarDividas_btn_cadastrar);
        btnCancelar = (Button) findViewById(R.id.cadastrarDividas_btn_cancelar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            Intent voltar = new Intent(this, DividasActivity.class);
            startActivity(voltar);
        }

        return super.onOptionsItemSelected(item);
    }
}
