package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.TestFactory

@DisplayName("Day 1")
class Day1Test {
    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @TestFactory
        @DisplayName("Santa should arrive at floor")
        fun shouldArriveAtFloor() = listOf(
            "(())" to 0,
            "()()" to 0,
            "(((" to 3,
            "(()(()(" to 3,
            "))(((((" to 3,
            "())" to -1,
            "))(" to -1,
            ")))" to -3,
            ")())())" to -3,
            ") ( ) )( ))   " to -3
        ).mapIndexed { x, (parentheses, floor) ->
            DynamicTest.dynamicTest("""Example ${x + 1}: $parentheses should arrive at floor $floor" """) {
                assertThat(Day1(parentheses).partOne()).isEqualTo(floor)
            }
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @TestFactory
        @DisplayName("Santa should enter the basement at move")
        fun shouldEnterBasementAtMove() = listOf(
            ")" to 1,
            "()())" to 5,
            "() () )" to 5,
            "())" to 3,
            "))(" to 1,
            ")))" to 1,
            ")())())" to 1,
            "((((((((((())))))))))" to -1,
            "((((((((((()))))))))))" to -1,
            "((((((((((())))))))))))" to 23,

        ).mapIndexed { x, (parentheses, index) ->
            DynamicTest.dynamicTest("""Example ${x + 1}: "$parentheses" should enter basement at index $index""") {
                assertThat(Day1(parentheses).partTwo()).isEqualTo(index)
            }
        }
    }
}