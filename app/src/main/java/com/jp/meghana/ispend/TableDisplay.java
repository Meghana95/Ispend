package com.jp.meghana.ispend;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.util.Log;
import android.widget.LinearLayout;
import android.graphics.Color;
import android.view.Window;
import android.app.Activity;
import java.util.Date;
import java.text.DateFormat;
import java.util.Locale;
import java.text.SimpleDateFormat;
import android.content.Intent;
public class TableDisplay extends Activity {

    String[] detail;
    String [] sam;
    String[] category;
    String[] offer;
    String[] fromdate;
    String[] todate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_display);
        String sample[]=new String[4];
        String cat_name = getIntent().getStringExtra("title");
        int len=getIntent().getIntExtra("size", 0);
        String details[]=new String[len];
        String offers=getIntent().getStringExtra("offers");
        details=getIntent().getStringArrayExtra("entry");
        TextView title=(TextView)findViewById(R.id.title);
        TextView trans=(TextView)findViewById(R.id.trans);
        TextView off=(TextView)findViewById(R.id.offer);

        detail=offers.split(",");
        category=new String[detail.length];
        offer=new String[detail.length];
        fromdate=new String[detail.length];
        todate=new String[detail.length];

        for (int i = 0; i < detail.length; i++) {
            sam = detail[i].split("&");
            category[i] = sam[0];
            offer[i] = sam[1];
            fromdate[i] = sam[2];
            todate[i] = sam[3];
        }
        TableRow.LayoutParams params1 = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 2.0f);
        TableRow.LayoutParams params3 = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
        TableRow.LayoutParams params4 = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 3.0f);
        TableRow.LayoutParams params2 = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TableLayout l2=(TableLayout)findViewById(R.id.table_offers);
        int leftMargin=10;
        int topMargin=5;
        int rightMargin=10;
        int bottomMargin=5;
        params2.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
        title.setText(cat_name);
        TableLayout ll=(TableLayout)findViewById(R.id.table_main);
        TableRow rw = new TableRow(this);
        TextView t11 = new TextView(this);
        TextView t22 = new TextView(this);
        TextView t33 = new TextView(this);
        trans.setText("Transactions");
        t11.setText("Store");
        t22.setText("Amount");
        t33.setText("Date");
        t11.setTextColor(Color.BLUE);
        t22.setTextColor(Color.BLUE);
        t33.setTextColor(Color.BLUE);
        t11.setTextSize(16);
        t22.setTextSize(16);
        t33.setTextSize(16);
        t11.setLayoutParams(params1);
        t22.setLayoutParams(params1);
        t33.setLayoutParams(params1);

        rw.addView(t11);
        rw.addView(t22);
        rw.addView(t33);
        rw.setLayoutParams(params2);
        ll.addView(rw);
        for (int i = 0; i < details.length; i++) {
            if (details[i].contains(cat_name)) {
                sample=details[i].split("&");
                TableRow row = new TableRow(this);
                TextView txt1 = new TextView(this);
                TextView txt2 = new TextView(this);
                TextView txt3=new TextView(this);
                txt1.setText(sample[1]);
                txt2.setText(sample[2]);
                txt3.setText(sample[3]);
                txt1.setTextSize(14);
                txt2.setTextSize(14);
                txt3.setTextSize(14);
                txt1.setLayoutParams(params1);
                txt2.setLayoutParams(params1);
                txt3.setLayoutParams(params1);
                txt1.setTextColor(Color.BLACK);
                txt2.setTextColor(Color.BLACK);
                txt3.setTextColor(Color.BLACK);
                row.addView(txt1);
                row.addView(txt2);
                row.addView(txt3);
                ViewGroup.LayoutParams p1 = txt1.getLayoutParams();
                p1.width = getResources().getDimensionPixelSize(R.dimen.offer_width);
                txt1.setLayoutParams(p1);
                ViewGroup.LayoutParams p2 = txt2.getLayoutParams();
                p2.width = getResources().getDimensionPixelSize(R.dimen.fromdate_width);
                txt2.setLayoutParams(p2);
                ViewGroup.LayoutParams p3 = txt3.getLayoutParams();
                p3.width=getResources().getDimensionPixelSize(R.dimen.fromdate_width);
                txt3.setLayoutParams(p3);
                row.setLayoutParams(params2);
                ll.addView(row);
            }
        }
       for(int i=0;i<detail.length;i++)
        {
            if(category[i].equals(cat_name)){

                off.setText("Offers you can avail");
                TableRow row = new TableRow(this);
                TextView txt1 = new TextView(this);
                TextView txt2 = new TextView(this);
                TextView txt3=new TextView(this);
                txt1.setText(offer[i] + " . Valid till "+ todate[i] + ".");
                //txt2.setText(fromdate[i]);
                //txt3.setText(todate[i]);
                txt1.setLayoutParams(params1);
                //txt2.setLayoutParams(params3);
                //txt3.setLayoutParams(params3);
                txt1.setTextSize(15);
               // txt2.setTextSize(12);
               //txt3.setTextSize(12);
                txt1.setTextColor(Color.BLACK);
                //txt2.setTextColor(Color.BLACK);
                //txt3.setTextColor(Color.BLACK);
                ViewGroup.LayoutParams p1 = txt1.getLayoutParams();
                p1.width = getResources().getDimensionPixelSize(R.dimen.width);
                txt1.setLayoutParams(p1);
                //ViewGroup.LayoutParams p2 = txt2.getLayoutParams();
                //p2.width = getResources().getDimensionPixelSize(R.dimen.fromdate_width);
                //txt2.setLayoutParams(p2);
               // ViewGroup.LayoutParams p3 = txt3.getLayoutParams();
                //p3.width=getResources().getDimensionPixelSize(R.dimen.fromdate_width);
               // txt3.setLayoutParams(p3);
                row.addView(txt1);
                //row.addView(txt3);
                //row.setLayoutParams(params2);
                row.setBackgroundDrawable(getResources().getDrawable(R.drawable.border));
                l2.addView(row);

            }
        }
    }

}
