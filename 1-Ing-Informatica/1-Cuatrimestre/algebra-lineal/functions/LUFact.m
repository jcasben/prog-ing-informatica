function [L,U]=LUFact(A)
 n=length(A(:,1));
 m=length(A(1,:));
 if m != n
  printf("different number of rows and columns");
  return;
 endif
 L=eye(n);
 U=A;
 for k=1:n-1
  for i=k+1:n
    L(i,k)=U(i,k)/U(k,k);
    U(i,k)=0;
    for j=k+1:n
      U(i,j)=U(i,j)-L(i,k)*U(k,j);
    endfor
  endfor
 endfor
endfunction

