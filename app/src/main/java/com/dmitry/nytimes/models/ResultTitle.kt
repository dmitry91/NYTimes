package com.dmitry.nytimes.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ResultTitle : Serializable{
    @SerializedName("results")
    var result:List<Title>?=null
}