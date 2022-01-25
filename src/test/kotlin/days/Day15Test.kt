package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 15")
class Day15Test {
    val twoIngredients = """
        Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8
        Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3
    """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Two Ingredients can be combined in 101 ways`() {
            assertThat(Day15(twoIngredients).combinations()).isEqualTo(101)
        }

        @Test
        fun `Given the ingredients in your kitchen and their properties, what is the total score of the highest-scoring cookie you can make`() {
            assertThat(Day15(twoIngredients).partOne()).isEqualTo(62_842_880)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test fun `Given the ingredients in your kitchen and their properties, what is the total score of the highest-scoring cookie you can make with a calorie total of 500`() {
            assertThat(Day15(twoIngredients).partTwo()).isEqualTo(57_600_000)
        }

    }
}