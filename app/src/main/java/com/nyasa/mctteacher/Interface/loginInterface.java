package com.nyasa.mctteacher.Interface;

import com.nyasa.mctteacher.Pojo.ParentPojoLogin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface loginInterface {

    String user_id="";
   /* @GET("Parents/list/{parent_id}")
    Call<ParentPojoLogin> doGetListResources(@Path("parent_id") String parent_id);
*/

   @FormUrlEncoded
   @POST("teacher/Login/")
   Call<ParentPojoLogin> doGetListResources(@Field("username") String username, @Field("password") String password);

}
