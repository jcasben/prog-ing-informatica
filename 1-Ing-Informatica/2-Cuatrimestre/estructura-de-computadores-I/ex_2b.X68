*-----------------------------------------------------------
* Title      : ex_2b
* Written by : Jesus Castillo Benito
* Date       : 06/03/2023
* Description: set C.W to 1 if A.W - B.W > -1 (signed), and 0 otherwise
*-----------------------------------------------------------
    ORG    $1000
    
A       DC.W    25      ; declare word A with 25dec
B       DC.W    30      ; declare word B with 30dec
C       DS.W    1       ; declare word C storage

START:                  
        MOVE.W A,D0     ; move A to D0
        SUB.W B,D0      ; substract D0 to B and save it in D0
        CMP.W #-1,D0    ; compare D0 to -1
        BGT ET0         ; if D0 greater than -1, branches to ET0
        MOVE.W #0,C     ; move 0dec to C
        BRA ENDSIM      ; jumps incondicionally to ENDSIM
                    
        
ET0:
        MOVE.W #1,C     ; move 1dec to C

ENDSIM:    
        SIMHALT             ; halt simulator


    END    START        ; last line of source

*~Font name~Courier New~
*~Font size~10~
*~Tab type~1~
*~Tab size~4~
