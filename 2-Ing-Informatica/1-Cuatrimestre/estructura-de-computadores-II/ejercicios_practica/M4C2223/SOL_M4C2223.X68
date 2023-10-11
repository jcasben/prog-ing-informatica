*-----------------------------------------------------------
* Title      :
* Written by :
* Date       :
* Description:
*-----------------------------------------------------------
            ORG    $1000
VALUES     DC.B   $24,$12,$9,0             
            
START:      ; show hadware window
            MOVE.B  #32,D0
            CLR.B   D1
            TRAP    #15
            
            ; get 7 segment display address into A0
            MOVE.B  #32,D0
            ADDQ.B  #1,D1
            TRAP    #15
            
            ; add 14 to go to the last 7seg display
            ADD.L   #14,D1
            MOVE.L  D1,A0
            
            ; get .VALUES address
.GTVALADD   LEA     VALUES,A1
            
            ; display value
.LOOP       MOVE.B  (A1)+,(A0)

            ; if 0 -> .GTVALADD
            BEQ     .GTVALADD

            ; wait half a second
            MOVE.B  #23,D0
            MOVE.L  #50,D1
            TRAP    #15
            
            ; loop
            BRA     .LOOP

            SIMHALT

            END    START

*~Font name~Courier New~
*~Font size~10~
*~Tab type~1~
*~Tab size~4~
