package com.honeybadger.gamblingbadger.gamesession;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.honeybadger.gamblingbadger.R;
import com.honeybadger.gamblingbadger.databinding.FragmentStartGameBinding;

import java.util.Objects;

public class StartGameFragment extends Fragment {

    private FragmentStartGameBinding binding;

    private NumberPicker numberPickerPickGame;

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
        initNumberPickerPickGame();
        initButtonBack();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // Interface Initializers:

    private void initNumberPickerPickGame() {
        numberPickerPickGame = getActivity().findViewById(R.id.startGameFrag_numberPicker_pickGame);
        numberPickerPickGame.setMinValue(0);
        numberPickerPickGame.setMaxValue(4);
        numberPickerPickGame.setWrapSelectorWheel(false);

        String[] pickerVals = new String[5];
        pickerVals[0] = getResources().getString(R.string.blackjack_title);
        pickerVals[1] = getResources().getString(R.string.poker_title);
        pickerVals[2] = getResources().getString(R.string.texasHoldem_title);
        pickerVals[3] = getResources().getString(R.string.roulette_title);
        pickerVals[4] = getResources().getString(R.string.americanRoulette_title);

        numberPickerPickGame.setDisplayedValues(pickerVals);
    }

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