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

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.honeybadger.gamblingbadger.R;
import com.honeybadger.gamblingbadger.databinding.FragmentGeneralStatisticsBinding;
import com.honeybadger.gamblingbadger.gamesession.EGameType;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GeneralStatisticsFragment extends Fragment {
    // CONSTANTS:

    private static final ArrayList<Integer> COLORS = new ArrayList<Integer>() {
        {
            add(Color.parseColor("#E62E2E"));
            add(Color.parseColor("#2EE63D"));
            add(Color.parseColor("#4D2EE6"));
            add(Color.parseColor("#E6622E"));
            add(Color.parseColor("#2EE66E"));
            add(Color.parseColor("#7D2EE6"));
            add(Color.parseColor("#E68D2E"));
            add(Color.parseColor("#2EE69F"));
            add(Color.parseColor("#AE2EE6"));
            add(Color.parseColor("#E6BE2E"));
            add(Color.parseColor("#2EE6CD"));
            add(Color.parseColor("#DC2EE6"));
            add(Color.parseColor("#DCE62E"));
            add(Color.parseColor("#2ECDE6"));
            add(Color.parseColor("#E62EBE"));
            add(Color.parseColor("#AEE62E"));
            add(Color.parseColor("#2E9FE6"));
            add(Color.parseColor("#E62E8D"));
            add(Color.parseColor("#7DE62E"));
            add(Color.parseColor("#2E6EE6"));
            add(Color.parseColor("#E62E5F"));
            add(Color.parseColor("#4DE62E"));
            add(Color.parseColor("#2E3DE6"));
        }
    };

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
        Map<String, Duration> valueList = new HashMap<>();
        for (Map.Entry<EGameType, Duration> entry : statistics.aggregateTimePlayingEachGame.entrySet()) {
            valueList.put(entry.getKey().NAME, entry.getValue());
        }

        // Input data
        for (String type : valueList.keySet()) {
            pieEntries.add(new PieEntry((float)valueList.get(type).toMinutes(), type));
        }

        // Pie stuff
        PieDataSet pieDataSet = new PieDataSet(pieEntries, label);
        pieDataSet.setValueTextSize(12f);
        pieDataSet.setColors(COLORS);
        PieData pieData = new PieData(pieDataSet);
        pieData.setDrawValues(true);

        pieChart.setData(pieData);
        pieChart.invalidate();
    }

    private void initAggregateMoneyDeltaForEachGame() {
        PieChart pieChart = getActivity().findViewById(R.id.generalStatisticsFrag_pieChart_aggregateMoneyDeltaEachGame);
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        String label = "Game Type";

        // Initialize data
        Map<String, BigDecimal> valueList = new HashMap<>();
        for (Map.Entry<EGameType, BigDecimal> entry : statistics.aggregateMoneyDeltaForEachGame.entrySet()) {
            valueList.put(entry.getKey().NAME, entry.getValue());
        }

        // Input data
        for (String type : valueList.keySet()) {
            pieEntries.add(new PieEntry((float)valueList.get(type).floatValue(), type));
        }

        // Pie stuff
        PieDataSet pieDataSet = new PieDataSet(pieEntries, label);
        pieDataSet.setValueTextSize(12f);
        pieDataSet.setColors(COLORS);
        PieData pieData = new PieData(pieDataSet);
        pieData.setDrawValues(true);

        pieChart.setData(pieData);
        pieChart.invalidate();
    }

    private void initAggregateLuckIndexForEachGame() {
        // TODO: Bar Chart
        BarChart barChart = getActivity().findViewById(R.id.generalStatisticsFrag_barChart_aggregateLuckIndexEachGame);
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        String label  ="Game Type";

        // Input Data
        Map<String, Float> valueList = new HashMap<>();
        for (Map.Entry<EGameType, Float> entry : statistics.aggregateLuckIndexForEachGame.entrySet()) {
            valueList.put(entry.getKey().NAME, entry.getValue());
        }

        // Data
        for (int i = 0; i < valueList.size(); i++) {
            barEntries.add(new BarEntry(i, 0f));
        }

        // Bar Stuff:
        BarDataSet barDataSet = new BarDataSet(barEntries, label);
        barDataSet.setValueTextSize(12f);
        barDataSet.setColors(COLORS);
        BarData barData = new BarData(barDataSet);
        barData.setDrawValues(true);

        barChart.setData(barData);
        barChart.invalidate();
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