package com.kotlin.awankkaley.footballmatchscedhule

import com.kotlin.awankkaley.footballmatchscedhule.utils.CoroutineContextProvider
import kotlinx.coroutines.experimental.Unconfined
import kotlin.coroutines.experimental.CoroutineContext

class TestContextProvider: CoroutineContextProvider() {
    override val main: CoroutineContext = Unconfined
}