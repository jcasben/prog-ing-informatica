import json
import sys
from queue import PriorityQueue
from math import radians, cos, sin, asin, sqrt


class BaMHD(object):
    def __init__(self, db_file='ba_mhd_db.json'):
        # Initialize BaMHD object, load data from json file
        self.data = json.load(open(db_file, 'r'))

    def distance(self, stop1, stop2):
        # Return distance between two stops in km.
        if isinstance(stop1, BusStop):
            stop1 = stop1.name
        if isinstance(stop2, BusStop):
            stop2 = stop2.name
        coords1 = self.data['bus_stops'][stop1]
        coords2 = self.data['bus_stops'][stop2]

        def haversine(lon1, lat1, lon2, lat2):
            # (You don`t need to understand following code - it`s just geo-stuff)
            # Calculate the great circle distance between two points on the earth (specified in
            # decimal degrees)
            # Courtesy of http://stackoverflow.com/a/15737218

            # convert decimal degrees to radians
            lon1, lat1, lon2, lat2 = map(radians, [lon1, lat1, lon2, lat2])
            # haversine formula
            dlon = lon2 - lon1
            dlat = lat2 - lat1
            a = sin(dlat/2)**2 + cos(lat1) * cos(lat2) * sin(dlon/2)**2
            c = 2 * asin(sqrt(a))
            km = 6367 * c
            return km

        return haversine(coords1[0], coords1[1], coords2[0], coords2[1])

    def neighbors(self, stop):
        # Return neighbors for a given stop
        return self.data['neighbors'][stop.name if isinstance(stop, BusStop) else stop]

    def stops(self):
        # Return list of all stops (names only)
        return self.data['neighbors'].keys()


class BusStop(object):
    # Object representing node in graph traversal. Includes name, parent node, and total cost of
    # path from root to this node (i.e. distance from start).
    def __init__(self, name, parent=None, pathLength=0):
        self.name = name
        self.parent = parent
        self.pathLength = pathLength

    def traceBackPath(self):
        # Returns path represented by this node as list of node names (bus stop names).
        if self.parent is None:
            return [self.name]
        else:
            path = self.parent.traceBackPath()
            path.append(self.name)
            return path


def findPathUniformCost(bamhd, stopA, stopB):
    # Implement Uniform-cost search to find the shortest path between two MHD stops in Bratislava.
    # Return a list of MHD stops, print how many bus stops were added to the "OPEN list"
    # and total path length in km.

    # Initialize OPEN list and explored set to avoid cycles
    added_to_open = 0
    open_list = PriorityQueue()
    explored = set()

    # Add first stop
    open_list.put((0, BusStop(stopA)))
    added_to_open += 1

    while not open_list.empty():
        # Get from the Priority Queue the top 1 element
        current_cost, node = open_list.get()

        # If we are in the final stop, terminate
        if node.name == stopB:
            print(f'\t{added_to_open} bus stops in "OPEN list", length = {current_cost:.2f}km')
            return node.traceBackPath()

        # If we already have been in this stop, iterate to the next one
        if node.name in explored:
            continue

        # Add the following stop to the set of already visited
        explored.add(node.name)

        # Iterate through all the neighbours of the current stop
        for neighbor in bamhd.neighbors(node):
            # If the neighbour hasn't been explored yet, add it to the open list
            if neighbor not in explored:
                # Calculate its total cost to use it as the stop's priority
                neigh_distance = bamhd.distance(node, neighbor)
                total_cost = current_cost + neigh_distance

                new_node = BusStop(neighbor, node, total_cost)
                open_list.put((total_cost, new_node))
                added_to_open += 1

    # If a solution hasn't been found at the end of all the iterations, print error message
    print('\t{} bus stops in "OPEN list", no valid paths found'.format(added_to_open))
    return []


def findPathAStar(bamhd, stopA, stopB):
    # Implement A* search to find the shortest path between two MHD stops in Bratislava.
    # Return a list of MHD stops, print how many bus stops were added to the "OPEN list"
    # and total path length in km.

    # Initialize OPEN list and explored set to avoid cycles
    added_to_open = 0
    open_list = PriorityQueue()
    explored = set()

    # Add first stop
    open_list.put((0, BusStop(stopA)))
    added_to_open += 1

    while not open_list.empty():
        # Get from the Priority Queue the top
        node = open_list.get()[1]
        current_cost = node.pathLength

        # If we are in the final stop, terminate
        if node.name == stopB:
            print(f'\t{added_to_open} bus stops in "OPEN list", length = {current_cost:.2f}km')
            return node.traceBackPath()

        # If we already have been in this stop, iterate to the next one
        if node.name in explored:
            continue

        # Add the following stop to the set of already visited
        explored.add(node.name)

        # Iterate through all the neighbours of the current stop
        for neighbor in bamhd.neighbors(node):
            # If the neighbour hasn't been explored yet, add it to the open list
            if neighbor not in explored:
                neigh_distance = bamhd.distance(node, neighbor)

                # Calculate f(n) = g(n) + h(n)
                g = current_cost + neigh_distance
                h = bamhd.distance(neighbor, stopB)
                total_cost = g + h


                new_node = BusStop(neighbor, node, g)
                open_list.put((total_cost, new_node))
                added_to_open += 1

    # If a solution hasn't been found at the end of all the iterations, print error message
    print('\t{} bus stops in "OPEN list", no valid paths found'.format(added_to_open))
    return []


if __name__ == "__main__":
    # Initialization
    bamhd = BaMHD()

    # Examples of function usage:
    # -> accessing the list of bus stops (is 'Zoo' a bus stop?)
    print('Zoo' in bamhd.stops())
    # -> get neighbouring bus stops. Parameters can be string or BusStop object
    print(bamhd.neighbors('Zochova'))
    # -> get distance between two bus stops (in km). Parameters can be string or BusStop objects
    print(bamhd.distance('Zochova', 'Zoo'))
    # -> get whole path from last node of search algorithm
    s1 = BusStop('Zoo')     # some dummy data
    s2 = BusStop('Lanfranconi', s1)
    s3 = BusStop('Park kultury', s2)
    print(s3.traceBackPath())
    # -> using priority queue
    pq = PriorityQueue()
    pq.put((3, 'Not important stuff'))  # pq.put((priority, object))
    pq.put((1, 'Important stuff'))
    pq.put((2, 'Medium stuff'))
    print(pq.get()[1])
    print(pq.get()[1])
    print(pq.get()[1])
    print(pq.empty())

    # Your task: find best route between two stops with:
    # A) Uniform-cost search
    print('Uniform-cost search:')
    print('Zoo - Aupark:')
    path = findPathUniformCost(bamhd, 'Zoo', 'Aupark')
    print('\tpath: {}'.format(path))

    print('VW - Astronomicka:')
    path = findPathUniformCost(bamhd, 'Volkswagen', 'Astronomicka')
    print('\tpath: {}'.format(path))

    print('Vodostav - Astronomicka:')
    path = findPathUniformCost(bamhd, 'Vodostav', 'Astronomicka')
    print('\tpath: {}'.format(path))

    # B) A* search
    print('\nA* search:')
    print('Zoo - Aupark:')
    path = findPathAStar(bamhd, 'Zoo', 'Aupark')
    print('\tpath: {}'.format(path))

    print('VW - Astronomicka:')
    path = findPathAStar(bamhd, 'Volkswagen', 'Astronomicka')
    print('\tpath: {}'.format(path))
