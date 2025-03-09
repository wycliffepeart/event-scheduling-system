package org.ess.module.asset.controller;

import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ess.app.common.TabType;
import org.ess.app.controller.TabController;
import org.ess.module.asset.model.AssetModel;
import org.ess.module.asset.repository.AssetFakeRepository;
import org.ess.module.user.model.UserModel;
import org.ess.module.user.repository.UserFakeRepository;
import org.ess.module.user.route.UserRoute;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AssetTableController extends TabController implements Initializable {

    @FXML
    TableView<AssetModel> fxAssetTableLayout;

    private final ObservableList<AssetModel> userModelObservableList = FXCollections.observableArrayList(new ArrayList<>());

    protected static final Logger logger = LogManager.getLogger(AssetTableController.class);

    @Override
    public void initialize(Tab tab) {
        userModelObservableList.clear();
        userModelObservableList.addAll(AssetFakeRepository.getData(50));
        fxAssetTableLayout.setItems(userModelObservableList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logger.info("Initialize");

        TableColumn<AssetModel, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getId()));

        TableColumn<AssetModel, String> usernameColumn = new TableColumn<>("Name");
        usernameColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getName()));

        TableColumn<AssetModel, String> firstNameColumn = new TableColumn<>("Category");
        firstNameColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCategory()));

        TableColumn<AssetModel, Integer> lastNameColumn = new TableColumn<>("Quantity");
        lastNameColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getQuantity()));

        TableColumn<AssetModel, String> roleColumn = new TableColumn<>("Condition");
        roleColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCondition()));

        TableColumn<AssetModel, String> dobColumn = new TableColumn<>("Status");
        dobColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getStatus()));

        TableColumn<AssetModel, String> updateAtColumn = new TableColumn<>("Update At");
        updateAtColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getUpdatedAt()));

        TableColumn<AssetModel, String> createAtColumn = new TableColumn<>("Create At");
        createAtColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCreatedAt()));

        TableColumn<AssetModel, HBox> deleteStaff = getAssetActionsTableColumn();

        fxAssetTableLayout
                .getColumns()
                .addAll(idColumn, usernameColumn, firstNameColumn, lastNameColumn, roleColumn, dobColumn, updateAtColumn, createAtColumn, deleteStaff);

        setTabType(TabType.ASSET);
        Platform.runLater(this);
    }

    private static @NotNull TableColumn<AssetModel, HBox> getAssetActionsTableColumn() {
        TableColumn<AssetModel, HBox> deleteStaff = new TableColumn<>("Actions");

        deleteStaff.setCellValueFactory(cellData -> {
            var hBox = new HBox();
            var editBtn = new Button("Edit");
            var deleteBtn = new Button("Delete");
            editBtn.setOnMouseClicked(event -> UserRoute.userFormLayout());
            deleteBtn.setOnMouseClicked(event -> UserRoute.deleteConfirmationLayout());
            hBox.getChildren().add(editBtn);
            hBox.getChildren().add(deleteBtn);
            hBox.setSpacing(10);
            hBox.styleProperty().set("-fx-alignment: center");

            return new SimpleObjectProperty<>(hBox);
        });
        return deleteStaff;
    }
}
