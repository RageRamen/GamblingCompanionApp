package com.honeybadger.gamblingbadger.tips;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.honeybadger.gamblingbadger.R;
import com.honeybadger.gamblingbadger.timer.TimerFragment;

public class pokerHands extends Fragment {


    public pokerHands()
    {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (item.getItemId() == android.R.id.home)
        {
            goBack();
            return true; // must return true to consume it here
        }

        return super.onOptionsItemSelected(item);
    }

    // ACTIONS:

    private void goBack()
    {
        NavHostFragment.findNavController(pokerHands.this)
                .navigate(R.id.action_pokerHandsFrag_pop);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_poker_hands, container, false);
    }
}