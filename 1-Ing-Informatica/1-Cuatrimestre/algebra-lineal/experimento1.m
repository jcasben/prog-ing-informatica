##Declaración de los epsilon

e1 = 10^-4;
e2 = 10^-6;
e3 = 10^-8;

##Declaración de los instantes iniciales

i1 = [0.1;-0.6;0;0;-0.7];
i2 = [0.5;0.1;-0.5;-0.5;-0.3];


## ----------- Experimento 1 ----------

%Función que utiliza ele método de Newton para aproximar los ceros de la función deseada (ver newton.m)
[x1,y1,steps1] = newton(e1,25,i1);
%Utiliza los vectores columna devueltos por la función para realizar el plot()
plot(x1,y1, 'o-r');

## ---------- Experimento 2 ----------

%Función que utiliza ele método de Newton para aproximar los ceros de la función deseada (ver newton.m)
##[x2,y2] = newton(e2,25,i1);
%Utiliza los vectores columna devueltos por la función para realizar el plot()
##plot(x2,y2);
##

## ---------- Experimento 3 ----------

%Función que utiliza ele método de Newton para aproximar los ceros de la función deseada (ver newton.m)
##[x3,y3] = newton(e3,25,i1);
%Utiliza los vectores columna devueltos por la función para realizar el plot()
##plot(x3,y3);
##

## ---------- Experimento 4 ----------

%Función que utiliza ele método de Newton para aproximar los ceros de la función deseada (ver newton.m)
##[x4,y4] = newton(e1,25,i2);
%Utiliza los vectores columna devueltos por la función para realizar el plot()
##plot(x4,y4);


##---------- Experimento 5 ----------

%Función que utiliza ele método de Newton para aproximar los ceros de la función deseada (ver newton.m)
##[x5,y5] = newton(e2,25,i2);
##plot(x5,y5);


## ---------- Experimento 6 ----------

%Función que utiliza ele método de Newton para aproximar los ceros de la función deseada (ver newton.m)
##[x6,y6] = newton(e3,25,i2);
##plot(x6,y6);
