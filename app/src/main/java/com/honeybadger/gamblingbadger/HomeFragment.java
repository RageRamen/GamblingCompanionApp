package com.honeybadger.gamblingbadger;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.honeybadger.gamblingbadger.databinding.FragmentHomeBinding;
import com.honeybadger.gamblingbadger.statistics.GeneralStatisticsFragment;
import com.honeybadger.gamblingbadger.tips.TipsFragment;

public class HomeFragment extends Fragment
{

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        initButtonStartSession();
        initButtonGameTips();
        initButtonStatistics();
    }


    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
    }

    // INTERFACE:

    private void initButtonStartSession() {
        binding.homeFragButtonStartSession.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                actionGoToStartSession();
            }
        });
    }

    private void initButtonGameTips() {
        binding.homeFragButtonGameTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionGoToTips();
            }
        });
    }

    private void initButtonStatistics() {
        binding.homeFragButtonStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionGoToStatistics();
            }
        });
    }

    // ACTIONS:

    private void actionGoToStartSession() {
        NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_homeFragment_to_startSessionFragment);
    }

    private void actionGoToTips() {
        TipsFragment.callingFragmentReturnActionID = R.id.action_tipsFragment_to_homeFragment;
        NavHostFragment.findNavController((HomeFragment.this)).navigate(R.id.action_homeFragment_to_tipFragment);
    }

    private void actionGoToStatistics() {
        GeneralStatisticsFragment.returnFragmentID = R.id.homeFragment;
        NavHostFragment.findNavController(this).navigate(R.id.action_homeFragment_to_generalStatisticsFragment);
    }

}