even(0).
even(N) :- N > 0,
           N1 is N - 2,
           even(N1).