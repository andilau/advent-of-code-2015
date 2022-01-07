package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.TestFactory

@DisplayName("Day 3")
class Day3Test {
    @Nested
    inner class Part1 {
        @TestFactory
        fun shouldDeliverNumberOfHousesAtLeastOnce() = listOf(
            ">" to 2,
            "^>v<" to 4,
            "^v^v^v^v^v" to 2
        ).mapIndexed { x, (instructions, houses) ->
            dynamicTest("""${x + 1}: Instruction "$instructions" should deliver $houses houses""") {
                assertThat(Day3(instructions).partOne()).isEqualTo(houses)
            }
        }
    }

    @Nested
    inner class Part2 {
        @TestFactory
        fun shouldDeliverNumberOfHousesAtLeastOnce() = listOf(
            "^v" to 3,
            "^>v<" to 3,
            "^v^v^v^v^v" to 11
        ).mapIndexed { x, (instructions, houses) ->
            dynamicTest("""${x + 1}: Instruction "$instructions" should deliver $houses houses""") {
                assertThat(Day3(instructions).partTwo()).isEqualTo(houses)
            }
        }
    }
}