package com.jho.alana.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jho.alana.calcif.Main2Activity;
import com.jho.alana.calcif.R;

import java.io.Serializable;


public class CalcFragmentl extends Fragment{

  private TextView textView;
  private String expression = "jh";
  private Main2Activity m;

  public static CalcFragmentl newInstance(){
    CalcFragmentl calcFragmentl = new CalcFragmentl();

    return calcFragmentl;
  }

  /*@SuppressLint("ValidFragment") public CalcFragmentl(Main2Activity m){
    this.m = m;
  }*/

  public CalcFragmentl(){}

  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
    View rootView = inflater.inflate(R.layout.content_main, container, false);

    //TextView textView = (TextView) rootView.findViewById(R.id.tvCalc);

    return rootView;
  }
}
