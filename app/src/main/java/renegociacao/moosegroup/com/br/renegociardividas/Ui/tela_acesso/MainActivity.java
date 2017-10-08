package renegociacao.moosegroup.com.br.renegociardividas.Ui.tela_acesso;

import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import renegociacao.moosegroup.com.br.renegociardividas.Data.Remote.APIUserClient;
import renegociacao.moosegroup.com.br.renegociardividas.Data.Remote.ApiUtils;
import renegociacao.moosegroup.com.br.renegociardividas.Mask;
import renegociacao.moosegroup.com.br.renegociardividas.Model.ClienteModel;
import renegociacao.moosegroup.com.br.renegociardividas.Model.Login;
import renegociacao.moosegroup.com.br.renegociardividas.Model.User;
import renegociacao.moosegroup.com.br.renegociardividas.R;
import renegociacao.moosegroup.com.br.renegociardividas.Ui.cadastrar_cliente.TelaCadastroActivity;
import renegociacao.moosegroup.com.br.renegociardividas.Ui.tela_menu.MenuActivity;
import renegociacao.moosegroup.com.br.renegociardividas.Validator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(ApiUtils.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    APIUserClient userClient = retrofit.create(APIUserClient.class);

    private EditText edtCpf, edtSenha;
    private TextInputLayout tilCpf, tilSenha;
    private APIUserClient mUserClient;
    private Button btnEntrar;
    private TextView txtCadastrar;
    private static String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ActionBar ab = getSupportActionBar();
        ab.hide();

        mUserClient = ApiUtils.getAPIUserClient();
        loadComponents();
        final ClienteModel cliente = new ClienteModel();

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                cliente.setCpf(edtCpf.getText().toString());
                cliente.setSenha(edtSenha.getText().toString());

                if (validateCpf() && validatePassword()) {

                    Login login = new Login(cliente.getCpf(), cliente.getSenha());
                    Call<User> call = userClient.login(login);

                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(MainActivity.this, response.body().getToken(), Toast.LENGTH_SHORT).show();
                                token = response.body().getToken();
                                SharedPreferences sp = getPreferences(MODE_PRIVATE);
                                SharedPreferences.Editor e = sp.edit();
                                e.putString("token", response.body().getToken());
                                e.commit();
                                Intent telaEntrar = new Intent(MainActivity.this, MenuActivity.class);
                                startActivity(telaEntrar);
                            } else {
                                Toast.makeText(MainActivity.this, "Usuarios não conferem", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "ERRO API", Toast.LENGTH_SHORT).show();
                        }
                    });


                }
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

    private void loadComponents() {
        edtCpf = (EditText) findViewById(R.id.main_edt_cpf);
        edtCpf.addTextChangedListener(Mask.insert(Mask.CPF_MASK, edtCpf));
        tilCpf = (TextInputLayout) findViewById(R.id.main_til_cpf);
        edtSenha = (EditText) findViewById(R.id.main_edt_password);
        tilSenha = (TextInputLayout) findViewById(R.id.main_til_password);
        btnEntrar = (Button) findViewById(R.id.main_btn_entrar);
        txtCadastrar = (TextView) findViewById(R.id.main_txt_cadastrar);
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
}

