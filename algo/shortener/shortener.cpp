// shortener.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"

void list_substrings(string::const_iterator begin, 
                     string::const_iterator end,   
                     const size_t substring_length, 
                     const string prefix,
                     vector<string> *out)
{
    if (substring_length == 0)
    {
        out->push_back(prefix);
        return;
    }

    string str(prefix);
    str.resize(prefix.length() + 1, '\0');

    for (string::const_iterator it = begin; it != end; ++it)
    {
        str[prefix.length()] = *it;
        list_substrings(it + 1, end, substring_length - 1, str, out);
    }
}

void list_all_substrings(const string &str, const size_t substring_length, vector<string> *out)
{
    for (size_t i = 1; i <= substring_length; ++i)
        list_substrings(str.begin(), str.end(), i, "", out);
}


class substrings_matcher
{
private:
    typedef vector<bool> used_set;
    typedef vector<int> edges_map;
    const static int UNUSED = -1;
    const static size_t RADIX = 27;

public:
    static size_t hash_string(const string &str)
    {
        size_t val = 0;
        for (string::const_reverse_iterator it = str.rbegin(); it != str.rend(); ++it)
        {
            val *= RADIX;
            val += (*it) - 'a' + 1;
        }
        return val;
    }

    static string unhash_string(const size_t hash)
    {
        string str;
        for (size_t val = hash; val != 0; val /= RADIX)
            str.push_back('a' + ((val % RADIX) - 1));
        return str;
    }

    bool try_kuhn(const size_t index)
    {
        if (used_[index])
            return false;

        used_[index] = true;

        for (vector<string>::const_iterator it = substrings_[index].begin(); it != substrings_[index].end(); ++it)
        {
            const size_t hash = hash_string(*it);
            const int val = mt_[hash];
            if (val == UNUSED || try_kuhn(val))
            {
                mt_[hash] = index;
                return true;
            }
        }
        return false;
    }

    bool process(const vector<string> &strings, vector<string> *out)
    {
        strings_ = strings;
        substrings_.resize(strings.size());
        for (size_t i = 0; i < strings.size(); ++i)
            list_all_substrings(strings[i], 4, &(substrings_[i]));

        mt_.resize(RADIX * RADIX * RADIX * RADIX, UNUSED);
        used_.resize(strings.size());
        
        for (size_t i = 0; i < strings.size(); ++i)
        {
            std::fill(used_.begin(), used_.end(), false);
            try_kuhn(i);
        }

        out->resize(strings.size());
        size_t counter = 0;
        
        for (size_t i = 0; i < mt_.size(); ++i)
        {
            if (mt_[i] != UNUSED)
            {
                out->at(mt_[i]) = unhash_string(i); 
                ++counter;
            }
        }

        return (counter == strings.size());
    }

private:
    vector<string> strings_;
    vector<vector<string> > substrings_;
    used_set used_;
    edges_map mt_;
};


void read_data(const char *filename, vector<string> *out)
{
    ifstream s(filename);
    
    size_t num_strings;
    s >> num_strings;

    out->resize(num_strings);
    std::istream_iterator<string> begin(s);
    std::istream_iterator<string> end;

    std::copy (begin, end, out->begin());
}

int main()
{
    vector<string> strings;
    read_data("input.txt", &strings);

    substrings_matcher matcher;
    vector<string> substrings;
    const bool res = matcher.process(strings, &substrings);

    ofstream s("output.txt");
    if (res)
    {
        std::ostream_iterator<string> out_it (s, "\n");
        std::copy(substrings.begin(), substrings.end(), out_it);
    }
    else
    {
        s << "-1" << endl;
    }
    
    return 0;
}

