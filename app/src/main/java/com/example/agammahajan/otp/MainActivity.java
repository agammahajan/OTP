package com.example.agammahajan.otp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.button);
        final TextView textView = (TextView) findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                DateFormat d = new SimpleDateFormat("yyMMddHHmm");
                String da = d.format(Calendar.getInstance().getTime());

                int tt = Integer.parseInt(da);


                String test= Integer.toHexString(tt);

                String temp="";
                String mykey = "secret";

                int c=5;
                try {
                    Mac mac = Mac.getInstance("HmacSHA1");
                    SecretKeySpec secret = new SecretKeySpec(mykey.getBytes(),"HmacSHA1");
                    mac.init(secret);
                    byte[] digest = mac.doFinal(test.getBytes());
                    String enc = new String(digest);

                    for (byte b : digest) {
                        if(c>=0){
                            temp=temp+String.format("%02x", b);
                            c--;
                        }
                        else break;

                    }

                    long result=Long.parseLong(temp,16);
                    result=result%100000;

                    textView.setText(Long.toString(result));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }




            }
        });

    }


}
