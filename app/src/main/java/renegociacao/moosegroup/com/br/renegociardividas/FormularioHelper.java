package renegociacao.moosegroup.com.br.renegociardividas;

import android.widget.EditText;

import renegociacao.moosegroup.com.br.renegociardividas.Model.ClienteModel;

/**
 * Created by re034850 on 13/09/2017.
 */

public class FormularioHelper {
    private final EditText campoNome;
    private final EditText campoCpf;
    private final EditText campoEmail;
    private final EditText campoSenha;
    private final EditText campoTelefone;

    private ClienteModel cliente;

    public FormularioHelper(TelaCadastroActivity activity){
        campoNome = (EditText) activity.findViewById(R.id.cadastrar_edt_nome);
        campoCpf = (EditText) activity.findViewById(R.id.cadastrar_edt_cpf);
        campoEmail = (EditText) activity.findViewById(R.id.cadastrar_edt_email);
        campoSenha = (EditText) activity.findViewById(R.id.cadastrar_edt_senha);
        campoTelefone = (EditText) activity.findViewById(R.id.cadastrar_edt_telefone);
        cliente = new ClienteModel();

    }

    public ClienteModel pegaCliente(){
        cliente.setNome(campoNome.getText().toString());
        cliente.setCpf(campoCpf.getText().toString());
        cliente.setEmail(campoEmail.getText().toString());
        cliente.setSenha(campoSenha.getText().toString());
        cliente.setTelefone(campoTelefone.getText().toString());
        return cliente;
    }


}
