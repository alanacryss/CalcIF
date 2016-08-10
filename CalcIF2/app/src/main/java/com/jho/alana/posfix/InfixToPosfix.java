package com.jho.alana.posfix;

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

    public String infixToPosfix(String expressao){

        String res = "";
        char caracter;
        int priority = 0;

        for(int i = 0; i < expressao.length(); i++){
            caracter = expressao.charAt(i);
            if(operating(caracter))
                res += caracter;
            else if(operator(caracter)){
                priority = getPriority(caracter);
                // o método .peek retorna um object, porém o método que eu criei obterPrioridade aceita char no parametro...
                //como dizer que o objeto retornado é char, tentei casting mas não deu certo.
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
}
