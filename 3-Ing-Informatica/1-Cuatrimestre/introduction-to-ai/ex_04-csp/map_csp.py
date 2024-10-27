import sys
import time
from maps import *

class MapCSP():
    def __init__(self, states, neighbours):
        # List of available colors
        self.color_options = ['red', 'green', 'blue', 'yellow']
        self.states = states
        self.neighbours = neighbours
        self.colors = {s: None for s in self.states}

    def print_map(self):
        # Prints all states and their colors
        for s in sorted(self.states):
            print('{} has color: {}'.format(s, self.get_color(s)))
        print()


    def set_color(self, state, color):
        # Assign color to a state
        self.colors[state] = color

    def del_color(self, state):
        # Remove color from state - reset to None
        self.colors[state] = None

    def get_color(self, state):
        # Get color assigned to a state
        return self.colors[state]

    def has_color(self, state):
        # Returns True if state has already a color
        return self.colors[state] != None

    def same_colors(self, state1, state2):
        # Returns True if state1 and state2 are colored with the same color.
        return self.has_color(state1)  and  self.get_color(state1) == self.get_color(state2)

    def all_colored(self):
        # Returns True if all states of the map are already colored.
        return all([self.has_color(s) for s in self.states])

    def is_correct_coloring(self):
        # Returns True if coloring is all correct, False if not. Prints the result with found error (if any).
        print('Coloring is ', end='')
        for s1 in self.states:
            if self.get_color(s1) not in self.color_options:
                print('INCORRECT - {} has invalid color: {}\n'.format(s1, self.get_color(s1)))
                return False
            for s2 in self.neighbours[s1]:
                if self.same_colors(s1,s2):
                    print('INCORRECT - {} and {} have conflicting color {}\n'.format(s1, s2, self.get_color(s1)))
                    return False
        print('OK\n')
        return True


    def can_set_color(self, state, color):
        # Returns True if we can set color to a state without violating constrains - all neighbouring
        # states must have None or different color.
        for n in self.neighbours[state]:
            if self.get_color(n) == color:
                return False
        return True

    def select_next_state(self, use_heuristic=True):
        # Selects next state that will be colored, or returns False if no such exists (all stated are
        # colored). You can use heuristics or simply choose a state without color for start.
        if use_heuristic:
            mrv_state = None
            min_options = float('inf')

            for state in self.states:
                if not self.has_color(state):
                    # Number of valid colors for the selected state
                    valid_colors = sum(self.can_set_color(state, color) for color in self.color_options)

                    # Update the state with the minimum number of valid colors
                    if valid_colors < min_options:
                        min_options = valid_colors
                        mrv_state = state

            return mrv_state
        else:
            for state in self.states:
                if not self.has_color(state):
                    return state
            return False

    def color_map(self):
        # Assign colors to all states on the map. (! Beware: 'map' is python`s reserved word - function)
        if self.all_colored():
            return True

        # Get next state to color
        next_state = self.select_next_state()
        if not next_state:
            return False

        # For all possible colors
        for color in self.color_options:
            if self.can_set_color(next_state, color):
                # Set color to the actual state
                self.set_color(next_state, color)
                # Recursive call to continue coloring the map
                if self.color_map():
                    return True

            # Solution didn't work, so we backtrack to use another color combination instead
            self.del_color(next_state)

        return False




if __name__ == "__main__":
    maps = [('Australia', AustraliaMap()),
            ('USSR', USSRMap()),
            ('USA', USAMap()),
            ('World', WorldMap()),

            ('Impossible Australia', ImpossibleMap(AustraliaMap())),
            ('Impossible USSR', ImpossibleMap(USSRMap())),
            ('Impossible USA', ImpossibleMap(USAMap())),
            ('Impossible World', ImpossibleMap(WorldMap()))
            ]

    for name, mapa in maps:
        print('==== {} ===='.format(name))
        t = time.time()
        has_result = mapa.color_map()    # Compute the colors for an empty map
        print('Time: {:.3f} ms'.format( (time.time() - t)*1000 ))
        if has_result:
            mapa.is_correct_coloring()  # Print whether coloring is correct
        else:
            print('Coloring does not exist\n')
        # mapa.print_map()    # Print whole coloring
