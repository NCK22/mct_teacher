package com.nyasa.mctteacher.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nyasa.mctteacher.APIClient;
import com.nyasa.mctteacher.Interface.loginInterface;
import com.nyasa.mctteacher.Pojo.ParentPojoLogin;
import com.nyasa.mctteacher.Storage.SPProfile;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etUname,etPwd;
    Button btnLogin;
    ProgressDialog progressDialog;
    SPProfile spProfile;
    boolean flagAllValid=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        spProfile=new SPProfile(this);
        progressDialog=new ProgressDialog(this);
        etUname=(EditText)findViewById(R.id.edt_email);
        etPwd=(EditText)findViewById(R.id.edt_pwd);
        btnLogin=(Button)findViewById(R.id.btn_submit);
        btnLogin.setOnClickListener(this);
    }

    public void checkValidity()
    {
        progressDialog.show();
        if(etUname.getText().toString().equals("")){
            showToast("Please fill all fields");
        }
        else if(etPwd.getText().toString().length()<4)
            showToast("Enter valid password");
        else
            flagAllValid=true;
        progressDialog.dismiss();
    }

    public void login(){

        checkValidity();

        if(flagAllValid==true) {
            progressDialog.show();
            loginInterface getResponse = APIClient.getClient().create(loginInterface.class);
            retrofit2.Call<ParentPojoLogin> call = getResponse.doGetListResources(etUname.getText().toString(),etPwd.getText().toString());
            call.enqueue(new Callback<ParentPojoLogin>() {
                @Override
                public void onResponse(retrofit2.Call<ParentPojoLogin> call, Response<ParentPojoLogin> response) {

                    Log.e("Inside", "onResponse");
               /* Log.e("response body",response.body().getStatus());
                Log.e("response body",response.body().getMsg());*/
                    ParentPojoLogin parentPojoLogin = response.body();
                    if (parentPojoLogin != null) {
                        if (parentPojoLogin.getStatus().equalsIgnoreCase("true")) {
                            Log.e("Response", parentPojoLogin.getStatus());
                            try {
                                Log.e("json response 1",""+parentPojoLogin.getObjProfile().get(0).get("driver_id"));
                            } catch (Exception e) {
                                Log.e("exception",""+e);
                                e.printStackTrace();
                            }

                            spProfile.setIsLogin("true");
                            JSONObject jsonObject=new JSONObject(parentPojoLogin.getObjProfile().get(0));
                            spProfile.setIsLogin("true");
                            try {
                                Log.e("driver_id",jsonObject.getString("driver_id"));
                                spProfile.setDriver_id(jsonObject.getString("driver_id"));
                                spProfile.setMac_id(jsonObject.getString("mac_id"));
                                spProfile.setName(jsonObject.getString("name"));

                                spProfile.setAddress(jsonObject.getString("address"));
                                spProfile.setVehReg(jsonObject.getString("vehicle_reg_no"));
                                spProfile.setVehType(jsonObject.getString("vehicle_type"));

                                spProfile.setUsername(jsonObject.getString("username"));
                                spProfile.setPassword(jsonObject.getString("password"));
                                spProfile.setMobile(jsonObject.getString("school_id"));
                                spProfile.setSchoolId(jsonObject.getString("school_id"));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            // Log.e("json response",""+jsonObject);
                            Intent inent=new Intent(LoginActivity.this,MainActivity.class);
                            inent.putExtra("response", String.valueOf(jsonObject));
                            startActivity(inent);
                            finish();
                        }

                        else
                            showToast("Invalid username or Password");
                    }

                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(retrofit2.Call<ParentPojoLogin> call, Throwable t) {

                    Log.e("Throwabe ", "" + t);
                    progressDialog.dismiss();
                }
            });
        }
    }

    public void showToast(String msg)
    {
        Toast.makeText(LoginActivity.this,msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

        login();

    }
}

