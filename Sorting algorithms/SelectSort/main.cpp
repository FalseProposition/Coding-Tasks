void SelectSort(int *arry,int length)
{
	int i, j, temp,min_poisition;
	for (i = 0; i <length-1; i++)
	{
		min_poisition = i;
		for (j = i + 1; j < length; j++)
			if (arry[j] < arry[min_poisition])min_poisition = j;//降序则将'<'改为'>'
		if (min_poisition != i)
		{
			temp = arry[min_poisition];
			arry[min_poisition] = arry[i];
			arry[i] = temp;
		}
	}
}
