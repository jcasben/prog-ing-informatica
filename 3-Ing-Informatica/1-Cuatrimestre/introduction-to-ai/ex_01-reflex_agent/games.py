import random
from time import time


# # GAME DEFINITION

class Game:
    def __init__(self):
        pass

    def initial_state(self):
        # Returns initial state of the game
        pass

    def players(self):
        # Returns list of players
        pass

    def actions(self, state):
        # Returns list of valid actions for given state of the game
        pass

    def is_terminal(self, state):
        # Returns Tru if state is terminal (game has finished)
        pass

    def utility(self, state, player):
        # Returns utility function for player in given state. Note that utility is non-zero only
        # in terminal states
        pass

    def state_after_move(self, state, move):
        # Execute move in given state of the game and return new state
        pass

    def player_at_turn(self, state):
        # Return which player is at turn in given state of the game
        pass

    def other_player(self, player):
        # Given a player, return the other player (opponent)
        pass

    def display_state(self, state):
        # Pretty-print given state of the game
        pass

    def play(self, players, show_moves=True):
        # Executes one match of the game. Returns index of winning player, or -1 if draw.
        assert len(players) == 2, 'Game must be played by exactly two players!'
        state = self.initial_state()
        if show_moves:
            self.display_state(state)
        while True:
            for i, player in enumerate(players):
                p_char = self.player_at_turn(state)
                # Choose move
                move = player.choose_move(self, state)
                # Make move
                state = self.state_after_move(state, move)
                if state is None:
                    print('Player "{}" made invalid move and lost'.format(p_char))
                    return 1 - i
                if show_moves:
                    self.display_state(state)
                # Check (normal) game termination
                if self.is_terminal(state):
                    p0 = self.players()[0]
                    p0_score = self.utility(state, p0)
                    if p0_score > 0:
                        print('Player "{}" won'.format(p0))
                        return 0
                    if p0_score < 0:
                        print('Player "{}" won'.format(self.other_player(p0)))
                        return 1
                    print("Draw")
                    return -1

    def play_n_games(self, players, n, can_break_limit=[False, False], show_moves=False):
        # Execute N games of two players. In each game order of the players is switched (i.e.
        # different player starts the game).
        results = [[0]*3, [0]*3]
        score1 = 0
        plrs, plr_chrs = players, self.players()
        for i in range(n):
            print('Game {}: player 1 is "{}", player 2 is "{}": '.format(i+1, *plr_chrs), end='')
            w = self.play(plrs, show_moves)
            winner = w if w < 0 or i%2==0 else 1-w  # switching-aware winner choice
            score1 += winner + 1 if winner < 1 else -1
            if winner == -1:
                results[0][1] += 1
                results[1][1] += 1
            else:
                results[winner][0] += 1
                results[1 - winner][2] += 1
            # Switch the order of two players
            plrs, plr_chrs = [list(reversed(x)) for x in (plrs, plr_chrs)]
        print('Results:')
        print('\tplayer 1: {} win, {} draw, {} lost, total score: {}'.format(*results[0], score1 / n))
        print('\tplayer 2: {} win, {} draw, {} lost, total score: {}'.format(*results[1], -score1 / n))


class TicTacToe(Game):
    def __init__(self, h=3, w=3, k=3):
        self.h = h
        self.w = w
        self.k = k
        self.fields = self.h*self.w
        self.win_unility = 1

    def initial_state(self):
        return {'player_on_turn': 'X',
                'board': [[' ']*self.w]*self.h,
                'utility': 0}

    def players(self):
        return ['X', 'O']

    def actions(self, state):
        return [self.rc_to_idx(r, c) for r in range(self.h) for c in range(self.w) if state['board'][r][c] == ' ']

    def is_terminal(self, state):
        return len(self.actions(state)) == 0 or \
            self.utility(state, state['player_on_turn']) != 0

    def rc_to_idx(self, r, c):
        return r*self.w + c

    def idx_to_rc(self, idx):
        return idx // self.w, idx % self.w

    def k_in_row(self, board, position, player, delta):
        dr, dc = delta
        n = 0
        r, c = self.idx_to_rc(position)
        while (0 <= r < self.h and
               0 <= c < self.w and
               board[r][c] == player):
            r, c = r + dr, c + dc
            n += 1

        r, c = self.idx_to_rc(position)
        while (0 <= r < self.h and
               0 <= c < self.w and
               board[r][c] == player):
            r, c = r - dr, c - dc
            n += 1

        n -= 1
        return n >= self.k

    def utility(self, state, player):
        utility = state['utility']
        return utility if player != state['player_on_turn'] else -utility

    def compute_utility(self, board, move, player):
        if (self.k_in_row(board, move, player, (1, 0)) or
                self.k_in_row(board, move, player, (0, 1)) or
                self.k_in_row(board, move, player, (1, 1)) or
                self.k_in_row(board, move, player, (1, -1))):
            return self.win_unility
        return 0

    def state_after_move(self, state, move):
        if move not in self.actions(state):
            raise ValueError("Invalid move")

        player = state['player_on_turn']
        board = [list(row) for row in state['board']]
        r, c = self.idx_to_rc(move)
        board[r][c] = player
        return {'player_on_turn': self.other_player(player),
                'board': board,
                'utility': self.compute_utility(board, move, player)}

    def player_at_turn(self, state):
        return state['player_on_turn']

    def board_in_state(self, state):
        return state['board']

    def other_player(self, player):
        return 'X' if player == 'O' else 'O'

    def display_state(self, state, show_nums=False):
        board = state['board']
        pos = 0
        dig = len(str(len(self.actions(state))))
        for r in range(self.h):
            print('|', end='')
            for c in range(self.w):
                ch = board[r][c]
                print('{:^{dig}}'.format((pos if show_nums and ch==' ' else ch), dig = dig), end='|')
                pos += 1
            print()
        if not show_nums: print()


class Gomoku(TicTacToe):
    def __init__(self):
        TicTacToe.__init__(self, 15, 15, 5)
