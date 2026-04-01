son(pep, pere).
son(lluis, pere).
father(X, Y) :- son(Y, X).
siblings(X, Y) :- father(Z, X), father(Z, Y).