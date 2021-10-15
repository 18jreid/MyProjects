; CS4700_HW2
; Justin Reid
; A02276642

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


; The countingNumbers function returns a list from numbers 1 ... n.
; Inputs - limit n numbers
; Output - the list of numbers 1 ... n
; Examples
;		(countingNumbers 5) returns (1 2 3 4 5)
;		(countingNumbers 10) returns (1 2 3 4 5 6 7 8 9 10)
(define (countingNumbers limit)
	(if (= limit 1)
		(list limit)
		(append (countingNumbers(- limit 1)) (list limit))
	)
)


; The eveNumbers function returns a list of even from numbers 1 ... n.
; Inputs - limit n numbers
; Output - the list of even numbers 1 ... n
; Examples
;		(eveNumbers 5) returns (2 4)
;		(eveNumbers 10) returns (2 4 6 8 10)
(define (evenNumbers limit)
	(cond 
		((odd? limit) (evenNumbers(- limit 1)))
		(else (if (= limit 2)
				(list limit)
				(append (evenNumbers(- limit 2)) (list limit))
			  )
		)
	)
)


; The primeNumbers function returns a list of prime from numbers 1 ... n.
; Inputs - limit n numbers
; Output - the list of prime numbers 1 ... n
; Examples
;		(primeNumbers 5) returns (2 3 5)
;		(primeNumbers 20) returns (2 3 5 7 11 13 17 19)
(define (primeNumbers limit)
	(if (= limit 2)
		(list limit)
		(if (car (isPrime limit (- limit 1)))
			(append (primeNumbers (- limit 1)) (list limit))
			(append (primeNumbers (- limit 1)) (list ))
		)
	)
)


; (Helper function for primeNumbers)
; The isPrime function returns a boolean of whether the number is prime.
; Inputs - numToCheck The desired number to be checked if prime
;		   counter One number less than the numToBeChecked(since we know a prime is divisible by itself)
; Output - A boolean value of whether the value is
; Examples
;		(isPrime 5 4) returns (#t)
;		(isPrime 7 6) returns (#t)
;		(isPrime 20 19) returns (#f)
(define (isPrime numToCheck counter)
	(cond
		((= counter 1) (list #t))
		((= (modulo numToCheck counter) 0) (list #f))
		(else (isPrime numToCheck (- counter 1)))
	)
)


; The merge function returns a ordered merged list of two lists.
; Inputs - listOne (ordered)
;		   listTwo (ordered)
; Output - The two lists merged and ordered
; Examples
;		(merge (primeNumbers 30) (evenNumbers 10)) returns (2 2 3 4 5 6 7 8 10 11 13 17 19 23 29)
;		(merge (countingNumbers 5) (evenNumbers 4)) returns (1 2 2 3 4 4 5)
(define (merge listOne listTwo)
  (if (null? listOne)
    listTwo
    (if (null? listTwo)
      listOne
      (if ( > (car listOne) (car listTwo))
        (append (list (car listTwo)) (merge (cdr listTwo) listOne))
        (append (list (car listOne)) (merge (cdr listOne) listTwo))
      )
    )
  )
)


; The wrap function moves the first n values to the bottom of the list given
; Inputs - numberToWrap n number of spots to move
;		   aList the list to be wrapped
; Output - the list of prime numbers 1 ... n
; Examples
;		(wrap 5 (countingNumbers 10)) returns (6 7 8 9 10 1 2 3 4 5)
;		(wrap 3 (countingNumbers 5)) returns (4 5 1 2 3)
(define (wrap numberToWrap aList)
	(if (= numberToWrap 0)
		aList
		(append (wrap (- numberToWrap 1) (cdr aList)) (list numberToWrap))
	)
)


; The subLists function returns a list of sublists, orderded from (1 - m) - n
; Inputs - aList The desired list to make into sublists
; Output - the list of sublists of numbers (1 - m) - n
; Examples
;		(subLists (countingNumbers 3)) returns ((1) (1 2) (1 2 3))
;		(subLists (primeNumbers 5)) returns ((2) (2 3) (2 3 5))
(define (subLists aList)
	(if (null? aList)
		aList
		(append (subLists (removeLastItem aList)) (list aList))
	)
)

; (Helper function for subLists)
; The removeLastItem function removes the very last item from a list
; Inputs - aList The desired list to remove the last item from
; Output - a new list with the last item removed from the previous list
; Examples
;		(removeLastItem (countingNumbers 3)) returns (1 2)
;		(removeLastItem (primeNumbers 10)) returns (2 3 5)
(define (removeLastItem aList)
    (if (null? (cdr aList))
        '()
        (cons (car aList) (removeLastItem (cdr aList)))))


(define (reduceLists func initialValue listOfLists)
"reduceLists"
)
