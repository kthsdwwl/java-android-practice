package com.mini2assignment4.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.MapView;
import com.mini2assignment4.R;


/**
 * Created by xlin2 on 2015/11/16.
 */
public class GeoFragment extends Fragment {
    private Button sendBtn;
    private MapView mapView;

    public GeoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_geo, container, false);

        // Initialize map view
        mapView = (MapView) view.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();

        // Initialize button
        sendBtn = (Button) view.findViewById(R.id.btn_send);
        sendBtn.setOnClickListener(new SendInfoOnClickListener(getActivity(), mapView));
        return view;
    }
}
