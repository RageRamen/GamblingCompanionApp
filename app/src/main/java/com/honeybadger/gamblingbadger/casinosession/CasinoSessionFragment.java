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
import com.honeybadger.gamblingbadger.databinding.FragmentCasinoSessionBinding;

public class CasinoSessionFragment extends Fragment {

    private FragmentCasinoSessionBinding binding;
    private AlertDialog.Builder alertEndSession;

    public static CasinoSession casinoSession;

    // Fragment Events:

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        // Alert Builder
        initAlertBuilderEndSession();

        // Binding
        binding = FragmentCasinoSessionBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Visual
        binding.casinoSessionFragTextviewText.setText(casinoSession.getBalance().getBalance().toString());

        // Button bindings
        initButtonStartGame();
        initButtonSetTimer();
        initButtonEndSession();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // Alert Initializers:

    private void initAlertBuilderEndSession() {
        alertEndSession = new AlertDialog.Builder(this.getActivity());
        alertEndSession.setCancelable(true);
        alertEndSession.setTitle(R.string.casinoSessionFrag_alert_endSession_title);
        alertEndSession.setMessage(R.string.casinoSessionFrag_alert_endSession_message);
        alertEndSession.setPositiveButton(R.string.casinoSessionFrag_alert_endSession_positive,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        actionEndSession();
                    }
                });
        alertEndSession.setNegativeButton(R.string.casinoSessionFrag_alert_endSession_negative,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {}
                });
    }

    // Interface Initializers:

    private void initButtonStartGame() {
        binding.casinoSessionFragButtonStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionGoToStartGame();
            }
        });
    }

    private void initButtonSetTimer() {
        binding.casinoSessionFragButtonSetTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionGoToTimer();
            }
        });
    }

    private void initButtonEndSession() {
        binding.casinoSessionFragButtonEndSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertEndSession.show();
            }
        });
    }

    // Actions:

    private void actionGoToStartGame() {
        NavHostFragment.findNavController(CasinoSessionFragment.this)
                .navigate(R.id.action_casinoSessionFragment_to_startGameFragment);
    }

    private void actionGoToTimer() {
        NavHostFragment.findNavController(CasinoSessionFragment.this)
                .navigate(R.id.action_casinoSessionFragment_to_timerFragment);
    }

    private void actionEndSession() {
        // TODO: Ask to confirm
        casinoSession.closeSession();

        NavHostFragment.findNavController(CasinoSessionFragment.this)
                .navigate(R.id.action_casinoSessionFragment_to_homeFragment);
    }

}