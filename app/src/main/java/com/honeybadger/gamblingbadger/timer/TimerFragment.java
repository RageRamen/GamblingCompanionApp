package com.honeybadger.gamblingbadger.timer;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.honeybadger.gamblingbadger.R;
import com.honeybadger.gamblingbadger.casinosession.CasinoSessionFragment;
import com.honeybadger.gamblingbadger.databinding.FragmentTimerBinding;

public class TimerFragment extends Fragment
{
    private FragmentTimerBinding binding;
    private Timer timer;

    NumberPicker hours, mins;
    TextView msg;
    Button setB; // first button
    Button confB; // second button
    int hr, min;

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

        timer = CasinoSessionFragment.casinoSession.getTimer(); // timer class is very important and is associated with its casinosession
        hours = binding.npH; //initializing global gui elements to be used in onclick functions
        mins = binding.npM;
        msg = binding.tv3;
        setB = binding.timerFragButtonSetTimer;
        confB = binding.conbutton;

        hours.setMinValue(0);  //setting values for number pickers
        hours.setMaxValue(23); hours.setValue(4); // default is 4:00
        mins.setMinValue(0); // valid range is [0:01,23:59]
        mins.setMaxValue(59); mins.setValue(0);

        initButtonSetTimer(); // setting onclick functions for buttons
        initButtonConfirm();

        if(timer.isRunning) // used to prevent user from setting multiple timers even if they exit the fragment
        {
            setB.setEnabled(false);
            confB.setEnabled(false);
            String timerRunningMsg = "Timer running.. come back in ";
            if(timer.msRemaining < 60000)
            {
                timerRunningMsg += "<1 min";
            }
            else
            {
                timerRunningMsg += ((timer.msRemaining / 60000) + " mins");
            }
            msg.setText(timerRunningMsg);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        // menu.clear();
        // inflater.inflate(R.menu.menu_blackjack, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) // makes the back button on the top left work
    {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (item.getItemId() == android.R.id.home)
        {
            actionGoToCasinoSession();
            return true; // must return true to consume it here
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
    }

    // Initialize Interface

    private void initButtonSetTimer() // if hour and min are valid, show confirmation message in text view and allow for confirmation button
    {
        binding.timerFragButtonSetTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hr = hours.getValue(); //converting and saving user input into integers for later use
                min = mins.getValue();
                String toBeSent = "Trying to break the program?"; // default error message if user inputs 0:0
                confB.setVisibility(View.INVISIBLE);
                if (min != 0 || hr != 0)
                {
                    toBeSent = "Set timer for ";
                    if (hr == 1)
                    {
                        toBeSent += ("1 hour");
                    } else if (hr > 1)
                    {
                        toBeSent += (hr + " hours");
                    }

                    if (hr > 0 && min > 0)   // string building so that it is grammatically correct
                    {
                        toBeSent += (" and ");
                    }

                    if (min == 1)
                    {
                        toBeSent += ("1 min");
                    } else if (min > 1)
                    {
                        toBeSent += (min + " mins");
                    }
                    toBeSent += "?";
                    confB.setVisibility(View.VISIBLE);
                }
                msg.setText(toBeSent);
            }
        });
    }

    private void initButtonConfirm()
    {
        binding.conbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                setB.setEnabled(false);
                confB.setVisibility(View.INVISIBLE);
                timer.isRunning = true;
                long millis  = ((hr*60)+min)*60000;  // calculating how much time (in ms) to put on the countdown timer
                Log.d("BEN","Timer started @ " + System.currentTimeMillis() + " for " + millis/60000 + " mins");
                CountDownTimer cdt = new CountDownTimer(millis,60000) //saving countdowntimer object to casino session
                {
                    long m;
                    @Override
                    public void onTick(long l) //updates gui in timer fragment every 1 minute
                    { // TODO: Make it so that onTick also updates a display in the action bar
                        m = l/60000;
                        String s = "Time (mins) remaining: ";
                        timer.msRemaining = l;
                        if(l < 60000)
                        {
                            s += "<1";
                        }
                        else
                        {
                            s += m;
                        }
                        msg.setText(s);
                    }

                    @Override
                    public void onFinish()
                    {
                        msg.setText("TIMER DONE!!"); //note: user most likely wont see this message as they will probably leave the timer fragment by the time the timer goes off
                        setB.setEnabled(true);
                        timer.isRunning = false; // user can now set a new timer
                        Log.d("BEN","Timer finished @ " + System.currentTimeMillis());
                        try
                        {
                            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION); // default notification
                            Ringtone r = RingtoneManager.getRingtone(getContext(), notification);
                            r.play();
                            Toast.makeText(getContext(),"Timer finished",Toast.LENGTH_LONG).show();
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }.start();
                timer.attachedCDT = cdt;
            }
        });
    }

    // ACTION METHODS:

    private void actionGoToCasinoSession() {
        NavHostFragment.findNavController(TimerFragment.this)
                .navigate(R.id.action_timerFragment_to_casinoSessionFragment);
    }
}