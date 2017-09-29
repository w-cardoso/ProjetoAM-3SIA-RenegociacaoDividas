package renegociacao.moosegroup.com.br.renegociardividas.Data.Remote;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by re034850 on 28/09/2017.
 */

public interface APIService {

    @POST("/Usuarios")
    @FormUrlEncoded
    Call<POST> criarUsuario(@Field("Nome") String nome,
                            @Field("Cpf") String cpf,
                            @Field("Email") String email,
                            @Field("Senha") String senha,
                            @Field("Telefone") String telefone);
}
