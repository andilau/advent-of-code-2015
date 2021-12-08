package `2015`

import days.Day5
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 5")
class Day5Test {
    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        internal fun `ugknbfddgicrmopn is nice because it has at least three vowels, a double letter, and none of the disallowed substrings`() {
            val input = "ugknbfddgicrmopn".lines()
            assertThat(Day5(input).partOne()).isEqualTo(1)
        }

        @Test
        internal fun `aaa is nice because it has at least three vowels and a double letter, even though the letters used by different rules overlap`() {
            val input = "aaa".lines()
            assertThat(Day5(input).partOne()).isEqualTo(1)
        }

        @Test
        internal fun `jchzalrnumimnmhp is naughty because it has no double letter`() {
            val input = "jchzalrnumimnmhp".lines()
            assertThat(Day5(input).partOne()).isEqualTo(0)
        }

        @Test
        internal fun `haegwjzuvuyypxyu is naughty because it contains the string xy`() {
            val input = "haegwjzuvuyypxyu".lines()
            assertThat(Day5(input).partOne()).isEqualTo(0)
        }

        @Test
        internal fun `dvszwmarrgswjxmb is naughty because it contains only one vowel`() {
            val input = "dvszwmarrgswjxmb".lines()
            assertThat(Day5(input).partOne()).isEqualTo(0)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        internal fun `qjhvhtzxzqqjkmpb is nice because is has a pair that appears twice (qj) and a letter that repeats with exactly one letter between them (zxz)`() {
            val input = listOf("qjhvhtzxzqqjkmpb")
            assertThat(Day5(input).partTwo()).isEqualTo(1)
        }

        @Test
        internal fun `xxyxx is nice because it has a pair that appears twice and a letter that repeats with one between, even though the letters used by each rule overlap`() {
            val input = listOf("xxyxx")
            assertThat(Day5(input).partTwo()).isEqualTo(1)
        }

        @Test
        internal fun `uurcxstgmygtbstg is naughty because it has a pair (tg) but no repeat with a single letter between them`() {
            val input = "uurcxstgmygtbstg".lines()
            assertThat(Day5(input).partTwo()).isEqualTo(0)
        }

        @Test
        internal fun `ieodomkazucvgmuy is naughty because it has a repeating letter with one between (odo), but no pair that appears twice`() {
            val repeatingLetterByNoRepeatingPair = "ieodomkazucvgmuy".lines()
            assertThat(Day5(repeatingLetterByNoRepeatingPair).partTwo()).isEqualTo(0)
        }
    }
}