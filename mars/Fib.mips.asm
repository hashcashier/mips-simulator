.text
	#Print head
	la $a0, head
	li $v0, 4
	syscall
	#Start fib loop
	li $t2, 13
	li $a0, 0
loop:	beq $a0, $t2, exit
	jal fib
	add $t3, $zero, $a0

	#Print number
	add $a0, $zero, $v0
	li $v0, 1
	syscall
	#Print space
	la $a0, space
	li $v0, 4
	syscall
	#Increment counter
	addi $a0, $t3, 1
	j loop
exit:	li $v0, 10
	syscall


fib:
	addi $sp, $sp, -8	# Move stack
	sw $ra, 0($sp)		# Save return
	sw $a0, 4($sp)		# Save parameter
	
	slti $t0, $a0, 2
	beq $t0, $zero, rec
	slti $t0, $a0 1
	beq $t0, $zero, one
zer:
	addi $sp, $sp, 8
	li $v0, 0
	jr $ra
		
one:	addi $sp, $sp, 8
	li $v0, 1
	jr $ra

rec:	addi $sp, $sp, -4
	#Fib(n-1)
	addi $a0, $a0, -1
	jal fib
	sw $v0, 0($sp)
	#Fib(n-2)
	addi $a0, $a0, -1
	jal fib
	#Add
	lw $t1, 0($sp)
	add $v0, $t1, $v0
	lw $ra, 4($sp)
	lw $a0, 8($sp)
	addi $sp, $sp, 12
	jr $ra

.data
space:	.asciiz	" "
head:	.asciiz	"The fibonacci numbers are: \n"
array:	.space 48


