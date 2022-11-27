package com.honeybadger.gamblingbadger.statistics;

import android.graphics.Color;
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

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.honeybadger.gamblingbadger.R;
import com.honeybadger.gamblingbadger.databinding.FragmentGeneralStatisticsBinding;
import com.honeybadger.gamblingbadger.gamesession.EGameType;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GeneralStatisticsFragment extends Fragment {
    // CONSTANTS:

    private static final int SOFT_COLOR_CAP = 23;
    private static final int COLOR_STEP = 360 / SOFT_COLOR_CAP;

    // VARIABLES:

    private FragmentGeneralStatisticsBinding binding;

    public static Integer returnFragmentID = R.id.generalStatisticsFragment;

    private LongListOfData statistics;

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
        binding = FragmentGeneralStatisticsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Update statistics:
        Statistics.updateStatistics();

        // Get statistics
        statistics = Statistics.getGeneralStatistics();

        // Init Interface
        initAggregateTimePlayingEachGame();
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
        else if (id == R.id.menu_statistics_item_casinoStatistics) {
            actionGoToCasinoStatistics();
            return true;
        }
        else if (id == R.id.menu_statistics_item_generalGameStatistics) {
            actionGoToGeneralGameStatistics();
            return true;
        }
        else if (id == R.id.menu_statistics_item_casinoGameStatistics) {
            actionGoToSpecificGameStatistics();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // INTERFACE:

    private void initAggregateTimeSpentInCasinos() {
        // TODO
    }

    private void initAggregateTimePlayingGames() {
        // TODO
    }

    private void initAggregateTimePlayingEachGame() {
        PieChart pieChart = getActivity().findViewById(R.id.generalStatisticsFrag_pieChart_aggregateTimePlayingEachGame);
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        String label = "Game Type";

        // Initialize data
        Map<String, Duration> typeMap = new HashMap<>();
        for (Map.Entry<EGameType, Duration> entry : statistics.aggregateTimePlayingEachGame.entrySet()) {
            typeMap.put(entry.getKey().NAME, entry.getValue());
        }

        // Get colors
        ArrayList<Integer> colors = new ArrayList<>();
        int H;
        float S, V, C, X, m, Rp, Gp, Bp;
        int R, G, B;
        for (int i = 0; i < statistics.aggregateTimePlayingEachGame.size(); i++) {
            H = (i * (int)COLOR_STEP) % 360;
            S = 1.0f;
            V = 1.0f;
            C = V * S;
            X = C * (1 - Math.abs((H / 60) % 2 - 1));
            m = V - C;
            if (0 <= H && H < 60) {
                Rp = C;
                Gp = X;
                Bp = 0;
            }
            else if (60 <= H && H < 120) {
                Rp = X;
                Gp = C;
                Bp = 0;
            }
            else if (120 <= H && H < 180) {
                Rp = 0;
                Gp = C;
                Bp = X;
            }
            else if (180 <= H && H < 240) {
                Rp = 0;
                Gp = X;
                Bp = C;
            }
            else if (240 <= H && H < 300) {
                Rp = X;
                Gp = 0;
                Bp = C;
            }
            else {
                Rp = C;
                Gp = 0;
                Bp = X;
            }
            R = (int)((Rp + m) * 255);
            G = (int)((Gp + m) * 255);
            B = (int)((Bp + m) * 255);
            int color = (R << 16) + (G << 8) + B;
        }

        // Input data
        for (String type : typeMap.keySet()) {
            pieEntries.add(new PieEntry(typeMap.get(type).toMinutes(), type));
        }

        // Pie stuff
        PieDataSet pieDataSet = new PieDataSet(pieEntries, label);
        pieDataSet.setValueTextSize(12f);
        pieDataSet.setColors(colors);
        PieData pieData = new PieData(pieDataSet);
        pieData.setDrawValues(true);

        pieChart.setData(pieData);
        pieChart.invalidate();
    }

    private void initAggregateMoneyDeltaForEachGame() {
        // TODO: Pie Chart
    }

    private void initAggregateLuckIndexForEachGame() {
        // TODO: Bar Chart
    }

    // ACTIONS:

    private void actionReturnToCallingFrag() {

        if (returnFragmentID == R.id.homeFragment)
            actionGoToHome();
        else if (returnFragmentID == R.id.casinoSessionFragment)
            actionGoToCasinoSession();
    }

    private void actionGoToHome() {
        NavHostFragment.findNavController(this).navigate(R.id.action_generalStatisticsFragment_to_homeFragment);
    }

    private void actionGoToCasinoSession() {
        NavHostFragment.findNavController(this).navigate(R.id.action_generalStatisticsFragment_to_casinoSessionFragment);
    }

    private void actionGoToCasinoStatistics() {
        CasinoStatisticsFragment.returnFragmentID = returnFragmentID;
        NavHostFragment.findNavController(this).navigate(R.id.action_generalStatisticsFragment_to_casinoStatisticsFragment);
    }

    private void actionGoToGeneralGameStatistics() {
        GeneralGameStatisticsFragment.returnFragmentID = returnFragmentID;
        NavHostFragment.findNavController(this).navigate(R.id.action_generalStatisticsFragment_to_generalGameStatisticsFragment);
    }

    private void actionGoToSpecificGameStatistics() {
        SpecificGameStatisticsFragment.returnFragmentID = returnFragmentID;
        NavHostFragment.findNavController(this).navigate(R.id.action_generalStatisticsFragment_to_specificGameStatisticsFragment);
    }
}