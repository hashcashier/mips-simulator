.text

	la $s0, array
	li $s1, 5
	
	add $t0, $0, $s1
	mul $t0, $t0, 4
	add $t0, $s0, $t0

	add $t1, $0, $s0
loop:	beq $t1, $t0, exit	#i

	addi $t2, $t1, -4	#j
loop2:	blt $t2, $s0, exit2

	addi $t3, $t2, 4	#j+1
	
	lw $t4, ($t2)		#v[j]
	lw $t5, ($t3)		#v[j+1]
	
	ble $t4, $t5, exit2	#v[j] <= v[j+1]
	
	sw $t4, ($t3)
	sw $t5, ($t2)

	addi $t2, $t2, -4
	j loop2

exit2:	addi $t1, $t1, 4
	j loop

exit:	li $v0, 10
	syscall
	
.data
array:	.word 5, 4, 3, 2, 1