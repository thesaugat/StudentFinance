package com.thesaugat.studentfinance.server;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by sauga on 1/8/2018.
 */

public interface ApiServices {

    @POST("studentfinance/")
    Call<ServerResponse> LoginSignUpRequest(@Body ServerRequest request);

    @POST("studentfinance/")
    Call<List<TransactionResponse>> transactionHistoryRequest(@Body ServerRequest request);

    @POST("studentfinance/")
    Call<SumaryPojo> sumaryDataRequest(@Body ServerRequest request);
}
