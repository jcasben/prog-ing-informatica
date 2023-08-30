*-----------------------------------------------------------
* Title      : ex_3b
* Written by : Jesus Castillo Benito
* Date       : 13/03/2023
* Description: Calculate P = A*B
*-----------------------------------------------------------
    ORG    $1000
    
A   EQU     3
B   EQU     4
P   DC.L    0

START:
    MOVE.L #B,D0
    SUBQ.L #1,D0
LOOP:
    ADDQ.L #A,P
    DBRA D0,LOOP              
    
    SIMHALT             ; halt simulator

* Put variables and constants here

    END    START        ; last line of source

*~Font name~Courier New~
*~Font size~10~
*~Tab type~1~
*~Tab size~4~
