package renegociacao.moosegroup.com.br.renegociardividas.Data.Remote;

import okhttp3.ResponseBody;
import renegociacao.moosegroup.com.br.renegociardividas.Model.Login;
import renegociacao.moosegroup.com.br.renegociardividas.Model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by re034850 on 07/10/2017.
 */

public interface APIUserClient {



    @POST("/API/Tokens")
    Call<User> login(@Body Login login);

    @GET("/API/Tokens")
    Call<ResponseBody> getSecret(@Header("Autorizacao") String authToken);
}

