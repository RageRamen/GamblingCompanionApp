package com.honeybadger.gamblingbadger.tips;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.honeybadger.gamblingbadger.R;
import com.honeybadger.gamblingbadger.databinding.FragmentRouletteTipBinding;
import com.honeybadger.gamblingbadger.databinding.FragmentTimerBinding;
import com.honeybadger.gamblingbadger.timer.TimerFragment;


public class RouletteTipFrag extends Fragment
{
    private FragmentRouletteTipBinding binding;
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
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) // makes the back button on the top left work
    {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (item.getItemId() == android.R.id.home)
        {
            popFrag();
            return true; // must return true to consume it here
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentRouletteTipBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    private void popFrag()
    {
        NavHostFragment.findNavController(RouletteTipFrag.this)
                .navigate(R.id.action_rouletteTipFrag_pop);
    }
}