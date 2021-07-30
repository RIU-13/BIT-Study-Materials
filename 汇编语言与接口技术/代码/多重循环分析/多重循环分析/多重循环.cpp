#include<stdio.h>
int a[10] = { 1,2,3,4,5,6,7,8,9 };

int main()
{
	for (int i = 0; i < 2; i++)
	{
		for (int j = 0; j < 3; j++)
		{
			for (int k = 0; k < 4; k++)
			{
				for (int m = 0; m < 5; m++)
				{
					int sum = a[i] + a[j] + a[k] + a[m];
					printf("%d\n", sum);
				}
			}
		}
	}
	return 0;
}