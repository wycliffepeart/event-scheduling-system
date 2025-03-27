package org.ess.module.bookings.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ess.module.bookings.event.BookingEvent;
import org.ess.module.bookings.service.BookingService;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class BookingDeleteConfirmationController implements Initializable {

    @FXML
    protected VBox fxRoot;

    private final BookingService bookingService = new BookingService();

    private final Logger logger = LogManager.getLogger(BookingDeleteConfirmationController.class.getName());

    /**
     * Initializes the controller when the booking delete confirmation window is loaded.
     * Logs an informational message upon initialization.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logger.info("Initialize");
    }

    /**
     * Handles the delete button click event to delete the booking.
     * Retrieves booking data from the window's user data and calls the booking deletion service.
     * Upon success, logs the result and closes the window.
     */
    @FXML
    @SuppressWarnings("unchecked")
    void onClickDeleteBooking(MouseEvent event) {
        logger.info("Delete Booking");

        Map<String, Object> data = (Map<String, Object>) fxRoot.getScene().getWindow().getUserData();

        bookingService.delete((Long) data.get("id"), new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<Boolean> call, @NotNull Response<Boolean> response) {
                logger.info("Delete Booking Success {} {}", response.code(), response.message());
                Platform.runLater(() -> fxRoot.getScene().getWindow().hide());
                BookingEvent.subject.onNext(BookingEvent.Type.DELETE);
            }

            @Override
            public void onFailure(@NotNull Call<Boolean> call, @NotNull Throwable throwable) {
                logger.info("{} {}", throwable.getMessage(), throwable.getCause());
            }
        });
    }

    /**
     * Handles the cancel button click event to close the window.
     */
    @FXML
    void onClickCancelDeleteBooking(MouseEvent event) {
        logger.info("Delete Booking Cancelled");

        fxRoot.getScene().getWindow().hide();
    }
}
