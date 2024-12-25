import numpy as np
import random
from genetic import *

class MyCar(Car):
    def __init__(self, chromosome, **kwargs):
        super(MyCar, self).__init__(**kwargs)

        self.input_dim = len(self.sensors) + 2  # 5 sensors + speed + turn
        self.output_dim = 2  # Change speed and turn

        if chromosome is None:
            n_hidden = 16  # Increased neurons for better learning
            chromosome_size = (n_hidden * (self.input_dim + 1)) + (self.output_dim * (n_hidden + 1))
            self.chromosome = list(np.random.randn(chromosome_size) * np.sqrt(2 / (self.input_dim + self.output_dim)))
        else:
            self.chromosome = chromosome

        # Decode chromosome into weight matrices
        n_hidden = 16
        W_in_size = n_hidden * (self.input_dim + 1)
        W_out_size = self.output_dim * (n_hidden + 1)

        self.W_in = np.array(self.chromosome[:W_in_size]).reshape(n_hidden, self.input_dim + 1)
        self.W_out = np.array(self.chromosome[W_in_size:W_in_size + W_out_size]).reshape(self.output_dim, n_hidden + 1)

    def compute_output(self):
        sensor_norms = []
        for point_1, point_2 in self.sensors:
            length = np.linalg.norm(np.array(point_2) - np.array(point_1))
            normalized_length = length / 200  # Normalize to [0, 1]
            sensor_norms.append(normalized_length)

        input_vector = np.array(sensor_norms + [self.speed, self.turn, 1])  # Add bias

        hidden_input = np.dot(self.W_in, input_vector)
        hidden_activation = np.maximum(0, hidden_input)  # ReLU activation

        hidden_with_bias = np.append(hidden_activation, 1)

        output_input = np.dot(self.W_out, hidden_with_bias)
        output = np.tanh(output_input)  # Output in (-1, 1)

        return output

    def update_parameters(self, instructions):
        delta_speed, delta_turn = instructions

        self.speed = max(0, min(self.speed_limit[1], self.speed + delta_speed * self.speed_limit[1]))
        self.turn = max(self.turn_limit[0], min(self.turn_limit[1], self.turn + delta_turn * self.turn_limit[1]))

        self.orientation += self.turn


class MyRaceCars(RaceCars):
    def __init__(self, *args, **kwargs):
        super(MyRaceCars, self).__init__(*args, **kwargs)

    def generate_individual(self, chromosome=None):
        initial_position = [500, 600 + random.randint(-25, 25)]
        orientation = 90
        new_car = MyCar(chromosome, position=initial_position, orientation=orientation,
                        additional_scale=self.scale_factor)
        sensors = []
        if self.show_sens:
            for _ in new_car.sensors:
                sensors.append(self.canvas.create_line(0, 0, 0, 0, dash=(2, 1)))
        return [new_car, self.canvas.create_image(initial_position), sensors]


if __name__ == '__main__':
    track = create_track()

    # Useful parameters to play with:
    population_size = 16        # Total population size used for training
    select_top = 4              # During selection, only the best select_top cars are chosen as parents

    show_training = True        # Each generation is shown on canvas
    show_all_cars = False       # The code is faster if not all cars are always displayed
    displayed_cars = 8          # Only the first few cars are displayed

    # show_training = False     # The training is done in the background
    show_every_n = 3            # The best cars are shown after every few generations (due to faster training)

    mutation_prob = 0.05        # Mutation probability for number mutation
    deviation = 1               # This standard deviation used when mutating a chromosome

    RC = MyRaceCars(track, population_size=population_size, show_sensors=False, gen_steps=500, n_gens=100,
                    show_all_cars=show_all_cars, select_top=select_top, mutation_prob=mutation_prob,
                    show_training=show_training, displayed_cars=displayed_cars, vis_pause=10, show_every_n=show_every_n,
                    can_width=1100, deviation=deviation)

    RC.run_simulation()
