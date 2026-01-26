#include<stdio.h>
#include<stdlib.h>

/*DoMod is a recursive loop to mod the n value.*/
int DoMod(int n,int i, int c) {
	/*i is a value made to help mod n, the value the user enters.*/
	int   counter1;
	 if (i <= n) {

		if (n % i == 0) {
			c++;

		}


	 counter1 = DoMod(n,i+1,c);




	   return counter1;
	}
	else {
		return c;
	}


}
/*Function that uses DoMod in place a direct loop to calculate whether it's prime or not.*/
void DoPrime(int n) {
	int  c=0,i=1,counter=0;
	/*There to determine the limits of which the user can enter.*/
	 if(n !=-1 && n >=2 && n <= 2147483647) {
		printf("Enter a number between 2 and 2147483647 (enter -1 to stop): ");
		scanf_s("%d", &n);

	        counter=DoMod(n,i,c);

		if (counter == 2) {
			printf("The number %d is a prime number.\n", n);
			DoPrime(n);
		}
		/*The limits */
		else {
			if (n == -1) {
				printf("done\n");
				return;
			}
			if (n < 2 && n>2147483647 && n != -1) {
				printf("that's not a valid input!\n");
			}
			else {
				printf("The number %d is NOT a prime number.\n", n);
				DoPrime(n);
			}
		}


	}
}

/*Main program to calculate whether it's prime or not.*/
main() {

	int n=2, c = 0, counter=0;


	DoPrime(n);

	system("pause");
}
