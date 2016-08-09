package com.jho.alana.calcif;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.TextView;
import android.widget.Toast;

import com.jho.alana.fragments.CalcFragmentl;
import com.jho.alana.fragments.Pager;
import com.jho.alana.myStack.MyStack;

public class Main2Activity extends AppCompatActivity{

  MyStack infixStack = new MyStack();

  private TextView tvCalc;
  private String expression = "";

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

    switch(view.getId()){

      case R.id.btnBack:
        backspace(view);
        break;
      case R.id.btnDel:
        clearAll(view);
        break;
      case R.id.btnEqual:
        makeCalc(view);
        break;
      default:
        expression += ((TextView) view).getText().toString();
        tvCalc.setText(expression);
        break;
    }

  }

  /**
   * Gambiarra pura para poder pegar a instância do TextView. Para isso, deve-se dar um clique nesse
   * textView antes de qualquer outra coisa...
   */
  public void getScreen(View view){
    tvCalc = (TextView) view;
  }

  //Método para apagar um caractere
  public void backspace(View view){
    int length = expression.length();

    if(expression.isEmpty()){
      tvCalc.setText("");
      expression = "";
    } else{
      tvCalc.setText(expression.substring(0, length - 1));
      expression = expression.substring(0, length - 1);
    }
  }

  //Método para apagar todos os caracteres da View
  public void clearAll(View view){
    tvCalc.setText("");
    expression = "";
  }


  //2 * (3 + 1) / 5 - (7 * (3 - 2)) * 9

  /*
  * 2 (2)
  *
  *
  * */

  //Método para dar push na pilha
  public void makeCalc(View view){

    StringBuffer infix = new StringBuffer();
    StringBuffer postfix = new StringBuffer();
    Integer k = 0;
    char charSequence;

    int tam = expression.length();

    infix.append(expression);

    for(int i = 0; i < infix.length(); i++){
      infixStack.push(infix.charAt(i));
    }

    //String[] resp = expression.split("");

    infixStack.push('(');
    //infixStack.push(')');
    infix.append(')');

    while(/*!infixStack.isEmpty() ||*/ k <= tam){

      charSequence = infix.charAt(k);

      if(isDigit(charSequence)){

        postfix.append(charSequence);

      }else if(isLeftParent(charSequence)){

        infixStack.push(charSequence);

      }else if(isOperator(charSequence)){

        do{

          if(infixStack.top() == null)
            break;

          if(priority(charSequence, (Character) infixStack.top())){
            postfix.append(infixStack.pop().getValue());

          }else{
            infixStack.push(charSequence);
          }

          if(infixStack.top() == null)
            break;

        }while(isOperator((Character) infixStack.top()));

      }else if(isRightParent(charSequence)){

        if(infixStack.top() != null){
          if(isOperator((Character) infixStack.top())){
            while(!isLeftParent((Character) infixStack.top())){
              postfix.append((Character) infixStack.pop().getValue());
            }

            if(isLeftParent((Character) infixStack.top())){
              infixStack.pop();
            }
          }
        }

      }
      k++;
    }

    //infixStack.push(")");

    Toast.makeText(this, postfix, Toast.LENGTH_SHORT).show();


  }

  private boolean isDigit(char dig){
    return dig != '(' && dig != ')' && dig != '/'
        && dig != '*' && dig != '-' && dig != '+';
  }

  private boolean isRightParent(char dig){
    return dig == ')';
  }

  private boolean isLeftParent(char dig){
    return dig == '(';
  }

  private boolean isOperator(char dig){
    return dig == '/' || dig == '*' || dig == '-'
        || dig == '+';
  }

  private boolean priority(char current, char previous){
    int i = priority(current);
    int j = priority(previous);

    return j >= i;
  }

  private int priority(char operator){
    if(operator == '/' || operator == '*')
      return 2;
    else if(operator == '-' || operator == '+')
      return 1;
    else
      return 0;
  }

  private char typeOperator(char operator){
    if(operator == '/')
      return '/';
    else if(operator == '*')
      return '*';
    else if(operator == '-')
      return '-';
    else
      return '+';
  }

}
