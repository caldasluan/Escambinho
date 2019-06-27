package com.unb.devapp.escambinho.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unb.devapp.escambinho.R;

public class HistoricFragment extends SearchFragment {

    public HistoricFragment() {
        // Required empty public constructor
    }

    public static HistoricFragment newInstance() {
        return new HistoricFragment();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_historic, container, false);
    }

    @Override
    public void search(String text) {

    }
}
