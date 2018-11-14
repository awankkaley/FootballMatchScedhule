package com.kotlin.awankkaley.footballmatchscedhule.main.idling

import android.support.test.espresso.IdlingResource
import com.kotlin.awankkaley.footballmatchscedhule.feature.favourite.favouritematch.MatchFavouriteFragment

class FavouriteIdlingResource:IdlingResource {

    private var callback: IdlingResource.ResourceCallback? = null
    override fun getName(): String {
        return FavouriteIdlingResource::class.java.simpleName
    }

    override fun isIdleNow(): Boolean {
        val idle = MatchFavouriteFragment.idlingResourceFavourite == 0
        if (idle){
            callback?.onTransitionToIdle()
        }
        return idle
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.callback = callback
    }
}