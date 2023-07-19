package com.erenpasavural.countryapp.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.erenpasavural.countryapp.model.Country

@Dao
interface CountryDao {

    @Insert
    suspend fun insertAll(vararg countries:Country) : List<Long>

    @Query("SELECT * FROM country")
    suspend fun getAllCountries() : List<Country>

    @Query("SELECT * FROM country WHERE countryId = :countryId")
    suspend fun getCountry(countryId : Int) : Country

    @Query("DELETE FROM country")
    suspend fun deleteAllCountries()





}