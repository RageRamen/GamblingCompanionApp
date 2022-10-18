package com.honeybadger.gamblingbadger.casinosession;

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

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {


        //
        binding = FragmentStartSessionBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonStartSessionStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get input
                BigDecimal inputBal = new BigDecimal(binding.editTextNumberDecimalStartSessionBalance.getText().toString());

                // Create Casino Session
                if (CasinoSessionFragment.casinoSession.isSessionOpen())
                    CasinoSessionFragment.casinoSession.closeSession();
                CasinoSessionFragment.casinoSession = new CasinoSession(new Balance(inputBal));

                // Navigate to Casino Session
                NavHostFragment.findNavController(StartSessionFragment.this)
                        .navigate(R.id.action_startSessionFragment_to_inSessionFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}