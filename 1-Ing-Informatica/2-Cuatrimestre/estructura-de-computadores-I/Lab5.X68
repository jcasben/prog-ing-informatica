*-----------------------------------------------------------
* Title      : Lab5
* Written by : Jesus Castillo Benito
* Date       : 27/03/2023
* Description: Move values from V1 to V2 in inverted order
*-----------------------------------------------------------
    ORG    $1000
N:    EQU 5
V1:   DC.W      10,-15,-100,89,-1
V2:   DS.W      N    

START:
    MOVE.L #0,A0
    LEA.L V2,A1
    MOVE.W #N,D2
    SUBQ.W #1,D2
LOOP:
    MOVE.W -(A1),D0
    NOT.W D0
    ADDQ.W #1,D0
    MOVE.W D0,V2(A0)
    ADDA.L #2,A0
    DBRA D2,LOOP


    SIMHALT             ; halt simulator


    END    START        ; last line of source

*~Font name~Courier New~
*~Font size~10~
*~Tab type~1~
*~Tab size~4~
