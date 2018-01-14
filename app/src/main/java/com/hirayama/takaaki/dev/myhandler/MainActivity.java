package com.hirayama.takaaki.dev.myhandler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView txt = (TextView)findViewById(R.id.textView);

        final Handler h = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                Log.d(TAG, new Integer(msg.what).toString());
                txt.setText(new Integer(msg.what).toString());
            }
        };


        Button b = (Button)findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "countup: start");

                Thread t = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        for (int i = 0; i < 50; i++) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            h.sendEmptyMessage(i);
                            //txt.setText(new Integer(i).toString());
                        }
                    }
                });

                t.start();

            }
        });

    }

}

