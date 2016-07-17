package com.jp.meghana.ispend;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewDataActivity extends Activity
        implements NavigationView.OnNavigationItemSelectedListener {


    private TextView tvDisplayDate, tvDisplayDate2,userid;
    private DatePicker dpResult;
    private Button btnChangeDate, btnChangeDate2;
    DatePickerDialog.OnDateSetListener from_dateListener;
    DatePickerDialog.OnDateSetListener to_dateListener;
    private LinearLayout data;
    private PieChart pie;

    Button generate,offers,email,sample;
    String x,y,user,cardno;
    String date1="";
    String date2="";


    private int year;
    private int month;
    private int day;

    static final int DATE_DIALOG_ID = 1;
    static final int DATE_DIALOG_ID2 = 2;
    int cur = 0;
    Date fdate,tdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_data);
        setCurrentDateOnView();
        addListenerOnButton();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        DatabaseOperations dop = new DatabaseOperations(NewDataActivity.this);
        Cursor CR = dop.getDetails(dop);
        if (CR.moveToLast()) {
            cardno = CR.getString(0);
        }

        userid = (TextView)findViewById(R.id.cardno);
        userid.setText("xxxxxxxxxxxx"+cardno.substring(12,16));
        offers=(Button) findViewById(R.id.bt_offers);
        email=(Button)findViewById(R.id.bt_email);
        sample=(Button)findViewById(R.id.sample);
        generate = (Button) findViewById(R.id.bt_generate);
        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((date1.isEmpty()) == true || (date2.isEmpty()) == true) {
                    Toast.makeText(getApplicationContext(), "Please select the dates",
                            Toast.LENGTH_SHORT).show();
                } else {

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
                        Intent i = new Intent(NewDataActivity.this, DisplayActivity.class);
                        i.putExtra("fdate", date1);
                        i.putExtra("tdate", date2);
                        i.putExtra("cardno", cardno);
                        startActivity(i);
                    } else {


                        Toast.makeText(getApplicationContext(), "Please select the dates accordingly",
                                Toast.LENGTH_SHORT).show();
                    }

                }

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
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_data, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.generate_report) {
            Intent i = new Intent(NewDataActivity.this, NewDataActivity.class);
            startActivity(i);

        } else if (id == R.id.user_details) {
            Intent i = new Intent(NewDataActivity.this, UserDetailsActivity.class);
            startActivity(i);

        } else if (id == R.id.get_offers) {
            Intent i = new Intent(NewDataActivity.this, NewOffers.class);
            startActivity(i);

        }else if (id == R.id.nav_mail) {
            Intent i = new Intent(NewDataActivity.this, EmailActivity.class);
            startActivity(i);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
