package org.ess;

import javafx.application.Application;
import javafx.stage.Stage;
import org.ess.app.common.Navigate;
import org.ess.app.websocket.JTHWebSocketClient;

public class EssApplication extends Application {
    @Override
    public void start(Stage stage) {
        Navigate.root(stage, "dashboard.fxml");
        JTHWebSocketClient.connect();
    }

    public static void main(String[] args) {
        launch();
    }
}