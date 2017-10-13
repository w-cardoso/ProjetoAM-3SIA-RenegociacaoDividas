package renegociacao.moosegroup.com.br.renegociardividas.Ui.cadastrar_dividas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import renegociacao.moosegroup.com.br.renegociardividas.Dao.DbHelper;
import renegociacao.moosegroup.com.br.renegociardividas.Dao.DividaDao;
import renegociacao.moosegroup.com.br.renegociardividas.R;
import renegociacao.moosegroup.com.br.renegociardividas.Ui.dividas.DividasActivity;


public class CadastrarDividasActivity extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    private TextView txtTitulo, txtDescricao, txtValor, txtEmpresa;
    private Button btnCadastrar, btnCancelar;
    private int user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_dividas);

        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        openHelper = new DbHelper(this);
        loadComponents();

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db = openHelper.getWritableDatabase();


                SharedPreferences sp = getSharedPreferences("sp", MODE_PRIVATE);
                user_id = sp.getInt("user_id", 0);
                String titulo = txtTitulo.getText().toString();
                String descricao = txtDescricao.getText().toString();
                String valor = txtValor.getText().toString();
                String empresa = txtEmpresa.getText().toString();

                DividaDao dao = new DividaDao(getBaseContext());
                boolean sucesso = dao.salvar(user_id, titulo, descricao, valor, empresa);
                if (sucesso) {
                    txtTitulo.setText("");
                    txtDescricao.setText("");
                    txtValor.setText("");
                    txtEmpresa.setText("");
                    Snackbar.make(view, "Divida salva com  Sucesso!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    Snackbar.make(view, "Erro ao salvar, consulte os logs!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }


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
