package com.dmitry.nytimes.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Title:Serializable {

    @SerializedName("url")
    var url:String?=null;
    @SerializedName("title")
    var title:String?=null;
    @SerializedName("media")
    var media:List<Media>?=null
}

