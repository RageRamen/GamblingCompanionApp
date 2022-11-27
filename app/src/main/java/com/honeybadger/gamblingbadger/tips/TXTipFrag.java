package com.honeybadger.gamblingbadger.tips;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.honeybadger.gamblingbadger.R;
import com.honeybadger.gamblingbadger.databinding.FragmentTXTipBinding;

public class TXTipFrag extends Fragment {
    // VARIABLES:

    @Nullable
    public static Integer callingFragmentReturnActionID;
    private FragmentTXTipBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        initGoodHandsButton();
    }

    private void initGoodHandsButton()
    {
        binding.whatisagoodhandbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                NavHostFragment.findNavController(TXTipFrag.this)
                        .navigate(R.id.action_TXTipFrag_to_pokerHandsFrag);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTXTipBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // menu.clear();
        // inflater.inflate(R.menu.menu_blackjack, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (item.getItemId() == android.R.id.home) {
            if (callingFragmentReturnActionID != null)
                actionReturnToCallingFrag();
            else
                actionGoToTips();
            return true; // must return true to consume it here
        }

        return super.onOptionsItemSelected(item);
    }

    // ACTIONS:

    private void actionGoToTips() {
        NavHostFragment.findNavController(this).navigate(R.id.action_TXTipFrag_to_tipsFragment);
    }

    private void actionReturnToCallingFrag() {
        assert callingFragmentReturnActionID != null;
        NavHostFragment.findNavController(this).navigate(callingFragmentReturnActionID);
        callingFragmentReturnActionID = null;
    }
}