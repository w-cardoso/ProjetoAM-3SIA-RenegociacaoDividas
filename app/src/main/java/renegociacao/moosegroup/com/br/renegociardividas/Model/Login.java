package renegociacao.moosegroup.com.br.renegociardividas.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by re034850 on 29/09/2017.
 */

public class Login {
    @SerializedName("cpf")
    @Expose
    private String Cpf;

    @SerializedName("senha")
    @Expose
    private String Senha;

    public Login(String cpf, String senha) {
        this.Cpf = cpf;
        this.Senha = senha;
    }

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String cpf) {
        Cpf = cpf;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }

    @Override
    public String toString() {
        return "Login{" +
                "Cpf='" + Cpf + '\'' +
                ", Senha='" + Senha + '\'' +
                '}';
    }
}
