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

public class StartGameFragment extends Fragment {

    // CONSTANTS:

    // VARIABLES:

    private FragmentStartGameBinding binding;

    private EGameType selectedGameType = EGameType.GT_BLACKJACK;

    // FRAGMENT EVENTS:

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
        initButtonStartGame();
        initButtonBack();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // INTERFACE:

    private void initNumberPickerPickGame() {
        NumberPicker numberPickerPickGame = getActivity().findViewById(R.id.startGameFrag_numberPicker_pickGame);
        numberPickerPickGame.setMinValue(0);
        numberPickerPickGame.setMaxValue(EGameType.getNumberOfGames()-1);
        numberPickerPickGame.setWrapSelectorWheel(false);

        String[] pickerVals = new String[EGameType.getNumberOfGames()];
        pickerVals[EGameType.GT_BLACKJACK.INDEX] = getResources().getString(R.string.blackjack_title);
        pickerVals[EGameType.GT_POKER.INDEX] = getResources().getString(R.string.poker_title);
        pickerVals[EGameType.GT_TEXAS_HOLDEM.INDEX] = getResources().getString(R.string.texasHoldem_title);
        pickerVals[EGameType.GT_ROULETTE.INDEX] = getResources().getString(R.string.roulette_title);
        pickerVals[EGameType.GT_AMERICAN_ROULETTE.INDEX] = getResources().getString(R.string.americanRoulette_title);

        numberPickerPickGame.setDisplayedValues(pickerVals);

        numberPickerPickGame.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                actionSetSelectedGame(EGameType.fromIndex(numberPicker.getValue()));
            }
        });
    }

    private void initButtonStartGame() {
        binding.startGameFragButtonStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionStartSelectedGame();
            }
        });
    }

    private void initButtonBack() {
        binding.startGameFragButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionNavigateBackToCasinoSession();
            }
        });
    }

    // ACTIONS:

    private void actionSetSelectedGame(EGameType gameType) {
        selectedGameType = gameType;
    }

    private void actionStartSelectedGame() {
            int navigateTo;

            switch (selectedGameType) {
                case GT_BLACKJACK: navigateTo = R.id.action_startGameFragment_to_blackjackFragment; break;
                case GT_POKER: navigateTo = R.id.action_startGameFragment_to_pokerFragment; break;
                case GT_TEXAS_HOLDEM: navigateTo = R.id.action_startGameFragment_to_texasHoldemFragment; break;
                case GT_ROULETTE:
                case GT_AMERICAN_ROULETTE: navigateTo = R.id.action_startGameFragment_to_rouletteFragment; break;
                default: return;
            }

            NavHostFragment.findNavController(StartGameFragment.this)
                    .navigate(navigateTo);
    }

    private void actionNavigateBackToCasinoSession() {
        // Navigate Home
        NavHostFragment.findNavController(StartGameFragment.this)
                .navigate(R.id.action_startGameFragment_to_casinoSessionFragment);
    }

}