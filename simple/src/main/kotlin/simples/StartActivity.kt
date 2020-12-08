package simples


import com.github.open_edgn.fx.manager.activity.FXMLActivity
import com.github.open_edgn.fx.manager.intent.Intent
import javafx.scene.layout.VBox

class StartActivity : FXMLActivity<VBox>() {
    fun onClick() {
        startActivity(Intent(this, SecondActivity::class).putExtra("key", "Value"))
    }

    override val fxmlPath: String = "/activity_start.fxml"
    override val title: String = "测试"


}