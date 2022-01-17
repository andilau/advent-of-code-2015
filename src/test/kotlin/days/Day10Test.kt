package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*

@DisplayName("Day 10")
class Day10Test {
    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        private val strings = mapOf(
            "1" to "11", //(1 copy of digit 1).
            "11" to "21", // (2 copies of digit 1).
            "21" to "1211", // (one 2 followed by one 1).
            "1211" to "111221", // (one 1, one 2, and two 1s).
            "111221" to "312211", // (three 1s, two 2s, and one 1).
        )

        @TestFactory
        fun `Sentences of look-and-say should sound like`() =
            strings
                .map { (look, say) ->
                    DynamicTest.dynamicTest("""Sentence of look-and-say: "$look" should become "$say"""") {
                        assertThat(Day10.lookAndSay(look).joinToString("")).isEqualTo(say)
                    }
                }

        @Test
        internal fun `Length of result after 40 rounds look-and-say`() {
            assertThat(Day10("3113322113").partOne()).isEqualTo(329356)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        internal fun `Length of result after 50 rounds look-and-say`() {
            assertThat(Day10("3113322113").partTwo()).isEqualTo(4666278)
        }

    }
}