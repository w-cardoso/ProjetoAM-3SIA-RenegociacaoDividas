package renegociacao.moosegroup.com.br.renegociardividas.Model;

import java.io.Serializable;

/**
 * Created by re034850 on 11/09/2017.
 */

public class DividaModel implements Serializable{
    private Long id;
    private int user_id;
    private String titulo;
    private String descricao;
    private double valor;
    private String empresa;


    public DividaModel(Long id, int user_id, String titulo, String descricao, double valor, String empresa) {
        this.id = id;
        this.user_id = user_id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.valor = valor;
        this.empresa = empresa;


    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
