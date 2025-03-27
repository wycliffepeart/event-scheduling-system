package org.ess.module.invoice.repository;

import org.ess.module.invoice.request.CreateInvoiceRequest;
import org.ess.module.invoice.request.CreateInvoiceResponse;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface InvoiceRepository {

    /**
     * Retrieves an invoice from the server.
     *
     * @param id the ID of the invoice to retrieve
     * @return the invoice
     */
    @GET("/api/invoices/{id}")
    Call<CreateInvoiceResponse> get(@Path("id") long id);

    /**
     * Updates an invoice on the server.
     *
     * @return the updated invoice
     */
    @GET("/api/invoices")
    Call<List<CreateInvoiceResponse>> get();

    /**
     * Creates a new invoice on the server.
     *
     * @param createInvoiceRequest the invoice to create
     * @return the created invoice
     */
    @POST("/api/invoices")
    Call<CreateInvoiceResponse> post(@Body CreateInvoiceRequest createInvoiceRequest);
}
