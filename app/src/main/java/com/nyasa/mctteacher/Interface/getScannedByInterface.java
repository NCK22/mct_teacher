package com.nyasa.mctteacher.Interface;

import com.nyasa.mctteacher.Pojo.ChildPojoStudProf;
import com.nyasa.mctteacher.Pojo.CommonParentPojo;
import com.nyasa.mctteacher.Pojo.ParentPojoStudProf;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface getScannedByInterface {

    String user_id="";
   /* @GET("Parents/list/{parent_id}")
    Call<ParentPojoLogin> doGetListResources(@Path("parent_id") String parent_id);
*/

   @FormUrlEncoded
   @POST("teacher/scan/")
   Call<ParentPojoStudProf> doGetListResources(@Field("scannedby") String scanned_by);

}
