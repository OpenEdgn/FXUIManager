package com.github.open_edgn.fx.manager.window

import javafx.scene.input.KeyCombination
import javafx.stage.Stage

internal class InternalWindow(private val stage: Stage) : Window() {

    override val name: String = javaClass.name

    override val fxStage: Stage
        get() = stage

    override val fxWindow: javafx.stage.Window
        get() = stage.scene.window

    init {
        stage.fullScreenExitHint = ""
        stage.fullScreenExitKeyCombination = KeyCombination.NO_MATCH
    }

    override var isAlwaysOnTop: Boolean
        get() = stage.isAlwaysOnTop
        set(value) {
            stage.isAlwaysOnTop = value
        }

    override var isFullScreen: Boolean
        get() = stage.isFullScreen
        set(value) {
            stage.isFullScreen = value
        }
    override var show: Boolean
        get() = stage.isShowing
        set(value) {
            if (value) {
                stage.show()
            } else {
                stage.hide()
            }
        }
    override var width: Double
        get() = stage.width
        set(value) {
            stage.width = value
        }
    override var height: Double
        get() = stage.height
        set(value) {
            stage.height = value
        }
    override var x: Double
        get() = stage.x
        set(value) {
            stage.x = value
        }
    override var y: Double
        get() = stage.y
        set(value) {
            stage.y = value
        }
    override var title: String
        get() = stage.title
        set(value) {
            stage.title = value
        }
    override var isMaximized: Boolean
        get() = stage.isMaximized
        set(value) {
            stage.isMaximized = value
        }
    override var isResizable: Boolean
        get() = stage.isResizable
        set(value) {
            stage.isResizable = value
        }
}