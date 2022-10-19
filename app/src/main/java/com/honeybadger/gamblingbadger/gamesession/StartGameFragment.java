package com.honeybadger.gamblingbadger.gamesession;

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
import com.honeybadger.gamblingbadger.databinding.FragmentStartGameBinding;

import java.math.BigDecimal;

public class StartGameFragment extends Fragment {

    private FragmentStartGameBinding binding;

    // Fragment Events:

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentStartGameBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Init Interface
        initButtonBack();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // Interface Initializers:

    private void initButtonBack() {
        binding.startGameFragButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionGoBack();
            }
        });
    }

    // Actions:

    private void actionGoBack() {
        // Navigate Home
        NavHostFragment.findNavController(StartGameFragment.this)
                .navigate(R.id.action_startGameFragment_to_casinoSessionFragment);
    }

}