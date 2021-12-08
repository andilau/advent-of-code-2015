package `2015`

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class Day1Test {
    @TestFactory
    fun shouldResultInCorrectFloor() = listOf(
        "(())" to 0,
        "(())" to 0,
        "(((" to 3,
        "(()(()(" to 3,
        "))(((((" to 3,
        "())" to -1,
        "))(" to -1,
        ")))" to -3,
        ")())())" to -3
    ).mapIndexed() { x, (instructions, floor) ->
        DynamicTest.dynamicTest("Example ${x + 1}: $instructions should result in floor $floor") {
            assertThat(instructions.floor()).isEqualTo(floor)
        }
    }


    /*
(()) and ()() both result in floor 0.
((( and (()(()( both result in floor 3.
))((((( also results in floor 3.
()) and ))( both result in floor -1 (the first basement level).
))) and )())()) both result in floor -3.

     */

    @TestFactory
    fun shouldResultInCorrectAt() = listOf(
        ")" to 1,
        "()())" to 5,
    ).mapIndexed() { x, (instructions, floor) ->
        DynamicTest.dynamicTest("Example ${x + 1}: $instructions should result in floor $floor") {
            assertThat(instructions.foo()).isEqualTo(floor)
        }
    }

    fun String.floor(): Int =
        this.fold(0, ::function)


    fun function(acc: Int, c: Char) =
        when (c) {
            '(' -> acc + 1
            ')' -> acc - 1
            else -> acc
        }


    fun String.foo() =
        runningFold(0, ::function)
            .indexOfFirst { it == -1 }

    fun String.floor2() =
        map {
            when (it) {
                '(' -> 1
                ')' -> -1
                else -> 0
            }
        }.sum()


}