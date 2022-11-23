package com.honeybadger.gamblingbadger.balance;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.honeybadger.gamblingbadger.R;
import com.honeybadger.gamblingbadger.casinosession.CasinoSession;
import com.honeybadger.gamblingbadger.casinosession.CasinoSessionFragment;
import com.honeybadger.gamblingbadger.databinding.FragmentAddBalanceBinding;

import java.math.BigDecimal;

public class AddBalanceFragment extends Fragment {

    private FragmentAddBalanceBinding binding;

    private AlertDialog.Builder alertInvalidBalance;

    // Fragment Events:

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

        // Alert Builder
        initAlertBuildersEndSession();

        binding = FragmentAddBalanceBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initAddButton();
        initBackButton();
    }

    @Override
    public void onDestroyView() {
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

    // Alert Initializers:

    private void initAlertBuildersEndSession() {
        alertInvalidBalance = new AlertDialog.Builder(this.getActivity());
        alertInvalidBalance.setCancelable(true);
        alertInvalidBalance.setTitle(R.string.startSessionFrag_alert_invalidBalance_title);
        alertInvalidBalance.setMessage(R.string.startSessionFrag_alert_invalidBalance_message);
        alertInvalidBalance.setNeutralButton(R.string.startSessionFrag_alert_invalidBalance_neutral,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
    }

    // Interface Initializers:

    private void initAddButton() {
        binding.addBalanceFragButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddToBalance();
            }
        });
    }

    private void initBackButton() {
        binding.addBalanceFragButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionGoBackHome();
            }
        });
    }

    // Actions:

    private void actionAddToBalance() {
        // Get input
        // TODO: vv Make sure valid input vv
        BigDecimal addAmount;
        try {
            addAmount = new BigDecimal(binding.addBalanceFragAddAmt.getText().toString());

        }
        catch (NumberFormatException e) {
            alertInvalidBalance.show();
            return;
        }

        // Add addAmount to balance on running Casino Session
        assert CasinoSessionFragment.casinoSession != null;
        //Get the existing balance in a casino session by calling getBalance()
        // the Balance returned by getBalance() is the balance of the casino session itself
        Balance casinoSessionBalance = CasinoSessionFragment.casinoSession.getBalance();
        casinoSessionBalance.addToBalance(addAmount); //test

        // Navigate to Casino Session
        NavHostFragment.findNavController(AddBalanceFragment.this)
                .navigate(R.id.action_addBalanceFragment_to_casinoSessionFragment);
    }

    private void actionGoBackHome() {
        // Navigate Home
        NavHostFragment.findNavController(AddBalanceFragment.this)
                .navigate(R.id.action_addBalanceFragment_to_casinoSessionFragment);
    }

}