package com.honeybadger.gamblingbadger.casinosession;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.honeybadger.gamblingbadger.R;
import com.honeybadger.gamblingbadger.databinding.FragmentCasinoSessionBinding;

import java.math.BigDecimal;

public class CasinoSessionFragment extends Fragment {

    private FragmentCasinoSessionBinding binding;

    public static CasinoSession casinoSession;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentCasinoSessionBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonCasinoSessionEndSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Ask to confirm
                casinoSession.closeSession();

                NavHostFragment.findNavController(CasinoSessionFragment.this)
                        .navigate(R.id.action_casinoSessionFragment_to_homeFragment);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}