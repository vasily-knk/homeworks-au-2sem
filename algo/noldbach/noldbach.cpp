// noldbach.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"


bool is_prime(int number, vector<int> &primes) 
{
	for (size_t i = 0; i < primes.size() && primes[i] < number; ++i)
	{
		if (number % primes[i] == 0)
			return false;
	}
	
	primes.push_back(number);
	return true;
}

bool check_noldbach(int number, const vector<int> &primes)
{
	for (size_t i = 0; i < primes.size() - 1; ++i)
	{
		const int current = primes[i];
		const int another = primes[i + 1];
		
		if (current + another + 1 == number)
			return true;
	}
	return false;
}

int main(int argc, char* argv[])
{
	int n;
	int k;
	
	cin >> n;
	cin >> k;

	vector<int> primes;
	int counter = 0;

	for (int i = 2; i <= n; ++i)
	{
		if (!is_prime(i, primes))
			continue;
		
		if (check_noldbach(i, primes))
			++counter;

		if (counter == k)
			break;
	}

	cout << (counter < k ? "NO" : "YES") << endl;

	return 0;
}

