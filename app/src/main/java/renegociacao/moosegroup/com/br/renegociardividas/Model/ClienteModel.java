package renegociacao.moosegroup.com.br.renegociardividas.Model;

import java.util.List;

/**
 * Created by re034850 on 10/09/2017.
 */

public class ClienteModel {
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private String telefone;
    private List<DividaModel> divida;

    public List<DividaModel> getDivida() {
        return divida;
    }

    public void setDivida(List<DividaModel> divida) {
        this.divida = divida;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


}
