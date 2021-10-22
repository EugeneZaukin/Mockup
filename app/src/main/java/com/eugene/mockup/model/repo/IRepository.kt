package com.eugene.mockup.model.repo

import com.eugene.mockup.model.AllValutes
import com.eugene.mockup.model.Valute
import retrofit2.Callback

interface IRepository {
    fun getValutesFromServer(callback: Callback<AllValutes>);
}