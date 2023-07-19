package com.erenpasavural.countryapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Country(
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val countryName:String?,
    @SerializedName("capital")
    @ColumnInfo(name = "capital")
    val countryCapital:String?,
    @SerializedName("region")
    @ColumnInfo(name = "region")
    val countryRegion:String?,
    @SerializedName("currency")
    @ColumnInfo(name = "currency")
    val countryCurrency:String?,
    @SerializedName("flag")
    @ColumnInfo(name = "flag")
    val countryFlag:String?,
    @SerializedName("language")
    @ColumnInfo(name = "language")
    val countryLanguage:String?,) {

    @PrimaryKey(autoGenerate = true)
    var countryId:Int = 0

}


