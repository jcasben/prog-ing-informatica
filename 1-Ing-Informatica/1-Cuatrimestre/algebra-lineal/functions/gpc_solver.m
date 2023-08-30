function [A,b] = gpc_solver(A,b)
  n = length(b);

  for row = 1:n-1
    idx_max = row;
    for idx = row+1:n
      if abs(A(idx,row)) > abs(A(idx_max,row))
        idx_max = idx;
      endif
    endfor

    %Permute row and idx_max
      for p = 1:n
        temp = A(row,p);
        A(row,p) = A(idx_max,p);
        A(idx_max,p) = temp;
      endfor

      temp = b(row);
      b(row) = b(idx_max);
      b(idx_max) = temp;

      %Gauss solver
      for row2=row+1:n
        N=A(row2,row)/A(row,row);
        A(row2,row)=0;
        for j=row+1:n
          A(row2,j)=A(row2,j)-N*A(row,j);
        endfor
        b(row2)=b(row2)-N*b(row);
      endfor

  endfor
endfunction
