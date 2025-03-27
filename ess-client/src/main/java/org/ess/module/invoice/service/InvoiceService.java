package org.ess.module.invoice.service;

import org.ess.app.common.HttpClient;
import org.ess.module.invoice.repository.InvoiceRepository;
import org.ess.module.invoice.request.CreateInvoiceRequest;
import org.ess.module.invoice.request.CreateInvoiceResponse;
import retrofit2.Callback;

import java.util.List;

public class InvoiceService {

    /**
     * Fetches a list of all invoice models asynchronously and processes the result using a callback.
     *
     * @param callback the callback to handle the response containing a list of invoice models or an error
     */
    public void get(Callback<List<CreateInvoiceResponse>> callback) {
        HttpClient.use(InvoiceRepository.class).get().enqueue(callback);
    }

    /**
     * Fetches an invoice model by ID asynchronously and processes the result using a callback.
     *
     * @param id       the ID of the invoice model to retrieve
     * @param callback the callback to handle the response containing the invoice model or an error
     */
    public void get(long id, Callback<CreateInvoiceResponse> callback) {
        HttpClient.use(InvoiceRepository.class).get(id).enqueue(callback);
    }

    /**
     * Fetches a list of all invoice models asynchronously and processes the result using a callback.
     *
     * @param createInvoiceRequest the invoice to create
     * @param callback             the callback to handle the response containing a list of invoice models or an error
     */
    public void post(CreateInvoiceRequest createInvoiceRequest, Callback<CreateInvoiceResponse> callback) {
        HttpClient.use(InvoiceRepository.class).post(createInvoiceRequest).enqueue(callback);
    }
}
