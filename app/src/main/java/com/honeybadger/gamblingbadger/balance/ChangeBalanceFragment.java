package com.honeybadger.gamblingbadger.balance;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.honeybadger.gamblingbadger.R;
import com.honeybadger.gamblingbadger.casinosession.CasinoSessionFragment;
import com.honeybadger.gamblingbadger.casinosession.StartSessionFragment;
import com.honeybadger.gamblingbadger.databinding.FragmentCasinoSessionBinding;
import com.honeybadger.gamblingbadger.databinding.FragmentChangeBalanceBinding;

public class ChangeBalanceFragment extends Fragment
{

    private FragmentChangeBalanceBinding binding;
    private AlertDialog.Builder alert;
    private AlertDialog.Builder alerts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Options menu
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        changeAddBalAlert();
        changeSubBalAlert();
        binding = FragmentChangeBalanceBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        initButtonAdd();
        initButtonSub();
        //Removing Back Button
        //initBackButton();

    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
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
            actionGoBackHome();
            return true; // must return true to consume it here
        }
        return super.onOptionsItemSelected(item);
    }

    // INTERFACE:
    /*private void initActionBarBalance() {
        TextView balanceText = getActivity().findViewById(R.id.activityMain_appBar_textView_balance_text);
        balanceText.setText(R.string.actionBar_balance_text);
    }*/

    private void changeAddBalAlert() {
        alert = new AlertDialog.Builder(this.getActivity());
        alert.setTitle("Balance Update Warning");
        alert.setMessage("Are you sure you want to Add to balance?");
        alert.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        actionGoToAdd();
                    }
                });
        alert.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {}
                });
    }

    private void changeSubBalAlert() {
        alerts = new AlertDialog.Builder(this.getActivity());
        alerts.setTitle("Balance Update Warning");
        alerts.setMessage("Are you sure you want to Subtract from balance?");
        alerts.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        actionGoToSub();
                    }
                });
        alerts.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {}
                });
    }

    private void initButtonAdd() {
        binding.changBalFragButtonAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            // on click, display alert: are you sure you want to add to balance
            public void onClick(View view)  {
                alert.show();
            }
        });
    }

    private void initButtonSub() {
        binding.changBalFragButtonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alerts.show();
            }
        });
    }

   /* private void initBackButton() {
        binding.changBalFragButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionGoBackHome();
            }
        });
    }*/


    // ACTIONS:

    private void actionGoToAdd() {
        NavHostFragment.findNavController(ChangeBalanceFragment.this)
                .navigate(R.id.action_changeBalanceFragment_to_addBalanceFragment);
    }

    private void actionGoToSub() {
        NavHostFragment.findNavController((ChangeBalanceFragment.this))
                .navigate(R.id.action_changeBalanceFragment_to_subBalanceFragment);
    }

    private void actionGoBackHome() {
        // Navigate to casino session
        NavHostFragment.findNavController(ChangeBalanceFragment.this)
                .navigate(R.id.action_changeBalanceFragment_to_casinoSessionFragment);
    }

}