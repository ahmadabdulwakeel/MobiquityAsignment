package com.mobiquity.infrastructure.android

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class MobiquityCoroutine : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + Job()

    val mainDispatcher : CoroutineContext by lazy { Dispatchers.Main }
}

interface MobiquityCoroutine_ {
    fun ioDispatcher() : CoroutineContext
    fun mainDispatcher() : CoroutineContext
}

class MobiquityCoroutine__: MobiquityCoroutine_ {
    private val job = Job()
    override fun ioDispatcher(): CoroutineContext {
        return Dispatchers.IO + job
    }

    override fun mainDispatcher(): CoroutineContext {
        return Dispatchers.Main + job
    }

}

class TestMobiquityCoroutineCoroutine__: MobiquityCoroutine_ {
    override fun ioDispatcher(): CoroutineContext {
        return Dispatchers.Unconfined
    }

    override fun mainDispatcher(): CoroutineContext {
        return Dispatchers.Unconfined
    }

}