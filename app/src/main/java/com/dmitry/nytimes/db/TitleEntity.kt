package com.dmitry.nytimes.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity
class TitleEntity {

    @NonNull
    @PrimaryKey
    var url : String ? = null

    var titleText : String ? = null

    var imgUrl : String ? = null
}