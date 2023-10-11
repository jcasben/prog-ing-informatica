*-----------------------------------------------------------
* Title      :
* Written by : Jesus Castillo Benito
* Date       :
* Description:
*-----------------------------------------------------------
    	    ORG    $1000
    	    
SCRWIDTH    EQU     800                     ; SCREEN WIDTH
SCRHEIGH    EQU     600                     ; SCREEN HEIGHT
CRLCSIZE    EQU     250


START:
            ; show hadware window
            MOVE.B  #32,D0
            CLR.B   D1
            TRAP    #15
            ; windowed mode
            MOVE.L  #1,D1
            MOVE.B  #33,D0
            TRAP    #15
            
            ; config screen resolution                  
            MOVE.L  #SCRWIDTH<<16|SCRHEIGH,D1
            TRAP    #15
            
            ; get switches address and put it into A0
.GTSWTADD   MOVE.B  #32,D0
            MOVE.B  #3,D1
            TRAP    #15
            MOVE.L  D1,A0
            
            
.LOOP       MOVE.B  (A0),D7
            BTST.L  #0,D7
            BEQ     .PNTRED 

            ; set pen color GREEN
            MOVE.B  #80,D0
            MOVE.L  #$0000FF00,D1
            TRAP    #15
            
            ; set fill color GREEN
            MOVE.B  #81,D0
            TRAP    #15
            BRA     .DRWCRCL
            
.PNTRED   ; set pen color RED
            MOVE.B  #80,D0
            MOVE.L  #$000000FF,D1
            TRAP    #15
            
            ; set fill color RED
            MOVE.B  #81,D0
            TRAP    #15
            
.DRWCRCL    ; draw top-left circle
            MOVE.W  #0,D1
            MOVE.W  #0,D2
            MOVE.W  #CRLCSIZE,D3
            MOVE.W  #CRLCSIZE,D4
            
            MOVE.B  #88,D0                  
            TRAP    #15
            
            ; draw top-right
            MOVE.W  #SCRWIDTH,D1
            SUB.W   #CRLCSIZE,D1
            MOVE.W  #0,D2
            MOVE.W  #SCRWIDTH,D3
            MOVE.W  #CRLCSIZE,D4
            
            MOVE.B  #88,D0                  
            TRAP    #15
            
            ; draw left-bottom circle
            MOVE.W  #0,D1
            MOVE.W  #SCRHEIGH,D2
            SUB.W   #CRLCSIZE,D2
            MOVE.W  #CRLCSIZE,D3
            MOVE.W  #SCRHEIGH,D4
            
            
            MOVE.B  #88,D0                  
            TRAP    #15
            
            ; draw right-bottom circle
            MOVE.W  #SCRWIDTH,D1
            SUB.W   #CRLCSIZE,D1
            MOVE.W  #SCRHEIGH,D2
            SUB.W   #CRLCSIZE,D2
            MOVE.W  #SCRWIDTH,D3
            MOVE.W  #SCRHEIGH,D4
            
            MOVE.B  #88,D0                  
            TRAP    #15
            
            BRA     .LOOP
            
           

    	    SIMHALT

    	    END    START

*~Font name~Fixedsys~
*~Font size~17~
*~Tab type~1~
*~Tab size~4~
