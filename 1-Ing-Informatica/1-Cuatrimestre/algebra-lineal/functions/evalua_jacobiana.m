function [J] = evalua_jacobiana(x)

  J = [2,3,4,0,-1;0,2*x(2),0,-1,-1;0,1,-3,3,-1;1,-1,0,0,1;2*x(1),2*x(2),2*x(3),2*x(4),2*x(5)];
endfunction
