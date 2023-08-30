A = [1/10^5, 1/10^4, 1/10^3, 1/10^2, 1/10, 1;
     1/2^5, 1/2^4, 1/2^3, 1/2^2, 1/2, 1;
     1/10000^5, 1/10000^4, 1/10000^3, 1/10000^2, 1/10000, 1;
     10^5, 10^4, 10^3, 10^2, 10, 1;
     100^5, 100^4, 100^3, 100^2, 100, 1;
     1000^5, 1000^4, 1000^3, 1000^2, 1000, 1];

b = [1; 1; 0; 1; 1; 0];

exact_sol = [199880029976793242200/9981899896193239895882001;
             -1233259785019090723703579/55454999423295777199344450;
             55247062927301712819753883/24707673010379306672975250;
             -2110922112147143999843521631/98830692041517226691901000;
             4034578405307821825231360507/332729996539774663196066700;
             -12101603206909988084210/9981899896193239895882001];

#APARTADO A

disp("APARTADO A");
disp("");

[A_gauss, b_gauss] = gauss_solver(A,b);
sol = upper_triangular_solver(A_gauss, b_gauss);

disp(sol);
disp("");

#APARTADO B

disp("APARTADO B");
disp("");

[A_gpc,b_gpc] = gpc_solver(A,b);
sol2 = upper_triangular_solver(A_gpc,b_gpc);

disp(sol2);
disp("");

#APARTADO C

disp("APARTADO C");

[A_mx,b_mx,perm_mx] = gauss_max(A,b);
unordered_solution = upper_triangular_solver(A_mx,b_mx);
sol3 = reorder_solution(unordered_solution,perm_mx);
disp(sol3);


