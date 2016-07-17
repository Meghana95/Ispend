package com.jp.meghana.ispend;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class NewOffers extends Activity
        implements NavigationView.OnNavigationItemSelectedListener {


    String[] category;
    String[] offer;
    String[] fromdate;
    String[] todate;
    String[] sample;
    String x;
    Date date;
    String modifiedDate;
    String convertedCurrentDate;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_offers);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
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

        Date date=new Date();
        Log.i("Date", date.toString());

        get_offers();


    }

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
        getMenuInflater().inflate(R.menu.new_offers, menu);
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
            Intent i = new Intent(NewOffers.this, NewDataActivity.class);
            startActivity(i);

        } else if (id == R.id.user_details) {
            Intent i = new Intent(NewOffers.this, UserDetailsActivity.class);
            startActivity(i);

        } else if (id == R.id.get_offers) {
            Intent i = new Intent(NewOffers.this, NewOffers.class);
            startActivity(i);

        } else if (id == R.id.nav_mail) {
            Intent i = new Intent(NewOffers.this, EmailActivity.class);
            startActivity(i);

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void get_offers(){
        class getOffers extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            UserClass uc = new UserClass();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(NewOffers.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                x=s;
                Log.i("Hey",s);
                loading.dismiss();
                setdata();
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
    public void setdata(){
        if(x.contains("No offers")){
            Toast.makeText(getApplicationContext(), "There are no offers to display", Toast.LENGTH_LONG).show();
        }
        else {
            date = new Date();
            modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
            sample = x.split(",");
            category = new String[sample.length];
            offer = new String[sample.length];
            fromdate = new String[sample.length];
            todate = new String[sample.length];
            String sam[] = new String[sample.length];
            TableRow.LayoutParams params1 = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.5f);
            TableRow.LayoutParams params3 = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 3.0f);
            TableRow.LayoutParams params4 = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
            TableRow.LayoutParams params2 = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            int leftMargin=10;
            int topMargin=5;
            int rightMargin=10;
            int bottomMargin=5;
            params2.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
            TableLayout tab = (TableLayout) findViewById(R.id.table_main);
            TableRow rw = new TableRow(this);
            TextView t1 = new TextView(this);
            TextView t2 = new TextView(this);
            //TextView t3 = new TextView(this);
            TextView t4 = new TextView(this);
            t1.setText("Category");
            t2.setText("Offer");
            //t3.setText("From");
            t4.setText("Valid till");
            t1.setLayoutParams(params1);
            t2.setLayoutParams(params3);
            //t3.setLayoutParams(params4);
            t4.setLayoutParams(params4);
            t1.setTextColor(Color.BLUE);
            t2.setTextColor(Color.BLUE);
            //t3.setTextColor(Color.BLUE);
            t4.setTextColor(Color.BLUE);
            t1.setTextSize(16);
            t2.setTextSize(16);
            //t3.setTextSize(16);
            t4.setTextSize(16);
            rw.addView(t1);
            rw.addView(t2);
            //rw.addView(t3);
            rw.addView(t4);
            rw.setLayoutParams(params2);
            tab.addView(rw);
            for (int i = 0; i < sample.length; i++) {
                sam = sample[i].split("&");
                category[i] = sam[0];
                offer[i] = sam[1];
                fromdate[i] = sam[2];
                todate[i] = sam[3];
                Log.i("offer", offer[i]);
            }

            for (int i = 0; i < sample.length; i++) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    convertedCurrentDate = sdf.format(sdf.parse(todate[i]));
                } catch (Exception e) {
                }
                if (modifiedDate.compareTo(convertedCurrentDate) <= 0) {
                    Log.i("date", convertedCurrentDate.toString());
                    TableRow row = new TableRow(this);
                    TextView txt1 = new TextView(this);
                    TextView txt2 = new TextView(this);
                    //TextView txt3=new TextView(this);
                    TextView txt4 = new TextView(this);
                    txt1.setText(category[i]);
                    txt2.setText(offer[i]);
                    //txt3.setText(fromdate[i]);
                    txt4.setText(todate[i]);
                    txt1.setTextSize(12);
                    txt2.setTextSize(12);
                    //txt3.setTextSize(10);
                    txt4.setTextSize(10);
                    txt1.setLayoutParams(params1);
                    txt2.setLayoutParams(params3);
                    //txt3.setLayoutParams(params4);
                    txt4.setLayoutParams(params4);
                    txt1.setTextColor(Color.BLACK);
                    txt2.setTextColor(Color.BLACK);
                    //txt3.setTextColor(Color.BLACK);
                    txt4.setTextColor(Color.BLACK);
                    row.addView(txt1);
                    row.addView(txt2);
                    //row.addView(txt3);
                    row.addView(txt4);
                    ViewGroup.LayoutParams p1 = txt1.getLayoutParams();
                    p1.width = getResources().getDimensionPixelSize(R.dimen.category_width);
                    txt1.setLayoutParams(p1);
                    ViewGroup.LayoutParams p2 = txt2.getLayoutParams();
                    p2.width = getResources().getDimensionPixelSize(R.dimen.offer_width);
                    txt2.setLayoutParams(p2);
                    //ViewGroup.LayoutParams p3 = txt3.getLayoutParams();
                    //p3.width=getResources().getDimensionPixelSize(R.dimen.fromdate_width);
                    //txt3.setLayoutParams(p3);
                    ViewGroup.LayoutParams p4 = txt4.getLayoutParams();
                    p4.width = getResources().getDimensionPixelSize(R.dimen.todate_width);
                    txt4.setLayoutParams(p4);
                    row.setLayoutParams(params2);
                    row.setBackgroundDrawable(getResources().getDrawable(R.drawable.border));
                    tab.addView(row);
                }

            }
        }
    }
}
