package com.example.lession02_asynctask;

import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.lang.reflect.Type;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int a[];
    final int SIZE = 100;
    TextView textView;
    TextView textView1;

    enum TYPE {CHAN, LE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textView = findViewById(R.id.textView);
        textView1 = findViewById(R.id.textView1);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayGeneration(SIZE);
                new MyAsyncTask(TYPE.CHAN).execute();
                new MyAsyncTask(TYPE.LE).execute();
            }
        });
    }

    void arrayGeneration(int size){
        a = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++){
            a[i] = random.nextInt(100);
        }
    }

    class MyAsyncTask extends AsyncTask<Void, Void, Integer>{

        TYPE type;

        public MyAsyncTask(TYPE type) {
            this.type = type;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
//            int tong = 0;
//            for (int i =0; i< SIZE; i++){
//                tong += a[i];
//            }
            int tong = 0, tongchan = 0, tongle = 0;
            for (int i=0; i < SIZE; i++) {
                tong += a[i];
                if (a[i] % 2 == 0  && type == TYPE.CHAN) {
                    tongchan += a[i];
                }
                if (a[i] % 2 != 0 && type == TYPE.LE){
                    tongle += a[i];
                }
            }
            return tong;
        }


        @Override
        protected void onPostExecute(Integer h) {
            textView.setText("tổng chẵn: " + h);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}