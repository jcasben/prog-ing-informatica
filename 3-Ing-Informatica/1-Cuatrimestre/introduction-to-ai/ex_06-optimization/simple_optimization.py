import math
import random
import os
import atexit
from time import sleep
import numpy as np
import matplotlib
from networkx.classes import neighbors

matplotlib.use('TkAgg') # fixme if plotting doesn`t work (try 'Qt5Agg' or 'Qt4Agg')
import matplotlib.pyplot as plt


def finish():
    plt.show(block=True) # Woraround to prevent plots from closing

atexit.register(finish)


class OptimizeMax:
    # Abstract class solving all maximization problems

    def __init__(self):
        pass

    def hillclimb(self, max_steps=100, plot=True):
        # A function that finds the maximal value of the fitness function by
        # executing the hill climbing algorithm.
        # Returns a state (e.g. x) for which fitness(x) is maximal.

        state = self.random_state()
        for _ in range(max_steps):
            best_neighbor = max(self.neighbors(state), key=self.fitness)
            if self.fitness(best_neighbor) <= self.fitness(state):
                return state
            state = best_neighbor
            if plot:
                self.plot(state, self.fitness(state), title='Hill climb')


    # These abstract methods are implemented in subclasses
    def fitness(self, x):
        # Returns the value of fitness for a given state.
        raise NotImplementedError("This function needs to be implemented in subclass.")

    def neighbors(self, x):
        # Returns a list of neighbouring states for a given state.
        raise NotImplementedError("This function needs to be implemented in subclass.")

    def random_state(self):
        # Returns a random state for a given problem.
        raise NotImplementedError("This function needs to be implemented in subclass.")

    def plot(self, x, fx):
        # Plots point [x, fx] onto a plot. If the inhereted class does not
        # override, this function it will do essentially nothing.
        pass



class MysteryFunction(OptimizeMax):
    # An optimization problem in which we are trying to find value for x such
    # that function sin(x)/x is maximized.

    def __init__(self, span=30, delta=0.1):
        self.cfg = None
        self.hist_x = []
        self.hist_y = []
        self.span = span
        self.delta = delta

    def keypress(self, e):
        if e.key in {'q', 'escape'}: os._exit(0) # unclean exit, but exit() or sys.exit() won't work
        if e.key in {' ', 'enter'}: plt.close() # skip blocking figures

    def plot(self, x, y, title, temperature=None):
        # Initialization of figure
        if title != self.cfg:
            self.cfg = title
            self.hist_x = []
            self.hist_y = []
            plt.figure(num=title).canvas.mpl_connect('key_press_event', self.keypress)
            plt.axis([-self.span, self.span, -0.5, 2.5])
            plt.ion()
        # Plotting
        plt.clf()
        xx = np.linspace(-self.span, self.span, 1000)
        plt.plot(xx, np.sin(xx)/xx + np.cos(xx/10)/3, c='k', lw=0.5)
        self.hist_x += [x]
        self.hist_y += [y]
        colors = np.arange(len(self.hist_x))
        plt.scatter(x, y, s=30, c='r')
        if temperature:
            plt.title('T          = {:.5f}\np(-0.3) = {:.8f} %\n[Press ESC to quit]'
                      .format(temperature, math.exp(-0.3/temperature) * 100), loc='left')
        else:
            plt.title('[Press ESC to quit]', loc='left')
        plt.gcf().canvas.flush_events()
        plt.waitforbuttonpress(timeout=0.001)

    def fitness(self, x):
        if x == 0:
            return 1
        return np.sin(x)/x + np.cos(x/10)/3

    def neighbors(self, x):
        res = []
        if x > -self.span + 3*self.delta: res += [x - i*self.delta for i in range(1, 4)]
        if x <  self.span - 3*self.delta: res += [x + i*self.delta for i in range(1, 4)]
        return res

    def random_state(self):
        return random.random() * self.span * 2 - self.span



class EightQueens(OptimizeMax):
    # An optimization problem in which we are trying to find positions of 8
    # queens on an 8x8 chessboard so that no two queens threaten each other.

    # For the solution of this problem, I will be representing the state as a simple list,
    # where each element represents a column, and the value of it represents the row where the
    # queen is placed. This is a great way of representing the state as it's a very simple
    # way of doing it, in addition to the low memory usage and the simplicity to implement the
    # other functions around it.

    # The fitness value for this problem is 28.

    def fitness(self, x):
        n = len(x)
        non_attacking_pairs = 0
        for i in range(n):
            for j in range(i + 1, n):
                if x[i] != x[j] and abs(x[i] - x[j]) != j - i:
                    non_attacking_pairs += 1
        return non_attacking_pairs

    def neighbors(self, x):
        neighbors_list = []
        n = len(x)
        for col in range(n):
            for row in range(n):
                if row != x[col]:
                    neighbor = list(x)
                    neighbor[col] = row
                    neighbors_list.append(neighbor)
        return neighbors_list

    def random_state(self):
        n = 8
        return [random.randint(0, n - 1) for _ in range(n)]


if __name__ == '__main__':
    #  Task 1
    for _ in range(10):
        problem = MysteryFunction()
        max_x = problem.hillclimb()
        print("Found maximum of Mystery function with hill climbing at x={}, f={}\n"
              .format(max_x, problem.fitness(max_x)))
        sleep(2)


    #  Task 2
    n_attempts = 10
    for _ in range(n_attempts):
        problem = EightQueens()
        solution = problem.hillclimb(plot=False)
        print("Found a solution (with fitness of {}) with hill climbing to 8 queens problem:\n{}\n"
              .format(problem.fitness(solution), solution))
