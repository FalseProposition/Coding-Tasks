void HeapAdjust(int *arry, int n)
{
	int  j, temp_poisition, temp;
	for (j = n / 2 - 1; j >= 0; j--)
	{
		temp_poisition = 2 * j + 1;//数组中第k个元素下标为k-1，其父节点下标为[k/2-1]，两个孩子节点下标为2*(k-1)+1和2*(k-1)+2
		if (2 * j + 2 >= n)//如果该节点只有左孩子可用（包括没有右孩子或者右孩子用来储存首元素）
		{
			if (arry[j] < arry[temp_poisition])
			{
				temp = arry[j];
				arry[j] = arry[temp_poisition];
				arry[temp_poisition] = temp;
			}
		}
		else//该节点左右孩子都可用
		{
			if (arry[temp_poisition] < arry[2 * j + 2])temp_poisition = 2 * j + 2;//比较两个孩子大小，选出最大值与父节点比较
			if (arry[j] < arry[temp_poisition])
			{
				temp = arry[j];
				arry[j] = arry[temp_poisition];
				arry[temp_poisition] = temp;
			}
		}
		
	}
}
void HeapCreast(int *arry, int n)//创建一个大（小）根堆，其中循环次数应为[log(2)n]+1而不是n/2,因为假设大根堆首元素为最小值，则每调整一次其下降一层，而n个节点的完全二叉树的深度为[log(2)n]+1
{
	for (int i = 0; i < n/2; i++)HeapAdjust(arry,  n);
}
void HeapSort(int *arry, int n)//大根堆的升序排序，若要小根堆的降序则将HeapAdjust中if语句条件中的'<'改为'>'
{
	HeapCreast(arry, n);
	int temp;
	for (int i = 0; i < n; i++)
	{
		temp = arry[n -i- 1];
		arry[n -i- 1] = arry[0];
		arry[0] = temp;//将首元素储存到末尾倒数第i+1个
		HeapAdjust(arry, n - i-1);
	}
}
