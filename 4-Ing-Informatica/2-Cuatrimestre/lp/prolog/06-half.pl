half(N, R) :- half(N, R, 0).

half(0, A, A).
half(1, A, A).

half(N, R, AC) :- N > 1,
                  ACC is AC + 1,
                  N1 is N - 2,
                  half(N1, R, ACC).