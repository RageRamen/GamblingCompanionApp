package com.honeybadger.gamblingbadger.casinosession;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.honeybadger.gamblingbadger.R;
import com.honeybadger.gamblingbadger.balance.Balance;
import com.honeybadger.gamblingbadger.databinding.FragmentStartSessionBinding;

import java.math.BigDecimal;

public class StartSessionFragment extends Fragment {

    private FragmentStartSessionBinding binding;

    private AlertDialog.Builder alertInvalidBalance;

    // Fragment Events:

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        // Alert Builder
        initAlertBuilderEndSession();

        binding = FragmentStartSessionBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initStartButton();
        initBackButton();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // Alert Initializers:

    private void initAlertBuilderEndSession() {
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

    private void initStartButton() {
        binding.startSessionFragButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionStartCasinoSession();
            }
        });
    }

    private void initBackButton() {
        binding.startSessionFragButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionGoBackHome();
            }
        });
    }

    // Actions:

    private void actionStartCasinoSession() {
        // Get input
        // TODO: vv Make sure valid input vv
        BigDecimal inputBalance;
        try {
            inputBalance = new BigDecimal(binding.startSessionFragEditTextNumberDecimalBalance.getText().toString());
        }
        catch (NumberFormatException e) {
            alertInvalidBalance.show();
            return;
        }

        // Create Casino Session
        CasinoSessionFragment.casinoSession = new CasinoSession(new Balance(inputBalance));

        // Navigate to Casino Session
        NavHostFragment.findNavController(StartSessionFragment.this)
                .navigate(R.id.action_startSessionFragment_to_inSessionFragment);
    }

    private void actionGoBackHome() {
        // Navigate Home
        NavHostFragment.findNavController(StartSessionFragment.this)
                .navigate(R.id.action_startSessionFragment_to_homeFragment);
    }

}