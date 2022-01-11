package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.TestFactory

@DisplayName("Day 6")
class Day6Test {
   @Nested
    @DisplayName("Part 1")
    inner class Part1 {
       private val example = listOf(
           "turn on 0,0 through 999,999" to 1000000,
           "toggle 0,0 through 999,0" to 1000,
           "turn off 499,499 through 500,500" to 0
       )

       @TestFactory
       internal fun test() = example
           .map { (input, turnedOn) ->
               dynamicTest("""Command "$input" should lit $turnedOn lights""") {
                   assertThat(Day6(input.lines()).partOne()).isEqualTo(turnedOn)
               }
           }
   }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        private val example = listOf(
            "turn on 0,0 through 999,999" to 1000000,
            "toggle 0,0 through 999,0" to 2000,
            "turn off 499,499 through 500,500" to 0
        )

        @TestFactory
        internal fun test() = example
            .map { (input, brightness) ->
                dynamicTest("""Command "$input" should combine to total brightness of $brightness""") {
                    assertThat(Day6(input.lines()).partTwo()).isEqualTo(brightness)
                }
            }

    }
}