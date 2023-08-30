function [L,U,P,Q]=LUFactPMX(A)
 n=length(A(:,1));
 m=length(A(1,:));
 if m != n
  printf("different number of rows and columns");
  return;
 endif
 L=zeros(n);
 U=A;
 P=eye(n);
 Q=eye(n);

 for k=1:n-1

   mx = k;
   my = k;
   for i = k+1:n
     for j = k:n
       if abs(U(i, j)) > abs(U(mx, my))
          mx = i;
          my = j;
       endif
     endfor
  endfor

  %Cambiar filas k y mx, y columnas k y my
   temp = U(k,:);
   U(k,:) = U(mx,:);
   U(mx,:) = temp;
   temp = U(:,k);
   U(:,k) = U(:,my);
   U(:,my) = temp;

   temp = P(k,:);
   P(k,:) = P(mx,:);
   P(mx,:) = temp;

   temp = L(k,:);
   L(k,:) = L(mx,:);
   L(mx,:) = temp;
   temp = L(:,k);
   L(:,k) = L(:,my);
   L(:,my) = temp;

   temp = Q(:,k);
   Q(:,k) = Q(:,my);
   Q(:,my) = temp;

  for i=k+1:n
    L(i,k)=U(i,k)/U(k,k);
    U(i,k)=0;
    for j=k+1:n
      U(i,j)=U(i,j)-L(i,k)*U(k,j);
    endfor
  endfor
 endfor
 L = L + eye(n);
endfunction
