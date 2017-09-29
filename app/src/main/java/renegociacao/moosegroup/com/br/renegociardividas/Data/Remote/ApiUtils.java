package renegociacao.moosegroup.com.br.renegociardividas.Data.Remote;

/**
 * Created by re034850 on 28/09/2017.
 */

public class ApiUtils {

    private ApiUtils() {
    }

    public static final String BASE_URL = "http://dividazero.azurewebsites.net/API/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }

}
