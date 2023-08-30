function [X0] = under_triangular_solver (A,b)
  n = length(b);
  X0 = zeros(n,1);

  X0(1,1) = b(1)/A(1,1);
  for i = 2:n
    N = b(i);
    for j = 1:i-1
      N = N-A(i,j)*X0(j);
    endfor
    X0(i) = N/A(i,i);
  endfor
endfunction
