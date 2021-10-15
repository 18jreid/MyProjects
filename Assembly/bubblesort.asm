# CS2810 HW-7
# Justin Reid, A02276642
# I could not have done this without google and youtube lol

.data
myArray: .word 3 1 2 7 5 8 11 4 6 9
length: .word 10

.text
main:
la $t1, myArray # load array address
lw $s0, length # load array length
subu $s0, $s0, 1
	
addu $s5, $zero,$zero
addu $s1, $zero, $zero # i = 0

loop: # main for loop
addu $s2, $zero, $zero #j = 0	
subu $t9, $s0, $s1

nestedLoop: # second for loop
	addu $t2, $t1, 4 # next indexx
	lw $t4, ($t1)
	lw $t5, ($t2)
	bleu $t4, $t5, noSwap
	sw $t4, ($t2)
	sw $t5, ($t1)
	addu $s5, $zero, 1
	
	noSwap:
	beq $s2, $t9, endNestedLoop
	addu $s2, $s2, 1
	addu $t1, $t1, 4 # next index
	addu $t2, $t2, 4 # next index
	b nestedLoop

endNestedLoop:
beqz $s5, endFor
beq $s1, $s0, endFor
addu $s1, $s1, 1
la $t1, myArray
b loop

endFor:
la $t1, myArray
and $s1, $zero, $zero


print:
	lw $a0, ($t1)
	addu $v0, $zero, 1
	syscall
	beq $s1, $s0, endPrint
	addu $s1, $s1, 1
	addu $t1, $t1, 4 # next index
	b print
endPrint:
	
