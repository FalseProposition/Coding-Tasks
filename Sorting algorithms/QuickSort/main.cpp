int OnceSort(int *arry,int low,int high)//arry[0]为辅助空间
{
	arry[0] = arry[low];
	int axis = arry[low];
	while (low < high)
	{
		while (low < high&&arry[high]>=axis)high--;
		arry[low] = arry[high];
		while (low < high&&arry[low]<=axis)low++;
		arry[high] = arry[low];
	}
	arry[low] = arry[0];
	return low;
}//降序改动最里层while语句条件的'<'和'>'
void QuickSort(int *arry, int low, int high)
{
	if (low < high)
	{
		int axis_poisition = OnceSort(arry, low, high);
		QuickSort(arry, low, axis_poisition - 1);
		QuickSort(arry, axis_poisition + 1, high);
	}
}
