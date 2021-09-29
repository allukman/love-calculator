package id.smartech.lovecalculator.remote

import id.smartech.lovecalculator.ui.result.model.ResultModel
import retrofit2.http.*

interface ApiService {

    @GET("getPercentage")
    suspend fun getPercentage(
        @Header("x-rapidapi-host")host: String,
        @Header("x-rapidapi-key")key: String,
        @Query("sname")sname: String,
        @Query("fname")fname: String
    ): ResultModel
}