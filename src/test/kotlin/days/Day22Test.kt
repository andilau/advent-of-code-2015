package days

import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 22")
class Day22Test {

    @Test
    fun `What is the least amount of mana you can spend and still win the fight`() {
        val input = """
        Hit Points: 51
        Damage: 9
        """.trimIndent().lines()
        val day22 = Day22(input)

        with(SoftAssertions()) {
            assertThat(day22.partOne()).isEqualTo(900)
            assertThat(day22.partTwo()).isEqualTo(1216)
            assertAll()
        }
    }

    @Test
    fun `Another Example 1`() {
        val input = """
        Hit Points: 58
        Damage: 9
        """.trimIndent().lines()
        val day22 = Day22(input)

        with(SoftAssertions()) {
            assertThat(day22.partOne()).isEqualTo(1269)
            assertThat(day22.partTwo()).isEqualTo(1309)
            assertAll()
        }
    }

    @Test
    fun `Another Example 2`() {
        val input = """
            Hit Points: 71
            Damage: 10            
        """.trimIndent().lines()
        val day22 = Day22(input)

        with(SoftAssertions()) {
            assertThat(day22.partOne()).isEqualTo(1824)
            assertThat(day22.partTwo()).isEqualTo(1937)
            assertAll()
        }
    }
}