%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% SQL-like output of a predicate, without duplicates
% (c) 2007, Tomas Plachetka
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% In shell, enter q(predicate(_, _, _)), with an
% appropriate number of underscores.

q(Pred) :-
	term_variables(Pred, Vlist),
	time(setof(Vlist, Pred, L)),
	writelist(L),
	length(L, Count),
	write(Count),
	write(' tuples.\n'),
	fail.

writelist([]).
writelist([H | T]) :-
	write(H),
	write('\n'),
	writelist(T).