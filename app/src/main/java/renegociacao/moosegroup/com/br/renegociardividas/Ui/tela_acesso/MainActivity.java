package renegociacao.moosegroup.com.br.renegociardividas.Ui.tela_acesso;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import renegociacao.moosegroup.com.br.renegociardividas.Mask;
import renegociacao.moosegroup.com.br.renegociardividas.Model.ClienteModel;
import renegociacao.moosegroup.com.br.renegociardividas.R;
import renegociacao.moosegroup.com.br.renegociardividas.Ui.tela_menu.MenuActivity;
import renegociacao.moosegroup.com.br.renegociardividas.Ui.cadastrar_cliente.TelaCadastroActivity;
import renegociacao.moosegroup.com.br.renegociardividas.Validator;


public class MainActivity extends AppCompatActivity {

    SQLiteOpenHelper dbHelper;
    SQLiteDatabase db;
    Cursor cursor;
    private EditText edtCpf;
    private  TextInputLayout tilCpf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar ab = getSupportActionBar();
        ab.hide();

        edtCpf = (EditText) findViewById(R.id.main_edt_cpf);
        edtCpf.addTextChangedListener(Mask.insert(Mask.CPF_MASK, edtCpf));

        final EditText edtSenha = (EditText) findViewById(R.id.main_edt_password);
        tilCpf = (TextInputLayout) findViewById(R.id.main_til_cpf);

        Button btnEntrar = (Button) findViewById(R.id.main_btn_entrar);
        TextView txtCadastrar = (TextView) findViewById(R.id.main_txt_cadastrar);


        final ClienteModel cliente = new ClienteModel();

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean cpfValido = Validator.validateCPF(edtCpf.getText().toString().trim());
                String cpf = edtCpf.getText().toString();
                if (!cpfValido || cpf.isEmpty()) {
                    tilCpf.setError(getString(R.string.err_msg_cpf));
                    requestFocus(edtCpf);
                } else {
                    tilCpf.setErrorEnabled(false);
                }


                
                cliente.setCpf(edtCpf.getText().toString());
                cliente.setSenha(edtSenha.getText().toString());


                    Intent telaEntrar = new Intent(MainActivity.this, MenuActivity.class);
                    startActivity(telaEntrar);



            }
        });

        txtCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent telaCadastrar = new Intent(MainActivity.this, TelaCadastroActivity.class);
                startActivity(telaCadastrar);
            }
        });
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private boolean validateCpf() {
        boolean cpfValido = Validator.validateCPF(edtCpf.getText().toString().trim());
        String cpf = edtCpf.getText().toString();
        if (!cpfValido || cpf.isEmpty()) {
            tilCpf.setError(getString(R.string.err_msg_cpf));
            requestFocus(edtCpf);
        } else {
            tilCpf.setErrorEnabled(false);
        }
        return true;
    }


}

