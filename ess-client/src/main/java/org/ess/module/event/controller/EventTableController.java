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
import org.ess.app.common.FormMode;
import org.ess.app.common.TabType;
import org.ess.app.controller.TabController;
import org.ess.module.event.event.EventEvent;
import org.ess.module.event.model.EventModel;
import org.ess.app.window.View;
import org.ess.module.event.service.EventService;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class EventTableController extends TabController implements Initializable {

    @FXML
    TableView<EventModel> fxEventTableLayout;

    private final EventService eventService = new EventService();

    private final ObservableList<EventModel> userModelObservableList = FXCollections.observableArrayList(new ArrayList<>());

    protected static final Logger logger = LogManager.getLogger(EventTableController.class);

    /**
     * Retrieves event data from the server and populates the event table.
     * Upon success, logs the result.
     */
    @Override
    public void initialize(Tab tab) {
        requestEvents();
    }

    /**
     * Initializes the controller when the event table window is loaded.
     * Sets up the table columns and populates the event table.
     * Subscribes to event updates to refresh the table upon changes.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logger.info("Initialize");

        TableColumn<EventModel, Long> idColumn = new TableColumn<>("ID");
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
                .addAll(
                        idColumn,
                        usernameColumn,
                        firstNameColumn,
                        lastNameColumn,
//                        dobColumn,
                        updateAtColumn,
                        createAtColumn,
                        getBookingButton(),
                        getEditButton(),
                        getDeleteButton()
                );

        setTabType(TabType.EVENT);
        Platform.runLater(this);

        EventEvent.subscribe(EventEvent.Type.CREATE, type -> requestEvents());
        EventEvent.subscribe(EventEvent.Type.UPDATE, type -> requestEvents());
        EventEvent.subscribe(EventEvent.Type.DELETE, type -> requestEvents());
    }

    /**
     * Retrieves event data from the server and populates the event table.
     * Upon success, logs the result.
     */
    void requestEvents() {
        eventService.get(new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<List<EventModel>> call, @NotNull Response<List<EventModel>> response) {
                logger.info("Get Success {} {}", response.code(), response.message());
                userModelObservableList.clear();
                userModelObservableList.addAll(response.body());
                fxEventTableLayout.setItems(userModelObservableList);
            }

            @Override
            public void onFailure(@NotNull Call<List<EventModel>> call, @NotNull Throwable throwable) {
                logger.error(throwable);
            }
        });
    }

    /**
     * Retrieves the event model from the form fields.
     *
     * @return the event model
     */
    private @NotNull TableColumn<EventModel, Button> getBookingButton() {
        TableColumn<EventModel, Button> bookingColumn = new TableColumn<>("Booking");
        bookingColumn.setCellValueFactory(cellData -> {
            var button = new Button("Booking");
            button.setOnMouseClicked(event -> View.bookingTableWindow(Map.of("eventModel", cellData.getValue())));
            return new SimpleObjectProperty<>(button);
        });

        return bookingColumn;
    }

    /**
     * Retrieves the event model from the form fields.
     *
     * @return the event model
     */
    private @NotNull TableColumn<EventModel, Button> getEditButton() {
        TableColumn<EventModel, Button> editColumn = new TableColumn<>("Edit");
        editColumn.setCellValueFactory(cellData -> {
            var button = new Button("Edit");
            button.setOnMouseClicked(event -> View.eventFormWindow(Map.of("mode", FormMode.EDIT, "eventModel", cellData.getValue())));
            return new SimpleObjectProperty<>(button);
        });

        return editColumn;
    }

    /**
     * Retrieves the event model from the form fields.
     *
     * @return the event model
     */
    private @NotNull TableColumn<EventModel, Button> getDeleteButton() {
        TableColumn<EventModel, Button> deleteColumn = new TableColumn<>("Delete");
        deleteColumn.setCellValueFactory(cellData -> {
            var button = new Button("Delete");
            button.setOnMouseClicked(event -> View.eventDeleteConfirmationWindow(Map.of("id", cellData.getValue().getId())));
            return new SimpleObjectProperty<>(button);
        });

        return deleteColumn;
    }

    /**
     * Retrieves the event model from the form fields.
     *
     * @return the event model
     */
    private static @NotNull TableColumn<EventModel, HBox> getAssetActionsTableColumn() {
        TableColumn<EventModel, HBox> deleteStaff = new TableColumn<>("Actions");

        deleteStaff.setCellValueFactory(cellData -> {
            var hBox = new HBox();
            var editBtn = new Button("Edit");
            var deleteBtn = new Button("Delete");
            editBtn.setOnMouseClicked(event -> View.eventFormWindow(Map.of("mode", FormMode.EDIT, "eventModel", cellData.getValue())));
            deleteBtn.setOnMouseClicked(event -> View.eventDeleteConfirmationWindow(Map.of("id", cellData.getValue().getId())));
            hBox.getChildren().add(editBtn);
            hBox.getChildren().add(deleteBtn);
            hBox.setSpacing(10);
            hBox.styleProperty().set("-fx-alignment: center");

            return new SimpleObjectProperty<>(hBox);
        });
        return deleteStaff;
    }
}
