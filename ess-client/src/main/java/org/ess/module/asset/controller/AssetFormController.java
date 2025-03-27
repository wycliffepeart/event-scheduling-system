package org.ess.module.asset.controller;

import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ess.app.common.FormMode;
import org.ess.app.repository.DropdownDTO;
import org.ess.app.repository.DropdownService;
import org.ess.module.asset.event.AssetEvent;
import org.ess.module.asset.model.AssetModel;
import org.ess.module.asset.service.AssetService;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class AssetFormController implements Initializable {

    @FXML
    protected VBox fxRoot;

    @FXML
    protected TextField fxAssetName;

    @FXML
    protected ComboBox<String> fxAssetCategory;

    @FXML
    protected ComboBox<Integer> fxAssetQuantity;

    @FXML
    protected ComboBox<String> fxAssetStatus;

    @FXML
    protected ComboBox<String> fxAssetCondition;

    @FXML
    protected TextField fxAssetPrice;

    private final AssetService assetService = new AssetService();
    private final DropdownService dropdownService = new DropdownService();

    protected static final Logger logger = LogManager.getLogger(AssetFormController.class);

    /**
     * Initializes the form by setting up default values for fields and loading required data.
     * If the form is in edit mode, it populates fields with existing asset details.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logger.info("Initialize");
        fxAssetQuantity.getItems().addAll(1, 2, 3, 4, 5);

        fxAssetStatus.getItems().addAll("Available", "Not Available");
        fxAssetCondition.getItems().addAll("New", "Old");

        Platform.runLater(() -> {
            loadAssetCategory();
            if (getAssetFormMode().equals(FormMode.EDIT)) {
                AssetModel assetModel = getAssetModelFromData();
                fxAssetName.setText(assetModel.getName());
                fxAssetCategory.setValue(assetModel.getCategory());
                fxAssetQuantity.setValue(assetModel.getQuantity());
                fxAssetStatus.setValue(assetModel.getStatus());
                fxAssetCondition.setValue(assetModel.getCondition());
                fxAssetPrice.setText(String.valueOf(assetModel.getPrice()));
            }
        });
    }

    /**
     * Handles the event when the cancel button is clicked. Closes the form window without saving changes.
     */
    @FXML
    void onCancelAssetForm(MouseEvent event) {
        logger.info("Cancel Asset Form");
        Platform.runLater(() -> fxRoot.getScene().getWindow().hide());
    }

    /**
     * Handles the submission of the asset form.
     * Determines whether to create a new asset or update an existing one based on the form mode.
     */
    @FXML
    void onSubmitAssetForm(MouseEvent event) {
        logger.info("onSubmitAssetForm");

        if (getAssetFormMode().equals(FormMode.CREATE)) {
            createAsset();
        } else {
            updateAsset();
        }
    }

    /**
     * Fetches and populates the dropdown for asset categories from the backend service.
     */
    private void loadAssetCategory() {
        dropdownService.get("ASSET_CATEGORY", new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<List<DropdownDTO>> call, @NotNull Response<List<DropdownDTO>> response) {
                logger.info("Asset Category Dropdown Success {} {}", response.code(), response.message());

                if (response.isSuccessful() && response.body() != null) {
                    fxAssetCategory.getItems().addAll(response.body().stream().map(dropdownDTO -> dropdownDTO.value).toList());
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<DropdownDTO>> call, @NotNull Throwable throwable) {

            }
        });
    }

    /**
     * Sends a request to update an existing asset with the data from the form.
     */
    private void updateAsset() {
        assetService.put(getAssetModel().setId(getAssetModelFromData().getId()), new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<AssetModel> call, @NotNull Response<AssetModel> response) {
                logger.info("Update Asset Success {} {}", response.code(), response.message());
                AssetEvent.subject.onNext(AssetEvent.Type.UPDATE);
                Platform.runLater(() -> fxRoot.getScene().getWindow().hide());
            }

            @Override
            public void onFailure(@NotNull Call<AssetModel> call, @NotNull Throwable throwable) {
                logger.error(throwable.getMessage());
            }
        });

        AssetEvent.subject.onNext(AssetEvent.Type.CREATE);
    }

    /**
     * Sends a request to create a new asset with the data from the form.
     */
    private void createAsset() {
        assetService.post(getAssetModel(), new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<AssetModel> call, @NotNull Response<AssetModel> response) {
                logger.info("Create Asset Success {} {}", response.code(), response.message());
                AssetEvent.subject.onNext(AssetEvent.Type.CREATE);
                Platform.runLater(() -> fxRoot.getScene().getWindow().hide());
            }

            @Override
            public void onFailure(@NotNull Call<AssetModel> call, @NotNull Throwable throwable) {
                logger.error(throwable.getMessage());
            }
        });

        AssetEvent.subject.onNext(AssetEvent.Type.CREATE);
    }

    /**
     * Creates an asset model object from the form data.
     *
     * @return the asset model object
     */
    private AssetModel getAssetModel() {
        AssetModel assetModel = new AssetModel();
        assetModel.setName(fxAssetName.getText());
        assetModel.setCategory(fxAssetCategory.getValue());
        assetModel.setQuantity(fxAssetQuantity.getValue());
        assetModel.setStatus(fxAssetStatus.getValue());
        assetModel.setCondition(fxAssetCondition.getValue());
        assetModel.setPrice(Double.parseDouble(fxAssetPrice.getText()));
        return assetModel;
    }

    /**
     * Determines the form mode based on the data passed to the form.
     *
     * @return the form mode
     */
    private FormMode getAssetFormMode() {

        if (getData().get("mode").toString().equals(FormMode.EDIT.name())) {
            return FormMode.EDIT;
        }

        return FormMode.CREATE;
    }

    /**
     * Retrieves the asset model object from the data passed to the form.
     *
     * @return the asset model object
     */
    private AssetModel getAssetModelFromData() {
        var assetModel = (AssetModel) getData().get("assetModel");

        logger.info("Asset Model For Editing: {}", new Gson().toJson(assetModel));

        return assetModel;
    }

    /**
     * Retrieves the data passed to the form.
     *
     * @return the data passed to the form
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> getData() {
        return (Map<String, Object>) fxRoot.getScene().getWindow().getUserData();
    }
}
