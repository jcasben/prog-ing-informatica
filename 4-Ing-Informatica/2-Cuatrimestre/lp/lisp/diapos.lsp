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

;;; Diapo 50
(defun long (l)
    (cond 
        ((null l) 0)
        (t (+ 1 (long (cdr l))))
    )
)

;;; Diapo 52
(defun belongs (a l)
    (cond 
        ((null l) nil)
        ((equal a (car l)) t)
        (t (belongs a (cdr l)))
    )
)

;;; Diapo 53
(defun exp (a b)
    (cond 
        ((= b 0) 1)
        (t (* a (exp a (- b 1))))
    )
)

;;; Diapo 55
(defun div (a d)
    (cond 
        ((> d a) 0)
        (t (+ 1 (div (- a d) d)))
    )
)

;;; Diapo 58
(defun borra (a l)
    (cond
        ((null l) nil)
        ((equal a (car l)) (cdr l))
        (t (cons (car l) (borra a (cdr l))))
    )
)

;;; Diapo 59
(defun borra-todo (a l)
    (cond
        ((null l) nil)
        ((equal a (car l)) (borra-todo a (cdr l)))
        (t (cons (car l) (borra-todo a (cdr l))))
    )
)

;;; Diapo 60
(defun rdc (l)
    (cond 
        ((null (cdr l)) nil)
        (t (cons (car l) (rdc (cdr l))))
    )
)

;;; Diapo 61
(defun snoc (x l)
    (cond
        ((null (cdr l)) (cons x nil))
        (t (cons (car l) (snoc x (cdr l))))
    )
)

;; Diapo 67
(defun esborrap (n l)
    (cond 
        ((= n 0) (cdr l))
        (t (cons (car l) (esborrap (- n 1) (cdr l))))
    )
)

;; Diapo 68
(defun veces (x l)
    (cond 
        ((null l) 0)
        ((equal x (car l)) (+ 1 (veces x (cdr l))))
        (t (veces x (cdr l)))
    )
)

;; Diapo 69
(defun atoms (l)
    (cond 
        ((null l) 0)
        ((listp (car l)) (+ (atoms (car l)) (atoms (cdr l))))
        (t (+ 1 (atoms (cdr l))))
    )
)

;; Diapo 70
(defun flatten (l)
    (cond 
        ((null l) l)

        ((listp (car l))
            (append (flatten (car l)) (flatten (cdr l)))
        )

        (t (append (list (car l)) (flatten (cdr l))))
    )
)

;; Diapo 71
(defun sacaprimeros (n l)
    (cond 
        ((= n 0) l)
        (t (sacaprimeros (- n 1) (cdr l)))
    )
)

(defun devuelveprimeros (n l)
    (cond
        ((= n 0) nil)
        (t (cons (car l) (devuelveprimeros (- n 1) (cdr l))))
    )
)

;; Diapo 73
(defun insert (x n l)
    (cond
        ((= n 0) (cons x l))
        (t (cons (car l) (insert x (- n 1) (cdr l))))
    )
)

;; Diapo 74
(defun change (x n l)
    (cond
        ((null l) nil)
        ((= n 0) (cons x (cdr l)))
        (t (cons (car l) (change x (- n 1) (cdr l))))
    )
)

;; Diapo 75
(defun insert-left (a x l)
    (cond 
        ((null l) nil)
        ((equal a (car l)) (cons x l))
        (t (cons (car l) (insert-left a x (cdr l))))
    )
)

;; Diapo 76
(defun insert-right (a x l)
    (cond 
        ((null l) nil)
        ((equal a (car l)) 
            (append (list (car l) x) (cdr l))
        )
        (t (cons (car l) (insert-right a x (cdr l))))
    )
)

;;;; SETS ;;;;

;; Diapo 78
(defun correct-set (l)
    (cond 
        ((null l) t)
        ((belongs (car l) (cdr l)) nil)
        (t (correct-set (cdr l)))
    )
)

;; Diapo 79
(defun make-set (l)
    (cond
        ((null l) nil)
        ((belongs (car l) (cdr l))
            (make-set (cdr l))
        )
        (t (cons (car l) (make-set (cdr l))))
    )
)

;; Diapo 80
(defun join (a b)
    (cond
        ((null a) b)
        ((belongs (car a) b) 
            (join (cdr a) b)
        )
        (t 
            (cons (car a) (join (cdr a) b))
        )
    )
)

;; Diapo 81
(defun intersection (a b)
    (cond
        ((null a) nil)
        ((belongs (car a) b)
            (cons (car a) (intersection (cdr a) b))
        )
        (t (intersection (cdr a) b))
    )
)

;; Diapo 82
(defun diff (a b)
    (cond 
        ((null a) nil)
        ((belongs (car a) b)
            (diff (cdr a) b)
        )
        (t (cons (car a) (diff (cdr a) b)))
    )
)

;; Diapo 83
(defun simetric-diff (a b)
    (join (diff a b) (diff b a))
)

;; Diapo 84
(defun make-list (a l)
    (cond
        ((null l) nil)
        (t (cons (list a (car l)) (make-list a (cdr l))))
    )
)

(defun cartesian-product (a b)
    (cond 
        ((null a) nil)
        (t (append (make-list (car a) b) (cartesian-product (cdr a) b)))
    )
)

;;;; IO ;;;;

;; Diapo 89: Lee la expresión de un fichero
(defun read-expr (name)
    (let* (
            (fp (open name))
            (e (read fp nil nil))
        )
        (close fp)
        e
    )
)

;; Diapo 90: Escribir una expresión a un fichero
(defun write-expr (name expr)
    (let 
        ((fp (open name :direction :output)))
        (print expr fp)
        (close fp)
    )
)

;; Diapo 91: Leer carácteres de un fichero y ponerlos en una lista
(defun myread (name)
    (let*
        (
            (fp (open name))
            (content (myread-intern fp))
        )
        (close fp)
        
        content
    )
)

(defun myread-intern (fp)
    (let 
        ((c (read-char fp nil nil)))
        (cond
            ((null c) '())
            (t (cons c (myread-intern fp)))
        )
    )
)

;; Diapo 92: Escribir una lista de carácteres en un fichero
(defun mywrite-intern (fp content)
    (cond
        ((null content) nil)
        (t (write-char (car content) fp) (mywrite-intern fp (cdr content)))
    )
)

(defun mywrite (name content)
    (let
        ((fp (open name :direction :output)))
        (mywrite-intern fp content)
        (close fp)
    )
)

;; Diapo 93: Añadir una lista de carácteres al final de un fichero
(defun addcontent (name content)
    (let
        ((fp (open name :direction :output
                        :if-exists :append
                        :if-does-not-exist :create)))
        (mywrite-intern fp content)
        (close fp)
    )
)

;;;; EVAL ;;;;

(defun repeat (x)
    (print x) 
    (repeat (eval (read)))
)