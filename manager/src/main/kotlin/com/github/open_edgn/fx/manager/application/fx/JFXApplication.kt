package com.github.open_edgn.fx.manager.application.fx

import com.github.openEdgn.logger4k.LoggerFactory
import com.github.openEdgn.logger4k.getLogger
import com.github.open_edgn.fx.manager.InternalObjects
import javafx.application.Application
import javafx.stage.Stage

/**
 * 程序承载核心
 */
internal class JFXApplication : Application() {
    private val logger = getLogger()
    override fun start(primaryStage: Stage) {
        logger.trace("主窗体初始化.")
        InternalObjects.container.containerInit(primaryStage)
    }
}