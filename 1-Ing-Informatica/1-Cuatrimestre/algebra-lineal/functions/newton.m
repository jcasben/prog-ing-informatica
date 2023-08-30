##Función que desarrolla el método de Newton para aproximar los ceros de la función deseada y que devuelve dos vectores columna
##que servirán para dibujar con plot() una vista de los pasos que se han relizado para aproximar los ceros según el método de
##Newton.

function[x,y,steps] = newton(eps,nmax,initial_point)

  iterations = [initial_point];

  iteration = 1;
  iteration_norm = Inf;
  iteration_vector = initial_point;
  n = 5;
  matr_iteration = zeros(n,nmax);
  matr_iteration(:,1) = initial_point;

  while (iteration < nmax && iteration_norm > eps)

    A = evalua_jacobiana(iteration_vector);
    b = evalua_funcion(iteration_vector);

    [L,U,P,Q] = LUFactPMX(A);
    t = under_triangular_solver(L,-P*b);
    sol = Q*upper_triangular_solver(U,t);

    iteration = iteration + 1;
    iteration_norm = norm(sol, "inf")

    iteration_vector = iteration_vector+sol;
    matr_iteration(:,iteration) = iteration_vector;

  endwhile

  Z = matr_iteration(:, 1:iteration);
  steps = iteration-1;

  x = Z(1,:);
  y = Z(2,:);

  if (iteration >= nmax && iteration_norm > eps)
    disp("Convergence is not reached.\n")
  else
    disp("Convergence reached. Solution is:\n")
    disp(iteration_vector)
  endif
endfunction
