from PIL import Image, ImageTk
import tkinter as tk
from utils import *
import random

car_options = ['blue', 'brown', 'cyan', 'green', 'grey', 'lime', 'orange', 'pink', 'purple', 'red', 'white',
               'yellow']


def sigmoid(x):
    return 1/(1+np.exp(-x))


class Car:
    def __init__(self, position=(0, 0), orientation=0, additional_scale=1):
        self.center, self.orientation = np.array(position, dtype=float), orientation
        self.speed_limit, self.turn_limit = [-2, 4], [-3, 3]
        self.max_speed_change, self.max_turn_change = 1.5, 1
        self.speed = 0.5
        self.turn = 0.0
        self.fitness, self.last_active = 0, 0
        self.crashed = False

        self.additional_scale = additional_scale
        self.scale_factor = 0.2

        self.img = Image.open(f'cars/{random.choice(car_options)}_car.png')
        self.width, self.height = self.img.size[0] * self.scale_factor, self.img.size[1] * self.scale_factor
        self.img = self.img.resize((int(self.width * self.additional_scale), int(self.height * self.additional_scale)))

        self.sensors = self.get_sensors()

    def compute_output(self):
        # To be implemented in subclasses
        raise NotImplementedError

    def update_parameters(self, signal):
        # To be implemented in subclasses
        raise NotImplementedError

    def get_sensors(self, sensor_len=200):
        # creates the sensors for a car, normalizes them and returns
        sensors = []
        p1 = np.array((self.center[0], self.center[1]))
        p2 = np.array((self.center[0], self.center[1] - self.height))
        p1, p2 = normalize_vector(rotate(self.center, p1, self.orientation), rotate(self.center, p2, self.orientation),
                                  norm=sensor_len)
        sensors += [[p1, rotate(p1, p2, 45)], [p1, p2], [p1, rotate(p1, p2, -45)], [p1, rotate(p1, p2, 90)],
                    [p1, rotate(p1, p2, -90)]]
        return sensors

    def move(self):
        # Change car position according to the current speed and turn
        angle = - (self.orientation - 90) * math.pi / 180
        direction = - np.array([math.cos(angle), math.sin(angle)])

        self.center += self.speed * direction
        self.sensors = self.get_sensors()

    def get_lidar_data(self, track_edges):
        for sensor_id in range(len(self.sensors)):
            for track_edge in track_edges:
                if track_edge[0] == 'ls':
                    intersection = ls_ls_intersection(self.sensors[sensor_id], track_edge[1])
                    if intersection is not False:
                        self.sensors[sensor_id][1] = intersection
                elif track_edge[0] == 'cs':
                    intersection = ls_arc_intersection(self.sensors[sensor_id], track_edge[1])
                    if intersection is not False:
                        self.sensors[sensor_id][1] = intersection


class RaceCars:
    def __init__(self, track_description, can_width=1100, bg_color='azure', population_size=1, gen_steps=10,
                 show_sensors=False, select_top=4, n_gens=50, show_all_cars=True, mutation_prob=0.05,
                 show_training=False, displayed_cars=10, angle_precision=3, vis_pause=10, show_every_n=5, deviation=.1):

        self.show_every_n = show_every_n
        self.vis_pause = vis_pause
        self.angle_precision = angle_precision
        self.show_training = show_training
        self.displayed_cars = displayed_cars
        self.show_all_cars = show_all_cars
        self.population_size = population_size
        self.bg_color = bg_color
        self.root = tk.Tk()
        self.frame = tk.Frame(self.root)
        self.frame.pack()

        self.can_width = int(can_width)
        self.can_height = int(self.can_width * 7 / 11)
        self.scale_factor = self.can_width / 1100
        self.canvas = tk.Canvas(self.frame, bg=self.bg_color, width=self.can_width, height=self.can_height)
        self.fitness_text = self.canvas.create_text(550 * self.scale_factor, 50 * self.scale_factor,
                                                    text=f'Best fitness: {0}', fill='black',
                                                    font=f'arial {int(20*self.scale_factor)} bold')
        self.n_generations_text = self.canvas.create_text(150*self.scale_factor, 50 * self.scale_factor,
                                                          text=f'Generation: {1}', fill='black',
                                                          font=f'arial {int(20*self.scale_factor)} bold')
        self.gen_steps = gen_steps
        self.time_left = self.canvas.create_text(950 * self.scale_factor, 50 * self.scale_factor,
                                                 text=f'Steps left: {self.gen_steps}', fill='black',
                                                 font=f'arial {int(20*self.scale_factor)} bold')
        self.start_time = None
        self.show_sens = show_sensors
        self.select_top = select_top

        self.canvas.pack()
        self.n_gens = n_gens
        self.track_description = track_description

        self.track = self.generate_track()
        self.edges = self.generate_track_edges(self.track_description[1])
        self.canvas.update()

        self.population = [self.generate_individual() for _ in range(self.population_size)]
        self.n_crossover = 3
        self.mutation_prob = mutation_prob
        self.deviation = deviation

    def update_text(self, t, population):
        mx = max(population, key=self.fitness)
        self.canvas.itemconfig(self.fitness_text, text=f'Best fitness: {mx[0].fitness:.3f}')
        self.canvas.itemconfig(self.time_left, text=f'Steps left: {t}')

    def solve(self):
        parents = self.population[:self.select_top]
        num_children = self.population_size - self.select_top
        parents_chroms = [car.chromosome for car, _, _ in parents]
        new_pop = parents_chroms.copy()

        for ind in range(num_children // 2):
            child1, child2 = self.crossover(random.choice(parents_chroms), random.choice(parents_chroms),
                                            self.n_crossover)
            new_pop.append(self.mutation(child1))
            new_pop.append(self.mutation(child2))
        self.population = [self.generate_individual(chro) for chro in new_pop]

    def run_simulation(self):
        for gen in range(self.n_gens):
            self.canvas.itemconfig(self.n_generations_text, text=f'Generation: {gen+1}')
            self.run_next_generation(self.population, self.show_training)
            self.population = sorted(self.population, key=self.fitness, reverse=True)

            max_fitness = max(self.population, key=self.fitness)[0].fitness
            print(f"Generation {gen + 1} finished. Best fitness = {max_fitness:.2f}")

            if not self.show_training and (gen + self.show_every_n) % self.show_every_n == 0:
                chromosomes = [x[0].chromosome for idx, x in enumerate(self.population) if idx < self.displayed_cars]
                cars = [self.generate_individual(ch) for ch in chromosomes]
                self.run_next_generation(cars, True)

            # update the generation
            self.solve()

    def run_next_generation(self, population, visualize=True):
        moved, num_steps = True, self.gen_steps
        car_images = [None] * len(population)
        car_angles = [float('inf')] * len(population)
        while moved and num_steps > 0:
            num_steps -= 1
            if visualize:
                self.update_text(num_steps, population)
            moved = False
            for idd, (car, car_image, sensors) in enumerate(population):
                if car.crashed:
                    continue

                output = car.compute_output()
                car.update_parameters(output)

                car.move()
                car.get_lidar_data(self.track_description[1])

                if not self.car_on_track(car):
                    car.crashed = True
                    for s in sensors:
                        self.canvas.delete(s)

                moved = True
                self.update_fitness(car)

                if visualize:
                    # Draw the cars at their current position
                    if idd < self.displayed_cars or self.show_all_cars:
                        if np.abs(car_angles[idd]-car.orientation) > self.angle_precision:
                            car_images[idd] = ImageTk.PhotoImage(car.img.rotate(car.orientation, expand=True))
                            car_angles[idd] = car.orientation
                            self.canvas.itemconfig(car_image, image=car_images[idd])
                        self.canvas.coords(car_image, car.center[0]*self.scale_factor, car.center[1]*self.scale_factor)

                        if self.show_sens:
                            for sensor_id, sensor in enumerate(sensors):
                                car_sensor = car.sensors[sensor_id]
                                x1, y1 = car_sensor[0]
                                x2, y2 = car_sensor[1]
                                self.canvas.coords(sensor, x1, y1, x2, y2)

            if visualize:
                self.canvas.update()
                self.canvas.after(0 if self.show_training else self.vis_pause)

    def generate_individual(self, chromosome=None):
        # To be implemented in subclasses
        raise NotImplementedError

    def crossover(self, x, y, k):
        # Take two parents (x and y) and make two children by applying k-point
        # crossover. Positions for crossover are chosen randomly.
        swaps = random.sample(range(1, len(x)), k)
        swaps.sort()
        swaps.insert(0, 0)
        swaps.append(len(x))
        x_new, y_new = [], []

        for i in range(len(swaps) - 1):
            x_new += y[swaps[i]:swaps[i + 1]].copy()
            y_new += x[swaps[i]:swaps[i + 1]].copy()
            x, y = y, x
        return x_new, y_new

    def mutation(self, x):
        # Elements of x are real numbers [0.0 .. 1.0]. Mutate (i.e. add/subtract random number)
        # each number in x with given probability.
        x = np.array(x)
        p = np.random.rand(len(x))
        x[p < self.mutation_prob] += np.random.randn(len(x[p < self.mutation_prob])) * self.deviation
        return list(x)

    def update_fitness(self, x):
        # updates the fitness of a car, if the center is on the track. Otherwise the fitness does not change.
        is_in = self.point_on_track(x.center)
        if is_in[0]:
            len_track = len(self.track_description[0])
            current = is_in[2]
            x.fitness = x.last_active  # index of the shape, each primitive shape can add 1 fitness point
            if np.abs(x.last_active % len_track - current) == 1:
                x.last_active += current - x.last_active % len_track
            elif current % len_track == 0 and (x.last_active + 1) % len_track == 0:
                x.last_active += 1
            elif np.abs(x.last_active % len_track - current) != 0:
                return

            if is_in[1][0] == 'rec':
                if is_in[1][2] == 'left':
                    x.fitness += (is_in[1][1][2] - x.center[0]) / (is_in[1][1][2] - is_in[1][1][0])
                elif is_in[1][2] == 'right':
                    x.fitness += (x.center[0] - is_in[1][1][0]) / (is_in[1][1][2] - is_in[1][1][0])
                elif is_in[1][2] == 'up':
                    x.fitness += (is_in[1][1][3] - x.center[1]) / (is_in[1][1][3] - is_in[1][1][1])
                elif is_in[1][2] == 'down':
                    x.fitness += (x.center[1] - is_in[1][1][1]) / (is_in[1][1][3] - is_in[1][1][1])
            elif is_in[1][0] == 'circle_seg':
                angle = get_angle_degrees([x.center[0] - is_in[1][1][0], x.center[1] - is_in[1][1][1]])
                if is_in[1][2] == 'clockwise':
                    x.fitness += (is_in[1][1][3] + is_in[1][1][4] - angle) / is_in[1][1][4]
                elif is_in[1][2] == 'anti_clockwise':
                    x.fitness += (angle - is_in[1][1][3]) / is_in[1][1][4]

    def fitness(self, x):
        return x[0].fitness

    def car_on_track(self, individual):
        # returns True if all the cars corners are in the track else return False
        for i in [0, 1]:
            for j in [0, 1]:
                corner = [individual.center[0] + (-1)**i * individual.width / 2,
                          individual.center[1] + (-1)**j * individual.height / 2]
                corner = rotate(individual.center, corner, individual.orientation)
                if not self.point_on_track(corner)[0]:
                    return False
        return True

    def point_on_track(self, point):
        # returns True if the point lies somewhere on the track along with the shape parameters, otherwise return False
        # and -1
        for ii, shape in enumerate(self.track_description[0]):
            if shape[0] == 'rec':
                x1, y1, x2, y2 = shape[1]
                if x1 < point[0] < x2 and y1 < point[1] < y2:
                    return True, shape, ii
            elif shape[0] == 'circle_seg':
                x, y, r, init_angle, end_angle = shape[1]
                if (point[0] - x) ** 2 + (point[1] - y) ** 2 < r ** 2:
                    angle = get_angle_degrees(np.array([point[0] - x, point[1] - y]))
                    if init_angle < angle < init_angle + end_angle:
                        return True, shape, ii
            else:
                raise NotImplementedError('Shape not implemented')
        return False, -1

    def test_inside_detections(self):
        r = 1
        for x in range(1, self.can_width, 4):
            for y in range(1, self.can_height, 4):
                if self.point_on_track(np.array([x, y], dtype=float))[0]:
                    self.canvas.create_oval(x - r, y - r, x + r, y + r, fill='red', outline='')
                else:
                    self.canvas.create_oval(x - r, y - r, x + r, y + r, fill='green', outline='')

    def generate_track_edges(self, edges):
        res = []
        for edge in edges:
            if edge[0] == 'ls':
                x1, y1, x2, y2 = edge[1]
                x1, y1 = x1 * self.scale_factor, y1 * self.scale_factor
                x2, y2 = x2 * self.scale_factor, y2 * self.scale_factor
                res.append(self.canvas.create_line(x1, y1, x2, y2))
            elif edge[0] == 'cs':
                x, y, r, init_angle, end_angle = edge[1]
                x, y, r = x * self.scale_factor, y * self.scale_factor, r * self.scale_factor
                res.append(self.canvas.create_arc(x - r, y - r, x + r, y + r, style='arc', start=init_angle,
                                                  extent=end_angle))
        return res

    def generate_track(self, color='lightgray'):
        tr = []
        for shape in self.track_description[0]:
            if shape[0] == 'oval':
                x, y, r = shape[1]
                x, y, r = x * self.scale_factor, y * self.scale_factor, r * self.scale_factor
                tr.append(self.canvas.create_oval(x - r, y - r, x + r, y + r, fill=color, outline=''))
            elif shape[0] == 'arc':
                x, y, r_big, r_small, init_angle, end_angle = shape[1]
                x, y, r_big, r_small = x*self.scale_factor, y, r_big*self.scale_factor, r_small*self.scale_factor
                tr.append(self.canvas.create_arc(x - r_big, y - r_big, x + r_big, y + r_big, start=init_angle,
                                                 extent=end_angle, fill=color, outline=''))
                tr.append(self.canvas.create_arc(x - r_small, y - r_small, x + r_small, y + r_small, start=init_angle,
                                                 extent=end_angle+1, fill=self.bg_color, outline=''))
            elif shape[0] == 'rec':
                x1, y1, x2, y2 = shape[1]
                x1, y1, x2, y2 = x1*self.scale_factor, y1*self.scale_factor, x2*self.scale_factor, y2*self.scale_factor
                tr.append(self.canvas.create_rectangle(x1, y1, x2, y2, fill=color, outline=''))
            elif shape[0] == 'circle_seg':
                x, y, r, init_angle, end_angle = shape[1]
                x, y, r = x * self.scale_factor, y * self.scale_factor, r * self.scale_factor
                tr.append(self.canvas.create_arc(x - r, y - r, x + r, y + r, start=init_angle, extent=end_angle,
                                                 fill=color, outline=''))
            else:
                raise ValueError(f'Shape "{shape[0]}" is not implemented!')
