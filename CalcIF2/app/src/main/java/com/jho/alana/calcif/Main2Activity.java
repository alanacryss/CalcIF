package com.jho.alana.calcif;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.jho.alana.fragments.CalcFragmentl;
import com.jho.alana.fragments.Pager;
import com.jho.alana.myStack.MyStack;

import java.util.zip.Inflater;

public class Main2Activity extends AppCompatActivity {

    MyStack myStack = new MyStack();

    private TextView tvCalc;
    private String expression = "";
    char operator;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        //Fragment d = mSectionsPagerAdapter.getItem(1);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);

        ViewGroup viewGroup = (ViewGroup) toolbar.getParent();
        LayoutInflater inflater = getLayoutInflater();

        mViewPager.setAdapter(mSectionsPagerAdapter);

        //mViewPager.getCurrentItem();

        //View view = inflater.inflate(R.layout.content_main, mViewPager, true);

        //tvCalc = new TextView(this);

        //mViewPager.findViewById(R.id.tvCalc);
        //mViewPager.findViewById()
        //tvCalc = (TextView) view.findViewById(R.id.tvCalc);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
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

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            /*return PlaceholderFragment.newInstance(position + 1);
        }

        @Override*/
        //public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return CalcFragmentl.newInstance();  //PlaceholderFragment.newInstance(position + 1);
                case 1:
                    return Pager.newInstance();
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }
    }

    //Método que mostra os caracteres digitados na view
    public void onClick(View view) {

        switch(view.getId()){
            
            case R.id.btnBack:
                backspace(view);
            case R.id.btnDel:
                clearAll(view);
            default:
                expression += ((TextView)view).getText().toString();
                Log.d("string", ((TextView)view).getText().toString());
                //tvCalc.setText(((TextView) view).getText().toString());
                tvCalc.setText(expression);
                Log.d("string", tvCalc.getText().toString());
        }

    }

    /**
     * Gambiarra pura para poder pegar a instância do TextView. Para isso,
     * deve-se dar um clique nesse textView antes de qualquer outra coisa...
     * */
    public void getScreen(View view){
        tvCalc = (TextView) view;
    }

    //Método para apagar um caractere
    public void backspace(View view) {
        expression = tvCalc.getText().toString();
        int length = expression.length();

        if (expression.isEmpty()) {
            tvCalc.setText("");
        } else
            tvCalc.setText(expression.substring(0, length - 1));
    }

    //Método para apagar todos os caracteres da View
    public void clearAll(View view) {
        //String cls = ((TextView)view).getText().toString();
        tvCalc.setText("");
    }

    //Método para dar push na pilha
    /*public void makeCalc(View view) {

        if (Double.parseDouble(expression) >= 0 || Double.parseDouble(expression) <= 9) {
            if (operator == '+' || operator == '-' || operator == 'x' || operator == '/') {
                myStack.push(Double.parseDouble(expression), operator);

                Log.d("stack", "myStack");

            }
        }
    }*/
}
