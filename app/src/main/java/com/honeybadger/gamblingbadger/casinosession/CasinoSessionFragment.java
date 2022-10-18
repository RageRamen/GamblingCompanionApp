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
    private AlertDialog.Builder alertBuilderEndSession;

    public static CasinoSession casinoSession;

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
        initButtonEndSession();

    }

    // Interface Initializers:

    private void initAlertBuilderEndSession() {
        alertBuilderEndSession = new AlertDialog.Builder(this.getActivity());
        alertBuilderEndSession.setCancelable(true);
        alertBuilderEndSession.setTitle(R.string.casinoSessionFrag_alert_endSession_title);
        alertBuilderEndSession.setMessage(R.string.casinoSessionFrag_alert_endSession_message);
        alertBuilderEndSession.setPositiveButton(R.string.casinoSessionFrag_alert_endSession_positive,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        actionEndSession();
                    }
                });
        alertBuilderEndSession.setNegativeButton(R.string.casinoSessionFrag_alert_endSession_negative,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {}
                });
    }

    private void initButtonEndSession() {
        binding.casinoSessionFragButtonEndSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertBuilderEndSession.show();
            }
        });
    }

    // Actions:

    private void actionEndSession() {
        // TODO: Ask to confirm
        casinoSession.closeSession();

        NavHostFragment.findNavController(CasinoSessionFragment.this)
                .navigate(R.id.action_casinoSessionFragment_to_homeFragment);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}