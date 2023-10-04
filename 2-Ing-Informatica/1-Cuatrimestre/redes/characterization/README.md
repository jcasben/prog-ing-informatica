# Characterization

A simple CLI tool that is able to calculate the characterizations of 
a font and its coding given the probabilities and the length of its 
symbols.

This program has been created for the subject "Comunicación de Datos
y Redes" from UIB.

### Constraints
- Must receive 2 params separated by a space: 
   - float numbers that represent the probabilities, separated 
    by commas.
   - integer numbers that represent the length of the symbols.
   - the order of the elements must be the same at the 2 params.
- The parameters' length must be the same.

### Use Example

To use this tool, you have to open a terminal at the folder where the
executable is saved.

This is an example of how we could use the tool:
```cmd
characterization.exe 0.2,0.15,0.05,0.15,0.45 3,3,3,3,1
```

The output would be the following:
```cmd
-- FONT CHARACTERIZATION --
S = 5
H(x) = 2.020
-- CODE CHARACTERIZATION --
k = 1.000
L = 2.100
n = 0.962
```