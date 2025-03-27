package org.ess.module.asset.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ess.module.asset.event.AssetEvent;
import org.ess.module.asset.service.AssetService;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class AssetDeleteConfirmationController implements Initializable {

    @FXML
    protected VBox fxRoot;

    private final AssetService assetService = new AssetService();

    protected static final Logger logger = LogManager.getLogger(AssetDeleteConfirmationController.class);

    /**
     * Initializes the controller when the asset delete confirmation window is loaded.
     * Logs an informational message upon initialization.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logger.info("Initialize");
    }


    /**
     * Handles the delete button click event to delete the asset.
     * Retrieves asset data from the window's user data and calls the asset deletion service.
     * Upon success, logs the result and closes the window.
     */
    @FXML
    @SuppressWarnings("unchecked")
    void onClickDeleteAsset(MouseEvent event) {
        logger.info("Delete Asset");

        Map<String, Object> data = (Map<String, Object>) fxRoot.getScene().getWindow().getUserData();

        assetService.delete((Long) data.get("id"), new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<Boolean> call, @NotNull Response<Boolean> response) {
                logger.info("Delete Asset Success {} {}", response.code(), response.message());
                AssetEvent.subject.onNext(AssetEvent.Type.DELETE);
                Platform.runLater(() -> fxRoot.getScene().getWindow().hide());
            }

            @Override
            public void onFailure(@NotNull Call<Boolean> call, @NotNull Throwable throwable) {

            }
        });

    }

    /**
     * Handles the cancel button click event to cancel the asset deletion.
     * Logs the cancellation action and closes the current window.
     */
    @FXML
    void onClickCancelDeleteAsset(MouseEvent event) {
        logger.info("Delete Asset Cancelled");

        fxRoot.getScene().getWindow().hide();
    }

}
