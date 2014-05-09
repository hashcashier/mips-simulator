.text
	la $s0, first
	la $s1, second
	
	jal comp
	
	move $a0, $v0
	li $v0, 1
	syscall
	li $v0, 10
	syscall

comp:
	move $t0, $s0
	move $t1, $s1
	
loop:	lb $t2, ($t0)
	lb $t3, ($t1)
	bne $t2, $t3, neq
	
	lb $t2, nullb
	beq $t2, $t3, eq
	
	addi $t0, $t0, 1
	addi $t1, $t1, 1
	j loop

neq:	li $v0, 1
	jr $ra
eq:	li $v0, 0
	jr $ra
	
.data
nullb:	.byte '\0'
first:	.asciiz "Hello, world!"
second:	.asciiz "Hello, 1orld!"