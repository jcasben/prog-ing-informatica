;;; Devuelve todas las soluciones de una ecuación de segundo grado
(defun ecuacion2 (a b c)
    (let ((discr (- (* b b) (* 4 a c)))
          (denom (* 2 a)))
        (cond 
            ((= discr 0) 
                (list (/ (- b) denom))
            )

            ((< discr 0) 
                '()
            )

            (t 
                (list (/ (+ (- b) (sqrt discr)) denom)
                      (/ (- (- b) (sqrt discr)) denom))
            )
        )
    )
)