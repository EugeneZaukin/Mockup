package com.eugene.mockup.model

import retrofit2.Call
import retrofit2.http.GET

interface ValCursApi {
    @GET("XML_daily.asp")
    fun getValCurs(): Call<AllValutes>
}