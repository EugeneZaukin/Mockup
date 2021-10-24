package com.eugene.mockup.model.repo

import com.eugene.mockup.model.AllValutes
import retrofit2.Callback

interface IRepository {
    fun getValutesFromServer(callback: Callback<AllValutes>);
}