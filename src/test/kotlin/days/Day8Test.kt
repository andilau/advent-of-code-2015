package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*

@DisplayName("Day 8")
class Day8Test {
    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        private val strings = mapOf(
            """""""" to 2,                // 2 - 0
            """"abc"""" to 2,             // 5 - 3
            """"aaa\"aaa"""" to 3,    // 10 - 7
            """"\x27"""" to 5         // 6 - 1
        )

        @TestFactory
        fun `Difference between code-length and memory-length`() =
            strings
                .map { (string, diff) ->
                    DynamicTest.dynamicTest("Difference between code-length and memory-length (decoded) of $string should equal $diff") {
                        assertThat(Day8(string.lines()).partOne()).isEqualTo(diff)
                    }
                }


        @Test
        internal fun `Differences between code-length and memory-length (decoded) should sum to 12`() {
            assertThat(Day8(strings.keys.toList()).partOne()).isEqualTo(12)
        }

        @Test
        internal fun `Difference between code-length and memory-length (decoded) should sum to 3`() {
            val element = """"aaaaaa\\"""" // -> 3,    // 10 - 7
            assertThat(Day8(element.lines()).partOne()).isEqualTo(3)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        private val strings = mapOf(
            """""""" to 4,            // 6 - 2
            """"abc"""" to 4,         // 9 - 5
            """"aaa\"aaa"""" to 6,    // 16 - 10
            """"\x27"""" to 5         // 11 - 6
        )

        @TestFactory
        fun `Differences between encoded-length and code-length (original)`() =
            strings
                .map { (string, diff) ->
                    DynamicTest.dynamicTest("Difference between encoded-length and code-length (original) of $string should equal $diff") {
                        assertThat(Day8(string.lines()).partTwo()).isEqualTo(diff)
                    }
                }

        @Test
        internal fun `Differences between encoded-length and code-length (original) should sum to 19`() {
            assertThat(Day8(strings.keys.toList()).partTwo()).isEqualTo(19)
        }
    }
}