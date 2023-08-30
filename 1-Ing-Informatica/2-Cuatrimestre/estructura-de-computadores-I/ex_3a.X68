*--------------------------------------------------------------------
* Title      : ex_3a
* Written by : Jesus Castillo Benito
* Date       : 13/03/2023
* Description: Calculate the sum of the N first natural numbers (Bxx)
*--------------------------------------------------------------------
    ORG    $1000
A   DC.W    1           ; if 0, do-while loop, else: for loop.
N   EQU     5           ; label N to substitute for the assigned value
SUM     DC.L    0       ; declare SUM for storage the resukt of the sums

START:
        CMP.W #1,A
        BEQ DBRALOOP
        
        MOVE.L #0,D0
WHILE:
        ADD.L D0,SUM
        ADDQ.L #1,D0
        CMP.L #N,D0
        BLO WHILE
        BRA ENDSIM           
        
DBRALOOP:                
        MOVE.L #N,D0    ; inicialize D0 to N
LOOP:                   ; label LOOP
        ADD.L D0,SUM    ; add D0 to SUM
        DBRA D0,LOOP    ; decrement D0, jump to LOOP
        
ENDSIM:    
        SIMHALT             ; halt simulator


    END    START

*~Font name~Courier New~
*~Font size~10~
*~Tab type~1~
*~Tab size~4~
