############################### CLASS DEFINITION ##############################

class CrossWord():
    # Dict of possible directions {name: (delta_row, delta_col)}
    directions = {'down': (1, 0), 'right': (0, 1)}

    def __init__(self, grid):
        self.grid = grid
        self.positions = self.get_positions(grid)

    def get_positions(self, grid):
        # Computes list of all possible positions for words.
        def check_line(line):
            res = []
            start_i, was_space = 0, False
            for i in range(len(line)):
                if line[i] == '#' and was_space:
                    was_space = False
                    if i - start_i > 1:
                        res.append((start_i, i - start_i))
                elif line[i] == ' ' and not was_space:
                    start_i = i
                    was_space = True
            return res

        poss = []
        for r in range(len(grid)):
            row = grid[r]
            poss = poss + [(r, p[0], p[1], 'right') for p in check_line(row)]
        for c in range(len(grid[0])):
            column = [row[c] for row in grid]
            poss = poss + [(p[0], c, p[1], 'down') for p in check_line(column)]
        return poss

    def print_grid(self):
        # Pretty prints the crossword
        for row in self.grid:
            print(''.join(row))

    def text_at_pos(self, position):
        # Returns text actually written in specified position.
        dr, dc = self.directions[position[3]]
        r, c = position[0], position[1]
        return ''.join([self.grid[r + i * dr][c + i * dc] for i in range(position[2])])

    def write_word(self, position, word):
        # Writes word to specified position and direction.
        dr, dc = self.directions[position[3]]
        r, c = position[0], position[1]
        for i in range(position[2]):
            self.grid[r + i * dr][c + i * dc] = word[i]

    def can_write_word(self, position, word):
        # Check whether the word can be placed into specified position.
        if len(word) != position[2]:
            return False
        dr, dc = self.directions[position[3]]
        r, c = position[0], position[1]
        for i in range(position[2]):
            grid_char = self.grid[r + i * dr][c + i * dc]
            if grid_char != ' ' and grid_char != word[i]:
                return False
        return True


############################### SERVICE METHODS ###############################

def load_words(path):
    # Loads all words from file
    return open(path, 'r').read().splitlines()


def load_grids(path):
    # Loads empty grids from file
    raw = open(path, 'r').read().split('\n\n')
    per_rows = [grid.rstrip().split('\n') for grid in raw]
    per_char = [[list(row) for row in grid] for grid in per_rows]
    return per_char


################################### SOLVING ###################################

def solve(crossword, words):
    # Fill the empty spaces in crossword with words
    def backtrack(remaining_positions):
        if not remaining_positions:
            return True  # All positions filled

        # Select the next position using MRV heuristic
        position = min(remaining_positions, key=lambda pos: len(possible_words[pos]))
        remaining_positions.remove(position)

        # Try each possible word for this position
        for word in possible_words[position]:
            if crossword.can_write_word(position, word):
                crossword.write_word(position, word)

                # Save current state of domains
                saved_domains = {pos: possible_words[pos][:] for pos in remaining_positions}

                # Update domains
                for pos in remaining_positions:
                    possible_words[pos] = [w for w in possible_words[pos] if crossword.can_write_word(pos, w)]

                # Recurse
                if backtrack(remaining_positions):
                    return True

                # Undo changes
                crossword.write_word(position, ' ' * position[2])
                possible_words.update(saved_domains)

        remaining_positions.append(position)
        return False

    # Preprocess words by length
    words_by_length = {}
    for word in words:
        words_by_length.setdefault(len(word), []).append(word)

    # Initialize domains for each position
    possible_words = {pos: words_by_length.get(pos[2], []) for pos in crossword.positions}

    backtrack(crossword.positions)


################################ MAIN PROGRAM #################################

if __name__ == "__main__":
    ## Load data:
    words = load_words('words.txt')
    grids = load_grids('krizovky.txt')

    ## Examples:
    dummy_grid = [list(s) for s in ['########', '#      #', '#      #', '#      #', '###    #', '#      #', '########']]
    cw = CrossWord(dummy_grid)
    cw.print_grid()  # empty grid
    print('Positions: ' + str(cw.positions))
    cw.write_word((2, 1, 5, 'right'), 'hello')
    cw.write_word((1, 5, 5, 'down'), 'world')
    cw.write_word((4, 3, 4, 'right'), 'milk')
    cw.print_grid()  # 3 words already filled in
    print('Text at position (1,4) down: "' + cw.text_at_pos((1, 4, 5, 'down')) + '"\n\n\n')

    points = [0.5, 1, 1.5, 1.5, 1.5, 2, 2, 2, 2, 2]
    points_so_far = 0
    # Solve crosswords (the last one is a bonus)
    # instead of range(len(grids)) specify in which order do you want your crosswords to be tested
    for i in range(len(grids)):
        print('==== Crossword No.' + str(i + 1) + ' ====')
        cw = CrossWord(grids[i])
        solve(cw, words)
        cw.print_grid()

        points_so_far += points[i]
        print(f'Given all the solved crosswords are correct, you have so far {points_so_far}'
              ' points!')