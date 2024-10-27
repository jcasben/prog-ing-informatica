from logic import *

def backward_chaining(goal, kb, inferred=None):
    # Store inferred literals to avoid loop
    if inferred is None:
        inferred = set()

    inf_goal = (goal.name, goal.is_true)

    # Check if the goal is in the kb as a fact
    for clause in kb.clauses:
        if isinstance(clause, Fact) and clause.conclusion == goal:
            return True

    # If already checked that goal, return False
    if inf_goal in inferred:
        return False

    # Add goal to the inferred ones
    inferred.add(inf_goal)

    # Check for all the rules where the conclusion is the same as the goal
    for clause in kb.find_clauses_with_conclusion(goal):
        if isinstance(clause, Implication):
            for premise in clause.premises:
                if backward_chaining(premise, kb, inferred):
                    return True

    return False


class KB:
    #  Representation of whole knowledge base
    def __init__(self):
        self.clauses = []

    def __str__(self):
        return 'Knowledge base:\n{}\n'.format('\n'.join(['\t'+str(c) for c in self.clauses]))

    def print_facts(self):
        print('Facts:\n{}\n'.format('\n'.join(['\t'+str(c) for c in self.clauses if len(c.premises) == 0])))

    def tell(self, clause):
        # Tell sentence (Implication/Fact) into KB
        if not isinstance(clause, Implication):
            raise TypeError('You can only tell clauses (Implication/Fact)! You passed: {}'
                            .format(type(clause).__name__))
        if clause not in self.clauses:
            self.clauses.append(clause)

    def find_clauses_with_conclusion(self, conclusion):
        # Find all clauses with given conclusion (Literal), sort them Facts first
        return sorted([c for c in self.clauses if c.conclusion == conclusion], key=lambda c: len(c.premises))

    def ask(self, goal_literal):
        # Backward-chaining
        if not isinstance(goal_literal, Literal):
            raise TypeError('You can only ask Literal! You asked: {}'
                            .format(type(goal_literal).__name__))

        # Performing Backward-chaining
        result = backward_chaining(goal_literal, self)

        if result:
            self.tell(Fact(goal_literal))  # Caching the result for future re-use

        return result


if __name__ == "__main__":
    # Task 1 - simple inference
    kb = KB()
    kb.tell(Implication(NOT('P'), L('Q')))            # -P => Q
    kb.tell(Implication(NOT('C'), L('P')))            # -C => P
    kb.tell(Implication(NOT('K'), L('M'), NOT('P')))  # -K ^ M => -P
    kb.tell(Implication(L('B'), NOT('K'), L('M')))    # B ^ -K => M
    kb.tell(Implication(L('A'), L('B'), NOT('K')))    # A ^ B => -K
    kb.tell(Fact(L('A')))
    kb.tell(Fact(L('B')))
    kb.tell(Fact(L('C')))
    print('Simple inference True: ', kb.ask(L('Q')))
    print('Simple inference False:', kb.ask(L('P')))

    # Task 2 - inference with cyclic rules
    kb.tell(Implication(L('Y'), L('X')))  # Y => X
    kb.tell(Implication(L('X'), L('Y')))  # X => Y
    kb.tell(Implication(L('Y'), L('Z')))  # Y => Z
    print('Cyclic rules False:', kb.ask(L('Z')))  # False
    kb.tell(Implication(L('B'), L('X')))  # B => X
    print('Cyclic rules True: ', kb.ask(L('Z')))  # True

    ### MORE EXAMPLES ###
    '''
    wum_dead = Literal('wumpus_dead', L)  # Literal whose truth value is L
    wum_dead = L('wumpus_dead')  # The same thing, just faster to write
    not_wum_dead = -wum_dead  # Negation - truth value of not_wum_dead is False
    print(wum_dead, ',', not_wum_dead)

    breeze_at_position_5_7 = ParamLiteral('breeze', 5, 7, L)  # Parametrized literal
    breeze_at_position_5_7 = L('breeze', 5, 7)  # The same param. literal

    if wum_dead:
        print('It`s safe to test literals in "if" - it checks its truth value')
    if not_wum_dead:
        pass
    else:
        print('Truth value of not_wum_dead is False')

    if L('abc') == -NOT('abc'):
        print('It`s safe to "==" and "!=" literals')
    if NOT('wumpus_at', 3, 5) == ParamLiteral('wumpus_at', 3, 5, False):
        print(
            '"L(...)" and "NOT(...)" are just convenience functions, '
            'you can use full constructors for Literal and ParamLiteral')

    # Clauses/sentences
    c1 = Fact(wum_dead)  # This is a simple fact, constructor takes one Literal
    c2 = Fact(L('breeze', 5, 7))  # Another simple fact
    c3 = Implication(L('wumpus_dead'), L('wumpus_at', 3, 5),  # Implication takes several premises (Literals),
                     L('safe', 3, 5)  # and one conclusion (Literal)
                     )
    c4 = Implication(NOT('wumpus_at', 3, 5), NOT('pit', 3, 5), L('safe', 3, 5))  # Another implication
    c4 = Implication([NOT('wumpus_at', 3, 5), NOT('pit', 3, 5)], L('safe', 3, 5))  # The same thing

    list_of_premises = c4.premises  # You can access premises...
    conclusion = c4.conclusion  # ...or conclusion

    # Knowledge base
    kb = KB()
    kb.tell(c1)
    kb.tell(c2)
    kb.tell(c3)
    kb.tell(c4)  # Put stuff to KB
    print(kb.ask(wum_dead))  # Ask the KB for Literals, not Facts or Implications
    print('\n', kb)
    print(kb.find_clauses_with_conclusion(L('safe', 3, 5)))  # Find appropriate clauses
    '''
