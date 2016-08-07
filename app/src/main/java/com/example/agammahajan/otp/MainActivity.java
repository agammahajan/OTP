package com.example.agammahajan.otp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.button);
        final TextView textView = (TextView) findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int h = c.get(Calendar.HOUR_OF_DAY);
                int m = c.get(Calendar.MINUTE);
                System.out.println(h);
                System.out.println(m);


                DateFormat hf = new SimpleDateFormat("HHmm");
                String hr = hf.format(Calendar.getInstance().getTime());

                DateFormat mf = new SimpleDateFormat("mm");
                String min = mf.format(Calendar.getInstance().getTime());


//                System.out.println(hr);
//                System.out.println(min);

                int minute = Integer.parseInt(min);
                int last_digit=Math.abs(minute % 10);

//                System.out.println(last_digit);
                String str = Integer.toString(last_digit)+hr;
//                System.out.println(str);
                textView.setText(str);
            }
        });

    }


}
