package com.kotlin.awankkaley.footballmatchscedhule.main.idling

import android.support.test.espresso.IdlingResource
import com.kotlin.awankkaley.footballmatchscedhule.feature.match.LastFragment

class LastFragmentIdlingResource : IdlingResource {

    private var callback: IdlingResource.ResourceCallback? = null

    override fun getName(): String {
        return LastFragmentIdlingResource::class.java.simpleName
    }

    override fun isIdleNow(): Boolean {
        val idle = LastFragment.idlingResourceLast == 0
        if (idle){
            callback?.onTransitionToIdle()
        }
        return idle
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
       this.callback = callback
    }
}