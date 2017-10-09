
package renegociacao.moosegroup.com.br.renegociardividas.Ui.cadastrar_cliente;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import renegociacao.moosegroup.com.br.renegociardividas.Data.Remote.APIService;
import renegociacao.moosegroup.com.br.renegociardividas.Data.Remote.ApiUtils;
import renegociacao.moosegroup.com.br.renegociardividas.Mask;
import renegociacao.moosegroup.com.br.renegociardividas.Model.ClienteModel;
import renegociacao.moosegroup.com.br.renegociardividas.R;
import renegociacao.moosegroup.com.br.renegociardividas.Ui.dividas.DividasActivity;
import renegociacao.moosegroup.com.br.renegociardividas.Ui.tela_acesso.MainActivity;
import renegociacao.moosegroup.com.br.renegociardividas.Validator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

public class TelaCadastroActivity extends AppCompatActivity {

    private EditText edtNome, edtCpf, edtEmail, edtSenha, edtTelefone;
    private TextInputLayout tilNome, tilCpf, tilEmail, tilSenha, tilTelefone;
    private ClienteModel cliente;
    private APIService mAPIService;
    private String nome, cpf, senha, telefone, email;
    private static final String TAG = TelaCadastroActivity.class.getSimpleName() ;
    private Button btnCadastrar, btnCancelar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cliente = new ClienteModel();
        Cast();
        mAPIService = ApiUtils.getAPIService();

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
                boolean cpfValido = Validator.validateCPF(edtCpf.getText().toString());

                if (cadastroBoolean(cpfValido)) {
                    PegarDadosDaTela();
                    loadComponents();
                    sendPost(nome, cpf, email, senha, telefone);
                    DialogPositivo();


                } else {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(TelaCadastroActivity.this);
                    builder.setTitle("Informação");
                    builder.setMessage("Sua conta não foi criada, preencha o(s) campo(s) informado(s)");
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

    private void loadComponents() {
        nome = edtNome.getText().toString().trim();
        cpf = edtCpf.getText().toString().trim();
        email = edtEmail.getText().toString().trim();
        senha = edtSenha.getText().toString().trim();
        telefone = edtTelefone.getText().toString().trim();
    }

    private boolean cadastroBoolean(boolean cpfValido) {
        return !edtTelefone.getText().toString().isEmpty() && validateName() && validateCpf() && cpfValido == true && validateEmail() && validatePassword() && validateTelefone();
    }

    private void PegarDadosDaTela() {
        cliente.setNome(edtNome.getText().toString());
        cliente.setCpf(edtCpf.getText().toString());
        cliente.setSenha(edtSenha.getText().toString());
        cliente.setTelefone(edtTelefone.getText().toString());
        cliente.setEmail(edtEmail.getText().toString());
    }

    private void sendPost(String nome, String cpf, String email, String senha, String telefone) {
        mAPIService.savePost(nome, cpf, email, senha, telefone).enqueue(new Callback<POST>() {
            @Override
            public void onResponse(Call<POST> call, Response<POST> response) {
                if (response.isSuccessful()) {

                    Log.i(TAG, "post submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<POST> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
            }
        });

    }

    private void DialogPositivo() {
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
    }

    private void Cast() {
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
        btnCadastrar = (Button) findViewById(R.id.cadastrar_btn_cadastrar);
        btnCancelar = (Button) findViewById(R.id.cadastrar_btn_cancelar);
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


    }

    private boolean validateTelefone() {
        if (edtTelefone.getText().toString().isEmpty()) {
            tilTelefone.setError(getString(R.string.err_msg_telefone));
            requestFocus(edtTelefone);
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

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
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
