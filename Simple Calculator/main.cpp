/*简易计算器，支持带有‘（‘、’）’的四则运算，没有GUI图形界面，日后会继续完善*/
#include <stdio.h>
#include <string.h>
#define max 100
char Precede(char op1, char op2)//比较运算符的优先级
{
	switch (op1)
	{
	case '+':case'-':
		if (op2 == '+' || op2 == '-' || op2 == ')' || op2 == '#') return '>';
		else if (op2 == '*' || op2 == '/' || op2 == '(') return '<';
	case '*':case '/':
		if (op2 == '+' || op2 == '-' || op2 == '*' || op2 == '/' || op2 == ')' || op2 == '#') return '>';
		else if (op2 == '(') return '<';
	case '(':
		if (op2 == '+' || op2 == '-' || op2 == '*' || op2 == '/' || op2 == '(' || op2 == '^') return '<';
		else if (op2 == ')') return '=';
	case ')':
		if (op2 == '+' || op2 == '-' || op2 == '*' || op2 == '/' || op2 == ')' || op2 == '#') return '>';
	case '#':
		if (op2 == '+' || op2 == '-' || op2 == '*' || op2 == '/' || op2 == '(') return '<';
		else if (op2 == '#') return '=';
	}
}
int operate(int a, char c, int b)//对数值进行四则运算
{
	switch (c)
	{
	case '+':return a + b;
	case '-':return a - b;
	case '*':return a*b;
	case '/':return a / b;
	}
}
void main()
{
	char input[max];//将输入的式子存放到该数组中
	char opt[max];//存放运算符
	int num[max];//存放数值
	char c;
	for (;;)
	{
		int i = 0, j = 0, k = 0, t = 0;
		gets(input);
		opt[0] = '#';
		if (input[0] >= '0'&&input[0] <= '9')//将数值赋予t
		{
			while (input[k] >= '0'&&input[k] <= '9')
			{
				t = t * 10 + (input[k] - '0');
				k++;
			}
		}
		else c = input[k++];//将运算符赋予c
		while (opt[1] != '#')
		{
			if (t != 0)
			{
				num[j++] = t;
				t = 0;
				if (k == strlen(input))c = '#';//若读取到数组的最后一位，则将#赋予c表示结束
				else c = input[k++];
			}
			else
				switch (Precede(opt[i], c))
			{
				case '<':
					opt[++i] = c;
					if (k == strlen(input))c = '#';
					else
					if (input[k] >= '0'&&input[k] <= '9')
					{
						while (input[k] >= '0'&&input[k] <= '9')
						{
							t = t * 10 + (input[k] - '0');
							k++;
						}
					}
					else c = input[k++];
					break;
				case '=':
					if (c != '#')//出现“=”有两种情况：（）与##，前者继续运算，后者直接结束
					{
						i--;
						if (k == strlen(input))c = '#';
						else
						if (input[k] >= '0'&&input[k] <= '9')
						{
							while (input[k] >= '0'&&input[k] <= '9')
							{
								t = t * 10 + (input[k] - '0');
								k++;
							}
						}
						else c = input[k++];
					}
					else opt[1] = '#';//运算结束，将while的循环条件设为假
					break;
				case '>':
					num[j - 2] = operate(num[j - 2], opt[i], num[j - 1]);
					j--;
					i--;
					break;
			}
		}
		printf("=%d\n", num[j - 1]);
		opt[1] = '!';//重置opt[1]，使while能够循环
	}
}
