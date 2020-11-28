package com.github.open_edgn.fx.manager

import com.github.open_edgn.fx.manager.application.IContainer
import com.github.open_edgn.fx.manager.application.InternalContainer

internal object InternalObjects {
    lateinit var fxBoot: FXBoot
    val container: IContainer = InternalContainer
}