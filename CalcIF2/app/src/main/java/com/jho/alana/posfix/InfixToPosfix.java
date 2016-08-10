package com.jho.alana.posfix;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;

/**
 * Created by alana on 09/08/16.
 */
public class InfixToPosfix {

    Stack stack = new Stack();

    public int getPriority(char caracter){

        int returnPriority = 0;
        String priority1 = "+-";
        String priority2 = "*/";

        if('(' == caracter)
            returnPriority = 1;
        else if(priority1.indexOf(caracter) >= 0)
            returnPriority = 2;
        else if(priority2.indexOf(caracter) >= 0)
            returnPriority = 3;
        else if ('^' == caracter)
            returnPriority = 4;
        return returnPriority;
    }

    public  boolean operating(char caracter){

        String operands = "1234567890";

        return (operands.indexOf(Character.toUpperCase(caracter)) >= 0);
    }

    public boolean operator(char caracter){

        String operators = "+-*/";

        return (operators.indexOf(caracter) >= 0);
    }

    public char top() {

        char summit;

        summit = (char)stack.peek();

        return summit;
    }

    public String infixToPosfix(String expression, Context context){

        String res = "";
        char caracter;
        int priority = 0;

        /*if (expression.indexOf("(") != expression.indexOf(")")) {
            Toast.makeText(context, "Expressão inválida!", Toast.LENGTH_SHORT).show();
        }*/

        for(int i = 0; i < expression.length(); i++) {
            caracter = expression.charAt(i);

            if (countOcurrence(expression)){
                Toast.makeText(context, "Expressão inválida!", Toast.LENGTH_SHORT).show();
            } else if (verifyParent(expression))
                Toast.makeText(context, "Expressão inválida!", Toast.LENGTH_SHORT).show();


            if(operating(caracter))
                res += caracter;
            else if(operator(caracter)){
                priority = getPriority(caracter);

                while((!stack.isEmpty()) && (getPriority(top()) >= priority))
                    res += stack.pop();
                stack.push(caracter);
            }
            else if('(' == caracter)
                stack.push(caracter);
            else if(')' == caracter){
                String item = stack.pop().toString();
                while(!item.equals("(")){
                    res += item;
                    item = stack.pop().toString();
                }
            }
        }
        while(!stack.isEmpty())
            res += stack.pop().toString();

        return res;
    }

    public boolean countOcurrence(String expression) {

        char exp[] = {};
        int countLeft = 0;
        int countRight = 0;

        for (int i = 0; i < expression.length(); i++) {
            exp = expression.toCharArray();

            if (exp[i] == '(') {
                countLeft++;
            } else if (exp[i] == ')') {
                countRight++;
            }
        }

        if (countLeft != countRight) {
            return true;
        }
        return false;
    }

    public boolean verifyParent(String expression) {

        char exp[] = {};
        String operators = "*/(";
        String numbers = "1234567890";

        for (int i = 0; i < expression.length(); i++) {
            exp = expression.toCharArray();

            if (i == 0 && exp[i] == '(')
                return false;

            if (exp[i] == '(') {

                if (exp[i - 1] != operators.charAt(i) || exp[i + 1] != numbers.charAt(i)) {
                    return true;
                }
            } else
                return false;
        }

        return false;
    }
}
