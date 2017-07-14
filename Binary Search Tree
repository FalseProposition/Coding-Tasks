#include <stdio.h>
#include <stdlib.h>
#define max 100
typedef struct node
{
	struct node *lchild;
	struct node *rchild;
	int data;
}BSTNode, *BSTree;
/*插入*/
int InsertBST(BSTree *root, int data)
{
	BSTree r, s, pre;
	r = (BSTree)malloc(sizeof(BSTNode));
	r->data = data;
	r->lchild = r->rchild = NULL;
	if (*root == NULL)*root = r;
	pre = NULL;
	s = *root;
	while (s)
	{
		if (data == s->data)
			return 0;
		else if (data < s->data)
		{
			pre = s;
			s = s->lchild;
		}
		else
		{
			pre = s;
			s = s->rchild;
		}
	}
	if (data < pre->data)
		pre->lchild = r;
	else
		pre->rchild = r;
	return 1;
}
/*初始化*/
void init(BSTree *root)
{
	int num;
	*root = NULL;
	scanf_s("%d", &num);
	while (num)
	{
		InsertBST(root, num);
		scanf_s("%d", &num);
	}
}
/*中序遍历存入arr*/
void InOrder(BSTree root,int *arr)
{
	if (root != NULL)
	{
		InOrder(root->lchild,arr);
		*arr=root->data;
		arr++;
		InOrder(root->rchild,arr);
	}
}
/*搜索*/
BSTree SearchBST(BSTree root, int data)
{
	BSTree q= root;
	while (q)
	{
		if (q->data == data)
			return q;
		if (q->data > data)
			q = q->lchild;
		else
			q = q->rchild;
	}
	return 0;                        
}
/*销毁*/
void deinit(BSTree root)
{
	if (root)
	{
		if (root->lchild)deinit(root->lchild);
		if (root->rchild)deinit(root->rchild);
		free(root);
		root = NULL;
	}
}
/*删除*/
int DeleteBST(BSTree *root, int data)//找到所要删除的节点
{
	BSTree *s=root;
	if (data == (*s)->data)Delete(s);
	else if (data < (*s)->data)return DeleteBST(&(*s)->lchild, data);
	else if (data >(*s)->data)return DeleteBST(&(*s)->rchild, data);
	else return 0;
}
BSTree* Delete(BSTree *root)
{
	BSTree q, s;
	if (!(*root)->lchild && !(*root)->rchild) *root = NULL;// p为叶子节点，直接删除 
	else if (!(*root)->lchild)*root = (*root)->rchild; //左子树为空
	else if (!(*root)->rchild)*root = (*root)->lchild; //右子树为空
	else                        
	{
		q = *root;
		s = (*root)->lchild;
		while (s->rchild)//寻找左子树的最右子树
		{
			q = s;
			s = s->rchild;
		}
		(*root)->data = s->data;
		if (q != *root)//判断是否执行上述while循环 
			q->rchild = s->lchild;    
		else
			q->lchild = s->lchild;  
		free(s);
	}
	return root;
}
/*判断是否为BST*/
bool isValidBST(BSTree* root)
{
	int arr[max],i=0;
	InOrder(*root, arr);//若中序遍历为升序，则为BST
	while (i < max)
	{
		if (arr[i] >= arr[i + 1])return 0;
	}
	return 1;
}
/*寻找两节点之差的最小值*/
int getMinimumDifference(BSTree* root)
{
	int arr[max], i = 1,temp,min;
	InOrder(*root, arr);//只需在中序遍历的数组中比较即可
	min = arr[1] - arr[0];
	while (i < max)
	{
		temp = arr[i+1] - arr[i];
		if (min>temp)min = temp;
	}
	return min;
}
