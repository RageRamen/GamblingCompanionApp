package com.honeybadger.gamblingbadger.timer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.honeybadger.gamblingbadger.HomeFragment;
import com.honeybadger.gamblingbadger.R;
import com.honeybadger.gamblingbadger.casinosession.CasinoSessionFragment;
import com.honeybadger.gamblingbadger.databinding.FragmentTimerBinding;

public class TimerFragment extends Fragment {

    private FragmentTimerBinding binding;

    private Timer timer;

    NumberPicker hours;
    NumberPicker mins;
    TextView msg;
    Button conf;
    int hr;
    int min;

    // FRAGMENT EVENTS:

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Options menu
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {



        binding = FragmentTimerBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        timer = CasinoSessionFragment.casinoSession.getTimer();

        hours = binding.npH; //initializing global gui elements to be used in onclick functions
        mins = binding.npM;
        msg = binding.tv3;
        conf = binding.conbutton;
        hours.setMinValue(0);  //setting values for number pickers
        hours.setMaxValue(23);
        hours.setValue(4); // default is 4:00
        mins.setMinValue(0); // valid range is [0:01,23:59]
        mins.setMaxValue(59);
        mins.setValue(0);

        initButtonBack(); // setting onclick functions for buttons
        initButtonSetTimer();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // menu.clear();
        // inflater.inflate(R.menu.menu_blackjack, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (item.getItemId() == android.R.id.home) {
            return true; // must return true to consume it here
        }

        return super.onOptionsItemSelected(item);
    }

    // Initialize Interface

    private void initButtonBack() {
        binding.timerFragButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(TimerFragment.this)
                        .navigate(R.id.action_timerFragment_to_casinoSessionFragment);
            }
        });
    }

    private void initButtonSetTimer() // if hour and min are valid, show confirmation message in text view and allow for confirmation button
    {
        binding.timerFragButtonSetTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                hr = hours.getValue();
                min = mins.getValue();
                String toBeSent = "Trying to break the program?"; // default error message if user inputs 0:0
                conf.setVisibility(View.INVISIBLE);
                if(min != 0 || hr != 0)
                {
                    toBeSent = "Set timer for ";
                    if(hr == 1)
                    {
                        toBeSent += ("1 hour");
                    }
                    else if(hr > 1)
                    {
                        toBeSent += (hr + " hours");
                    }

                    if(hr > 0 && min > 0)
                    {
                        toBeSent += (" and ");
                    }

                    if(min == 1)
                    {
                        toBeSent += ("1 min");
                    }
                    else if(min > 1)
                    {
                        toBeSent += (min + " mins");
                    }
                    toBeSent += "?";
                    conf.setVisibility(View.VISIBLE);
                }
                msg.setVisibility(View.VISIBLE);
                msg.setText(toBeSent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}