A = [4,1,2;2,4,-1;1,2,-3];
b = [0;0;0];

[B,z] = gauss_solver(A,b);

disp(B);
disp(z);

