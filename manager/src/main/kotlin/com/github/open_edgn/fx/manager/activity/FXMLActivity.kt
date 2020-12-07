package com.github.open_edgn.fx.manager.activity

import com.github.open_edgn.fx.manager.InternalObjects
import javafx.fxml.FXMLLoader
import javafx.fxml.JavaFXBuilderFactory
import javafx.scene.Node
import javafx.util.Callback
import java.net.URL

abstract class FXMLActivity<T : Node> : Activity<T>() {
    protected open val iconPath: String = ""
    protected abstract val fxmlPath: String
    protected open val stylePaths: Array<String> = arrayOf()
    override val styles: Array<URL> by lazy {
        stylePaths.map { javaClass.getResource(it) }.toTypedArray()
    }
    override val icon: URL by lazy {
        if (iconPath.isEmpty()) {
            InternalObjects.fxBoot.icon
        } else {
            javaClass.getResource(iconPath)
        }
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