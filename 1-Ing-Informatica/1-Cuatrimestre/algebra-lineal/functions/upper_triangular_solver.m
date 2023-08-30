function [X0] = upper_triangular_solver(A,b)
 n=length(b);
 X0 = zeros(n, 1);

 X0(n, 1)=b(n)/A(n,n);
 for i=n-1:-1:1
    N=b(i);
    for j=i+1:n
       N=N-A(i,j)*X0(j);
    endfor
    X0(i, 1)=N/A(i,i);
 endfor
endfunction
