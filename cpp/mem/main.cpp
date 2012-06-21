// mem.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"

using std::cout;
using std::endl;
using std::vector;

struct my_struct 
{
    int arr[100];
};

const size_t Size = sizeof(my_struct);

class mem_manager
{
public:
    mem_manager(size_t chunk_size);
    ~mem_manager();
public:
    void *alloc();
    void free (void* ptr);

private:
    void alloc_chunk(size_t size);

    struct record
    {
        mem_manager *man;
        record *prev, *next;
    };

private:
    const size_t CHUNK_SIZE;

    record *free_;
    record *occupied_;

    vector<char*> chunks_;

};

mem_manager::mem_manager(size_t chunk_size)
    : CHUNK_SIZE(chunk_size)
    , free_(NULL)
    , occupied_(NULL)
{
    
}


mem_manager::~mem_manager()
{
    for (vector<char*>::const_iterator it = chunks_.begin(); it != chunks_.end(); ++it)
        delete[] *it;
}



void mem_manager::alloc_chunk(size_t size)
{
    const size_t stride = Size + sizeof(record);
    char *pchunk = new char[stride * size];
    
    char *ptr = pchunk;
    for (size_t i = 0; i < size; ++i, ptr += stride)
    {
        record *r = reinterpret_cast<record*>(ptr);
        r->man = this;
        
        if (i == 0)
            r->prev = NULL;
        else
            r->prev = reinterpret_cast<record*>(ptr - stride);

        if (i == size - 1)
            r->next = free_;
        else
            r->next = reinterpret_cast<record*>(ptr - stride);
    }

    free_ = reinterpret_cast<record*>(pchunk);

    chunks_.push_back(pchunk);
}

void *mem_manager::alloc()
{
    if (free_ == NULL)
        alloc_chunk (CHUNK_SIZE);

    assert (free_ != NULL);
    assert (free_->prev == NULL);

    record *r = free_;
    free_ = r->next;
    
    if (free_ != NULL)
        free_->prev = NULL;

    if (occupied_ != NULL)
    {
        assert (occupied_->prev == NULL);
        occupied_->prev = r; 
    }

    r->next = occupied_;
    r->prev = NULL;
    occupied_ = r;

    return reinterpret_cast<void*>(r + 1);
}

void mem_manager::free(void* ptr)
{
    record *r = reinterpret_cast<record*>(ptr) - 1;

}


int main(int argc, char* argv[])
{
	cout << "Hello world!" << endl;
    return 0;
}

