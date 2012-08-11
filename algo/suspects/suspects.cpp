// suspects.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"


int main()
{
	size_t num_suspects, num_truths_expected;
    
    cin >> num_suspects >> num_truths_expected;

    vector<vector<size_t> > positive_for_me(num_suspects);
    vector<vector<size_t> > negative_for_me(num_suspects);
    vector<size_t> positive;
    vector<size_t> negative;


    for (size_t i = 0; i < num_suspects; ++i)
    {   
        int vote;
        cin >> vote;

        assert (vote != 0);

        if (vote > 0)
        {
            const size_t index = vote - 1;
            positive_for_me[index].push_back(i);
            positive.push_back(i);
        }
        else
        {
            const size_t index = -vote - 1;
            negative_for_me[index].push_back(i);
            negative.push_back(i);
        }
    }
    
    for (size_t i = 0; i < num_suspects; ++i)
    {
        std::sort(positive_for_me[i].begin(), positive_for_me[i].end());
        std::sort(negative_for_me[i].begin(), negative_for_me[i].end());
    }

    vector<size_t> truths(num_suspects, 0);
    //vector<size_t> lies(num_suspects, 0);
    size_t num_cases = 0;

    for (size_t i = 0; i < num_suspects; ++i)
    {
        const size_t num_truths = positive_for_me[i].size() + negative.size() - negative_for_me[i].size();
        if (num_truths != num_truths_expected)
        {
            // definitely not murderer
            //for (vector<size_t>::const_iterator it = positive_for_me[i].begin(); it != positive_for_me[i].end(); ++it)
                //lies[*it] = num_suspects;
            
            for (vector<size_t>::const_iterator it = negative_for_me[i].begin(); it != negative_for_me[i].end(); ++it)
                truths[*it] = num_suspects;

            continue;
        }

        // can be murderer
        for (vector<size_t>::const_iterator it = positive_for_me[i].begin(); it != positive_for_me[i].end(); ++it)
            ++truths[*it];

        for (vector<size_t>::const_iterator it = negative.begin(); it != negative.end(); ++it)
        {
            if (!std::binary_search(negative_for_me[i].begin(), negative_for_me[i].end(), *it))
                ++truths[*it];
        }
        
        ++num_cases;
    }


    for (size_t i = 0; i < num_suspects; ++i)
    {
        if (truths[i] >= num_cases)
            cout << "Truth" << endl;
        else if (truths[i] == 0)
            cout << "Lie" << endl;
        else
            cout << "Not defined" << endl;
    }


    return 0;
}

