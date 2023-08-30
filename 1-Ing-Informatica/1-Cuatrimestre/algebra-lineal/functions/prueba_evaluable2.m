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

disp(A + " = " + L1 + "*" + U1);


