package com.nbcb.algorithms.basic;

import java.util.Stack;

/**
 * 通过Stack这个数据结构，实现对表达式的解析、运算
 * 这个算法的关键点是，用到了Stack后进先出(LIFO)的特点，保存表达式的各个元素
 */
public class Evaluate {
    public static void main(String[] args) {
        /**
         * 定义一段表达式，比如( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )
         * 备注：这个表达式中，每个元素(括号、操作符、数字)必须以空格进行分割
         */
        String expression = "( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )";
        System.out.println("original expression : " + expression);
        String[] elements = expression.split(" ");

        /**
         * 定义两个Stack，一个放操作符，一个放数据
          */
        Stack<String> ops = new Stack<String>();
        Stack<Double> vals = new Stack<Double>();

        // 逐个字符读取这个表达式
        for(String element : elements){
//            System.out.println(element);

            if( element.equals("(")){
                // 碰到"("，什么都不做
            }else if(element.equals("+")){
                // 碰到"+"/"-"/"*"/"/"/"sqrt"这些操作符，就放到Stack中去
                ops.push(element);
            }else if(element.equals("-")){
                ops.push(element);
            }else if(element.equals("*")){
                ops.push(element);
            }else if(element.equals("/")){
                ops.push(element);
            }else if(element.equals("sqrt")){
                ops.push(element);
            }else if(element.equals(")")){
                // 碰到")"，要把"()"中的内容计算出来
                String op = ops.pop();  // 取出"()"中的操作符
                Double secondValue = vals.pop();  // 取出"()"中第二个数据
                Double result = 0.0;
                if(op.equals("+")){
                    // 如果是"+"/"-"/"*"/"/"/"这些操作符
                    // 就把"()"中第二个数据和第一个数据进行计算
                    Double firstValue = vals.pop();
                    result = firstValue + secondValue;
                    System.out.println( firstValue + "+" + secondValue + "=" + result);
                }else if(op.equals("-")){
                    // 碰到"-"/"/"，要特别注意第一个第二个数据的顺序
                    Double firstValue = vals.pop();
                    result = firstValue - secondValue;
                    System.out.println( firstValue + "-" + secondValue + "=" + result);
                }else if(op.equals("*")){
                    Double firstValue = vals.pop();
                    result = firstValue * secondValue;
                    System.out.println( firstValue + "*" + secondValue + "=" + result);
                }else if(op.equals("/")){
                    Double firstValue = vals.pop();
                    result = firstValue / secondValue;
                    System.out.println( firstValue + "/" + secondValue + "=" + result);
                }else if(op.equals("sqrt")){
                    result = Math.sqrt(secondValue);
                    System.out.println( "sqrt" + secondValue + "=" + result);
                }
                vals.push(result);

            }else{
                // 如果不是操作符，那肯定就是数字了，push到数据的stack中去
                vals.push(Double.parseDouble(element));
            }
        }

        // stack最后一个元素，是计算的结果
        System.out.println("the result : " + vals.pop());

    }
}
