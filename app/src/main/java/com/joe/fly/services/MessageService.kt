package com.joe.fly.services

import retrofit2.http.GET
import retrofit2.http.Url

interface MessageService {

    @GET
    fun getMessages(@Url url: String): Class<String>

}