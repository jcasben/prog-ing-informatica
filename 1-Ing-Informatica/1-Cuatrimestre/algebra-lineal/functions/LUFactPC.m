function [L,U,P]=LUFactPC(A)
 n=length(A(:,1));
 m=length(A(1,:));
 if m != n
  printf("different number of rows and columns");
  return;
 endif
 L=zeros(n);
 U=A;
 P=eye(n)

 for k=1:n-1

   mx = k;
   for i = k+1:n
     if abs(U(i, k)) > abs(U(mx, k))
        mx = i;
     endif
   endfor

   temp = U(k,:);
   U(k,:) = U(mx,:);
   U(mx,:) = temp;

   temp = P(k,:);
   P(k,:) = P(mx,:);
   P(mx,:) = temp;

   temp = L(k,:);
   L(k,:) = L(mx,:);
   L(mx,:) = temp;

  for i=k+1:n
    L(i,k)=U(i,k)/U(k,k);
    U(i,k)=0;
    for j=k+1:n
      U(i,j)=U(i,j)-L(i,k)*U(k,j);
    endfor
  endfor
 endfor
endfunction
