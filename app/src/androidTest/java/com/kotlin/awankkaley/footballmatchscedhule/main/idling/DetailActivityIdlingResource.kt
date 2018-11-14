package com.kotlin.awankkaley.footballmatchscedhule.main.idling

import android.support.test.espresso.IdlingResource
import com.kotlin.awankkaley.footballmatchscedhule.feature.details.DetailActivity

class DetailActivityIdlingResource:IdlingResource {
    private var callback: IdlingResource.ResourceCallback? = null
    override fun getName(): String {
        return DetailActivityIdlingResource::class.java.simpleName
    }

    override fun isIdleNow(): Boolean {
        val idle = DetailActivity.idlingResourceDetail == 0
        if (idle){
            callback?.onTransitionToIdle()
        }
        return idle
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
       this.callback = callback
    }
}