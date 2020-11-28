package com.github.open_edgn.fx.manager.activity

import javafx.scene.Node
import java.net.URL

interface IParent<T : Node> {
    /**
     * 标题
     */
    val title: String

    /**
     * 根布局 UI
     */
    val root: T

    /**
     * Activity 主题
     */
    val styles: Array<URL>


    /**
     * 图标
     */
    val icon:URL
}