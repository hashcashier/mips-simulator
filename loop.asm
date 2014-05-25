.text
addi $t0, $zero, 15
addi $s1, $zero, 2008
addi $s0, $zero, 2000
loop: sw $t0, 0($s0)
addi $s0, $s0, 4
nop
nop
bne $s0, $s1, loop
nop
nop
