// mem.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"

using std::cout;
using std::endl;
using std::vector;

struct my_struct 
{
    int i;
};

const size_t Size = sizeof(my_struct);



class mem_manager
{


private:
    struct record
    {
        void *ptr;
        size_t next;
    };

    vector<record> records_;
    size_t first_;
};

int main(int argc, char* argv[])
{
	cout << "Hello world!" << endl;
    return 0;
}

