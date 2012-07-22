#if !defined(DUMB_MAP_H)
#define DUMB_MAP_H

template <typename T>
class dumb_map
{
    typedef size_t key;
public:
    dumb_map ();
    explicit dumb_map (size_t size);

    void rehash (size_t size);
    void clear ();
    
    inline T& operator[] (const key &k);
    inline const T& at (const key &k) const;

    void erase (const key &k);

    size_t count (const key& k) const;
    size_t size () const;
    bool empty () const;
public:
/*
    struct iterator
    {
        inline explicit iterator (bool found) : found_(found) {};
        inline bool operator == (const iterator& other)
        {
            return found_ == other.found_;
        }
        inline bool operator != (const iterator& other)
        {
            return !(*this == other);
        }
    private:
        bool found_;
    };

    inline iterator find (const key& k) const;
    inline iterator end () const; */


private:
    std::vector<T> data_;
    std::vector<bool> flags_;
    size_t size_;
};


template <typename T>
dumb_map<T>::dumb_map()
:   size_   (0)
{

}

template <typename T>
dumb_map<T>::dumb_map(size_t size)
:   data_   (size)
,   flags_  (size, false)
,   size_   (0)
{
    
}

template <typename T>
void dumb_map<T>::rehash(size_t size)
{
    data_.resize (size);
    flags_.resize (size, false);
}


template <typename T>
void dumb_map<T>::clear()
{
    assert (data_.size() == flags_.size());
    std::fill (flags_.begin(), flags_.end(), false);
    size_ = 0;
}



/*
template <typename T>
typename dumb_map<T>::iterator dumb_map<T>::find(const key& k) const
{
    assert (data_.size() == flags_.size());
    if (k >= data_.size() || !flags_[k])
        return end();
    return iterator (true);
}


template <typename T>
typename dumb_map<T>::iterator dumb_map<T>::end() const
{
    return iterator (false);
}
*/


template <typename T>
size_t dumb_map<T>::count(const key& k) const
{
    assert (data_.size() == flags_.size());
    if (k >= data_.size() || !flags_[k])
        return 0;
    return 1;   
}


template <typename T>
inline T& dumb_map<T>::operator[](const key &k)
{
    assert (data_.size() == flags_.size());
    if (k >= data_.size())
        rehash (k+1);
    
    if (!flags_[k])
    {
        data_[k] = T ();
        flags_[k] = true;
        ++size_;
    }

    return data_[k];
}

template <typename T>
inline const T& dumb_map<T>::at(const key &k) const
{
    assert (data_.size() == flags_.size());
    assert (flags_[k]);
    assert (k < data_.size());

    return data_[k];
}


template <typename T>
void dumb_map<T>::erase(const key &k)
{
    assert (data_.size() == flags_.size());
    assert (count (k) > 0);
    flags_[k] = false;
    --size_;
}

template <typename T>
bool dumb_map<T>::empty() const
{
    return (size_==0);
}

template <typename T>
size_t dumb_map<T>::size() const
{
    return size_;
}

#endif // DUMB_MAP_H
