package com.jho.alana.calcif;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.TextView;
import android.widget.Toast;

import com.jho.alana.fragments.CalcFragmentl;
import com.jho.alana.fragments.Pager;
import com.jho.alana.myStack.MyStack;
import com.jho.alana.posfix.InfixToPosfix;

import org.w3c.dom.Text;

import java.util.Stack;

public class Main2Activity extends AppCompatActivity{

  //MyStack infixStack = new MyStack();
  private InfixToPosfix infixToPosfix = new InfixToPosfix();
  private Context context;
  private Stack stack;

  private TextView tvCalc, tvPosfix, tvInfix;
  private String expression = "";
  private String posfix = "";
  private int downSize = 0;
  private boolean isFirst = true;
  private Integer sizeText = 5;
  private Integer sizeTextOriginal = 0;
  /**
   * The {@link android.support.v4.view.PagerAdapter} that will provide fragments for each of the
   * sections. We use a {@link FragmentPagerAdapter} derivative, which will keep every loaded
   * fragment in memory. If this becomes too memory intensive, it may be best to switch to a {@link
   * android.support.v4.app.FragmentStatePagerAdapter}.
   */
  private SectionsPagerAdapter mSectionsPagerAdapter;

  /**
   * The {@link ViewPager} that will host the section contents.
   */
  private ViewPager mViewPager;

  @Override
  protected void onCreate(Bundle savedInstanceState){
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
    mViewPager.setAdapter(mSectionsPagerAdapter);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu){
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main2, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item){
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if(id == R.id.action_settings){
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  public class SectionsPagerAdapter extends FragmentPagerAdapter{

    public SectionsPagerAdapter(FragmentManager fm){
      super(fm);
    }

    @Override
    public Fragment getItem(int position){
      // getItem is called to instantiate the fragment for the given page.
      // Return a PlaceholderFragment (defined as a static inner class below).
            /*return PlaceholderFragment.newInstance(position + 1);
        }

        @Override*/
      //public CharSequence getPageTitle(int position) {
      switch(position){
        case 0:
          return CalcFragmentl.newInstance();  //PlaceholderFragment.newInstance(position + 1);
        case 1:
          return Pager.newInstance();
      }
      return null;
    }

    @Override
    public int getCount(){
      // Show 2 total pages.
      return 2;
    }
  }

  //Método que mostra os caracteres digitados na view
  public void onClick(View view){


    if(isFirst){
      tvCalc = (TextView) findViewById(R.id.tvCalc);
      downSize = (int) tvCalc.getTextSize();
      tvCalc.setText("\n");
      isFirst = !isFirst;
    }

    switch(view.getId()){

      case R.id.btnBack:
        backspace(view);
        setTextSize(true);
        break;
      case R.id.btnDel:
        clearAll(view);
        tvCalc.setTextSize(35);
        break;
      case R.id.btnEqual:
        equality(view);
        makeCalc(view);
        break;
      default:
        expression += ((TextView) view).getText().toString();
        print(tvCalc, expression);
        setTextSize(false);
        break;
    }
  }

  //Método para apagar um caractere
  public void backspace(View view){
    int length = expression.length();

    if(expression.isEmpty()){
      print(tvCalc, "");
      expression = "";
    } else{
      print(tvCalc, expression.substring(0, length - 1));
      expression = expression.substring(0, length - 1);
    }
  }

  //Método para apagar todos os caracteres da View
  public void clearAll(View view){
    print(tvCalc, "");
    expression = "";
  }

  //Método para dar push na pilha
  public void makeCalc(View view){

    try{
      exception();
    } catch(ArithmeticException e){
      tvCalc.setText(e.getMessage());
      expression = "";
      return;
    }

    stack = new Stack();
    String[] term = posfix.split("");

    for(String s : term){

      if(s.equals("+"))
        stack.push((Double) stack.pop() + (Double) stack.pop());
      else if(s.equals("-"))
        stack.push((Double) stack.pop() - (Double) stack.pop());
      else if(s.equals("*"))
        stack.push((Double) stack.pop() * (Double) stack.pop());
      else if(s.equals("/"))
        stack.push((Double) stack.pop() / (Double) stack.pop());
      else if(!s.equals(""))
        stack.push(Double.parseDouble(s));

    }

    expression = String.valueOf(stack.pop());
    print(tvCalc, expression);
    setTextSize(false);

  }

  private void exception(){

    char first;
    char second;

    for(int i = 0; i < expression.length() - 1; i++){
      first = expression.charAt(i);
      second = expression.charAt(i + 1);

      if(first == '/' && second == '0')
        throw new ArithmeticException("Divisão por zero!");
    }
  }

  private void setTextSize(boolean status){
    if(tvCalc.getLineCount() > 1 && !status){
      tvCalc.setTextSize(35 - sizeText);
      sizeTextOriginal = 35 - sizeText;
      sizeText += 5;
      //downSize++;
    } else if(status){
      Log.d("size", tvCalc.getTextSize() + " | " + downSize);
      if(tvCalc.getLineCount() == 1 && tvCalc.getTextSize() <= downSize){
        tvCalc.setTextSize(sizeTextOriginal + 5);
        sizeTextOriginal += 5;
        sizeText = 5;
      }
    }
  }

  //Método que pega as expressões infixa e posfixa e adiciona nas views
  public void equality(View view){

    tvPosfix = (TextView) findViewById(R.id.tvFormatPosfixa);
    tvInfix = (TextView) findViewById(R.id.tvFormatOrigin);
    context = getApplicationContext();

    if (infixToPosfix.countOcurrence(expression)) {
      Toast.makeText(context, "Expressão inválida!", Toast.LENGTH_SHORT).show();
      tvInfix.setText("Expressão inválida!");
      tvPosfix.setText("Expressão inválida!");
    } else {
      posfix = infixToPosfix.infixToPosfix(expression, context);
      tvInfix.setText(expression);
      tvPosfix.setText(posfix);
    }
  }

  private void print(TextView view, String value){
    view.setText(value);
  }

}
