package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 22")
class Day22Test {
    val input = """
        Hit Points: 51
        Damage: 9
        """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `What is the least amount of mana you can spend and still win the fight`() {
            assertThat(Day22(input).partOne()).isEqualTo(900)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `What is the least amount of mana you can spend and still win the fight in hard mode`() {
            assertThat(Day22(input).partTwo()).isEqualTo(1216)
        }
    }
}