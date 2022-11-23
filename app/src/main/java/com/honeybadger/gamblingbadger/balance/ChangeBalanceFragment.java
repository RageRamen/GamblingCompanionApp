package com.honeybadger.gamblingbadger.balance;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.honeybadger.gamblingbadger.R;
import com.honeybadger.gamblingbadger.casinosession.StartSessionFragment;
import com.honeybadger.gamblingbadger.databinding.FragmentChangeBalanceBinding;

public class ChangeBalanceFragment extends Fragment
{

    private FragmentChangeBalanceBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentChangeBalanceBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        initButtonAdd();
        initButtonSub();
        initBackButton();

    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
    }

    // INTERFACE:

    private void initButtonAdd() {
        binding.changBalFragButtonAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                actionGoToAdd();
            }
        });
    }

    private void initButtonSub() {
        binding.changBalFragButtonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionGoToSub();
            }
        });
    }

    private void initBackButton() {
        binding.changBalFragButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionGoBackHome();
            }
        });
    }


    // ACTIONS:

    private void actionGoToAdd() {
        NavHostFragment.findNavController(ChangeBalanceFragment.this)
                .navigate(R.id.action_changeBalanceFragment_to_addBalanceFragment);
    }

    private void actionGoToSub() {
        NavHostFragment.findNavController((ChangeBalanceFragment.this))
                .navigate(R.id.action_changeBalanceFragment_to_subBalanceFragment);
    }

    private void actionGoBackHome() {
        // Navigate Home
        NavHostFragment.findNavController(ChangeBalanceFragment.this)
                .navigate(R.id.action_changeBalanceFragment_to_subBalanceFragment);
    }

}