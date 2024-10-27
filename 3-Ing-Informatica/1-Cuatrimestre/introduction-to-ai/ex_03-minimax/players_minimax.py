import sys
import random

from scipy.stats import alpha

from games import TicTacToe, Gomoku

infinity = 0
try:
    infinity = sys.maxint
except:
    infinity = sys.maxsize


class Player:
    def choose_move(self, game, state):
        raise NotImplementedError


class AskingPlayer(Player):
    def choose_move(self, game, state):
        # Asks user (human) which move to take. Useful for debug.
        actions = game.actions(state)
        action = None
        while True:
            print("Choose one of the following positions: {}".format(actions))
            game.display_state(state, True)
            inp = input('> ')
            try:
                action = int(inp)
            except ValueError:
                pass
            if action in actions:
                return action
            print('"{}" is not valid action!'.format(inp))


class RandomPlayer(Player):
    def choose_move(self, game, state):
        # Picks random move from list of possible ones.
        return random.choice(game.actions(state))


class MinimaxPlayer(Player):
    def choose_move(self, game, state):
        # # # Task 1 # # #
        my_player = game.player_at_turn(state)  # get 'X' or 'O'

        def max_value(state):
            if game.is_terminal(state):
                return game.utility(state, my_player)

            v = -infinity
            for a in game.actions(state):
                v = max(v, min_value(game.state_after_move(state, a)))

            return v

        def min_value(state):
            if game.is_terminal(state):
                return game.utility(state, my_player)

            v = infinity
            for a in game.actions(state):
                v = min(v, max_value(game.state_after_move(state, a)))

            return v

        best_action = None
        best_value = -infinity

        # Check all possible actions
        for action in game.actions(state):
            value = min_value(game.state_after_move(state, action))
            # Check if the actual action produces better value than the previous one
            if value > best_value:
                best_value = value
                best_action = action

        return best_action


class AlphaBetaPlayer(Player):
    def choose_move(self, game, state):
        # # # Task 2 # # #
        my_player = game.player_at_turn(state)  # get 'X' or 'O'

        def max_value(state, alpha, beta):
            if game.is_terminal(state):
                return game.utility(state, my_player)

            v = -infinity
            for a in game.actions(state):
                v = max(v, min_value(game.state_after_move(state, a), alpha, beta))
                if v >= beta:
                    return v

                alpha = max(alpha, v)

            return v


        def min_value(state, alpha, beta):
            if game.is_terminal(state):
                return game.utility(state, my_player)

            v = infinity
            for a in game.actions(state):
                v = min(v, max_value(game.state_after_move(state, a), alpha, beta))
                if v <= alpha:
                    return v

                beta = min(beta, v)

            return v

        value = max_value(state, -infinity, infinity)
        target_action = None

        for act in game.actions(state):
            if min_value(game.state_after_move(state, act), -infinity, infinity) == value:
                target_action = act
                break

        return target_action


# PRODUCES BIG EXECUTION TIME
class AlphaBetaEvalPlayer(Player):
    def choose_move(self, game, state):
        # # # Task 3  # # #
        my_player = game.player_at_turn(state)
        opponent = game.other_player(my_player)

        def evaluate(state):
            if game.is_terminal(state):
                return game.utility(state, my_player)

            board = game.board_in_state(state)
            size = len(board)

            my_score = 0
            opponent_score = 0
            directions = [(1, 0), (0, 1), (1, 1), (1, -1)]  # All directions

            for x in range(size):
                for y in range(size):
                    if board[x][y] == my_player:
                        # Verify neighbours in each direction for my_player
                        for dx, dy in directions:
                            nx, ny = x + dx, y + dy
                            if 0 <= nx < size and 0 <= ny < size and board[nx][ny] == my_player:
                                my_score += 1
                    elif board[x][y] == opponent:
                        # Verify neighbours in each direction for opponent
                        for dx, dy in directions:
                            nx, ny = x + dx, y + dy
                            if 0 <= nx < size and 0 <= ny < size and board[nx][ny] == opponent:
                                opponent_score += 1

            # Compare score and return value between -1, 0 and 1
            if my_score > opponent_score:
                return 1
            elif my_score < opponent_score:
                return -1
            else:
                return 0


        def max_value(state, alpha, beta, depth):
            if depth == 0 or game.is_terminal(state):
                return evaluate(state)

            v = -infinity
            for a in game.actions(state):
                v = max(v, min_value(game.state_after_move(state, a), alpha, beta, depth - 1))
                if v >= beta:
                    return v

                alpha = max(alpha, v)

            return v


        def min_value(state, alpha, beta, depth):
            if depth == 0 or game.is_terminal(state):
                return evaluate(state)

            v = infinity
            for a in game.actions(state):
                v = min(v, max_value(game.state_after_move(state, a), alpha, beta, depth - 1))
                if v <= alpha:
                    return v

                beta = min(beta, v)

            return v


        best_action = None
        best_value = -infinity

        alpha = -infinity
        beta = infinity

        # Check all possible actions
        for action in game.actions(state):
            value = min_value(game.state_after_move(state, action), alpha, beta, 2)
            # Check if the actual action produces better value than the previous one
            if value > best_value:
                best_value = value
                best_action = action

        return best_action



if __name__ == '__main__':
    # # Print all moves of the game? Useful for debugging, annoying if it`s already working.
    show_moves = True

    # # Task 1
    # print('MiniMax plays as O and goes second - O must win or draw:')
    # TicTacToe().play([RandomPlayer(), MinimaxPlayer()], show_moves)
    #
    # print('\n\nMiniMax plays as X and goes first - X must win or draw:')  # might take some extra time (max. cca 20s)
    # TicTacToe().play([MinimaxPlayer(), RandomPlayer()], show_moves)
    #
    # print('\n\nMiniMax vs. MiniMax - should be draw:')
    # TicTacToe().play([MinimaxPlayer(), MinimaxPlayer()], show_moves)  # might take some extra time (max. cca 20s)

    # Task 2
    # print('\n\nAlpha-Beta plays as X and goes first - X must win or draw:')
    # TicTacToe().play([AlphaBetaPlayer(), RandomPlayer()], show_moves)
    #
    # print('\n\nAlpha-Beta vs. MiniMax - should be draw:')
    # TicTacToe().play([AlphaBetaPlayer(), MinimaxPlayer()], show_moves)

    # # Task 3
    print('\n\nAlpha-Beta Eval vs. itself - should be a well-played game.')
    Gomoku().play([AlphaBetaEvalPlayer(), AlphaBetaEvalPlayer()], show_moves=True)

    # # # Play computer against human:
    # # # a) human cannot win, draw is possible (assuming algorithm is correct)
    # TicTacToe().play([AskingPlayer(), MinimaxPlayer()])   # Task 1
    # TicTacToe().play([MinimaxPlayer(), AskingPlayer()])   # Task 1
    # TicTacToe().play([AskingPlayer(), AlphaBetaPlayer()]) # Task 2
    # TicTacToe().play([AlphaBetaPlayer(), AskingPlayer()]) # Task 2
    # ## b) computer will win, human will loose, draw is not possible
    # TicTacToe(4,4,3).play([AlphaBetaPlayer(), AskingPlayer()]) # Task 2
    # ## c) human, have fun! (recommended depth limit=2~3)
    # Gomoku().play([AskingPlayer(), AlphaBetaEvalPlayer()]) # Task 3
