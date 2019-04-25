package com.otto.paulus.footballmatchschedule.view

import com.otto.paulus.footballmatchschedule.model.Event

interface MatchListView {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<Event>)
}