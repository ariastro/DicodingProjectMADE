package com.otto.paulus.footballmatchschedule.presenter

import android.util.Log
import com.google.gson.Gson
import com.otto.paulus.footballmatchschedule.api.ApiRepository
import com.otto.paulus.footballmatchschedule.api.TheSportDBApi
import com.otto.paulus.footballmatchschedule.model.EventDetailResponse
import com.otto.paulus.footballmatchschedule.model.EventResponse
import com.otto.paulus.footballmatchschedule.model.TeamResponse
import com.otto.paulus.footballmatchschedule.view.MatchDetailView
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchDetailPresenter(private val view: MatchDetailView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson):AnkoLogger {
    fun getEventDetail(eventId: String) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getEventDetail(eventId)),
                    EventDetailResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showDetailEvent(data.events.get(0))
            }
        }
    }

    fun getTeamDetail(teamId: String, isHomeTeam: Boolean=true) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeamDetail(teamId)),
                    TeamResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showDetailTeam(data.teams.get(0), isHomeTeam)
            }
        }
    }
}