*-------------------------------------------------------------------------------------------------------
* Title      : ex_4
* Written by : Jesus Castillo Benito
* Date       : 20/03/2023
* Description: Save in C tha values of ARRAY in absolute value which are contained in between T1 and T2
*-------------------------------------------------------------------------------------------------------
    ORG    $1000
ARRAY:  DC.B    -10,120,8,70,0,-99,-106
N:      EQU     7
C:      DC.B    0 
T1:     EQU     8
T2:     EQU     99
 
START:                 
    LEA.L ARRAY,A0
    MOVE.B #N,D1
    
LOOP:
    MOVE.B (A0)+,D0
    BPL POSITIVE
    
    NOT.B D0
    ADDQ.B #1,D0
    
POSITIVE:
    CMP.B #T1,D0
    BLO ENDLOOP
    
    CMP.B #T2,D0
    BHI.B ENDLOOP
    
    ADDQ.B #1,C
ENDLOOP:    
    DBRA D1,LOOP
    

    
ENDSIM:
        SIMHALT             ; halt simulator

* Put variables and constants here

    END    START        ; last line of source

*~Font name~Courier New~
*~Font size~10~
*~Tab type~1~
*~Tab size~4~
