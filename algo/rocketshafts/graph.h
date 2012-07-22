#if !defined (GRAPH_H)
#define GRAPH_H

namespace my_graph
{
    typedef size_t vertex_id;
    typedef size_t edge_id;
    typedef int edge_weight;

    class graph;

    /*
     * --------------------
     * vert_edge
     * --------------------
     */

    struct vert_edge
    {
        vert_edge (vertex_id v, edge_id e) : v(v), e(e) {};

        vertex_id v;
        edge_id e;
    };

    /*
     * --------------------
     * vertex
     * --------------------
     */

    class vertex
    {
    public:
        friend class graph;
        typedef vector<vert_edge>::const_iterator adj_iterator;

        adj_iterator in_begin()  const {return adj.begin();}
        adj_iterator out_begin() const {return adj.begin();}
        adj_iterator in_end()    const {return adj.end();}
        adj_iterator out_end()   const {return adj.end();}

        inline size_t get_degree () const {return adj.size();};
    private:
        vector<vert_edge> adj;
    };

    /*
     * --------------------
     * edge
     * --------------------
     */

    struct edge
    {
        friend class graph;
    public:
        edge(const edge_weight &weight) : weight(weight) {};

        const edge_weight &get_data() const {return weight;}

    public:
        edge_weight weight;
    };


    /*
     * --------------------
     * graph
     * --------------------
     */

    class graph
    {
    public:
        inline       vertex &get_vertex(vertex_id id);
        inline const vertex &get_vertex(vertex_id id) const;
        inline       edge   &get_edge  (edge_id id);
        inline const edge   &get_edge  (edge_id id)   const;

        inline size_t v_count() const;
        inline size_t e_count() const;

        inline vertex_id add_vertex();
        inline edge_id add_edge(vertex_id v1, vertex_id v2, const  edge_weight &data);

        inline void reserve(size_t n_verts, size_t n_edges);
        inline void clear();

    private:
        typedef vector<vertex> vertex_map;
        typedef vector<edge> edge_map;
    public:
        typedef vertex_map::iterator v_iterator;
        typedef vertex_map::const_iterator v_const_iterator;

        typedef edge_map::iterator e_iterator;
        typedef edge_map::const_iterator e_const_iterator;

        v_iterator         v_begin    ()        {return vertices_.begin();};
        v_const_iterator   v_begin    () const  {return vertices_.begin();};
        v_iterator         v_end      ()        {return vertices_.end();};
        v_const_iterator   v_end      () const  {return vertices_.end();};

        e_iterator         e_begin    ()        {return edges_.begin();};
        e_const_iterator   e_begin    () const  {return edges_.begin();};
        e_iterator         e_end      ()        {return edges_.end();};
        e_const_iterator   e_end      () const  {return edges_.end();};

        inline vertex_id get_vertex_id(const v_const_iterator &it) const {return it - v_begin();}
        inline vertex_id get_vertex_id(const v_iterator &it) {return it - v_begin();}
        inline edge_id get_edge_id(const e_const_iterator &it) const {return it - e_begin();}
        inline edge_id get_edge_id(const e_iterator &it) {return it - e_begin();}

    private:
        vertex_map vertices_;
        edge_map edges_;
    };

    vertex &graph::get_vertex(vertex_id id)
    {
        return vertices_[id];
    }

    const vertex &graph::get_vertex(vertex_id id) const
    {
        return vertices_[id];
    }

    edge &graph::get_edge(edge_id id)
    {
        return edges_[id];
    }

    const edge &graph::get_edge(edge_id id) const
    {
        return edges_[id];
    }

    size_t graph::v_count() const
    {
        return vertices_.size();
    }

    
    size_t graph::e_count() const
    {
        return edges_.size();
    }
    
    vertex_id graph::add_vertex()
    {	
		size_t size = vertices_.size();
		vertices_.push_back(vertex());
		return vertices_.size() - 1;
    }

    edge_id graph::add_edge(vertex_id v1, vertex_id v2, const edge_weight &weight)
    {
        edges_.push_back(edge(weight));
        edge_id e = edges_.size() - 1;

        vertices_[v1].adj.push_back(vert_edge(v2, e));
        vertices_[v2].adj.push_back(vert_edge(v1, e));
        return e;
    }

    
    void graph::reserve(size_t n_verts, size_t n_edges)
    {
        vertices_.reserve(n_verts);
        edges_.reserve(n_edges);
    }

    
    void graph::clear()
    {
        vertices_.clear();
        edges_.clear();
    }

}

#endif // GRAPH_H
