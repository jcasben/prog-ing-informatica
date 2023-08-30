c = [(1/200);(1/75);(1/15);1;3;10;20;100];
b = [1;-5;-1;2;0;1;-1;-2];
A = vander(c);
exact_sol = [-456547599068726817076076042748125/2091483846816079254458865491167776;
             489691412182659110718553121002503725/16731870774528634035670923929342208;
             -122708825438523411105646534380737534801/150586836970757706321038315364079872;
             1833550249147538501512000490399566061959/250978061617929510535063858940133120;
             -2161902481470101446989583995802788619381/107562026407684075943598796688628480;
             166070759287366506638891913539842214737/11408093709905886842502902679096960;
             -4198869670106420075327488729338627257/4278035141214707565938588504661360;
             8699606188343403635653696130082301/1568612885112059440844149118375832];

#---PROBLEMA 1---
#APARTADO A

disp("APARTADO A");
disp("");
disp(A);
disp("");

#APARTADO B

disp("APARTADO B");
disp("");
[A_g,b_g] = gauss_solver(A,b);
solb = upper_triangular_solver(A_g,b_g);
disp(A_g);
disp("");
disp(b_g);
disp("");
disp(solb);
disp("");

#APARTADO C

disp("APARTADO C");
disp("");
[A_gpc, b_gpc] = gpc_solver(A,b);
solc = upper_triangular_solver(A_gpc,b_gpc);
disp(A_gpc);
disp("");
disp(b_gpc);
disp("");
disp(solc);

#APARTADO D

disp("APARTADO D");
disp("");
[A_mx,b_mx,perm_mx] = gauss_max(A,b);
unordered_solution = upper_triangular_solver(A_mx,b_mx);
sold = reorder_solution(unordered_solution,perm_mx);
disp(A_mx);
disp("");
disp(b_mx);
disp("");
disp("Vector de ordenación");
disp(perm_mx);
disp("");
disp(sold);

#APARTADO E

disp("APARTADO E");
disp("Al verctor de ordenación podemos observar que las primeras 4 filas se han mantenido igual mientras que las filas 5, 6, 7 y 8 han invertido su orden.");

#APARTADO F

disp("APARTADO F");
disp("Error de Gauss");
err_g = calculate_error(exact_sol, solb);
disp(err_g);
disp("");

disp("Error de Gauss Por Columnas");
err_gpc = calculate_error(exact_sol, solc);
disp(err_gpc);
disp("");

disp("Error de Gauss Maximal");
err_mx = calculate_error(exact_sol, sold);
disp(err_mx);
disp("");

#---PROBLEMA 2---
c2 = [100;20;10;3;1;1/15;1/75;1/200];
b2 = [-2;-1;1;0;2;-1;-5;1];
A2 = vander(c2);

#APARTADO A

disp("APARTADO A");
disp("");
[A2_g,b2_g] = gauss_solver(A2,b2);
solb2 = upper_triangular_solver(A2_g,b2_g);
disp("");
disp(solb2);

disp("Resultado 2 por columanas");
[A2_gpc, b2_gpc] = gpc_solver(A2,b2);
solc2 = upper_triangular_solver(A2_gpc,b2_gpc);

disp(solc2);

disp("Resultado 2 maximal");
[A2_mx,b2_mx,perm2_mx] = gauss_max(A2,b2);
unordered_solution2 = upper_triangular_solver(A2_mx,b2_mx);
sold2 = reorder_solution(unordered_solution2,perm2_mx);
disp("");
disp(sold2);

#APARTADO B

err2_g = calculate_error(exact_sol,solb2);
disp(err2_g);
disp("");
err2_gpc = calculate_error(exact_sol,solc2);
disp(err2_gpc);
disp("");
err2_mx = calculate_error(exact_sol,sold2);
disp(err2_mx);
