package renegociacao.moosegroup.com.br.renegociardividas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar ab = getSupportActionBar();
        ab.hide();

        final TextInputLayout tilCpf = (TextInputLayout) findViewById(R.id.main_til_cpf);

        final EditText edtCpf = (EditText) findViewById(R.id.main_edt_cpf);
        edtCpf.addTextChangedListener(Mask.insert(Mask.CPF_MASK, edtCpf));

        Button btnEntrar = (Button) findViewById(R.id.main_btn_entrar);
        TextView txtCadastrar = (TextView) findViewById(R.id.main_txt_cadastrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean cpfValido = Validator.validateCPF(edtCpf.getText().toString().trim());
                if (!cpfValido) {
                    tilCpf.setError("Digite um CPF válido");
                    requestFocus(edtCpf);
                }else{
                    tilCpf.setErrorEnabled(false);
                }

                Intent telaInicial = new Intent(MainActivity.this, TelaInicialActivity.class);
                startActivity(telaInicial);

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


}

