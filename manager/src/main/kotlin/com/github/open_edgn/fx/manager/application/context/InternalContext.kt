package com.github.open_edgn.fx.manager.application.context

import com.github.open_edgn.fx.manager.InternalObjects
import com.github.open_edgn.fx.manager.intent.Intent

abstract class InternalContext : Context() {

    override fun startActivity(intent: Intent) {
        InternalObjects.container.showActivity(intent)
    }

    override fun startService(intent: Intent) {
        TODO("Not yet implemented")
    }

    override fun stopService(intent: Intent) {
        TODO("Not yet implemented")
    }
}