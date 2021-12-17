package `2015`

import days.Day2
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.TestFactory

@DisplayName("Day 2")
class Day2Test {
    @Nested
    inner class Part1 {
        @TestFactory
        fun shouldCalculateAreaOfWrappingPaper() = listOf(
            "2x3x4" to 58,
            "1x1x10" to 43,
        )
            .mapIndexed() { x, (dimensions, area) ->
                DynamicTest.dynamicTest("${x + 1}: Dimensions $dimensions require $area square feet of wrapping paper") {
                    assertThat(Day2.Box.from(dimensions).areaWithExtra()).isEqualTo(area)
                }
            }
    }

    @Nested
    inner class Part2 {
        @TestFactory
        fun shouldCalculateLengthOfRibbon() = listOf(
            "2x3x4" to 34,
            "1x1x10" to 14,
        )
            .mapIndexed() { x, (dimensions, length) ->
                val underTest = Day2.Box.from(dimensions)
                DynamicTest.dynamicTest("${x + 1}: Dimensions $dimensions require $length feet of ribbon") {
                    assertThat(underTest.ribbonToWrapAndMakeBow()).isEqualTo(length)
                }
            }
    }
}