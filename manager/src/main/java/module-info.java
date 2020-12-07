module fx.ui.manager {
    requires kotlin.reflect;
    requires kotlin.stdlib;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires core;
    exports com.github.open_edgn.fx.manager;
    opens com.github.open_edgn.fx.manager;
    exports com.github.open_edgn.fx.manager.activity;
    exports com.github.open_edgn.fx.manager.activity.event;
    exports com.github.open_edgn.fx.manager.application.context;
    exports com.github.open_edgn.fx.manager.application.fx;
    exports com.github.open_edgn.fx.manager.intent;
    exports com.github.open_edgn.fx.manager.window;
    exports icon;
    opens icon;
}