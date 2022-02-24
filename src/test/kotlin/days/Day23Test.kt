package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DisplayName("Day 23")
class Day23Test {
    @Test
    fun `This program should set a to 6`() {
        val input = """
            inc a
            jio a, +3
            inc a
            hlf a
            tpl a
            jie a, +2
            jmp -5
            """.trimIndent().lines()
        assertThat(Day23(input).solveForRegisterA()).isEqualTo(6)
        assertThat(Day23(input).solveForRegisterB()).isEqualTo(0)
    }

    @Test
    fun `This program should set a to 2`() {
        val input = """
            inc a
            jio a, +2
            tpl a
            inc a
            """.trimIndent().lines()
        assertThat(Day23(input).solveForRegisterA()).isEqualTo(2)
        assertThat(Day23(input).solveForRegisterB()).isEqualTo(0)
    }

    @Test
    fun `This program should set a to 0`() {
        val input = """
            """.trimIndent().lines()
        assertThat(Day23(input).solveForRegisterA()).isEqualTo(0)
        assertThat(Day23(input).solveForRegisterB()).isEqualTo(0)
    }

    @Test
    fun `This program should throw IllegalStateException`() {
        val input = """
            xxx a
            jio a, +2
            tpl a
            inc a
            """.trimIndent().lines()
        assertThrows<java.lang.IllegalStateException> { Day23(input).solveForRegisterA() }
    }
}