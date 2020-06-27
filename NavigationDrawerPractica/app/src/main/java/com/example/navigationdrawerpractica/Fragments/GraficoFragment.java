package com.example.navigationdrawerpractica.Fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.navigationdrawerpractica.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


public class GraficoFragment extends Fragment {

    PieChart pieChart;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_grafico, container, false);
        View view = inflater.inflate(R.layout.fragment_grafico,container,false);
        pieChart = view.findViewById(R.id.gradicopastel);

        crearGraficoPastel();

        return view;
    }
    private void crearGraficoPastel() {

        Description description = new Description();
        description.setText("Grafico de encuesta");

        pieChart.setDescription(description);

        ArrayList<PieEntry> pieEntries=  new ArrayList<>();

        pieEntries.add(new PieEntry(2,3));
        pieEntries.add(new PieEntry(2,8));
        pieEntries.add(new PieEntry(6,7));

        PieDataSet pieDataSet= new PieDataSet(pieEntries,"Texto descripcion");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);

    }
}