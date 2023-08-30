%% Problema 1 ---------------------------

%% APARTADO 1
c = [(1/79);(1/50);(1/10);2;7;10;23;31];
b = [-1;1;-1;5;-7;-1;1;2];

A =  vander(c);

disp("La matriz A es: ");
disp(A);

disp("El término independiente es:");
disp(b);

%% APARTADO 2

[L1, U1] = LUFact(A);

disp("EL resultado de A = LU es : ");

disp("La matriz A es:");
disp(A);
disp("");
disp("la matriz L es:");
disp(L1);
disp("");
disp("la matriz U es:");
disp(U1);

[L2, U2, P2] = LUFactPC(A);

disp("La matriz P es:");
disp(P2);
disp("La matriz A es:");
disp(A);
disp("");
disp("la matriz L es:");
disp(L2);
disp("");
disp("la matriz U es:");
disp(U2);

[L3, U3, P3, Q3] = LUFactPMX(A);

disp("La matriz P es:");
disp(P2);
disp("");
disp("La matriz A es:");
disp(A);
disp("");
disp("la matriz Q es:");
disp(Q3);
disp("");
disp("la matriz L es:");
disp(L3);
disp("");
disp("la matriz U es:");
disp(U3);

