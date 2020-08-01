package com.example.digiforms.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.example.digiforms.Interfaces.MainActivity;
import com.example.digiforms.R;

import java.util.ArrayList;
import java.util.List;


public class Grafico2Fragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    AnyChartView anyChartView;
    String[] estado = {"Pendientes","Completados"};
    private int completo = 0;
    private int pendientes = 0;
    private int[] valor =new int[2];
    private static TextView tvTotalCompl,tvTotalPend;

    public Grafico2Fragment() {
        // Required empty public constructor
    }


    public static Grafico2Fragment newInstance(String param1, String param2) {
        Grafico2Fragment fragment = new Grafico2Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_grafico2,container,false);
        anyChartView = view.findViewById(R.id.gradicopastel2);
        tvTotalCompl = view.findViewById(R.id.tvTotalCompl2);
        tvTotalPend = view.findViewById(R.id.tvTotalPend2);
        SharedPreferences preferences =((MainActivity)getActivity()).getSharedPreferences("gymapp", Context.MODE_PRIVATE);

        tvTotalCompl.setText(preferences.getString("completos",""));
        tvTotalPend.setText(preferences.getString("pendientes",""));
        completo = Integer.parseInt(preferences.getString("completos",""));
        pendientes= Integer.parseInt(preferences.getString("pendientes",""));
        valor[0] = pendientes;
        valor[1] = completo;
        setupPieChart(valor);
        return view;
    }

    public void setupPieChart(int[] valr){
        Pie pie = AnyChart.pie();
        List<DataEntry> dataEntries = new ArrayList<>();
        for (int i = 0; i < estado.length; i++ ){
            dataEntries.add(new ValueDataEntry(estado[i],valr[i]));
        }

        pie.data(dataEntries);
        pie.title("Encuestas");

        anyChartView.setChart(pie);
    }
}