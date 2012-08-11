// message.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"

int get_diff(const string::const_iterator begin1, 
            const string::const_iterator end1, 
            const string::const_iterator begin2)
{
    string::const_iterator it1 = begin1;
    string::const_iterator it2 = begin2;
    int diff = 0;
    
    for (; it1 != end1; ++it1, ++it2)
    {
        if (*it1 != *it2)
            ++diff;           
    }
    return diff;
}

int main(int argc, char* argv[])
{
	string src, dst;
    
    cin >> src;
    cin >> dst;

    string match(dst.size() * 2 + src.size(), ' ');
    std::copy(src.begin(), src.end(), match.begin() + dst.size());

    int min_diff = dst.size();
    //string::const_iterator mid_diff_it = match.begin();
    for (string::const_iterator it = match.begin(); it != match.end() - dst.size(); ++it)
    {
        const int diff = get_diff(dst.begin(), dst.end(), it);
        if (diff < min_diff)
        {
            min_diff = diff;
            //mid_diff_it = it;
        }
    }
    
    cout << min_diff << endl;

    return 0;
}

