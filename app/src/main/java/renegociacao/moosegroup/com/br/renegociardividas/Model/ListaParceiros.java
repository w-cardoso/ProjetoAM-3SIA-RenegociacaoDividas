package renegociacao.moosegroup.com.br.renegociardividas.Model;

/**
 * Created by re034850 on 04/10/2017.
 */

public class ListaParceiros {
    private String nomeFanstasia;
    private String cnpj;
    private String telefone;

    public ListaParceiros(){

    }

    public ListaParceiros(String nomeFanstasia, String cnpj, String telefone) {
        this.nomeFanstasia = nomeFanstasia;
        this.cnpj = cnpj;
        this.telefone = telefone;
    }


    public String getNomeFanstasia() {
        return nomeFanstasia;
    }

    public void setNomeFanstasia(String nomeFanstasia) {
        this.nomeFanstasia = nomeFanstasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
