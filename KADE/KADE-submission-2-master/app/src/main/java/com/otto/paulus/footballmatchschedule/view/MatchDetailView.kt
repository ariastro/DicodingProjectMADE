package com.otto.paulus.footballmatchschedule.view

import com.otto.paulus.footballmatchschedule.model.Event
import com.otto.paulus.footballmatchschedule.model.EventDetail
import com.otto.paulus.footballmatchschedule.model.Team

interface MatchDetailView {
    fun showLoading()
    fun hideLoading()
    fun showDetailEvent(data: EventDetail)
    fun showDetailTeam(data: Team, isHomeTeam: Boolean)
}