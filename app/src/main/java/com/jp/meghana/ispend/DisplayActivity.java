package com.jp.meghana.ispend;
import java.util.Set;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.HashSet;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Highlight;
import com.github.mikephil.charting.utils.PercentFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
public class DisplayActivity extends Activity {

    private LinearLayout data;
    private PieChart pie;

    String date1, date2, cardno, val;
    String entries[];
    String category[];
    String[] result;
    String[] date;
    Float[] values;
    String x;
    Float amount[];
    String store[];
    //private float[] ydata = {5, 10, 15, 30, 40};
    //private String[] xdata = {"a", "b", "c", "d", "e"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        date1 = getIntent().getStringExtra("fdate");//date received from datepicker
        date2 = getIntent().getStringExtra("tdate");
        cardno = getIntent().getStringExtra("cardno");
        fetchData();
        get_offers();
        data = (LinearLayout) findViewById(R.id.data);
        pie = new PieChart(this);
        data.addView(pie);
        pie.setUsePercentValues(true);
        pie.setDescription("Your Expenditure");
        pie.setDrawHoleEnabled(true);
        pie.setHoleColorTransparent(true);
        pie.setHoleRadius(7);
        pie.setTransparentCircleRadius(10);
        pie.setRotationAngle(0);
        pie.setRotationEnabled(true);
        pie.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry entry, int i, Highlight highlight) {
                if (entry == null)
                    return;
                Toast.makeText(DisplayActivity.this, result[entry.getXIndex()] + "=" + entry.getVal() + "/-", Toast.LENGTH_LONG).show();
                Intent n =new Intent(DisplayActivity.this,TableDisplay.class);
                n.putExtra("title",result[entry.getXIndex()]);
                n.putExtra("entry",entries);
                n.putExtra("size",entries.length);
                n.putExtra("offers",x);
                startActivity(n);
            }

            @Override
            public void onNothingSelected() {

            }
        });
        //addData();
        Legend l = pie.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);

    }

    private void addData() {
        Log.i("After fetch data", val);
        if(val.contains("No results")){
            Toast.makeText(getApplicationContext(), "There are no transactions to display",Toast.LENGTH_LONG).show();
        }
        else {
            entries = val.split(",");
            category = new String[entries.length];
            store = new String[entries.length];
            amount = new Float[entries.length];
            date = new String[entries.length];
            String sample[] = new String[entries.length];
            for (int i = 0; i < entries.length; i++) {
                sample = entries[i].split("&");
                //category store amount
                category[i] = sample[0];
                store[i] = sample[1];
                amount[i] = Float.parseFloat(sample[2]);
                date[i]=sample[3];
            }
            List<String> list = Arrays.asList(category);
            Set<String> set = new HashSet<String>(list);
            result = new String[set.size()];
            set.toArray(result);// result contains unique categories
            for (String s : result) {
                Log.i("Category", s);
            }
            values = new Float[result.length]; // values contains amount spent on each unique category
            Arrays.fill(values, 0.0f);
            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < category.length; j++) {
                    if (result[i].equals(category[j])) {
                        values[i] += amount[j];
                    }
                }
                System.out.println(values[i]);
            }


            ArrayList<Entry> yVals1 = new ArrayList<Entry>();
            for (int i = 0; i < values.length; i++) {
                yVals1.add(new Entry(values[i], i));
            }
            ArrayList<String> xVals = new ArrayList<String>();
            for (int i = 0; i < result.length; i++)
                xVals.add(result[i]);
            PieDataSet dataSet = new PieDataSet(yVals1, "Categories");
            dataSet.setSliceSpace(3);
            dataSet.setSelectionShift(5);
            ArrayList<Integer> colors = new ArrayList<Integer>();
            for (int c : ColorTemplate.VORDIPLOM_COLORS)
                colors.add(c);
            for (int c : ColorTemplate.JOYFUL_COLORS)
                colors.add(c);
            for (int c : ColorTemplate.COLORFUL_COLORS)
                colors.add(c);
            for (int c : ColorTemplate.LIBERTY_COLORS)
                colors.add(c);
            for (int c : ColorTemplate.PASTEL_COLORS)
                colors.add(c);
            colors.add(ColorTemplate.getHoloBlue());
            dataSet.setColors(colors);
            PieData pdata = new PieData(xVals, dataSet);
            pdata.setValueFormatter(new PercentFormatter());
            pdata.setValueTextSize(12f);
            pdata.setValueTextColor(Color.BLACK);
            pie.setData(pdata);
            pie.highlightValues(null);
            pie.invalidate();

        }
    }

    private void fetchData() {
        fetch_data(cardno, date1, date2);
    }

    private void fetch_data(String cardno, String date1, String date2) {
        class dataFetch extends AsyncTask<String, Void, String> {

            ProgressDialog loading;

            UserClass uc = new UserClass();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DisplayActivity.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                val = s;
                addData();
            }

            @Override
            protected String doInBackground(String... params) {
                HashMap<String, String> data = new HashMap<String, String>();
                data.put("cardno", params[0]);
                data.put("date1", params[1]);
                data.put("date2", params[2]);

                String res = uc.sendPostRequest("http://ispendproj.esy.es/fetchdetails.php", data);
                return res;
            }
        }
        dataFetch lu = new dataFetch();
        lu.execute(cardno, date1, date2);
    }
    private void get_offers(){
        class getOffers extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            UserClass uc = new UserClass();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DisplayActivity.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                x=s;
                Log.i("Hey",s);
                loading.dismiss();
            }
            @Override
            protected String doInBackground(String... params) {
                HashMap<String, String> data = new HashMap<String, String>();
                data.put("A","");
                data.put("date1","");
                String res = uc.sendPostRequest("http://ispendproj.esy.es/offer.php", data);
                return res;
            }
        }
        getOffers lu = new getOffers();
        lu.execute();
    }

}
