from kb import *
import sys


################################ WORLD DEFINITION ################################


class WumpusWorld:
    def __init__(self, map_path):
        ch_to_type = {'#': 'wall', 'W': 'wumpus', 'p': 'pit', 'g': 'gold', 'H': 'hero', '.': 'empty'}
        self.mapa = []
        self.bump = False
        self.scream = False
        self.hero = (0, 0)
        self.arrow = 1
        self.killed_wumpus = False
        self.got_gold = False
        with open(map_path, 'r') as f:
            for r, line in enumerate(f.read().splitlines()):
                row = []
                for c, ch in enumerate(line):
                    tile = {'type': ch_to_type[ch], 'visited': False, 'percept': set()}
                    if tile['type'] == 'hero':
                        tile['type'] = 'empty'
                        tile['visited'] = True
                        self.hero = (r, c)
                    row.append(tile)
                self.mapa.append(row)
        self.init_hero = self.hero
        # Percepts
        deltas = [(-1, 0), (0, 1), (1, 0), (0, -1)]
        for r, row in enumerate(self.mapa):
            for c, tile in enumerate(row):
                for dr, dc in deltas:
                    nr, nc = r+dr, c+dc
                    if tile['type'] == 'wumpus':
                        self.mapa[nr][nc]['percept'].add('stench')
                    elif tile['type'] == 'pit':
                        self.mapa[nr][nc]['percept'].add('breeze')
                if tile['type'] == 'gold':
                    tile['percept'].add('glitter')

    def print_map(self, print_unv=False):
        # Prints visible part of map, or whole map if print_unvisited == True
        type_to_ch = {'wall': '#', 'wumpus': 'W', 'pit': 'p', 'gold': 'g', 'empty': ' '}
        hidden_tile = '-'
        print()
        for r, row in enumerate(self.mapa):
            for c, tile in enumerate(row):
                if (r, c) == self.hero:
                    print('H', end='')
                else:
                    print((type_to_ch[tile['type']] if tile['visited'] or print_unv else hidden_tile), end='')
            print()
        print()

    def directions_str_to_num(self, direction):
        # 0 = up, 1 = right, 2 = down, 3 = left
        return {'up': 0,    'u': 0,
                'right': 1, 'r': 1,
                'down': 2,  'd': 2,
                'left': 3,  'l': 3,
                }[direction.lower()]

    def move(self, direction):
        # Moves the hero in desired direction, returns True if he survives and game goes on
        # 0 = up, 1 = right, 2 = down, 3 = left
        direction = self.directions_str_to_num(direction)
        dr = ((direction+1) % 2) * (direction - 1)
        dc = (direction % 2) * (-direction + 2)
        r, c = self.hero
        nr, nc = r+dr, c+dc
        if self.mapa[nr][nc]['type'] == 'wall':
            self.bump = True
            self.mapa[nr][nc]['visited'] = True
            nr, nc = r, c
        elif self.mapa[nr][nc]['type'] in ['wumpus', 'pit']:
            print('--> You ran into ' + self.mapa[nr][nc]['type'])
            return False
        self.hero = (nr, nc)
        self.mapa[nr][nc]['visited'] = True
        return True

    def shoot(self, direction):
        # Shoots an arrow in desired direction, returns True as game goes on
        # 0 = up, 1 = right, 2 = down, 3 = left
        direction = self.directions_str_to_num(direction)
        if self.arrow < 1:
            print('You have no more arrows')
            return True
        self.arrow -= 1
        dr = ((direction+1) % 2) * (direction - 1)
        dc = (direction % 2) * (-direction + 2)
        r, c = self.hero
        while self.mapa[r][c]['type'] != 'wall':
            r, c = r+dr, c+dc
            if self.mapa[r][c]['type'] == 'wumpus':
                self.scream = True
                self.mapa[r][c]['type'] = 'empty'
                print('--> You killed Wumpus!')
                self.killed_wumpus = True
                deltas = [(-1, 0), (0, 1), (1, 0), (0, -1)]
                for dr, dc in deltas:
                    nr, nc = r+dr, c+dc
                    self.mapa[nr][nc]['percept'].remove('stench')
                return True
        print('--> You missed')
        return True

    def climb(self, _):
        # Climb out of the cave, if you`re at your starting position.
        # Returns True if game goes on, False is you climbed out.
        if self.hero == self.init_hero:
            print('--> You escaped from the cave.')
            if self.got_gold and self.killed_wumpus:
                print('--> You won the game!')
            if not self.got_gold:
                print('--> You did not pick up gold.')
            if not self.killed_wumpus:
                print('--> You did not kill wumpus.')
            return False
        print('--> You cannot climb unless you are at your starting position.')
        return True

    def pick(self, _):
        r, c = self.hero
        if self.mapa[r][c]['type'] == 'gold':
            print('--> You have a gold!')
            self.got_gold = True
            self.mapa[r][c]['type'] = 'empty'
            self.mapa[r][c]['percept'].remove('glitter')
        else:
            print('--> You cannot pick up gold, it is not here.')
        return True

    def percept(self):
        # Returns set of percepts in current time
        r, c = self.hero
        p = self.mapa[r][c]['percept'].copy()
        if self.bump:
            p.add('bump')
            self.bump = False
        if self.scream:
            p.add('scream')
            self.scream = False
        return p

    def play(self, choose_action, print_unv=False):
        # Main loop of the game
        while True:
            print('\n------------------------------------------------------------')
            self.print_map(print_unv)
            action, direction = choose_action(self, self.percept())
            if not action(direction):
                return


################################ TOOLS ################################


def print_safe_moves(world):
    print('Safe moves: ')
    r, c = world.hero
    moves = [('up', -1, 0), ('right', 0, 1), ('down', 1, 0), ('left', 0, -1)]
    for direction, dr, dc in moves:
        nr, nc = r+dr, c+dc
        if kb.ask(L('safe', nr, nc)):
            print('\t{} to ({}, {})'.format(direction, nr, nc))
    print()


def get_action_from_user(world):
    try:
        s = input('Choose action ([move|shoot|pick|climb|quit], direction): ')
        if s in ['exit', 'quit', 'q']:
            print('Good bye...')
            sys.exit(0)
        a = s.split(' ')
        action = {'move': world.move, 'shoot': world.shoot, 'climb': world.climb, 'pick': world.pick}[a[0]]
        return action, (a[1] if len(a) > 1 else None)
    except KeyError:
        print('Invalid action, try again...')
        return get_action_from_user(world)


################################ KB MANAGMENT ################################


def choose_action_interactive(world, percept):
    # Simple interactive player - asks user for each action.
    # User inputs: "[move|shoot|climb], direction". E.g "move up".
    print('You are at position {} and you percept: {}'.format(world.hero, (percept if percept else '{}')))
    kb_add_step(world, percept)
    print_safe_moves(world)
    return get_action_from_user(world)


def kb_initialize(knowledge_base, world):
    # At the beginning of the game: Fill KB with implication rules that will
    # help infer whether tiles are safe or not
    for r in range(len(world.mapa)):
        for c in range(len(world.mapa[0])):
            # Safe if there`s no pit or Wumpus
            knowledge_base.tell(Implication([NOT('wumpus_at', r, c), NOT('pit_at', r, c)], L('safe', r, c)))
            for dr, dc in [(-1, 0), (0, 1), (1, 0), (0, -1)]:
                # There`s no Wumpus if there`s no stench besides
                knowledge_base.tell(Implication([NOT('stench', r+dr, c+dc)], NOT('wumpus_at', r, c)))
                # There`s no pit if there`s no breeze besides
                knowledge_base.tell(Implication([NOT('breeze', r+dr, c+dc)], NOT('pit_at', r, c)))


def kb_add_step(world, percept):
    # Every time after player makes a step: Add percepts and/or other knowledge to KB
    r, c = world.hero        # row-column position of hero
    for p in ['stench', 'breeze', 'glitter']:
        kb.tell(Fact(ParamLiteral(p, r, c, p in percept)))
    kb.tell(Fact(L('safe', r, c)))
    # kb.print_facts() # useful for debugging


################################ MAIN PROGRAM ################################


if __name__ == "__main__":
    print_unvisited = False  # Only print the tiles I`ve been to. Make True for debugging

    kb = KB()
    ww = WumpusWorld('mapa1.txt')  # TODO you can also try mapa2.txt for a bigger map
    kb_initialize(kb, ww)
    ww.play(choose_action_interactive, print_unvisited)
