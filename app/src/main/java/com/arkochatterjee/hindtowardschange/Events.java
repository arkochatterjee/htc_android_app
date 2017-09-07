package com.arkochatterjee.hindtowardschange;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Arko Chatterjee on 06-09-2017.
 */

public class Events extends Fragment {

    private TextView text;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.domain_comingsoon, container, false);

        //  text = (TextView) v.findViewById(R.id.home_text);
        //  Typeface typeface1 = Typeface.createFromAsset(getActivity().getAssets(), "font/LatoLight.ttf");
        // text.setTypeface(typeface1);

        return v;

    }

}
