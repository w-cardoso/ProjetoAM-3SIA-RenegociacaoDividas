package renegociacao.moosegroup.com.br.renegociardividas.Model;

/**
 * Created by re034850 on 11/09/2017.
 */

public class DividaModel {
    private String titulo;
    private String descricao;
    private String url;
    private double valor;
    private String empresa;



    public DividaModel(String titulo, String descricao, String url, double valor, String empresa, int fotoEmpresa) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
        this.valor = valor;
        this.empresa = empresa;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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


}
