package com.mobiquity.infrastructure.platform

import android.os.Bundle

interface BaseView {
    fun showMessage(message:String)
    fun ignite(bundle:Bundle?)
}