package com.jho.alana.calcif;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    Pager adapter;

    TextView tvCalc;
    TextView tvExp;
    String number;
    String compareParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        adapter = new Pager(this);
        viewPager.setAdapter(adapter);

        TextView tvC = (TextView) findViewById(R.id.tvCalc);
        //TextView tvE = (TextView) findViewById(R.id.tvExp);
        //Button btn1 = (Button) findViewById(R.id.btn1);
    }

    //Método que mostra os caracteres digitados na view
    public void addNumber(View view, int value) {
        number = ((TextView)view).getText().toString();
        tvCalc = ((TextView) findViewById(R.id.tvCalc));
        tvCalc.setText(tvCalc.getText() + number);
    }

    public void addOperator(View view, String operator) {

    }

    //Método para apagar os caracteres da View
    public void clearAll(View view) {

        //String cls = ((TextView)view).getText().toString();
        tvCalc = ((TextView) findViewById(R.id.tvCalc));
        tvCalc.setText("");
    }

    public void makeCalc(View view) {

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
