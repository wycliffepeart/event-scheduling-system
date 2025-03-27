package org.ess.module.event.controller;

import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.ess.app.common.FormMode;
import org.ess.module.event.event.EventEvent;
import org.ess.module.event.model.EventModel;
import org.ess.module.event.service.EventService;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.net.URL;
import java.time.LocalDate;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EventFormController implements Initializable {

    @FXML
    protected VBox fxRoot;

    @FXML
    protected TextField fxEventName;

    @FXML
    protected TextField fxEventLocation;

    @FXML
    protected ComboBox<String> fxEventStatus;

    @FXML
    protected VBox fxStartDateContainer;

    @FXML
    protected DatePicker fxEventStartDate;

    @FXML
    protected DatePicker fxEventEndDate;

    private final EventService eventService = new EventService();

    private static final Logger logger = LogManager.getLogger(EventFormController.class.getName());

    /**
     * Initializes the controller when the event form window is loaded.
     * Sets the start and end date fields to be non-editable.
     * Populates the status dropdown with active and inactive options.
     * If the form is in edit mode, it populates fields with existing event details.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            fxEventStartDate.setEditable(false);
            fxEventEndDate.setEditable(false);
            fxEventStatus.getItems().addAll("Active", "Inactive");

            if (getEventFormMode().equals(FormMode.EDIT)) {
                EventModel eventModel = getEventModelFromData();

                fxEventName.setText(eventModel.getName());
                fxEventLocation.setText(eventModel.getLocation());
                fxEventStatus.setValue(eventModel.getStatus());
                fxEventStartDate.setValue(LocalDate.parse(eventModel.getStartDate()));
                fxEventEndDate.setValue(LocalDate.parse(eventModel.getEndDate()));
            }
        });
    }

    /**
     * Handles the cancel button click event to close the event form window.
     * Closes the window upon click.
     */
    @FXML
    void onCancelEventForm(MouseEvent event) {
        logger.info("onCancelEventForm");
        Platform.runLater(() -> fxRoot.getScene().getWindow().hide());
    }

    /**
     * Handles the submit button click event to create or update the event.
     * Calls the create or update event method based on the form mode.
     */
    @FXML
    void onSubmitEventForm(MouseEvent event) {
        logger.info("onSubmitEventForm");

        if (getEventFormMode().equals(FormMode.CREATE)) {
            createEvent();
        } else {
            updateEvent();
        }
    }

    /**
     * Retrieves the form mode from the window's user data.
     * If the form mode is edit, it returns the edit mode.
     * Otherwise, it returns the create mode.
     *
     * @return the form mode
     */
    private void updateEvent() {
        eventService.put(getEventModelFromForm().setId(getEventModelFromData().getId()), new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<EventModel> call, @NotNull Response<EventModel> response) {
                logger.info("Update Event Success {} {}", response.code(), response.message());

                if (response.code() == 200) {
                    EventEvent.subject.onNext(EventEvent.Type.UPDATE);
                    Platform.runLater(() -> fxRoot.getScene().getWindow().hide());
                }
            }

            @Override
            public void onFailure(@NotNull Call<EventModel> call, @NotNull Throwable throwable) {
                logger.info("Update Event Failure {}", throwable.getMessage());
            }
        });
    }

    /**
     * Creates a new event with the data from the form.
     * Calls the event service to post the new event.
     * Upon success, logs the result and closes the window.
     */
    private void createEvent() {
        eventService.post(getEventModelFromForm(), new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<EventModel> call, @NotNull Response<EventModel> response) {
                logger.info("Create Event Success {} {}", response.code(), response.message());

                if (response.isSuccessful()) {
                    EventEvent.subject.onNext(EventEvent.Type.CREATE);
                    Platform.runLater(() -> fxRoot.getScene().getWindow().hide());
                }
            }

            @Override
            public void onFailure(@NotNull Call<EventModel> call, @NotNull Throwable throwable) {
                logger.info("Create Event Failure {}", throwable.getMessage());
            }
        });
    }

    /**
     * Retrieves the event model from the form fields.
     *
     * @return the event model
     */
    private EventModel getEventModelFromForm() {
        EventModel eventModel = new EventModel();
        eventModel.setName(fxEventName.getText());
        eventModel.setLocation(fxEventLocation.getText());
        eventModel.setStatus(fxEventStatus.getValue());
        eventModel.setStartDate(String.valueOf(fxEventStartDate.getValue()));
        eventModel.setEndDate(String.valueOf(fxEventEndDate.getValue()));

        logger.info("Event Model From Form: {}", new Gson().toJson(eventModel));

        return eventModel;
    }

    /**
     * Retrieves the form mode from the window's user data.
     * If the form mode is edit, it returns the edit mode.
     * Otherwise, it returns the create mode.
     *
     * @return the form mode
     */
    private FormMode getEventFormMode() {

        if (getData().get("mode").toString().equals(FormMode.EDIT.name())) {
            return FormMode.EDIT;
        }

        return FormMode.CREATE;
    }

    /**
     * Retrieves the event model object from the data passed to the form.
     *
     * @return the event model object
     */
    private EventModel getEventModelFromData() {
        var eventModel = (EventModel) getData().get("eventModel");

        logger.info("Event Model For Editing: {}", new Gson().toJson(eventModel));

        return eventModel;
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
