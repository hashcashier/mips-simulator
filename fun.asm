addi $a0, $zero, 1
jal fun
j die
fun: addi $v0, $a0, 1
jr $ra
die: nop
