package com.mounla.hani.mcalculator;

import java.util.Stack;

/**
 * Created by hani-_000 on 2017-10-02.
 */

public class MathHelper {

    //region EVALUATION

    Stack<Double> values = new Stack<>();
    Stack<Character> ops = new Stack<>();
    double answer ;


    public  double evaluate(String input){

        char [] tokens = input.toCharArray();
        for (int i = 0; i < tokens.length; i++)
        {
            if (isletter(tokens[i]))
            {
                String word = "" ;
                while (i < tokens.length && isletter(tokens[i]))
                    word += tokens[i++];
                if (word.contains("ans")) {
                    values.push(answer);
                    i--;
                }
                else
                {
                    String sub = "";
                    double betweenbrackts;
                    i++;
                    while (tokens[i] != ')')
                    {
                        sub += tokens[i];
                        i++;
                    }
                    betweenbrackts = evaluate(sub);
                    if (word.lastIndexOf("^") == 0)
                        values.push(doMath(word, betweenbrackts, values.pop()));
                    else
                        values.push(doMath(word, betweenbrackts, 0.0));
                }
            }
            else if (isNumber(tokens[i]))
            {
                StringBuilder digitsNumber = new StringBuilder();

                if(tokens[i] == 'e')
                    values.push(Math.E);
                else
                {
                    if (tokens[0] == '-' && i == 1)
                        digitsNumber.append(tokens[0]);
                    while ((i < tokens.length && isNumber(tokens[i]))) // if the number is more than on digit
                        digitsNumber.append(tokens[i++]);
                    values.push(Double.parseDouble(digitsNumber.toString()));
                    i--;
                }
            }
            else if (tokens[i] == '%') // if its a percent character
                values.push(values.pop()/100);
            else if (tokens[i] == '!') // if its a Factorial character
                values.push(factorial(values.pop()));

            else if (isOp(tokens[i])) // if its a operation sign
            {
                if(tokens[i] =='-' && i == 0)
                    continue;
                while (!ops.empty() && hasPriority(tokens[i], ops.peek()))
                    values.push(doOperation(ops.pop(), values.pop(), values.pop()));
                ops.push(tokens[i]);
            }
            else if (tokens[i] == '(') // if its an open bracket
                ops.push(tokens[i]);

            else if (tokens[i] == ')') // if its a close bracket
            {                          // evaluate the string until we see the '('
                while (ops.peek() != '(')
                    values.push(doOperation(ops.pop(), values.pop(), values.pop()));
                ops.pop();
            }
        }
        while (!ops.isEmpty()) // do all the operations until the operation stack is empty
            values.push(doOperation(ops.pop(), values.pop(), values.pop()));
        answer = values.pop();
        return answer;
    }

//    private double subEvaluate(String sub) {
//        char [] tokens = sub.toCharArray();
//        Stack<Double> values = new Stack<>();
//        Stack<Character> ops = new Stack<>();
//        double subAnswer;
//        for (int i = 0; i < tokens.length; i++)
//        {
//            if (isletter(tokens[i]))
//            {
//                StringBuilder word = new StringBuilder();
//                while (i < tokens.length && isletter(tokens[i]))
//                    word.append(tokens[i++]);
//                String subsub ="";
//                double betweenbrackts;
//                i++;
//                while (tokens[i] != ')')
//                {
//                    subsub +=tokens[i];
//                    i++;
//                }
//                betweenbrackts = Double.parseDouble(sub);
//                values.push(doMath(word.toString() , betweenbrackts, values.peek()));
//            }
//            else if (isNumber(tokens[i]))
//            {
//                StringBuilder digitsNumber = new StringBuilder();
//
//                if(tokens[i] == 'e')
//                    values.push(Math.E);
//                else
//                {
//                    if (tokens[0] == '-' && i == 1)
//                        digitsNumber.append(tokens[0]);
//                    while ((i < tokens.length && isNumber(tokens[i]))) // if the number is more than on digit
//                        digitsNumber.append(tokens[i++]);
//                    values.push(Double.parseDouble(digitsNumber.toString()));
//                    i--;
//                }
//            }
//            else if (isOp(tokens[i])) // if its a operation sign
//            {
//                if(tokens[i] =='-' && i == 0)
//                    continue;
//                while (!ops.empty() && hasPriority(tokens[i], ops.peek()))
//                    values.push(doOperation(ops.pop(), values.pop(), values.pop()));
//                ops.push(tokens[i]);
//            }
//            else if (tokens[i] == '(') // if its an open bracket
//                ops.push(tokens[i]);
//
//            else if (tokens[i] == ')') // if its a close bracket
//            {                          // evaluate the string until we see the '('
//                while (ops.peek() != '(')
//                    values.push(doOperation(ops.pop(), values.pop(), values.pop()));
//                ops.pop();
//            }
//        }
//        while (!ops.isEmpty()) // do all the operations until the operation stack is empty
//            values.push(doOperation(ops.pop(), values.pop(), values.pop()));
//        subAnswer = values.pop();
//        return subAnswer;
//    }

    private double doMath(String word, double subNumber, double num) {
        switch (word)
        {
            case "sin" : return Math.sin(subNumber*Math.PI/180);
            case "cos" : return Math.cos(subNumber * Math.PI / 180);
            case "tan" : return Math.tan(subNumber * Math.PI / 180);
            case "ln" : return Math.log(subNumber);
            case "log" : return Math.log10(subNumber);
            case "√" : return Math.sqrt(subNumber);
            case "^" : return Math.pow(num,subNumber);
        }
        return 0;
    }

    public boolean isNumber(char c) {
        return (c >= '0' && c <= '9')
                || c == '.' || c == 'e' || c == 'E';
    }

    public boolean isOp(char c) {
        return c == '+' || c == '-' ||
                c == '×' || c == '÷' || c == '%' || c== '!';
    }

    public boolean hasPriority(char op1, char op2) // check the priority between the current char (op1)
    {                                              // and the operation stack top (op2) if current char
        if(op2 =='(' || op2 == ')')          // is more important than the operation stack top do its operation
            return false;
        else if((op1 == '×' || op1 == '÷') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }

    private  double doOperation(char op, double b, double a)  {
        switch(op)
        {
            case '+' : return a + b;
            case '-' : return a - b;
            case '×' : return a * b;
            case '*' : return a * b;
            case '%' : return b /100;
            case '/' :
                if(b == 0) {
//                    throw new Throwable("\"Cannot divide by Zero ! \\n check your input\"");
//                    Toast t = Toast.makeText(getApplicationContext(),, 2000 );
//                    t.show();
                }
                else
                    return a / b;
            case '÷' :
                if(b == 0) {
//                    throw new Throwable("\"Cannot divide by Zero ! \\n check your input\"");

//                    Toast t = Toast.makeText(this,"Cannot divide by Zero ! \n check your input", 2000 );
//                    t.show();
                }
                else
                    return a / b;
        }
        return 0;
    }

    private double factorial(double num) {
        double fact = 1;
        if(num >1)
            fact = num *factorial(num-1);
        return fact;
    }

    public boolean isletter(char c) {
        return c >= 'a' && c <= 'z' && c != 'e' || c == '√' || c == '^';  // Simplified if statment it will return
        // true if any of these conditions are true.
    }
    //endregion

}
