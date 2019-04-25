package com.otto.paulus.footballmatchschedule.presenter

import com.google.gson.Gson
import com.otto.paulus.footballmatchschedule.api.ApiRepository
import com.otto.paulus.footballmatchschedule.api.TheSportDBApi
import com.otto.paulus.footballmatchschedule.model.EventResponse
import com.otto.paulus.footballmatchschedule.model.TeamResponse
import com.otto.paulus.footballmatchschedule.view.MatchListView
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.info
import org.jetbrains.anko.uiThread

class MatchListPresenter(private val view: MatchListView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson):AnkoLogger {
    fun getLast15EventsList(leagueId: Int?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getLast15Events(leagueId)),
                    EventResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showEventList(data.events)
            }
        }
    }

    fun getNext15EventsList(leagueId: Int?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getNext15Events(leagueId)),
                    EventResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showEventList(data.events)
            }
        }
    }
}