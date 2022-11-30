package com.honeybadger.gamblingbadger.tips;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.honeybadger.gamblingbadger.R;


public class RouletteTipFrag extends Fragment
{

    public RouletteTipFrag()
    {
        // Required empty public constructor
    }

    public static RouletteTipFrag newInstance(String param1, String param2)
    {
        RouletteTipFrag fragment = new RouletteTipFrag();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_roulette_tip, container, false);
    }
}