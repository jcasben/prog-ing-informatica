function [A,b,perm]=gauss_max(A,b)
 n=length(b);
 perm = 1:n;
 for k=1:n-1

   mx = k;
   my = k;
   for i = k:n
     for j = k:n
       if abs(A(i,j)) > abs (A(mx,my))
         mx = i;
         my = j;
       endif
     endfor
   endfor

   #Permute rows k and mx
   for p = 1:n
     temp = A(k,p);
     A(k,p) = A(mx,p);
     A(mx,p) = temp;
   endfor

   temp = b(k);
   b(k) = b(mx);
   b(mx) = temp;

   #Permute rows k and my
   for p = 1:n
     temp = A(p,k);
     A(p,k) = A(p,my);
     A(p,my) = temp;
   endfor

     temp = perm(k);
     perm(k) = perm (my);
     perm(my) = temp;

  #Gauss solver
  for i=k+1:n
    m=A(i,k)/A(k,k);
    A(i,k)=0;
    for j=k+1:n
      A(i,j)=A(i,j)-m*A(k,j);
    endfor
    b(i)=b(i)-m*b(k);
  endfor
 endfor
endfunction
