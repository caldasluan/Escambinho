package com.unb.devapp.escambinho.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unb.devapp.escambinho.R;

public class EscambinhoFragment extends SearchFragment {
    public EscambinhoFragment() {
        // Required empty public constructor
    }

    public static EscambinhoFragment newInstance() {
        return new EscambinhoFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_escambinho, container, false);
    }

    @Override
    public void search(String text) {

    }
}
