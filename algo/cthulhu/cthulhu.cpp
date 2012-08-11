#include "stdafx.h"
#include "graph.h"


class cthulhu_checker
{
public:
    bool operator()(const graph &g);
private:
    int dfs(vertex_id vid, const vertex_id parent);
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
    map<vertex_id, bfs_vertex_t> verts_;
    vertex_id tail_;
};

int cthulhu_checker::dfs(const vertex_id vid, const vertex_id parent) 
{
    const vertex &v = g_->get_vertex(vid);
    int loops = 0;

    verts_[vid].parent = parent;
    verts_[vid].state = bfs_vertex_t::GREY;

    for (vertex::adj_iterator it = v.out_begin(); it != v.out_end(); ++it)
    {
        if (it->v == verts_.at(vid).parent)
            continue;
        
        if (verts_.count(it->v) != 0)
        {
            if (verts_.at(it->v).state == bfs_vertex_t::GREY)
            {
                tail_ = vid;
                ++loops;
            }
            continue;
        }

        loops += dfs(it->v, vid);
    }
    verts_[vid].state = bfs_vertex_t::BLACK;
    return loops;
}

bool cthulhu_checker::operator()(const graph &g)
{
    g_ = &g;
    verts_.clear();

    int loops = 0;
    bool found = false;
    for (graph::v_const_iterator it = g.v_begin(); it != g.v_end(); ++it)
    {
        const vertex_id vid = it - g.v_begin();
        if (verts_.count(vid) == 0)
        {
            if (found)
                return false;
            loops += dfs(vid, vid);
            found = true;
        }
    }
    
    if (loops != 1)
        return false;

    int branches = 1;
    for (vertex_id vid = verts_.at(tail_).parent; vid != tail_; vid = verts_.at(vid).parent)
    {
        if (g.get_vertex(vid).get_degree() < 2)
            throw 0;

        ++branches;

        if (branches == 3)
            return true;
    }
    
    return false;
}

int main()
{
    size_t n_verts, n_edges;
    cin >> n_verts >> n_edges;
    
    graph g;

    for (size_t i = 0; i < n_verts; ++i)
        g.add_vertex();
        
    for (size_t i = 0; i < n_edges; ++i)
    {
        vertex_id vid1, vid2;
        cin >> vid1 >> vid2;
        g.add_edge(vid1 - 1, vid2 - 1, 1);
    }

    cthulhu_checker c;
    cout << (c(g) ? "FHTAGN!" : "NO") << endl;
	return 0;
}

