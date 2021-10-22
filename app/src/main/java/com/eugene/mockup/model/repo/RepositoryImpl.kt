package com.eugene.mockup.model.repo

import com.eugene.mockup.model.Valute
import com.eugene.mockup.model.AllValutes
import com.eugene.mockup.model.ValCursApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class RepositoryImpl: IRepository {

    override fun getValutesFromServer(callback: Callback<AllValutes>) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.cbr.ru/scripts/")
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build().create(ValCursApi::class.java);

        retrofit.getValCurs().enqueue(callback);
    }
}