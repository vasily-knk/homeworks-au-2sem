// rocketshafts.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "graph.h"
#include "dumb_map.h"
#include "dijkstra.h"

using namespace my_graph;


int main(int argc, char* argv[])
{
	size_t n_cities;
	size_t n_roads;
	size_t capital_id;

	cin >> n_cities >> n_roads >> capital_id;
	--capital_id;;

	graph g;
	
	for (size_t i = 0; i < n_cities; ++i)
		g.add_vertex();


	for (size_t i = 0; i < n_roads; ++i)
	{
		vertex_id v1, v2;
		edge_weight weight;
		cin >> v1 >> v2 >> weight;
		g.add_edge(v1 - 1, v2 - 1, weight);
	}

	edge_weight shaft_dist;
	cin >> shaft_dist;

	path_map out;


	/*if (n_cities > 50)
	{
		cout << "dist: " << shaft_dist << endl;
	}*/

	reach_dijkstra d(g, capital_id, out, shaft_dist);
	while (!d.done())
		d.iterate();

	int shaft_counter = 0;
	
	for (graph::v_const_iterator it = g.v_begin(); it != g.v_end(); ++it)
	{
		const vertex_id vid = it - g.v_begin();
		for (vertex::adj_iterator adj_it = it->out_begin(); adj_it != it->out_end(); ++adj_it)
		{
			const vertex_id &adj_vid = adj_it->v;
			const vertex &adj_v = g.get_vertex(adj_vid);
			const edge_id &eid = adj_it->e;
			const edge &e = g.get_edge(eid);

			const edge_weight ew = e.weight;

			for (int l = 1; l < ew; ++l)
			{
				edge_weight www = std::min(l + out.at(vid).d, (ew - l) + out.at(adj_vid).d);
				if (www == shaft_dist)
					++shaft_counter;
			}
		}
	}

	shaft_counter /= 2;
	shaft_counter += d.get_n_shafts();

	cout << shaft_counter << endl;


	return 0;
}

