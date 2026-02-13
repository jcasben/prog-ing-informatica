;;; OPERACIONES CON LISTAS
(caddr '(a b c d))

(remove 'b '(a b c d))

(append '(a b) '(a b c d))

(cons 'a '(b c d))

(setq l '(b c d))

(cons (car l) (cons 'a (cdr l)))

;;; DECLARACIÓN DE FUNCIONES
(defun suma1 (x) (+ x 1))

(defun square (x) (* x x))

(defun suma3 (x y z) (+ x y z))

(defun tercer (l) (caddr l))

(defun last (l) (car (reverse l)))

;;; EXPRESIONES CONDICIONALES

;; Devuelve el n + 1 si x es impar, y x si es par
(defun par(x) 
    (cond 
        ((oddp x) (+ x 1))
        (t x)
    )
)

;; Devuelve el valor absoluto de x
(defun absolute (x)
    (cond 
        ((>= x 0) x)
        (t (- x))
    )
)

(defun last (l)
    (cond 
        ((null (cdr l)) (car l))
        (t (last (cdr l)))
    )
)