package com.kotlin.awankkaley.footballmatchscedhule.main.idling

import android.support.test.espresso.IdlingResource
import com.kotlin.awankkaley.footballmatchscedhule.feature.team.HomeTeamFragment

class TeamFragmentIdlingResource: IdlingResource {
    private var callback: IdlingResource.ResourceCallback? = null
    override fun getName(): String {
        return TeamFragmentIdlingResource::class.java.simpleName
    }

    override fun isIdleNow(): Boolean {
        val idle = HomeTeamFragment.idlingResourceTeam == 0
        if (idle){
            callback?.onTransitionToIdle()
        }
        return idle
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.callback = callback
    }
}