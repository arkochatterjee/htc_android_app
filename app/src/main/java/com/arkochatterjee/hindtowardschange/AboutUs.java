package com.arkochatterjee.hindtowardschange;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Arko Chatterjee on 12-08-2017.
 */

public class AboutUs extends Fragment {

    private TextView text;
    public AboutUs() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.aboutus, container, false);

        //  text = (TextView) v.findViewById(R.id.home_text);
        //  Typeface typeface1 = Typeface.createFromAsset(getActivity().getAssets(), "font/LatoLight.ttf");
        // text.setTypeface(typeface1);

        return v;

    }

}
