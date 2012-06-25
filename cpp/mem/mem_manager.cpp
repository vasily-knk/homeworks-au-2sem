//#include "stdafx.h"

#include <iostream>
#include <vector>
#include <cassert>

using std::cout;
using std::endl;
using std::vector;


template <size_t Size>
class mem_manager
{
public:
    mem_manager(size_t default_chunk_size);
    ~mem_manager();
public:

    void *alloc();
    void free (void *r);

    void print() const;

private:
    void alloc_chunk(size_t size);

private:
    struct record
    {
        //mem_manager *man;
        record *prev, *next;
        bool is_free;
    };

    struct chunk_record
    {
        size_t size;
        chunk_record *next;
    };
private:
    const size_t DEFAULT_CHUNK_SIZE;

    record *free_;
    record *occupied_;

    //vector<char*> chunks_;
    chunk_record *pchunks_;

};

template <size_t Size>
mem_manager<Size>::mem_manager(size_t default_chunk_size)
    : DEFAULT_CHUNK_SIZE(default_chunk_size)
    , free_(NULL)
    , occupied_(NULL)
    , pchunks_(NULL)
{
    
}


template <size_t Size>
mem_manager<Size>::~mem_manager()
{
    for (const chunk_record *cr = pchunks_; cr != NULL;)
    {
        const chunk_record *next = cr->next;
        delete [] (reinterpret_cast<const char*>(cr));
        cr = next;
    }
}


template <size_t Size>
void mem_manager<Size>::alloc_chunk(size_t size)
{
    const size_t stride = Size + sizeof(record);
    char *pchunk = new char[stride * size + sizeof(chunk_record)];
    
    chunk_record *pchunk_record = reinterpret_cast<chunk_record*>(pchunk);
    pchunk_record->size = size;
    pchunk_record->next = pchunks_;
    pchunks_ = pchunk_record;
    
    char *ptr = reinterpret_cast<char*>(pchunk_record + 1);
    for (size_t i = 0; i < size; ++i, ptr += stride)
    {
        record *r = reinterpret_cast<record*>(ptr);
        //r->man = this;
        r->is_free = true;
        
        if (i == 0)
            r->prev = NULL;
        else
            r->prev = reinterpret_cast<record*>(ptr - stride);

        if (i == size - 1)
            r->next = free_;
        else
            r->next = reinterpret_cast<record*>(ptr + stride);
    }

    free_ = reinterpret_cast<record*>(pchunk_record + 1);

}

template <size_t Size>
void *mem_manager<Size>::alloc()
{
    if (free_ == NULL)
        alloc_chunk (DEFAULT_CHUNK_SIZE);

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

    assert(r->is_free);
    r->is_free = false;

    return reinterpret_cast<void*>(r + 1);
}

template <size_t Size>
void mem_manager<Size>::free(void* ptr)
{
    record *r = reinterpret_cast<record*>(ptr) - 1;
    
    if (r->prev != NULL)    
        r->prev->next = r->next;
    
    if (r->next != NULL)
        r->next->prev = r->prev;
    
    r->prev = NULL;
    r->next = free_;
    
    if (free_ != NULL)
    {
        assert (free_->prev == NULL);
        free_->prev = r;
    }

    free_ = r;

    assert(!r->is_free);
    r->is_free = true;
}

template <size_t Size>
void mem_manager<Size>::print() const
{
    int counter = 0;
    for (const chunk_record *cr = pchunks_; cr != NULL; cr = cr->next, ++counter)
    //for (vector<char*>::const_iterator it = chunks_.begin(); it != chunks_.end(); ++it)
    {
        const size_t stride = Size + sizeof(record);
        const chunk_record *pchunk_record = cr;
        const char *ptr = reinterpret_cast<const char*>(pchunk_record + 1);

        cout << "Chunk " << counter << ": "; 
        for (size_t i = 0; i < pchunk_record->size; ++i, ptr += stride)
        {
            const record *r = reinterpret_cast<const record*>(ptr);
            cout << (r->is_free ? '.' : 'O');
        }
        cout << endl;
    }
}




struct my_struct 
{
    int arr[100];

    static void *operator new(size_t size);
    static void operator delete(void *ptr);
};




mem_manager<sizeof (my_struct)> g_manager(10);


void *my_struct::operator new(size_t size)
{
    return g_manager.alloc();
}

void my_struct::operator delete(void *ptr)
{
    g_manager.free(ptr);
}

int main(int argc, char* argv[])
{
    const size_t N = 1000000;

    vector<my_struct*> ptrs (N);

    cout << "Custom allocation" << endl;
    for (size_t i = 0; i < N; ++i)
        ptrs[i] = new my_struct;

    for (size_t i = 0; i < N; ++i)
        delete ptrs[i];


    cout << "Default allocation" << endl;
    for (size_t i = 0; i < N; ++i)
        ptrs[i] = ::new my_struct;

    for (size_t i = 0; i < N; ++i)
        ::delete ptrs[i];

    return 0;
}

