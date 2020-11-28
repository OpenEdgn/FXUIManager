package simples

import com.github.open_edgn.fx.manager.activity.Activity
import javafx.event.EventHandler
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.VBox

class SecondActivity : Activity<VBox>() {
    override val title: String = "第二个"
    override val root: VBox = VBox()

    override fun onCreate() {
        super.onCreate()
        val element = Button("exit")
        root.children.add(element)
        element.onMouseClicked = EventHandler {
            finish()
        }
    }

    override fun onStart() {
        root.children.add(Label(intent.getExtra("key","null")))
    }
}