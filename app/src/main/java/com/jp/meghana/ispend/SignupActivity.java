package com.jp.meghana.ispend;
import android.content.ContentValues;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.ProgressDialog;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.os.AsyncTask;
import android.widget.Toast;
import android.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import org.json.JSONException;
import org.json.JSONObject;


public class SignupActivity extends Activity {

    private ProgressDialog pDialog;

    Button signup;
    EditText scardno,spassword ;
    JSONParser jsonParser = new JSONParser();
    private static final String REGISTER_URL = "http://ispendproj.esy.es/signup.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signup=(Button)findViewById(R.id.bt_signup);
        scardno=(EditText)findViewById(R.id.s_cardno);
        spassword=(EditText)findViewById(R.id.s_password);

        SpannableString ss = new SpannableString("Existing User? Sign In");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                startActivity(new Intent(SignupActivity.this, MainActivity.class));
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };
        ss.setSpan(clickableSpan, 15, 22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        TextView textView = (TextView) findViewById(R.id.textView1);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setHighlightColor(Color.TRANSPARENT);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cardno = scardno.getText().toString();
                String password = spassword.getText().toString();
                createUser();
            }
        });
    }


   private void createUser(){

       String cardno = scardno.getText().toString();
       String password = spassword.getText().toString();


       register(cardno, password);
   }
    private void register(String cardno, String password) {
        class RegisterUser extends AsyncTask<String, Void, String> {

            ProgressDialog loading;

            UserClass uc = new UserClass();
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(SignupActivity.this, "Please Wait",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
                if(s.contains("successfully registered"))
                {
                    Intent i = new Intent(SignupActivity.this, MainActivity.class);
                    startActivity(i);
                }
            }


            @Override
            protected String doInBackground(String... params) {
                HashMap<String, String> data = new HashMap<String,String>();
                data.put("cardno",params[0]);
                data.put("password",params[1]);


                String result = uc.sendPostRequest(REGISTER_URL,data);

                return  result;
            }
        }
        RegisterUser ru = new RegisterUser();
        ru.execute(cardno,password);

        }


    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isValidPhoneNumber(String num) {
        if (num.length()!=10) {
            return false;
        } else {
            return true;
        }
    }
}
