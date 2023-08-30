function [A,b] = gauss_pivotaje (A,b)
  n = length(b);

  for j = 1:n-1
    i_max = j;
    for i = j+1:n
      if (A(i,j))>(A(i_max,j))
        i_max = i;
      endif
      for K = j:n
        v = A(j,k);
        A(j,k) = A(i_max,k);
        A(i_max,k) = v;
      endfor
      v = b(j);
      b(j) = b(i_max);
      b(i_max) = v;
    endfor
  endfor
endfunction
