package renegociacao.moosegroup.com.br.renegociardividas.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by re034850 on 07/10/2017.
 */

public class User {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("nome")
    @Expose
    private String nome;

    @SerializedName("cpf")
    @Expose
    private String cpf;

    @SerializedName("token")
    @Expose
    private String token;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
