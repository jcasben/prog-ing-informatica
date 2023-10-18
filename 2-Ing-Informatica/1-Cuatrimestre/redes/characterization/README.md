# Characterization

A simple CLI tool that is able to calculate the characterizations of 
a font and its coding given the probabilities and the length of its 
symbols.

This program has been created for the subject "Comunicación de Datos
y Redes" from UIB.

## Constraints
- Must receive 2 params separated by a space: 
   - float numbers that represent the probabilities, separated 
    by commas.
   - integer numbers that represent the length of the symbols.
   - the order of the elements must be the same at the 2 params.
- The parameters' length must be the same.

## Use Example

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

***

> NOTE: if we want to calculate, for example, the Entropy of the font
> but we don't have the lengths of the symbols, we can still do it 
> putting trivial numbers instead of the lengths.
> The number of probabilities still has to be the same as the number
> of lengths.

#### An example of this case:

We have to calculate the Entropy of our font, and we know that the
probabilities are p(x1) = 0.35, p(x2) = 0.15, p(x3) = 0.5. We don't
know the lengths of the coding, but we don't have to calculate
anything relational with it. So we could use the program this way:

```cmd
characterization.exe 0.35,0.15,0.5 1,1,1
```

And our output would be:
```cmd
-- FONT CHARACTERIZATION --
S = 3
H(x) = 1.441
-- CODE CHARACTERIZATION --
k = 1.500
L = 1.000
n = 1.441
```

In this output, the only value that we are interested in is H(x).