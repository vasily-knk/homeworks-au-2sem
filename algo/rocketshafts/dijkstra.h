#if !defined (DIJKSTRA_H)
#define DIJKSTRA_H

namespace my_graph
{

	struct path_vertex
	{
		inline path_vertex (edge_weight d, vertex_id parent) : d(d), parent(parent) {}
		inline path_vertex () {};

		edge_weight d;
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


	typedef dumb_map<path_vertex> path_map;

	class reach_dijkstra
	{
	public:

		typedef std::unary_function<vertex_id, bool> vert_checker;

		inline reach_dijkstra(const graph &ref_graph, vertex_id start, path_map &ref_out, vector<int> &out_counters, edge_weight shaft_dist);

		inline bool check_vertex(vertex_id id) const;
		inline bool done();
		inline vertex_id iterate();
		inline vertex_id get_next();
		inline const path_map &get_border() const;


		inline size_t get_n_shafts() const {return n_shafts_;}
	private:
		inline void discard_dublicates();    
		inline edge_weight get_weight(const edge &e) {return e.weight;}

	private:
		graph const * const pgraph_;
		path_map * const pout_;
		path_map border_;
		priority_queue<heap_vertex> q_;
		const edge_weight shaft_dist_;
		size_t n_shafts_;

        vector<int> *counters_;
	public:
		size_t max_heap_size_;
	};

	reach_dijkstra::reach_dijkstra(const graph &ref_graph, vertex_id start, path_map &ref_out, vector<int> &out_counters, edge_weight shaft_dist)
		: pgraph_(&ref_graph)
		, pout_(&ref_out)
        , counters_(&out_counters)
		, max_heap_size_(0)
		, shaft_dist_(shaft_dist)
		, n_shafts_(0)
	{
		counters_->resize(pgraph_->e_count(), 0);
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

		const heap_vertex hv = q_.top();  
		q_.pop();

		const path_vertex &pv = (*pout_)[hv.id] = border_.at(hv.id);
		border_.erase(hv.id);
			
		if (pv.d == shaft_dist_) 
		{
			//cout << "exact shaft at " << hv.id << endl;
			++n_shafts_;
		}

		if (pv.d < shaft_dist_)
		{
			const vertex &v = pgraph_->get_vertex(hv.id);

			for (vertex::adj_iterator it = v.out_begin(); it != v.out_end(); ++it)
			{
				const vertex_id &adj_vid = (*it).v;
				const vertex &adj_v = pgraph_->get_vertex(adj_vid);
				const edge_id &eid = (*it).e;
				const edge &e = pgraph_->get_edge(eid);
                
				const edge_weight ew = get_weight(e);


				if (pv.d < shaft_dist_ && pv.d + ew > shaft_dist_)
				{
					if (pout_->count (adj_vid) == 0)
					{
						//cout << "shaft candidate at " << hv.id << " - " << adj_vid << endl;
						++(*counters_)[eid];
					}
					else 
					{
						const path_vertex &pv_other = pout_->at(adj_vid);
						
						if (pv_other.d + ew > shaft_dist_)
						{
							const edge_weight leftover1 = shaft_dist_ - pv.d;
							const edge_weight leftover2 = shaft_dist_ - pv_other.d;

							if (leftover1 + leftover2 < ew)
                                ++(*counters_)[eid];
                            else if (leftover1 + leftover2 > ew)
                            {
                                --(*counters_)[eid];
                                if ((*counters_)[eid] < 0)
                                {
                                    cout << "UBER ACHTUNG: " << leftover1 << ", " << leftover2 << ": " << ew
                                         << " at " << hv.id + 1 << ", " << adj_vid + 1 << endl;
                                }
                                         
                            }
						}
					}

				}

				const path_vertex pv2 (pv.d + get_weight(e), hv.id);
				const heap_vertex hv2 (adj_vid, pv2.d); 

				if (pout_->count (adj_vid) != 0)
					continue;



				if (border_.count(adj_vid) == 0 || border_.at(adj_vid).d > pv2.d)
				{
					q_.push(hv2);
					border_[adj_vid] = pv2;
				}
			}
		}



		return hv.id;
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

#endif // DIJKSTRA_H
