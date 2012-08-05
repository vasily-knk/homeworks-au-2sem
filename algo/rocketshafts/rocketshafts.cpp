// rocketshafts.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "graph.h"
#include "dumb_map.h"
#include "dijkstra.h"

using namespace my_graph;


void bruteforce(const graph &g, const edge_weight shaft_dist, const path_map &tree, vector<int> &dst)
{
    dst.resize(g.e_count(), 0);

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
                edge_weight www = std::min(l + tree.at(vid).d, (ew - l) + tree.at(adj_vid).d);
                if (www == shaft_dist)
                    ++dst[eid];
            }
        }
    }

    for (vector<int>::iterator it = dst.begin(); it != dst.end(); ++it)
        *it /= 2;
}

int main(int argc, char* argv[])
{
	size_t n_cities;
	size_t n_roads;
	size_t capital_id;

	cin >> n_cities >> n_roads >> capital_id;
	--capital_id;

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

	path_map tree;

	vector<int> bruteforce_counters, normal_counters;
    
    reach_dijkstra d(g, capital_id, tree, normal_counters, shaft_dist);
	while (!d.done())
		d.iterate();

    /*bruteforce(g, shaft_dist, tree, bruteforce_counters);

    for (size_t i = 0; i < g.e_count(); ++i)
    {
        if (bruteforce_counters[i] != normal_counters[i])
        {
            cout << "ACHTUNG: bf " << bruteforce_counters[i] << " normal " << normal_counters[i] << " at " << i << endl;
            return -1;
        }
    }*/

    int shaft_counter = d.get_n_shafts() 
                      + std::accumulate(normal_counters.begin(), 
                                        normal_counters.end(), 
                                        0);

	cout << shaft_counter << endl;

	return 0;
}

