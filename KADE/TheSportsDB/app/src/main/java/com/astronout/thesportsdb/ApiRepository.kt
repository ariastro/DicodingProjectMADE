package com.astronout.thesportsdb

class ApiRepository {

    fun doRequest(url: String): String {
        return URL(url).readText()
    }
}