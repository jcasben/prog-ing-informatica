*-----------------------------------------------------------
* Program : L1A
* Written by : Jesus Castillo Benito
* Date : 27/02/2023
* Description: set data containers with values
*-----------------------------------------------------------
        ORG     $1000           ; store program from position 1000hex
A       DC.W 40                 ; declare 1 word A with initial value 40dec
B       DC.W 13                 ; declare 1 word B with initial value 13dec
C       DS.L 1                  ; declare 1 long word C unitialized
D       DS.L 1                  ; declare 1 long word D unitialized

START:                          ; first instruction of program
        MOVE.W A,D0             ; copy A into D0
        MOVE.W D0,B             ; copy D0 into B    
        MOVE.W #4,A             ; set A with 4dec
        MOVE.W #-1,B            ; set B with -1dec 
        MOVE.L #$abcdef00,D2    ; set D2 with abcdef00hex
        
        MOVE.L D2,C             ; copy D2 into C
        MOVE.L #%11110000,D     ; set D with 11110000bin
        
        MOVE.B #9,D0            ; halt simulator, also STOP #$2000 & SIMHALT
        TRAP #15                ;
        
    * Variables and Strings
    
        END     START               ; last line of source 
*~Font name~Courier New~
*~Font size~10~
*~Tab type~1~
*~Tab size~4~
