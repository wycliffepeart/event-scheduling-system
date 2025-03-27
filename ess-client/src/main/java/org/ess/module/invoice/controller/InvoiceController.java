package org.ess.module.invoice.controller;

import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ess.module.bookings.model.BookingResponse;
import org.ess.module.invoice.pojo.InvoiceTableData;
import org.ess.module.invoice.request.CreateInvoiceResponse;
import org.ess.module.invoice.service.InvoiceService;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

public class InvoiceController implements Initializable {

    @FXML
    protected VBox fxRoot;

    @FXML
    protected Text fxInvoiceEventName;

    @FXML
    protected Text fxStartDate;

    @FXML
    protected Text fxEndDate;

    @FXML
    protected Text fxInvoiceTotal;

    @FXML
    protected TableView<InvoiceTableData> fxInvoiceTable;

    private final InvoiceService invoiceService = new InvoiceService();

    protected static final Logger logger = LogManager.getLogger(InvoiceController.class);

    private final ObservableList<InvoiceTableData> userModelObservableList = FXCollections.observableArrayList(new ArrayList<>());

    /**
     * Initializes the controller when the invoice window is loaded.
     * Sets up the table columns and populates the invoice table.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        TableColumn<InvoiceTableData, String> itemStartDateColumn = new TableColumn<>("Start Date");
        itemStartDateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getStartDate()));

        TableColumn<InvoiceTableData, String> itemEndDateColumn = new TableColumn<>("End Date");
        itemEndDateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getEndDate()));

        TableColumn<InvoiceTableData, Double> itemPriceColumn = new TableColumn<>("Price");
        itemPriceColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPrice()));

        TableColumn<InvoiceTableData, Double> itemTotalColumn = new TableColumn<>("Total");
        itemTotalColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTotal()));

        fxInvoiceTable
                .getColumns()
                .addAll(itemStartDateColumn, itemEndDateColumn, itemPriceColumn, itemTotalColumn);

        Platform.runLater(this::requestEvents);
        fxInvoiceTable.setItems(userModelObservableList);
    }

    /**
     * Retrieves invoice data from the server and populates the invoice table.
     * Upon success, logs the result.
     */
    void requestEvents() {
        var bookingModel = getBookingFromData();
        invoiceService.get(bookingModel.getInvoiceId(), new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<CreateInvoiceResponse> call, @NotNull Response<CreateInvoiceResponse> response) {
                logger.info("Get Success {} {}", response.code(), response.message());

                if (response.isSuccessful() && response.body() != null) {
                    var invoiceResponse = response.body();
                    fxInvoiceEventName.setText(invoiceResponse.getEvent().getName());
                    fxStartDate.setText(invoiceResponse.getEvent().getStartDate());
                    fxEndDate.setText(invoiceResponse.getEvent().getEndDate());
                    fxInvoiceTotal.setText("$" + invoiceResponse.getAmount());
                    userModelObservableList.addAll(response.body().getBookings().stream().map(item ->
                            InvoiceTableData
                                    .builder()
                                    .price(item.getPrice())
                                    .total(item.getTotal())
                                    .name(item.getEvent().getName())
                                    .startDate(item.getStartDate())
                                    .endDate(item.getEndDate())
                                    .build()
                    ).toList());

                    fxInvoiceTable.setItems(userModelObservableList);
                }
            }

            @Override
            public void onFailure(@NotNull Call<CreateInvoiceResponse> call, @NotNull Throwable throwable) {
                logger.error(throwable);
            }
        });
    }

    /**
     * Retrieves booking data from the form data.
     *
     * @return the booking model
     */
    private BookingResponse getBookingFromData() {
        var bookingModel = (BookingResponse) getData().get("bookingModel");

        logger.info("Booking Model For Editing: {}", new Gson().toJson(bookingModel));

        return bookingModel;
    }

    /**
     * Retrieves the data passed to the window.
     *
     * @return the data
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> getData() {
        return (Map<String, Object>) fxRoot.getScene().getWindow().getUserData();
    }
}
