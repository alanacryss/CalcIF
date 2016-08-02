package com.jho.alana.calcif;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by alana on 31/07/16.
 */
public class Pager extends Fragment {

    public static Pager newInstance() {
        Pager pager = new Pager();
        return pager;
    }

    public Pager() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.pager, container, false);

        return rootView;
    }

}
