package com.honeybadger.gamblingbadger.statistics;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.honeybadger.gamblingbadger.R;
import com.honeybadger.gamblingbadger.databinding.FragmentGeneralStatisticsBinding;
import com.honeybadger.gamblingbadger.gamesession.EGameType;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.Duration;
import java.util.ArrayList;
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

    // UTILITY:

    private static String getString(Duration duration) {
        long seconds = duration.getSeconds();
        return String.format("%d:%02d:%02d", seconds / 3600, (seconds % 3600) / 60, (seconds % 60));
    }

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

        // Get statistics
        Statistics.updateStatistics();
        statistics = Statistics.getGeneralStatistics();

        // Init Interface
        initAggregateTimeSpentInCasinos();
        initAggregateTimePlayingGames();
        initAggregateTimePlayingEachGame();
        initAggregateMoneyDeltaForEachGame();
        initAggregateLuckIndexForEachGame();
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
        TextView view = getActivity().findViewById(R.id.generalStatisticsFrag_textView_aggregateTimeSpentInCasinos);
        view.setText(getString(statistics.aggregateTimeSpentInCasinos) + " (H:M:S)");
    }

    private void initAggregateTimePlayingGames() {
        TextView view = getActivity().findViewById(R.id.generalStatisticsFrag_textView_aggregateTimePlayingGames);
        view.setText(getString(statistics.aggregateTimePlayingGames) + " (H:M:S)");
    }

    private void initAggregateTimePlayingEachGame() {
        PieChart pieChart = getActivity().findViewById(R.id.generalStatisticsFrag_pieChart_aggregateTimePlayingEachGame);
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        String label = "";

        // Input data
        PieEntry pe;
        for (Map.Entry<EGameType, Duration> entry : statistics.aggregateTimePlayingEachGame.entrySet()) {
            pe = new PieEntry(entry.getValue().getSeconds(), entry.getKey().NAME + ": " + getString(entry.getValue()));
            pieEntries.add(pe);
        }

        // Pie stuff
        PieDataSet pieDataSet = new PieDataSet(pieEntries, label);
        pieDataSet.setValueTextSize(12f);
        pieDataSet.setColors(COLORS);
        PieData pieData = new PieData(pieDataSet);
        pieData.setDrawValues(false);

        pieChart.setData(pieData);
        pieChart.getLegend().setTextColor(Color.parseColor("#FFFFFF"));
        pieChart.getDescription().setEnabled(false);

        pieChart.invalidate();
    }

    private void initAggregateMoneyDeltaForEachGame() {
        /*PieChart pieChart = getActivity().findViewById(R.id.generalStatisticsFrag_batChart_aggregateMoneyDeltaEachGame);
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        String label = "";

        // Input data
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        PieEntry pe;
        for (Map.Entry<EGameType, BigDecimal> entry : statistics.aggregateMoneyDeltaForEachGame.entrySet()) {
            pe = new PieEntry(entry.getValue().floatValue(), entry.getKey().NAME + ": " + formatter.format(entry.getValue()));
            pieEntries.add(pe);
        }

        // Pie stuff
        PieDataSet pieDataSet = new PieDataSet(pieEntries, label);
        pieDataSet.setValueTextSize(12f);
        pieDataSet.setColors(COLORS);
        PieData pieData = new PieData(pieDataSet);
        pieData.setDrawValues(false);

        pieChart.setData(pieData);
        pieChart.invalidate();*/

        BarChart barChart = getActivity().findViewById(R.id.generalStatisticsFrag_batChart_aggregateMoneyDeltaEachGame);
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();
        String label = "";

        // Input Data
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        BarEntry be;
        int count = 0;
        for (Map.Entry<EGameType, BigDecimal> entry : statistics.aggregateMoneyDeltaForEachGame.entrySet()) {
            labels.add(entry.getKey().NAME);
            //be = new BarEntry(count, presentationLuck.get(count));
            be = new BarEntry(count, entry.getValue().floatValue());
            count++;
            barEntries.add(be);
        }

        // Bar Stuff:
        BarDataSet barDataSet = new BarDataSet(barEntries, label);
        barDataSet.setValueTextSize(12f);
        barDataSet.setColors(COLORS);

        BarData barData = new BarData(barDataSet);
        barData.setDrawValues(true);
        barData.setValueTextColor(Color.parseColor("#FFFFFF"));

        barChart.setData(barData);

        YAxis rightAxis = barChart.getAxisRight();
        rightAxis.setDrawAxisLine(false);
        rightAxis.setDrawTopYLabelEntry(false);
        rightAxis.setDrawZeroLine(false);
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawGridLinesBehindData(false);
        rightAxis.setDrawLabels(false);
        rightAxis.setDrawLimitLinesBehindData(false);

        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setTextColor(Color.parseColor("#FFFFFF"));
        //leftAxis.setAxisMaximum(1.0f);
        //leftAxis.setAxisMinimum(-1.0f);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        xAxis.setTextColor(Color.parseColor("#FFFFFF"));

        barChart.getLegend().setTextColor(Color.parseColor("#FFFFFF"));
        barChart.getDescription().setEnabled(false);

        barChart.invalidate();
    }

    private void initAggregateLuckIndexForEachGame() {
        BarChart barChart = getActivity().findViewById(R.id.generalStatisticsFrag_barChart_aggregateLuckIndexEachGame);
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();
        String label = "WORK IN PROGRESS";

        // Input Data
        BarEntry be;
        int count = 0;
        ArrayList<Float> presentationLuck = new ArrayList<Float>() { {add(0.25f); add(-0.35f); add(0.4f); }};
        for (Map.Entry<EGameType, Float> entry : statistics.aggregateLuckIndexForEachGame.entrySet()) {
            labels.add(entry.getKey().NAME);
            be = new BarEntry(count, presentationLuck.get(count));
            count++;
            barEntries.add(be);
        }

        // Bar Stuff:
        BarDataSet barDataSet = new BarDataSet(barEntries, label);
        barDataSet.setValueTextSize(12f);
        barDataSet.setColors(COLORS);

        BarData barData = new BarData(barDataSet);
        barData.setValueTextColor(Color.parseColor("#FFFFFF"));
        barData.setDrawValues(true);

        barChart.setData(barData);

        YAxis rightAxis = barChart.getAxisRight();
        rightAxis.setDrawAxisLine(false);
        rightAxis.setDrawTopYLabelEntry(false);
        rightAxis.setDrawZeroLine(false);
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawGridLinesBehindData(false);
        rightAxis.setDrawLabels(false);
        rightAxis.setDrawLimitLinesBehindData(false);

        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setTextColor(Color.parseColor("#FFFFFF"));
        leftAxis.setAxisMaximum(1.0f);
        leftAxis.setAxisMinimum(-1.0f);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        xAxis.setTextColor(Color.parseColor("#FFFFFF"));


        barChart.getLegend().setTextColor(Color.parseColor("#FFFFFF"));
        barChart.getDescription().setEnabled(false);

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