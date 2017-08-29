package com.example.shinelon.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.util.Stack;

/**
 * 待完善：
 * 输入数据带有小数
 * 点击clear清空栈
 * 设置backspace
 * 支持负数
 * 判断表达式错误显示error
 * 连续计算，即可以直接用上式的计算结果
 * 输入')'时已经表示结算，是否有必要进行相关复杂判断
 * 简化过程
 */
public class MainActivity extends AppCompatActivity {

    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0,point,equal,plus,minus,multiply,divide,left_bracket,right_bracket,clear;
    TextView fomular,result;
    Stack<Integer> stack_num;
    Stack<Character> stack_op;
    String str="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stack_num=new Stack<Integer>();
        stack_op=new Stack<Character>();
        stack_op.push('#');
        btn1 = (Button) findViewById(R.id.num1);
        btn2 = (Button) findViewById(R.id.num2);
        btn3 = (Button) findViewById(R.id.num3);
        btn4 = (Button) findViewById(R.id.num4);
        btn5 = (Button) findViewById(R.id.num5);
        btn6 = (Button) findViewById(R.id.num6);
        btn7 = (Button) findViewById(R.id.num7);
        btn8 = (Button) findViewById(R.id.num8);
        btn9 = (Button) findViewById(R.id.num9);
        btn0 = (Button) findViewById(R.id.num0);
        point = (Button) findViewById(R.id.point);
        equal = (Button) findViewById(R.id.equal);
        minus = (Button) findViewById(R.id.minus);
        plus = (Button) findViewById(R.id.plus);
        multiply = (Button) findViewById(R.id.multiply);
        divide = (Button) findViewById(R.id.divide);
        left_bracket = (Button) findViewById(R.id.left_bracket);
        right_bracket = (Button) findViewById(R.id.right_bracket);
        clear = (Button) findViewById(R.id.clear);
        fomular = (TextView) findViewById(R.id.formula);
        result = (TextView) findViewById(R.id.result);
        btn0.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                stack_num.push(0);
                str=str+0;
                result.setText(str);
            }
        });
        btn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                stack_num.push(1);
                str=str+1;
                result.setText(str);
            }
        });
        btn2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                stack_num.push(2);
                str=str+2;
                result.setText(str);
            }
        });
        btn3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                stack_num.push(3);
                str=str+3;
                result.setText(str);
            }
        });
        btn4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                stack_num.push(4);
                str=str+4;
                result.setText(str);
            }
        });
        btn5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                stack_num.push(5);
                str=str+5;
                result.setText(str);
            }
        });
        btn6.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                stack_num.push(6);
                str=str+6;
                result.setText(str);
            }
        });
        btn7.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                stack_num.push(7);
                str=str+7;
                result.setText(str);
            }
        });
        btn8.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                stack_num.push(8);
                str=str+8;
                result.setText(str);
            }
        });
        btn9.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                stack_num.push(9);
                str=str+9;
                result.setText(str);
            }
        });
        plus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                str=str+'+';
                result.setText(str);
                switch (Compare.Precede( stack_op.peek(),'+')){
                    case '<':
                        stack_op.push('+');
                        break;
                    case '=':
                        stack_op.pop();
                        break;
                    case '>':
                        char c=stack_op.pop();
                        int i=stack_num.pop();
                        int j=stack_num.pop();
                        int t=Compare.operate(i,c,j);
                        stack_num.push(t);
                        stack_op.push('+');
                }
            }
        });
        minus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                str=str+'-';
                result.setText(str);
                switch (Compare.Precede( stack_op.peek(),'+')) {
                    case '<':
                        stack_op.push('-');
                        break;
                    case '=':
                        stack_op.pop();
                        break;
                    case '>':
                        char c = stack_op.pop();
                        int i = stack_num.pop();
                        int j = stack_num.pop();
                        int t = Compare.operate(i, c, j);
                        stack_num.push(t);
                        stack_op.push('-');
                }
            }
        });

        multiply.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                str=str+'*';
                result.setText(str);
                switch (Compare.Precede( stack_op.peek(),'+')) {
                    case '<':
                        stack_op.push('*');
                        break;
                    case '=':
                        stack_op.pop();
                        break;
                    case '>':
                        char c = stack_op.pop();
                        int i = stack_num.pop();
                        int j = stack_num.pop();
                        int t = Compare.operate(i, c, j);
                        stack_num.push(t);
                        stack_op.push('*');
                }
            }
        });
        divide.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                str=str+'/';
                result.setText(str);
                switch (Compare.Precede( stack_op.peek(),'+')) {
                    case '<':
                        stack_op.push('/');
                        break;
                    case '=':
                        stack_op.pop();
                        break;
                    case '>':
                        char c = stack_op.pop();
                        int i = stack_num.pop();
                        int j = stack_num.pop();
                        int t = Compare.operate(i, c, j);
                        stack_num.push(t);
                        stack_op.push('/');
                }
            }
        });
        left_bracket.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                str=str+'(';
                result.setText(str);
                switch (Compare.Precede( stack_op.peek(),'+')) {
                    case '<':
                        stack_op.push('(');
                        break;
                    case '=':
                        stack_op.pop();
                        break;
                    case '>':
                        char c = stack_op.pop();
                        int i = stack_num.pop();
                        int j = stack_num.pop();
                        int t = Compare.operate(i, c, j);
                        stack_num.push(t);
                        stack_op.push('(');
                }
            }
        });
        right_bracket.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                str=str+')';
                result.setText(str);
                switch (Compare.Precede( stack_op.peek(),'+')) {
                    case '<':
                        stack_op.push(')');
                        break;
                    case '=':
                        stack_op.pop();
                        break;
                    case '>':
                        char c = stack_op.pop();
                        int i = stack_num.pop();
                        int j = stack_num.pop();
                        int t = Compare.operate(i, c, j);
                        stack_num.push(t);
                        if(stack_op.peek()=='(') stack_op.push(')');
                        else stack_op.push(')');
                }
            }
        });
        equal.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                int t;
                if(stack_op.peek()=='#')t=stack_num.peek();
                else{
                    char c = stack_op.pop();
                    int i = stack_num.pop();
                    int j = stack_num.pop();
                     t = Compare.operate(i, c, j);
                }
                str=str+'=';
                fomular.setText(str);
                result.setText(t+"");
                str="";
            }
        });
        clear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                str="";
                fomular.setText(str);
                result.setText(str);
            }
        });
    }
}
