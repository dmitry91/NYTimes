package com.dmitry.nytimes.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Media:Serializable {

    @SerializedName("media-metadata")
    var mediaMedia:List<MediaMetadata>?=null;
    //for parse url images article title
    class MediaMetadata {
        @SerializedName("url")
        var url:String?=null;
        @SerializedName("format")
        var format:String?=null;
    }
}