/**
 * 用于进行运算符和数值的比较运算
 */

public class Compare {
    static char Precede(char op1, char op2)//比较运算符的优先级
    {
        switch (op1) {
            case '+':
            case '-':
                if (op2 == '+' || op2 == '-' || op2 == ')' || op2 == '#') return '>';
                else if (op2 == '*' || op2 == '/' || op2 == '(') return '<';
            case '*':
            case '/':
                if (op2 == '+' || op2 == '-' || op2 == '*' || op2 == '/' || op2 == ')' || op2 == '#')
                    return '>';
                else if (op2 == '(') return '<';
            case '(':
                if (op2 == '+' || op2 == '-' || op2 == '*' || op2 == '/' || op2 == '(' || op2 == '^')
                    return '<';
                else if (op2 == ')') return '=';
            case ')':
                if (op2 == '+' || op2 == '-' || op2 == '*' || op2 == '/' || op2 == ')' || op2 == '#')
                    return '>';
            case '#':
                if (op2 == '+' || op2 == '-' || op2 == '*' || op2 == '/' || op2 == '(') return '<';
                else if (op2 == '#') return '=';
        }
        return 0;
    }

    static int operate(int a, char c, int b)//对数值进行四则运算
    {
        switch (c) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
        }
        return 0;
    }
}
