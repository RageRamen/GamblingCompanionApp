package com.honeybadger.gamblingbadger.casinosession;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.honeybadger.gamblingbadger.R;
import com.honeybadger.gamblingbadger.databinding.FragmentCasinoSessionBinding;
import com.honeybadger.gamblingbadger.statistics.CasinoStatisticsFragment;
import com.honeybadger.gamblingbadger.tips.TipsFragment;

public class CasinoSessionFragment extends Fragment {

    private FragmentCasinoSessionBinding binding;
    private AlertDialog.Builder alertEndSession;

    public static @Nullable CasinoSession casinoSession = null;

    // Fragment Events:

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

        // Alert Builder
        initAlertBuilderEndSession();

        // Binding
        binding = FragmentCasinoSessionBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Visual
        binding.casinoSessionFragTextviewText.setText(casinoSession.getBalance().getCurrentBalance().toString());

        // Button bindings
        initButtonStartGame();
        initButtonChangeBalance();
        initButtonSetTimer();
        initButtonEndSession();
        initButtonTips();
        initButtonStatistics();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
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
            alertEndSession.show();
            return true; // must return true to consume it here
        }

        return super.onOptionsItemSelected(item);
    }

    // Alert Initializers:

    private void initAlertBuilderEndSession() {
        alertEndSession = new AlertDialog.Builder(this.getActivity());
        alertEndSession.setCancelable(true);
        alertEndSession.setTitle(R.string.casinoSessionFrag_alert_endSession_title);
        alertEndSession.setMessage(R.string.casinoSessionFrag_alert_endSession_message);
        alertEndSession.setPositiveButton(R.string.casinoSessionFrag_alert_endSession_positive,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        actionEndSession();
                    }
                });
        alertEndSession.setNegativeButton(R.string.casinoSessionFrag_alert_endSession_negative,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {}
                });
    }

    // Interface Initializers:

    private void initButtonStartGame() {
        binding.casinoSessionFragButtonStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionGoToStartGame();
            }
        });
    }

    private void initButtonChangeBalance() {
        binding.casinoSessionFragButtonChangeBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { actionGoToChangeBalance(); }
        });
    }

    private void initButtonSetTimer() {
        binding.casinoSessionFragButtonSetTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionGoToTimer();
            }
        });
    }

    private void initButtonEndSession() {
        binding.casinoSessionFragButtonEndSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertEndSession.show();
            }
        });
    }

    private void initButtonTips() {
        binding.casinoSessionFragButtonGameTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionGoToTips();
            }
        });
    }

    private void initButtonStatistics() {
        binding.casinoSessionFragButtonStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionGoToStatistics();
            }
        });
    }

    // Actions:

    private void actionGoToStartGame() {
        NavHostFragment.findNavController(CasinoSessionFragment.this)
                .navigate(R.id.action_casinoSessionFragment_to_startGameFragment);
    }

    private void actionGoToChangeBalance() {
        NavHostFragment.findNavController(CasinoSessionFragment.this)
                .navigate(R.id.action_casinoSessionFragment_to_changeBalanceFragment);
    }

    private void actionGoToTimer() {
        NavHostFragment.findNavController(CasinoSessionFragment.this)
                .navigate(R.id.action_casinoSessionFragment_to_timerFragment);
    }

    private void actionEndSession() {
        // TODO: Ask to confirm
        casinoSession.closeSession();

        NavHostFragment.findNavController(CasinoSessionFragment.this)
                .navigate(R.id.action_casinoSessionFragment_to_homeFragment);
    }

    public void actionGoToTips() {
        TipsFragment.callingFragmentReturnActionID = R.id.action_tipsFragment_to_casinoSessionFragment;
        NavHostFragment.findNavController(CasinoSessionFragment.this).navigate(R.id.action_casinoSessionFragment_to_tipsFragment);
    }

    private void actionGoToStatistics() {
        CasinoStatisticsFragment.returnFragmentID = R.id.casinoSessionFragment;
        NavHostFragment.findNavController(this).navigate(R.id.action_casinoSessionFragment_to_casinoStatisticsFragment);
    }

}