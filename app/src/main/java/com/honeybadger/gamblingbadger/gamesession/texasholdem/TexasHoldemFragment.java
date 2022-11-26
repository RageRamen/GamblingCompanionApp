package com.honeybadger.gamblingbadger.gamesession.texasholdem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.honeybadger.gamblingbadger.R;
import com.honeybadger.gamblingbadger.databinding.FragmentTexasHoldemBinding;
import com.honeybadger.gamblingbadger.tips.BJTipFrag;
import com.honeybadger.gamblingbadger.tips.PokerTipFrag;
import com.honeybadger.gamblingbadger.tips.TXTipFrag;

public class TexasHoldemFragment extends Fragment {

    // CONSTANTS:

    // VARIABLES:

    private FragmentTexasHoldemBinding binding;

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

        binding = FragmentTexasHoldemBinding.inflate(inflater, container, false);
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

        inflater.inflate(R.menu.menu_texas_holdem, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (item.getItemId() == android.R.id.home) {
            actionEndSession();
            return true; // must return true to consume it here
        }
        if (id == R.id.menu_poker_item_tips) {
            actionGoToTexasHoldemTips();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // INTERFACE:

    // ACTIONS:

    private void actionGoToTexasHoldemTips() {
        TXTipFrag.callingFragmentReturnActionID = R.id.action_TXTipFrag_to_texasHoldemFragment;
        NavHostFragment.findNavController(this).navigate(R.id.action_texasHoldemFragment_to_TXTipFrag);
    }

    private void actionEndSession() {
        // TODO
        NavHostFragment.findNavController(this).navigate(R.id.action_texasHoldemFragment_to_casinoSessionFragment);
    }

}