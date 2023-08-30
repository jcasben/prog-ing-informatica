*-----------------------------------------------------------
* Title      : ex1b
* Written by : Jesus Castillo Benito
* Date       : 27/02/2023
* Description:  C.W = A.W + B.W, D.W = A.W - B.W
*-----------------------------------------------------------
    ORG    $1000
    
A   DC.W   40
B   DC.W   13
C   DS.W   1
D   DS.W   1
    
START:                  ; first instruction of program

    MOVE.W A,D0
    ADD.W B,D0
    MOVE.W D0,C
    
    MOVE.W A,D0
    SUB.W B,D0
    MOVE.W D0,D

    SIMHALT             ; halt simulator

* Put variables and constants here

    END    START        ; last line of source

*~Font name~Courier New~
*~Font size~10~
*~Tab type~1~
*~Tab size~4~
