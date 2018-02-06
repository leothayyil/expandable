package com.example.lintojohny.myapplication;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by lintojohny on 07-02-2018.
 */

public interface API {

    @FormUrlEncoded
    @POST("/sample/school_app/api/parents_api.php")
    Call<JsonElement> getGeneralMsg(@Field("action")String action, @Field("user_id") String userId,
                                    @Field("class") String classa, @Field("division") String division);

}
