
package renegociacao.moosegroup.com.br.renegociardividas.Ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import renegociacao.moosegroup.com.br.renegociardividas.Data.Remote.APIService;
import renegociacao.moosegroup.com.br.renegociardividas.Data.Remote.ApiUtils;
import renegociacao.moosegroup.com.br.renegociardividas.Mask;
import renegociacao.moosegroup.com.br.renegociardividas.Model.ClienteModel;
import renegociacao.moosegroup.com.br.renegociardividas.R;
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
    private TextView mResponseTv;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        ActionBar ab = getSupportActionBar();
        ab.hide();

        cliente = new ClienteModel();


        Cast();


        mAPIService = ApiUtils.getAPIService();

        mResponseTv = (TextView) findViewById(R.id.tv_response);
        Button btnCadastrar = (Button) findViewById(R.id.cadastrar_btn_cadastrar);
        Button btnCancelar = (Button) findViewById(R.id.cadastrar_btn_cancelar);


        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
                boolean cpfValido = Validator.validateCPF(edtCpf.getText().toString());

                if (!edtTelefone.getText().toString().isEmpty() && validateName() && validateCpf() && cpfValido == true && validateEmail() && validatePassword() && validateTelefone()) {

                    PegarDadosDaTela();


                    String nome = edtNome.getText().toString().trim();
                    String cpf = edtCpf.getText().toString().trim();
                    String email = edtEmail.getText().toString().trim();
                    String senha = edtSenha.getText().toString().trim();
                    String telefone = edtTelefone.getText().toString().trim();

                    sendPost(nome, cpf, email, senha, telefone);


                } else {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(TelaCadastroActivity.this);
                    builder.setTitle("Informação");
                    builder.setMessage("Sua conta não foi criada, todos os campos devem ser preenchidos");
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


    private void PegarDadosDaTela() {
        cliente.setNome(edtNome.getText().toString());
        cliente.setCpf(edtCpf.getText().toString());
        cliente.setSenha(edtSenha.getText().toString());
        cliente.setTelefone(edtTelefone.getText().toString());
        cliente.setEmail(edtEmail.getText().toString());
    }

    private void sendPost(String nome, String cpf, String email, String senha, String telefone) {
        mAPIService.savePost(1, nome, cpf, email, senha, telefone).enqueue(new Callback<POST>() {
            @Override
            public void onResponse(Call<POST> call, Response<POST> response) {
                if (response.isSuccessful()) {
                    showResponse(response.body().toString());
                    Log.i(TAG, "post submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<POST> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
            }
        });

    }


    public void showResponse(String response) {
        if (mResponseTv.getVisibility() == View.GONE) {
            mResponseTv.setVisibility(View.VISIBLE);
        }
        mResponseTv.setText(response);
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


}
