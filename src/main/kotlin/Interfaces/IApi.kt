package Interfaces

import Models.DatosAPI
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IApi {

    @GET("recipes/complexSearch")
    fun obtenerDatos(
                     @Query("apiKey") apiKey:String,
                     @Query("query") query:String,
                     @Query("number") maxResult:Int,
                     @Query("addRecipeInformation") addRecipeInformation:String = "true",
                     ): Call<DatosAPI>
}