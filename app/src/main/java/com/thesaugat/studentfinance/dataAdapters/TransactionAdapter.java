package com.thesaugat.studentfinance.dataAdapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thesaugat.studentfinance.R;
import com.thesaugat.studentfinance.server.TransactionResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sauga on 1/9/2018.
 */

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
    Context context;
    List<TransactionResponse> transactionResponses;

    public TransactionAdapter(Context context, List<TransactionResponse> transactionResponses) {
        this.context = context;
        this.transactionResponses = transactionResponses;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.payment_history_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.date.setText(transactionResponses.get(position).getPaiddatetime());
        holder.tutionfee.setText(transactionResponses.get(position).getTutionfee());
        holder.total.setText(transactionResponses.get(position).getTotal());
        holder.extraFee.setText(transactionResponses.get(position).getExtras());
        holder.semesterfee.setText(transactionResponses.get(position).getSemesterfee());
        holder.due.setText("-"+transactionResponses.get(position).getDueAmount()+"(due)");
    }

    @Override
    public int getItemCount() {
        return transactionResponses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.tutionfee)
        TextView tutionfee;
        @BindView(R.id.semesterfee)
        TextView semesterfee;
        @BindView(R.id.extraFee)
        TextView extraFee;
        @BindView(R.id.total)
        TextView total;
        @BindView(R.id.due)
        TextView due;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
