factorialTR(0, A, A).
factorialTR(N, R, AC) :- N1 is N - 1,
    					 AC1 is AC * N,
    					 factorialTR(N1, R, AC1).