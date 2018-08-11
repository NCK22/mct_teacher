package com.nyasa.mctteacher.Pojo;

import com.google.gson.annotations.SerializedName;

public class ChildPojoStudProf {

    String found="false";

    @SerializedName("standard_id")
    String standard_id;

    @SerializedName("name")
    String name;

    @SerializedName("class_id")
    String class_id;

    @SerializedName("mac_id")
    String mac_id;

    @SerializedName("status")
    String status;




    public String getFound() {
        return found;
    }

    public void setFound(String found) {
        this.found = found;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getStandard_id() {
        return standard_id;
    }

    public void setStandard_id(String standard_id) {
        this.standard_id = standard_id;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
