// suspects.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"


int main()
{
	size_t num_suspects, num_truth;
    
    cin >> num_suspects >> num_truth;

    vector<vector<size_t> > positive_for_me(num_suspects);
    vector<vector<size_t> > negative_for_me(num_suspects);
    size_t num_positive = 0;
    size_t num_negative = 0;

    for (size_t i = 1; i <= num_suspects; ++i)
    {   
        int vote;
        cin >> vote;

        assert (vote != 0);

        if (vote > 0)
        {
            const size_t index = vote - 1;
            positive_for_me[index].push_back(i);
            ++num_positive;
        }
        else
        {
            const size_t index = -vote - 1;
            negative_for_me[index].push_back(i);
            ++num_negative;
        }
    }


    return 0;
}

