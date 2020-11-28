package simples

import com.github.open_edgn.fx.manager.FXBoot
import com.github.open_edgn.fx.manager.UIApplication
import com.github.open_edgn.fx.manager.activity.Activity

class Main

fun main(args: Array<String>) {
    val builder = FXBoot.Builder(StartActivity::class)
    builder.args = args
    UIApplication.boot(builder.build())
}