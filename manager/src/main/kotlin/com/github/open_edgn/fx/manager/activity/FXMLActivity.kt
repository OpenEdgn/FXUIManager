package com.github.open_edgn.fx.manager.activity

import javafx.fxml.FXMLLoader
import javafx.fxml.JavaFXBuilderFactory
import javafx.scene.Node
import javafx.util.Callback
import java.net.URL

abstract class FXMLActivity<T : Node> : Activity<T>() {
    abstract val fxmlPath: String
    open val stylePaths: Array<String> = arrayOf()
    override val styles: Array<URL> by lazy {
        stylePaths.map { javaClass.getResource(it) }.toTypedArray()
    }
    override val root by lazy {
        val fxmlLoader = FXMLLoader()
        fxmlLoader.builderFactory = JavaFXBuilderFactory()
        fxmlLoader.charset = Charsets.UTF_8
        fxmlLoader.location = javaClass.getResource(fxmlPath)
        fxmlLoader.controllerFactory = Callback {
            this
        }
        fxmlLoader.load<T>()
    }
}