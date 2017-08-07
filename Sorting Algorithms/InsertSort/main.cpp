void InsertSort(int *arry,int length)//arry[0]为哨兵
{
	int i, j;
	for (i = 2; i <= length; i++)
	{
		if (arry[i] < arry[i - 1])//降序则将'<'改为'>'
		{
			arry[0] = arry[i];
			for (j = i - 1; arry[0]<arry[j]; j--)arry[j + 1] = arry[j];//降序则将'<'改为'>'
			arry[j + 1] = arry[0];
		}
	}
}
