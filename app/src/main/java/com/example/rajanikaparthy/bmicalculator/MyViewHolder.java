package com.example.rajanikaparthy.bmicalculator;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by rajanikaparthy on 2017-12-20.
 */

public class MyViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = MyViewHolder.class.getSimpleName();

    public TextView txtHeight;
    public TextView txtWeight;
    public TextView txtDate;

    private final TextView mHeight;
    private final TextView mWeight;
    private final TextView mDate;

    private BMIValues mBMIValues;

    public MyViewHolder(final View itemView, final OnbmiEntryClickedListener listener) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                listener.OnbmiEntryClicked(mBMIValues);
            }
        });
        mHeight = itemView.findViewById(R.id.height_history);
        mWeight = itemView.findViewById(R.id.weight_history);
        mDate = itemView.findViewById(R.id.date_history);
    }

    public void bind(final BMIValues bmiEntry) {
        mBMIValues = bmiEntry;
        mHeight.setText(bmiEntry.getHeight());
        mWeight.setText(bmiEntry.getWeight());
        mDate.setText(bmiEntry.getDate());
    }

    public static MyViewHolder newInstance(final ViewGroup parent, final OnbmiEntryClickedListener listener) {
        return new MyViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.history_rv,
                        parent,
                        false
                ),
                listener
        );
    }
}