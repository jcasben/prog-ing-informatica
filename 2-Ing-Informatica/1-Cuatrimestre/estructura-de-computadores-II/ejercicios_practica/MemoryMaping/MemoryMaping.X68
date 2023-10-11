*-----------------------------------------------------------
* Title      : Memory mapping
* Written by : 
* Date       : 20/09/2023
* Description: Mapping hardware memory positions
*-----------------------------------------------------------

            ORG    $1000
START:      
            MOVE.B  #32,D0  ; cargamos 32 para realizar la tarea
            CLR.B   D1      
            TRAP    #15     ; llamada del sistema
            
            JSR     GTHWADDR    ; salto a la subrutina
            
            MOVE.L  (BUTADDR), A0
            MOVE.L  (LEDADDR), A1
.LOOP       MOVE.B  (A0),D0
            NOT.B   D0
            MOVE.B  D0,(A1)
            BRA     .LOOP
            SIMHALT
            
GTHWADDR                            ; subrutina que realiza un mapeo de las
                                    ; direcciones del hadware
            LEA     SEGADDR,A0
            LEA     .DATA,A1
            MOVE.B  #32,D0
            
      
.LOOP       MOVE.B  (A1)+,D1
            BEQ     .END
            TRAP    #15
            MOVE.L  D1,(A0)+            
            BRA     .LOOP
            
.END        RTS
.DATA       DC.B    1,2,3,7 ; vector que contiene el valor para acceder a cada
                            ; uno de los tipos de hardware (ordenados)
            
SEGADDR     DS.L    1
LEDADDR     DS.L    1
SWTADDR     DS.L    1
BUTADDR     DS.L    1

    END    START        ; last line of source

*~Font name~Courier New~
*~Font size~13~
*~Tab type~1~
*~Tab size~4~
