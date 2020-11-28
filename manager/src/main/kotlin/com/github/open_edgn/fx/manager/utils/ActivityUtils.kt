package com.github.open_edgn.fx.manager.utils

import com.github.open_edgn.fx.manager.activity.Activity
import javafx.scene.Node
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

object ActivityUtils {
    fun createActivity(act: KClass<Activity<Node>>):Activity<Node>{
        return act.objectInstance?:act.createInstance()
    }
}