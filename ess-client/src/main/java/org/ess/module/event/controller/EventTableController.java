package org.ess.module.event.controller;

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
import org.ess.module.event.model.EventModel;
import org.ess.module.event.repository.EventFakeRepository;
import org.ess.module.user.route.UserRoute;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EventTableController extends TabController implements Initializable {

    @FXML
    TableView<EventModel> fxEventTableLayout;

    private final ObservableList<EventModel> userModelObservableList = FXCollections.observableArrayList(new ArrayList<>());

    protected static final Logger logger = LogManager.getLogger(EventTableController.class);

    @Override
    public void initialize(Tab tab) {
        userModelObservableList.clear();
        userModelObservableList.addAll(EventFakeRepository.getData(50));
        fxEventTableLayout.setItems(userModelObservableList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logger.info("Initialize");

        TableColumn<EventModel, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getId()));

        TableColumn<EventModel, String> usernameColumn = new TableColumn<>("Name");
        usernameColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getName()));

        TableColumn<EventModel, String> firstNameColumn = new TableColumn<>("Location");
        firstNameColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getLocation()));

        TableColumn<EventModel, String> lastNameColumn = new TableColumn<>("Status");
        lastNameColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getStatus()));

        TableColumn<EventModel, String> dobColumn = new TableColumn<>("Status");
        dobColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getStatus()));

        TableColumn<EventModel, String> updateAtColumn = new TableColumn<>("Update At");
        updateAtColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getUpdatedAt()));

        TableColumn<EventModel, String> createAtColumn = new TableColumn<>("Create At");
        createAtColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCreatedAt()));

        TableColumn<EventModel, HBox> deleteStaff = getAssetActionsTableColumn();

        fxEventTableLayout
                .getColumns()
                .addAll(idColumn, usernameColumn, firstNameColumn, lastNameColumn, dobColumn, updateAtColumn, createAtColumn, deleteStaff);

        setTabType(TabType.EVENT);
        Platform.runLater(this);
    }

    private static @NotNull TableColumn<EventModel, HBox> getAssetActionsTableColumn() {
        TableColumn<EventModel, HBox> deleteStaff = new TableColumn<>("Actions");

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
