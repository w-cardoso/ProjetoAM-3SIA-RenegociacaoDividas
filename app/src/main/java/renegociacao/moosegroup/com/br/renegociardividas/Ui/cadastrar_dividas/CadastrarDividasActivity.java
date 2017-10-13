package renegociacao.moosegroup.com.br.renegociardividas.Ui.cadastrar_dividas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import renegociacao.moosegroup.com.br.renegociardividas.Dao.DbHelper;
import renegociacao.moosegroup.com.br.renegociardividas.Dao.DividaDao;
import renegociacao.moosegroup.com.br.renegociardividas.MascaraMonetaria;
import renegociacao.moosegroup.com.br.renegociardividas.R;
import renegociacao.moosegroup.com.br.renegociardividas.Ui.dividas.DividasActivity;


public class CadastrarDividasActivity extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    private EditText edtTitulo, edtDescricao, edtValor, edtEmpresa;
    private TextInputLayout tilTitulo, tilDescricao, tilValor, tilEmpresa;
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
        edtValor.addTextChangedListener(new MascaraMonetaria(edtValor));

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                submitForm();

                if (isaBoolean()) {
                    String str = edtValor.getText().toString();
                    db = openHelper.getWritableDatabase();


                    SharedPreferences sp = getSharedPreferences("sp", MODE_PRIVATE);
                    user_id = sp.getInt("user_id", 0);
                    String titulo = edtTitulo.getText().toString();
                    String descricao = edtDescricao.getText().toString();
                    double valor = stringMonetarioToDouble(str);
                    String empresa = edtEmpresa.getText().toString();

                    DividaDao dao = new DividaDao(getBaseContext());
                    boolean sucesso = dao.salvar(user_id, titulo, descricao, valor, empresa);
                    if (sucesso) {
                        edtTitulo.setText("");
                        edtDescricao.setText("");
                        edtValor.setText("");
                        edtEmpresa.setText("");
                        Snackbar.make(view, "Divida salva com  Sucesso!", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    } else {
                        Snackbar.make(view, "Erro ao salvar, consulte os logs!", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
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

    private boolean isaBoolean() {
        return validateTitulo() && validateDescricao() && validateValor() && validateEmpresa();
    }


    private void loadComponents() {
        edtTitulo = (EditText) findViewById(R.id.cadastrarDividas_edt_titulo);
        edtDescricao = (EditText) findViewById(R.id.cadastrarDividas_edt_descricao);
        edtValor = (EditText) findViewById(R.id.cadastrarDividas_edt_valor);
        edtEmpresa = (EditText) findViewById(R.id.cadastrarDividas_edt_empresa);

        tilTitulo = (TextInputLayout) findViewById(R.id.cadastrarDividas_til_titulo);
        tilDescricao = (TextInputLayout) findViewById(R.id.cadastrarDividas_til_descricao);
        tilValor = (TextInputLayout) findViewById(R.id.cadastrarDividas_til_valor);
        tilEmpresa = (TextInputLayout) findViewById(R.id.cadastrarDividas_til_empresa);

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

    private void submitForm() {
        if (!validateTitulo()) {
            return;
        }

        if (!validateDescricao()) {
            return;
        }

        if (!validateValor()) {
            return;
        }

        if (!validateEmpresa()) {
            return;
        }


    }

    private boolean validateTitulo() {
        if (edtTitulo.getText().toString().isEmpty()) {
            tilTitulo.setError("O campo está vazio ");
            requestFocus(edtTitulo);
            return false;

        } else {
            tilTitulo.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateDescricao() {
        if (edtDescricao.getText().toString().isEmpty()) {
            tilDescricao.setError("O campo está vazio ");
            requestFocus(edtDescricao);
            return false;

        } else {
            tilDescricao.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateValor() {
        if (edtValor.getText().toString().isEmpty()) {
            tilValor.setError("O campo está vazio ");
            requestFocus(edtValor);
            return false;

        } else {
            tilValor.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validateEmpresa() {
        if (edtEmpresa.getText().toString().isEmpty()) {
            tilEmpresa.setError("O campo está vazio ");
            requestFocus(edtEmpresa);
            return false;

        } else {
            tilEmpresa.setErrorEnabled(false);
            return true;
        }

    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private Double stringMonetarioToDouble(String str) {
        double retorno = 0;
        try {
            boolean hasMask = ((str.indexOf("R$") > -1 || str.indexOf("$") > -1) && (str
                    .indexOf(".") > -1 || str.indexOf(",") > -1));
            // Verificamos se existe máscara
            if (hasMask) {
                // Retiramos a máscara.
                //str = str.replaceAll("[R$]", "").replaceAll("\\,\\w+", ".").replaceAll("\\.\\w+", "");

                str = str.replaceAll("R\\$", "").replaceAll("\\.", "").replaceAll(",", ".");
            }
            // Transformamos o número que está escrito no EditText em
            // double.
            retorno = Double.parseDouble(str);

        } catch (NumberFormatException e) {

            //TRATAR EXCEÇÃO

        }

        return retorno;

    }
}