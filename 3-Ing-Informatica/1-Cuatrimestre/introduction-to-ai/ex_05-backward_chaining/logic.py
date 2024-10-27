class Literal:
    # Simple literal has a name and truth value, e.g. wumpus_dead == true
    def __init__(self, name, is_true):
        if not isinstance(name, str):
            raise TypeError('Name of Literal (i.e. logical variable in literal) must be string!')
        self.name = name
        self.is_true = is_true

    def __eq__(self, other):
        if isinstance(other, self.__class__):
            return self.__dict__ == other.__dict__
        if (other is True) or (other is False):
            return other == self.is_true
        return NotImplemented

    def __ne__(self, other):
        if isinstance(other, self.__class__):
            return not self.__eq__(other)
        if (other is True) or (other is False):
            return other != self.is_true
        return NotImplemented

    def __bool__(self):
        return self.is_true
    __nonzero__ = __bool__

    def __neg__(self):
        return Literal(self.name, not self.is_true)

    def __str__(self):
        return ('-' if not self else '') + self.name

    def __repr__(self): return str(self)


class ParamLiteral(Literal):
    # Parametrized literal takes (x,y) position as a parameters, e.g. wumpus_at(3,5) == false
    def __init__(self, name, x, y, is_true):
        Literal.__init__(self, name, is_true)
        self.x = x
        self.y = y

    def __neg__(self):
        return ParamLiteral(self.name, self.x, self.y, not self.is_true)

    def __str__(self):
        return ('-' if not self else '') + '{}({},{})'.format(self.name, self.x, self.y)


# Convenience functions for faster writing of Literals
def L(literal_name, x=None, y=None):
    if x is None or y is None:
        return Literal(literal_name, True)
    else:
        return ParamLiteral(literal_name, x, y, True)


def NOT(literal_name, x=None, y=None):
    return -L(literal_name, x, y)


class Implication:
    # Implication takes:
    #   list of premises ( list[Literal] ), and one conclusion (Literal)
    #   -- or --
    #   several premises (Literal) and one conclusion (Literal)
    def __init__(self, *prems_concl):
        if isinstance(prems_concl[0], list):
            self.premises = prems_concl[0]
        else:
            self.premises = prems_concl[:-1]
        self.conclusion = prems_concl[-1]

    def __str__(self):
        return '{} => {}'.format(' ^ '.join(map(str, self.premises)), str(self.conclusion))

    def __repr__(self): return str(self)

    def __eq__(self, other):
        if isinstance(other, self.__class__):
            return self.__dict__ == other.__dict__
        return NotImplemented

    def __ne__(self, other):
        if isinstance(other, self.__class__):
            return not self.__eq__(other)
        return NotImplemented


class Fact(Implication):
    # Fact only takes one Literal. (It`s basically implication with no premises)
    def __init__(self, fact_literal):
        Implication.__init__(self, fact_literal)

    def __str__(self):
        return str(self.conclusion)
