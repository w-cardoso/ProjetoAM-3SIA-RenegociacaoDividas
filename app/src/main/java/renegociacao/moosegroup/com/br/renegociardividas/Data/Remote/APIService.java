package renegociacao.moosegroup.com.br.renegociardividas.Data.Remote;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by re034850 on 28/09/2017.
 */

public interface APIService {

    @POST("/API")
    @FormUrlEncoded
    Call<POST> savePost(@Field("id") long id,
                        @Field("name") String nome,
                        @Field("cpf") String cpf,
                        @Field("email") String email,
                        @Field("senha") String senha,
                        @Field("telefone") String telefone);
}
