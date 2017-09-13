
package renegociacao.moosegroup.com.br.renegociardividas;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import renegociacao.moosegroup.com.br.renegociardividas.DAO.ClienteDAO;
import renegociacao.moosegroup.com.br.renegociardividas.Model.ClienteModel;

public class TelaCadastroActivity extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    private EditText edtNome, edtCpf, edtEmail, edtSenha, edtTelefone;
    private TextInputLayout tilNome, tilCpf, tilEmail, tilSenha, tilTelefone;
    private ClienteModel cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        ActionBar ab = getSupportActionBar();
        ab.hide();

        openHelper = new ClienteDAO(this);

        tilNome = (TextInputLayout) findViewById(R.id.cadastrar_til_nome);
        tilCpf = (TextInputLayout) findViewById(R.id.cadastrar_til_cpf);
        tilEmail = (TextInputLayout) findViewById(R.id.cadastrar_til_email);
        tilSenha = (TextInputLayout) findViewById(R.id.cadastrar_til_senha);
        tilTelefone = (TextInputLayout) findViewById(R.id.cadastrar_til_telefone);


        edtNome = (EditText) findViewById(R.id.cadastrar_edt_nome);

        edtCpf = (EditText) findViewById(R.id.cadastrar_edt_cpf);
        edtCpf.addTextChangedListener(Mask.insert(Mask.CPF_MASK, edtCpf));

        edtEmail = (EditText) findViewById(R.id.cadastrar_edt_email);

        edtSenha = (EditText) findViewById(R.id.cadastrar_edt_senha);

        edtTelefone = (EditText) findViewById(R.id.cadastrar_edt_telefone);
        edtTelefone.addTextChangedListener(Mask.insert(Mask.CELULAR_MASK, edtTelefone));

        Button btnCadastrar = (Button) findViewById(R.id.cadastrar_btn_cadastrar);
        Button btnCancelar = (Button) findViewById(R.id.cadastrar_btn_cancelar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean cpfValido = Validator.validateCPF(edtCpf.getText().toString());

                if (validateName() && validateCpf()&& cpfValido == true && validateEmail() && validatePassword() && validateTelefone() ) {
                    db = openHelper.getWritableDatabase();

                    String nome = edtNome.getText().toString();
                    String cpf = edtCpf.getText().toString();
                    String email = edtEmail.getText().toString();
                    String senha = edtSenha.getText().toString();
                    String telefone = edtTelefone.getText().toString();

                    inserirCliente(nome, cpf, email, senha, telefone);

                    final AlertDialog.Builder builder = new AlertDialog.Builder(TelaCadastroActivity.this);
                    builder.setTitle("Informação");
                    builder.setMessage("Sua conta foi criada com sucesso");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            finish();
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();


                } else {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(TelaCadastroActivity.this);
                    builder.setTitle("Informação");
                    builder.setMessage("Sua conta não foi criada, favor preencher os campos indicados");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();

                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }


        });

        btnCancelar.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                Intent cancelar = new Intent(TelaCadastroActivity.this, MainActivity.class);
                startActivity(cancelar);
            }
        });

    }


    private void submitForm() {
        if (!validateName()) {
            return;
        }


        if (!validateCpf()) {
            return;
        }

        if (!validateEmail()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }

        if (!validateTelefone()) {
            return;
        }

        Toast.makeText(getApplicationContext(), "Thank You!", Toast.LENGTH_SHORT).show();
    }

    private boolean validateTelefone() {
        if (edtTelefone.getText().toString().trim().isEmpty()) {
            tilTelefone.setError(getString(R.string.err_msg_telefone));
        } else {
            tilTelefone.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateName() {
        if (edtNome.getText().toString().trim().isEmpty()) {
            tilNome.setError(getString(R.string.err_msg_nome));
            requestFocus(edtNome);
            return false;
        } else {
            tilNome.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmail() {
        String email = edtEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            tilEmail.setError(getString(R.string.err_msg_email));
            requestFocus(edtEmail);
            return false;
        } else {
            tilEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (edtSenha.getText().toString().trim().isEmpty()) {
            tilSenha.setError(getString(R.string.err_msg_senha));
            requestFocus(edtSenha);
            return false;
        } else {
            tilSenha.setErrorEnabled(false);
        }

        return true;
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


    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void inserirCliente(String nome, String cpf, String email, String senha, String telefone) {
        ContentValues values = new ContentValues();
        values.put(ClienteDAO.COLUNA_NOME, nome);
        values.put(ClienteDAO.COLUNA_CPF, cpf);
        values.put(ClienteDAO.COLUNA_EMAIL, email);
        values.put(ClienteDAO.COLUNA_SENHA, senha);
        values.put(ClienteDAO.COLUNA_TELEFONE, telefone);
        long id = db.insert(ClienteDAO.TABELA_NOME, null, values);
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


}
