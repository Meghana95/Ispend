package com.jp.meghana.ispend;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.app.DatePickerDialog.OnDateSetListener;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Highlight;
import com.github.mikephil.charting.utils.PercentFormatter;
import android.content.DialogInterface.OnClickListener;
import java.util.Date;
import java.util.logging.Handler;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.text.SimpleDateFormat;
public class DataActivity extends Activity {
    private TextView tvDisplayDate, tvDisplayDate2,userid;
    private DatePicker dpResult;
    private Button btnChangeDate, btnChangeDate2;
    DatePickerDialog.OnDateSetListener from_dateListener;
    DatePickerDialog.OnDateSetListener to_dateListener;
    private LinearLayout data;
    private PieChart pie;

    Button generate,offers,email,sample;
    String x,y,user;
    String date1="";
    String date2="";


    private int year;
    private int month;
    private int day;

    static final int DATE_DIALOG_ID = 1;
    static final int DATE_DIALOG_ID2 = 2;
    int cur = 0;
    Date fdate,tdate;

    public DataActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        setCurrentDateOnView();
        addListenerOnButton();
        userid = (TextView)findViewById(R.id.cardno);
        userid.setText(getIntent().getStringExtra("mytext"));
        final String sam=userid.getText().toString();
        offers=(Button) findViewById(R.id.bt_offers);
        email=(Button)findViewById(R.id.bt_email);
        sample=(Button)findViewById(R.id.sample);
        generate = (Button) findViewById(R.id.bt_generate);
        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((date1.isEmpty())==true||(date2.isEmpty())==true)
                {
                    Toast.makeText(getApplicationContext(), "Please select the dates",
                            Toast.LENGTH_SHORT).show();
                }

                else {

                    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

                    try {
                        fdate = format.parse(date1);
                    } catch (Exception e) {
                    }
                    try {
                        tdate = format.parse(date2);
                    } catch (Exception e) {
                    }

                    if (fdate.compareTo(tdate) <= 0) {
                        user = userid.getText().toString();
                        Intent i = new Intent(DataActivity.this, DisplayActivity.class);
                        i.putExtra("fdate", date1);
                        i.putExtra("tdate", date2);
                        i.putExtra("cardno", user);
                        startActivity(i);
                    } else {




                        Toast.makeText(getApplicationContext(), "Please select the dates accordingly",
                                Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        offers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DataActivity.this,Offers.class);
                startActivity(i);
            }
        });
        sample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DataActivity.this, NewDataActivity.class);
                i.putExtra("mytext",sam);
                startActivity(i);
            }
        });
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

    }
     public void setCurrentDateOnView() {

        tvDisplayDate = (TextView) findViewById(R.id.tvDate);
        tvDisplayDate2 = (TextView) findViewById(R.id.tvDate2);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        // set current date into textview

    }

    protected void sendEmail() {
        Log.i("Send email", "");
        String[] TO = {"ispendofficial@gmail.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,"");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email.", "");
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(DataActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }


    public void addListenerOnButton() {

        btnChangeDate = (Button) findViewById(R.id.btnChangeDate);

        btnChangeDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(DATE_DIALOG_ID);

            }

        });
        btnChangeDate2 = (Button) findViewById(R.id.btnChangeDate2);

        btnChangeDate2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(DATE_DIALOG_ID2);

            }

        });

    }
    protected Dialog onCreateDialog(int id) {
        switch (id) {

            case DATE_DIALOG_ID:
                System.out.println("onCreateDialog  : " + id);
                cur = DATE_DIALOG_ID;
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener, year, month,
                        day);
            case DATE_DIALOG_ID2:
                cur = DATE_DIALOG_ID2;
                System.out.println("onCreateDialog2  : " + id);
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener, year, month,
                        day);

        }
        return null;
    }
   private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;
            if(day<10) {
                 x = "0" + day;
            }
            else { x=""+day;}
            if((month+1)<10) {
                 y = "0" + (month+1);
            }
            else {y=""+(month+1);}

            if(cur == DATE_DIALOG_ID){
                // set selected date into textview
                date1=String.valueOf( new StringBuilder().append(x).append("-").append(y).append("-").append(year).append(" "));
                tvDisplayDate.setText("From : " + date1);

            }
            else{
                date2=String.valueOf(new StringBuilder().append(x).append("-").append(y)
                        .append("-").append(year).append(" "));
                tvDisplayDate2.setText("To : " + date2 );

            }

        }
    };
}
