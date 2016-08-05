package Controller;

import android.util.Base64;
import com.squareup.okhttp.OkHttpClient;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;


/**
 ** Created by Jessie Obeng
 ** WatsonRestClient Makes call to get Watson QAAPI
 ** First checking to make sure only one instance of the API exists */
public class WatsonRestClient {
    private static GetWatsonApi sAPI;

    public static GetWatsonApi get(){
        if (sAPI == null){
            setupClient();
        }

        return sAPI;
    }

    /** Call to the API which is done in GetWatson API class
     * */
    private static void setupClient(){

        RestAdapter.Builder builder = new RestAdapter.Builder();

        builder.setEndpoint(GetWatsonApi.ENDPOINT);
        builder.setClient(new OkClient(new OkHttpClient()));
        builder.setLogLevel(RestAdapter.LogLevel.FULL);

        builder.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Authorization", "Basic " + getAuth());
            }
        });

        RestAdapter restAdapter = builder.build();
        sAPI = restAdapter.create(GetWatsonApi.class);
    }

    /** Authenticates access to the API by providing appropriate username & login
     * Follow Notation in Watson Reading
     * IBM Watson Academic Partners - Getting started
     * */
    public static String getAuth(){
        return new String(Base64.encode("quk_student1:HGjAdFxI".getBytes(), Base64.DEFAULT));
    }


}
