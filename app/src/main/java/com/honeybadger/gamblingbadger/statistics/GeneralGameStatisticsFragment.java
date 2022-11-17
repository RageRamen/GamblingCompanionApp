package com.honeybadger.gamblingbadger.statistics;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.honeybadger.gamblingbadger.R;
import com.honeybadger.gamblingbadger.databinding.FragmentGeneralGameStatisticsBinding;

public class GeneralGameStatisticsFragment extends Fragment {
// CONSTANTS:

    // VARIABLES:

    private FragmentGeneralGameStatisticsBinding binding;

    public static Integer returnFragmentID = R.id.generalGameStatisticsFragment;

    // FRAGMENT EVENTS:

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
        binding = FragmentGeneralGameStatisticsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Init Interface
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();

        inflater.inflate(R.menu.menu_statistics, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            actionReturnToCallingFrag();
            return true; // must return true to consume it here
        }
        else if (id == R.id.menu_statistics_item_generalStatistics) {
            actionGoToGeneralStatistics();
            return true;
        }
        else if (id == R.id.menu_statistics_item_casinoStatistics) {
            actionGoToCasinoStatistics();
            return true;
        }
        else if (id == R.id.menu_statistics_item_casinoGameStatistics) {
            actionGoToSpecificGameStatistics();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // INTERFACE:

    // ACTIONS:

    private void actionReturnToCallingFrag() {

        if (returnFragmentID == R.id.homeFragment)
            NavHostFragment.findNavController(this).navigate(R.id.action_generalGameStatisticsFragment_to_homeFragment);
        else if (returnFragmentID == R.id.casinoSessionFragment)
            NavHostFragment.findNavController(this).navigate(R.id.action_generalGameStatisticsFragment_to_casinoSessionFragment);
    }

    private void actionGoToHome() {
        NavHostFragment.findNavController(this).navigate(R.id.action_generalGameStatisticsFragment_to_homeFragment);
    }

    private void actionGoToCasinoSession() {
        NavHostFragment.findNavController(this).navigate(R.id.action_generalGameStatisticsFragment_to_casinoSessionFragment);
    }

    private void actionGoToGeneralStatistics() {
        GeneralStatisticsFragment.returnFragmentID = returnFragmentID;
        NavHostFragment.findNavController(this).navigate(R.id.action_generalGameStatisticsFragment_to_generalStatisticsFragment);
    }

    private void actionGoToCasinoStatistics() {
        CasinoStatisticsFragment.returnFragmentID = returnFragmentID;
        NavHostFragment.findNavController(this).navigate(R.id.action_generalGameStatisticsFragment_to_casinoStatisticsFragment);
    }

    private void actionGoToSpecificGameStatistics() {
        SpecificGameStatisticsFragment.returnFragmentID = returnFragmentID;
        NavHostFragment.findNavController(this).navigate(R.id.action_generalGameStatisticsFragment_to_specificGameStatisticsFragment);
    }
}