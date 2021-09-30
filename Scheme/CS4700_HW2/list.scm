; The gcd function computes the greatest common divisor of m and n.
; Inputs - m First number
;          n Second number
; Output - the GCD
; Examples
;    (gcd 4 2) returns 2
;    (gcd 7 3) returns 1
(define (gcd m n)
  (cond ((< m n) (gcd m (- n m)))
        ((< n m) (gcd (- m n) n))
        (else m)))


(define (countingNumbers limit)
)


(define (evenNumbers limit)
)


(define (primeNumbers limit)
)

(define (merge listOne listTwo)
)

(define (wrap numberToWrap aList)
)

(define (subLists aList)
)

(define (reduceLists func initialValue listOfLists)
)
