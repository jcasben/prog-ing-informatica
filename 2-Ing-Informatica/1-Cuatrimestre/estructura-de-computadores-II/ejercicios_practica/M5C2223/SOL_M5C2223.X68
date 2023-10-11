*-----------------------------------------------------------
* Title      :
* Written by :
* Date       :
* Description:
*-----------------------------------------------------------
            ORG    $1000
            
VALUES      DC.B    $81,$42,$24,$18
            DC.B    $24,$42,0,0

START:      ; show hadware window
            MOVE.B  #32,D0
            CLR.B   D1
            TRAP    #15
            
            ; get led address
            MOVE.B  #32,D0
            MOVE.B  #2,D1
            TRAP    #15
            MOVE.L  D1,A0
            
.GTVALADD   LEA     VALUES,A1

            ; show value on the leds            
.LOOP    MOVE.B  (A1)+,(A0)
            
            ; if 0 -> GTVALADD
            BEQ     .GTVALADD
            
            ; wait half a second
            MOVE.B  #23,D0
            MOVE.L  #50,D1
            TRAP    #15
            
            BRA     .LOOP

            SIMHALT

            END    START

*~Font name~Courier New~
*~Font size~10~
*~Tab type~1~
*~Tab size~4~
