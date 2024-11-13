import numpy as np
import random

from problems import *

class GenAlgProblem:

    def __init__(self, population_size=12, n_crossover=3, mutation_prob=0.05):
        # Initialize the population - create population of 'size' individuals,
        # each individual is a bit string of length 'word_len'.
        self.population_size = population_size
        self.n_crossover = 3
        self.mutation_prob = 0.05
        self.population = [self.generate_individual() for _ in range(self.population_size)]

    def generate_individual(self):
        # Generate random individual.
        # To be implemented in subclasses
        raise NotImplementedError

    def show_individual(self, x):
        # Show the given individual x, either to console or graphically.
        # To be implemented in subclasses
        raise NotImplementedError

    def show_population(self, title='Population:', limit=None, **kwargs):
        # Show whole population.
        # To be implemented in subclasses
        raise NotImplementedError


    def fitness(self, x):
        # Returns fitness of a given individual.
        # To be implemented in subclasses
        raise NotImplementedError

    def crossover(self, x, y, k):
        # Take two parents (x and y) and make two children by applying k-point
        # crossover. Positions for crossover are chosen randomly.

        # Generate random crossover points
        points = sorted(random.sample(range(len(x)), k))
        children = [x.copy(), y.copy()]

        # Perform crossover
        for i in range(len(points)):
            if i % 2 == 0:
                for j in range(points[i], points[i+1] if i+1 < len(points) else len(x)):
                    children[0][j], children[1][j] = children[1][j], children[0][j]

        return children


    def boolean_mutation(self, x, prob):
        # Elements of x are 0 or 1. Mutate (i.e. change) each element of x with given probability.

        # Mutate elements
        for i in range(len(x)):
            if random.random() < prob:
                x[i] = 1 - x[i]
        return x

    def number_mutation(self, x, prob):
        # Elements of x are real numbers [0.0 .. 1.0]. Mutate (i.e. add/subtract random number)
        # each number in x with given probability.

        # Mutate elements
        for i in range(len(x)):
            if random.random() < prob:
                x[i] += random.gauss(0, 1)
                x[i] = max(0.0, min(1.0, x[i]))
        return x

    def mutation(self, x, prob):
        # To be specified in subclasses, uses boolean_mutation or number_mutation functions
        raise NotImplementedError

    def solve(self, max_generations, goal_fitness=1):
        # Implementation of genetic algorithm. Produce generations until some
        # individual`s fitness reaches goal_fitness, or you exceed total number
        # of max_generations generations. Return best found individual.

        for generation in range(max_generations):
            # Rating of individuals
            fits = []
            for x in self.population:
                fitness = self.fitness(x)
                if fitness == goal_fitness:
                    return x

                fits.append((x, fitness))

            # Sort individuals by fitness
            fits.sort(key=lambda item: item[1], reverse=True)

            # Select parents (best N/2 individuals) and generate pairs for crossover
            parents = [ fit[0] for fit in fits[:self.population_size // 2] ]
            # pairs = [ (parents[i], parents[len(parents) - 1 - i]) for i in range(0, len(parents)) ]
            pairs = []
            for i in range(len(parents)):
                if len(parents) - 1 - i == i:
                    break
                pairs.append((parents[i], parents[len(parents) - 1 - i]))

            # Crossover random parents to create new generation
            new_generation = []
            for parent1, parent2 in pairs:
                children = self.crossover(parent1, parent2, self.n_crossover)
                new_generation.extend(children)

            new_generation = [ self.mutation(child, self.mutation_prob) for child in new_generation ]
            self.population = parents + new_generation[:self.population_size // 2]

        individuals = sorted(self.population, key=lambda x: self.fitness(x), reverse=True)
        print(self.fitness(individuals[0]))
        return individuals[0]


if __name__ == "__main__":
    # Choose problem
    # ga = OnesString()
    ga = Smiley()
    # ga = Painting('painting.jpg', population_size=32, mutation_prob=0.25)
    ga.show_population('Initial population', limit=None)

    # You can play with parameters
    ga.n_crossover = 3
    ga.mutation_prob = 0.1

    # Solve to find optimal individual
    best = ga.solve(100) # you can also play with max. generations
    ga.show_population('Final population', limit=None)
    ga.show_individual(best, 'Best individual')


    ## Test your crossover function
