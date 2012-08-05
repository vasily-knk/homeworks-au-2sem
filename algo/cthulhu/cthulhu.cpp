#include "stdafx.h"
#include "graph.h"


class cthulhu_checker
{
public:
    bool operator()(const graph &g);
private:
    int dfs(vertex_id vid);
private:
    struct bfs_vertex_t
    {
        enum vertex_state_t
        {
            GREY, BLACK
        };

        vertex_state_t state;
        vertex_id parent;
    };

    const graph *g_;
    unordered_map<vertex_id, bfs_vertex_t> verts_;
    vertex_id tail_;
};

int cthulhu_checker::dfs(const vertex_id vid) 
{
    const vertex &v = g_->get_vertex(vid);
    int loops = 0;

    for (vertex::adj_iterator it = v.out_begin(); it != v.out_end(); ++it)
    {
        if (verts_.count(it->v) != 0 && verts_.at(it->v).state == bfs_vertex_t::GREY)
        {
            tail_ = vid;
            ++loops;
        }

        verts_[it->v].parent = vid;
        verts_[it->v].state = bfs_vertex_t::GREY;
        
        loops += dfs(it->v);
        verts_[it->v].state = bfs_vertex_t::BLACK;
    }
    return loops;
}

bool cthulhu_checker::operator()(const graph &g)
{
    g_ = &g;
    verts_.clear();

    int loops = 0;
    for (graph::v_const_iterator it = g.v_begin(); it != g.v_end(); ++it)
    {
        const vertex_id vid = it - g.v_begin();
        loops += dfs(vid);
    }
    
    if (loops != 1)
        return false;

    for (vertex_id vid = tail_; vid != tail_; vid = verts_.at(vid).parent)
    {

    }
    
    return true;
}

int main()
{
	return 0;
}

