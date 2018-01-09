package com.thesaugat.studentfinance.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.thesaugat.studentfinance.R;
import com.thesaugat.studentfinance.dataAdapters.TransactionAdapter;
import com.thesaugat.studentfinance.helper.Constants;
import com.thesaugat.studentfinance.server.ApiClient;
import com.thesaugat.studentfinance.server.ApiServices;
import com.thesaugat.studentfinance.server.ServerRequest;
import com.thesaugat.studentfinance.server.SumaryPojo;
import com.thesaugat.studentfinance.server.TransactionResponse;
import com.thesaugat.studentfinance.utils.SharedPreferencesUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.student_id)
    TextView studentId;
    @BindView(R.id.student_class)
    TextView studentClass;
    @BindView(R.id.current_due)
    TextView currentDue;
    @BindView(R.id.dead_line)
    TextView deadLine;
    @BindView(R.id.card_view)
    CardView cardView;
    @BindView(R.id.history_container)
    RecyclerView historyContainer;
    @BindView(R.id.name)
    TextView name;
    ApiServices apiServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        apiServices = ApiClient.getClient().create(ApiServices.class);


        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.GET_SUMARY);
        request.setSymbolno(SharedPreferencesUtils.getStringPreference(this, Constants.SYMBOLNO));
        Call<SumaryPojo> call = apiServices.sumaryDataRequest(request);
        call.enqueue(new Callback<SumaryPojo>() {
            @Override
            public void onResponse(Call<SumaryPojo> call, Response<SumaryPojo> response) {
                if (response != null) {

                    deadLine.setText(response.body().getDeadline());
                    studentClass.setText(response.body().getFaculty());
                    currentDue.setText(response.body().getDue());
                    studentId.setText(SharedPreferencesUtils.getStringPreference(HomeActivity.this, Constants.SYMBOLNO));
                    name.setText(SharedPreferencesUtils.getStringPreference(HomeActivity.this, Constants.NAME));
                    loadTransactions();

                }

            }

            @Override
            public void onFailure(Call<SumaryPojo> call, Throwable t) {

            }
        });


    }

    private void loadTransactions() {
        apiServices = ApiClient.getClient().create(ApiServices.class);

        ServerRequest request1 = new ServerRequest();
        request1.setOperation(Constants.GET_TRANSACTION);
        request1.setSymbolno(SharedPreferencesUtils.getStringPreference(this, Constants.SYMBOLNO));
        Call<List<TransactionResponse>> call1 = apiServices.transactionHistoryRequest(request1);
        call1.enqueue(new Callback<List<TransactionResponse>>() {
            @Override
            public void onResponse(Call<List<TransactionResponse>> call, Response<List<TransactionResponse>> response) {
                TransactionAdapter transactionAdapter = new TransactionAdapter(getApplicationContext(), response.body());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                historyContainer.setLayoutManager(mLayoutManager);
                historyContainer.setItemAnimator(new DefaultItemAnimator());
                historyContainer.setAdapter(transactionAdapter);
//        System.out.println("hotel pojo list size is:" + hotelPojoList.size() + "imhlinkis: " + hotelPojoList.get(0).getImageLink());
            }

            @Override
            public void onFailure(Call<List<TransactionResponse>> call, Throwable t) {

            }
        });

    }
}
