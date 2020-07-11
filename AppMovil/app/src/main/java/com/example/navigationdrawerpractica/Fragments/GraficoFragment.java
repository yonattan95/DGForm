package com.example.navigationdrawerpractica.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.CalendarContract;
import android.renderscript.Sampler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.navigationdrawerpractica.Adaptadores.DetalleHistorialA;
import com.example.navigationdrawerpractica.Adaptadores.DetalleHistorialB;
import com.example.navigationdrawerpractica.Entidades.HistorialA;
import com.example.navigationdrawerpractica.Entidades.HistorialB;
import com.example.navigationdrawerpractica.Interfaces.MainActivity;
import com.example.navigationdrawerpractica.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class GraficoFragment extends Fragment {

    private PieChart pieChart;
    private int[] sale =new int[2];
    private int completo = 0;
    private int pendientes = 0;
    private int[] colors= new int[]{Color.BLACK,Color.RED,Color.GREEN,Color.BLUE,Color.LTGRAY};
    private String[] DescTotal = new String[]{"Completos","Pendientes"};
    private RequestQueue mQueue;
    private static TextView tvTotalCompl,tvTotalPend;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grafico,container,false);
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_grafico, container, false);
        tvTotalCompl = view.findViewById(R.id.tvTotalCompl);
        tvTotalPend = view.findViewById(R.id.tvTotalPend);
        pieChart = view.findViewById(R.id.gradicopastel);
        mQueue = Volley.newRequestQueue(getActivity());
        final SharedPreferences preferences =((MainActivity)getActivity()).getSharedPreferences("gymapp", Context.MODE_PRIVATE);

        tvTotalCompl.setText(preferences.getString("completos",""));
        tvTotalPend.setText(preferences.getString("pendientes",""));
        pendientes = Integer.parseInt(preferences.getString("completos",""));
        completo = Integer.parseInt(preferences.getString("pendientes",""));
        sale[0] = pendientes;
        sale[1] = completo;
        //crearGraficoPastel();
        createCharts();
        return view;
    }
    private Chart getSameChart(Chart chart,String description,int textColor, int background,int animateY){
        chart.getDescription().setText(description);
        chart.getDescription().setTextSize(50);
        chart.getDescription().setTextColor(textColor);
        chart.setBackgroundColor(background);
        chart.animateY(animateY);
        legend(chart);
        return chart;
    }
    private void legend(Chart chart){
        Legend legend = chart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

        ArrayList<LegendEntry> entries=new ArrayList<>();
        for (int i = 0; i < DescTotal.length;i++){
            LegendEntry entry= new LegendEntry();
            entry.formColor = colors[i];
            entry.label = DescTotal[i];
            entries.add(entry);
        }
        legend.setCustom(entries);
    }
    private ArrayList<PieEntry> getPieEntries(){
        ArrayList<PieEntry> entries=new ArrayList<>();
        for (int i = 0; i < sale.length;i++)
            entries.add(new PieEntry(sale[i]));
        return entries;
    }
    private void axisX(XAxis axis){
        axis.setGranularityEnabled(true);
        axis.setPosition(XAxis.XAxisPosition.BOTTOM);
        axis.setValueFormatter(new IndexAxisValueFormatter(DescTotal));

    }
    private void axisLeft(YAxis axis){
        axis.setSpaceTop(30);
        axis.setAxisMaximum(0);
        axis.setGranularity(5);
    }
    private void axisReight(YAxis axis){
        axis.setEnabled(false);
    }
    public void createCharts(){
        pieChart = (PieChart) getSameChart(pieChart,"Encuestas",Color.GRAY,Color.MAGENTA,3000);
        pieChart.setHoleRadius(10);
        pieChart.setTransparentCircleRadius(12);
        pieChart.setData(getPieData());
        pieChart.invalidate();
        //pieChart.setDrawHoleEnabled(false);

    }
    private DataSet getData(DataSet dataSet){
        dataSet.setColors(colors);
        dataSet.setValueTextColor(Color.WHITE);
        dataSet.setValueTextSize(10);
        return dataSet;
    }
//    private BarData getBarData(){
//        BarDataSet barDataSet=(BarDataSet)getData(new BarDataSet(getBarEntries(),""));
//
//        barDataSet.setBarShadowColor(Color.GRAY);
//        BarData barData = new BarData(barDataSet);
//        barData.setBarWidth(0.45f);
//        return barData;
//    }
    private PieData getPieData(){
        PieDataSet pieDataSet = (PieDataSet)getData(new PieDataSet(getPieEntries(),""));

        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueFormatter(new PercentFormatter());
        return new PieData(pieDataSet);
    }


    private void crearGraficoPastel() {

        Description description = new Description();
        description.setText("Grafico de encuesta");

        pieChart.setDescription(description);

        ArrayList<PieEntry> pieEntries=  new ArrayList<>();

        pieEntries.add(new PieEntry(2,3));
        pieEntries.add(new PieEntry(2,8));
        //pieEntries.add(new PieEntry(6,7));

        PieDataSet pieDataSet= new PieDataSet(pieEntries,"Texto descripcion");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);

    }

}