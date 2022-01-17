package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.TestFactory

@DisplayName("Day 12")
class Day12Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @TestFactory
        internal fun `Numbers in JSON should sum`(): List<DynamicTest> = listOf(
            """[1,2,3]""" to 6,
            """{"a":2,"b":4}""" to 6,
            """[[[3]]]""" to 3,
            """{"a":{"b":4},"c":-1}""" to 3,
            """{"a":[-1,1]}""" to 0,
            """[-1,{"a":1}]""" to 0,
            "[]" to 0,
            "{}" to 0,
        )
            .map { (json, sum) ->
                DynamicTest.dynamicTest("""Numbers in JSON "$json" should sum to "$sum" """) {
                    assertThat(Day12(json.lines()).partOne()).isEqualTo(sum)
                }
            }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @TestFactory
        internal fun `Numbers in JSON ignoring object with attribute red should sum`(): List<DynamicTest> = listOf(
            """[1,2,3]""" to 6,
            """[1,{"c":"red","b":2},3]""" to 4,
            """{"d":"red","e":[1,2,3,4],"f":5}""" to 0,
            """[1,"red",5]""" to 6
        )
            .map { (json, sum) ->
                DynamicTest.dynamicTest("""Numbers in JSON ignoring object with attribute  red "$json" should sum to "$sum" """) {
                    assertThat(Day12(json.lines()).partTwo()).isEqualTo(sum)
                }
            }
    }
}