package com.jho.alana.calcif;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

    TextView tvCalc;
    TextView tvExp;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnZero, btnSum, btnSubt, btnMult, btnDiv, btnParentRight, btnParentLeft;
    String number;

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

        TextView tvC = (TextView) findViewById(R.id.tvCalc);
        TextView tvE = (TextView) findViewById(R.id.tvExp);
        Button btn1 = (Button) findViewById(R.id.btn1);
    }

    public void addNumber(View view) {
        number = ((TextView)view).getText().toString();
        tvCalc = ((TextView) findViewById(R.id.tvCalc));
        tvCalc.setText(tvCalc.getText() + number);
    }
    //Método para apagar os caracteres da View
    public void backspace(View view) {
        //String cls = ((TextView)view).getText().toString();
        tvCalc = ((TextView) findViewById(R.id.tvCalc));
        tvCalc.setText("");
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
