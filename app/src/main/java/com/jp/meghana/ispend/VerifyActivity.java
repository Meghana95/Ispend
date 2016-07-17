package com.jp.meghana.ispend;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class VerifyActivity extends Activity {

    Button verify;
    EditText vuser,vcard;
    String user,card;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        verify=(Button)findViewById(R.id.bt_send);
        vuser=(EditText)findViewById(R.id.v_user);
        //vcard=(EditText)findViewById(R.id.v_card);
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkuser();
            }
        });
    }
    private void checkuser(){
        user = vuser.getText().toString();
        //card = vcard.getText().toString();
        userCheck(user);
    }
    private void userCheck(String userid) {
        class Forgot extends AsyncTask<String, Void, String> {

            ProgressDialog loading;

            UserClass uc = new UserClass();
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(VerifyActivity.this, "Please Wait",null, true, true);
            }
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(VerifyActivity.this,"Please check your email", Toast.LENGTH_LONG);
                Intent i = new Intent(VerifyActivity.this, MainActivity.class);
                startActivity(i);


            }

            @Override
            protected String doInBackground(String... params) {
                HashMap<String, String> data = new HashMap<String,String>();
                data.put("userid",params[0]);
                data.put("", "");
                String result= uc.sendPostRequest("http://ispendproj.esy.es/forgot.php", data);
                return  result;
            }
        }
        Forgot lu = new Forgot();
        lu.execute(user);

    }
}
