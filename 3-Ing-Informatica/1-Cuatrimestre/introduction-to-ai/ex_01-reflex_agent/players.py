import random
from games import TicTacToe, Gomoku


# # PLAYERS

class Player:
    def choose_move(self, game, state):
        raise NotImplementedError


class AskingPlayer(Player):
    def choose_move(self, game, state):
        # Asks user (human) which move to take. Useful for debug.
        actions = game.actions(state)
        print("Choose one of the following positions: {}".format(actions))
        game.display_state(state, True)
        return int(input('> '))


class RandomPlayer(Player):
    def choose_move(self, game, state):
        # Picks random move from list of possible ones.
        return random.choice(game.actions(state))


def check_terminating_action(game, state):
    # Checks is some of the possible actions produces the termination of the game
    # returns the action to perform to win / avoid loosing or -1 if there isn't any
    for action in game.actions(state):
        if game.is_terminal(game.state_after_move(state, action)):
            return action
    return -1


class MyPlayer(Player):
    def choose_move(self, game, state):
        my_player = game.player_at_turn(state)
        opponent = game.other_player(my_player)
        board = game.board_in_state(state)

        # Check if there is a winning action and return it if there is one
        winning_action = check_terminating_action(game, state)

        if winning_action != -1:
            return winning_action

        # Change the player on turn to the opponent to see if there is
        # any action that produces MyPlayer to lose the game.
        state['player_on_turn'] = opponent
        avoid_loosing_action = check_terminating_action(game, state)

        # Change the player on turn to MyPlayer to perform the avoid loosing action
        state['player_on_turn'] = my_player

        if avoid_loosing_action != -1:
            return avoid_loosing_action

        return random.choice(game.actions(state))


if __name__ == '__main__':
    # # Print all moves of the game? Useful for debugging, annoying if it`s already working.
    show_moves = True

    # # Play computer against human:
    # # a) with random player
    # TicTacToe().play([RandomPlayer(), AskingPlayer()], show_moves=show_moves)
    # # b) simple TicTacToe with MyPlayer
    # TicTacToe().play([MyPlayer(), AskingPlayer()], show_moves=show_moves)
    # # c) difficult Gomoku with MyPlayer
    # Gomoku().play([MyPlayer(), AskingPlayer()], show_moves=show_moves)

    # # Test MyPlayer
    # # a) play single game of TicTacToe
    # TicTacToe().play([MyPlayer(), RandomPlayer()], show_moves=show_moves)
    # # b) play single game of Gomoku
    # Gomoku().play([MyPlayer(), RandomPlayer()], show_moves=show_moves)
    # # c) play N games
    TicTacToe().play_n_games([MyPlayer(), RandomPlayer()], n=500)
    # Gomoku().play_n_games([MyPlayer(), RandomPlayer()], n=100)
