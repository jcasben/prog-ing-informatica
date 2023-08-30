A = [1,3,0,9;3,9,1,3;6,3,1,3;2,5,2,7];
b = [1;2;3;4];

disp(A);
disp("");
disp("********************");
disp("");
disp(b);

[A_gauss, b_gauss] = gauss_solver(A,b);
sol = upper_triangular_solver(A_gauss, b_gauss);

disp("");
disp("********************");
disp("");
disp(sol);

[A_gpc,b_gpc] = gpc_solver(A,b);
sol2 = upper_triangular_solver(A_gpc,b_gpc);

disp("");
disp("********************");
disp("");
disp(sol2);
