#pragma once

namespace my_graph
{

	struct path_vertex
	{
		inline path_vertex (edge_weight d, vertex_id parent) : d(d), parent(parent) {}
		//inline path_vertex (edge_weight d, edge_id inc, vertex_id parent) : d(d),/* inc(inc),*/ parent(parent) {}
		inline path_vertex () {};

		edge_weight d;
		//edge_id inc;       // Will probably be removed (KNK)
		vertex_id parent;  // Redundant unless inc is removed (KNK)
	};

	struct heap_vertex 
	{
		inline heap_vertex () {}
		inline heap_vertex (vertex_id id, edge_weight d) : id(id), d(d) {}
		inline bool operator< (const heap_vertex& b) const
		{
			return (d > b.d);
		}

		vertex_id id;
		edge_weight d;
	};

	typedef unordered_map<vertex_id, path_vertex> path_map;

	class reach_dijkstra
	{
	public:

		typedef bool (*vert_checker) (vertex_id);

		inline reach_dijkstra(const graph &ref_graph, vertex_id start, path_map &ref_out, const vert_checker& checker = NULL);

		inline bool check_vertex(vertex_id id) const;
		inline bool done();
		inline vertex_id iterate();
		inline vertex_id get_next();
		inline const path_map &get_border() const;

	private:
		inline void discard_dublicates();    
		inline edge_weight get_weight(const edge &e) {return e.weight;}

	private:
		const graph *pgraph_;
		path_map *pout_;
		path_map border_;
		priority_queue<heap_vertex> q_;
		vert_checker vert_checker_;
	public:
		size_t max_heap_size_;
	};

	reach_dijkstra::reach_dijkstra(const graph &ref_graph, vertex_id start, path_map &ref_out, const vert_checker& checker)
		: pgraph_(&ref_graph)
		, pout_(&ref_out)
		, max_heap_size_(0)
		, vert_checker_(checker)
	{
		q_.push(heap_vertex(start, 0));
		border_[start] = path_vertex(0, start);
	}

	bool reach_dijkstra::done()
	{
		discard_dublicates();
		return q_.empty();
	}

	my_graph::vertex_id reach_dijkstra::iterate()
	{
		if (q_.size() > max_heap_size_)
			max_heap_size_ = q_.size();

		discard_dublicates();

		heap_vertex hv = q_.top();  
		q_.pop();

		const path_vertex &pv = (*pout_)[hv.id] = border_.at(hv.id);
		border_.erase(hv.id);

		bool check_result = check_vertex(hv.id);

		if (check_result)
		{
			const vertex &v = pgraph_->get_vertex(hv.id);

			for (vertex::adj_iterator it = v.out_begin(); it != v.out_end(); ++it)
			{
				const vertex_id &adj_vid = (*it).v;
				const vertex &adj_v = pgraph_->get_vertex(adj_vid);
				const edge_id &eid = (*it).e;
				const edge &e = pgraph_->get_edge(eid);

				if (pout_->count (adj_vid) > 0)
					continue;

				const path_vertex pv2 (pv.d + get_weight(e), hv.id);
				const heap_vertex hv2 (adj_vid, pv2.d); 

				if (border_.count(adj_vid) == 0 || border_.at(adj_vid).d > pv2.d)
				{
					q_.push(hv2);
					border_[adj_vid] = pv2;
				}
			}
		}
		return hv.id;
	}

	bool reach_dijkstra::check_vertex(vertex_id id) const
	{
		if (vert_checker_ == NULL)
			return true;
		return vert_checker_(id);
	}

	my_graph::vertex_id reach_dijkstra::get_next()
	{
		discard_dublicates();
		return q_.top().id;  
	}

	void reach_dijkstra::discard_dublicates()
	{
		while ((!q_.empty()) && (pout_->count(q_.top().id) != 0))
			q_.pop();
	}

	const my_graph::path_map & reach_dijkstra::get_border() const
	{
		return border_;
	}
}