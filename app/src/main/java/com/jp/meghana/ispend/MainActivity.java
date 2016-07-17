package com.jp.meghana.ispend;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
public class MainActivity extends Activity {
    DatabaseOperations db = new DatabaseOperations(MainActivity.this);

    Button login;
    EditText luserid,lpassword;
    String cardno;
    String userid,password;
    String[] userdetails;
    String CardNo,UserId,Name,Contact,EmailId;
    ArrayList<HashMap<String, String>> values;

    JSONParser jsonParser = new JSONParser();
    private static final String REGISTER_URL = "http://ispendproj.esy.es/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        values = new ArrayList<HashMap<String,String>>();

        login=(Button) findViewById(R.id.bt_login);
        luserid=(EditText) findViewById(R.id.l_userid);
        lpassword=(EditText) findViewById(R.id.l_password);


        SpannableString ss = new SpannableString("New User? Sign Up");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                startActivity(new Intent(MainActivity.this, SignupActivity.class));
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };
        ss.setSpan(clickableSpan, 10, 17, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        TextView textView = (TextView) findViewById(R.id.textView1);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setHighlightColor(Color.TRANSPARENT);


        SpannableString s1 = new SpannableString("Forgot Password?");
        ClickableSpan cspan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                startActivity(new Intent(MainActivity.this, VerifyActivity.class));
                finish();
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };
        s1.setSpan(cspan, 0, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        TextView tView = (TextView) findViewById(R.id.textView2);
        tView.setText(s1);
        tView.setMovementMethod(LinkMovementMethod.getInstance());
        tView.setHighlightColor(Color.TRANSPARENT);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUser();
            }
        });


    }

    private void checkUser(){
        userid = luserid.getText().toString();
        password = lpassword.getText().toString();
        userLogin(userid, password);
    }
    private void userLogin(final String userid,String password){
        class LoginUser extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            UserClass uc = new UserClass();
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this, "Please Wait",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                cardno=s;
                if(s.contains("Error")){
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();}
                if(!(s.contains("Error"))){
                    userdetails = s.split("&");
                    CardNo=userdetails[0];
                    UserId=userdetails[1];
                    Name=userdetails[2];
                    Contact=userdetails[3];
                    EmailId=userdetails[4];

                    db.putDetails(db, CardNo, UserId, Name,Contact,EmailId);
                    Log.i(s,"ABC");
                    Intent i = new Intent(MainActivity.this, NewDataActivity.class);
                    i.putExtra("mytext",CardNo);
                    startActivity(i);
                    finish();
                }
            }
            @Override
            protected String doInBackground(String... params) {
                HashMap<String, String> data = new HashMap<String,String>();
                data.put("userid",params[0]);
                data.put("password", params[1]);
                String result= uc.sendPostRequest(REGISTER_URL, data);
                Log.i("mainac value =",result);
                return  result;


            }
        }
        LoginUser lu = new LoginUser();
        lu.execute(userid, password);

    }


    }
