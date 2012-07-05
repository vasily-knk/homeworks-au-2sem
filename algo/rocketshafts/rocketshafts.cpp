// rocketshafts.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "graph.h"
#include "dijkstra.h"

using namespace my_graph;

int main(int argc, char* argv[])
{
	size_t n_cities;
	size_t n_roads;
	size_t capital_id;

	cin >> n_cities >> n_roads >> capital_id;

	graph g;
	
	for (size_t i = 0; i < n_cities; ++i)
		g.add_vertex();


	for (size_t i = 0; i < n_roads; ++i)
	{
		vertex_id v1, v2;
		edge_weight weight;
		cin >> v1 >> v2 >> weight;
		g.add_edge(v1, v2, weight);
	}

	edge_weight shaft_dist;
	cin >> shaft_dist;




	return 0;
}

