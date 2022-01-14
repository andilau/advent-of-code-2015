package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*

@DisplayName("Day 11")
class Day11Test {
    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @TestFactory
        internal fun `Next password candidate should be`(): List<DynamicTest> = listOf(
            "hepxcrrq" to "hepxcrrr",
            "aaaaaaaa" to "aaaaaaab",
            "aaaaaaaz" to "aaaaaaba",
            "aaaaaazz" to "aaaaabaa",
        )
            .map { (oldPassword, newPassword) ->
                DynamicTest.dynamicTest("Next password candidate of $oldPassword should equal $newPassword") {
                    assertThat(Day11(oldPassword).nextCandidate()).isEqualTo(newPassword)

                }

            }

        @Test fun `First valid password starting from abcdefgh is abcdffaa`() {
            assertThat(Day11("abcdefgh").partOne()).isEqualTo("abcdffaa")
        }

        @Test fun `First valid password starting from ghijklmn is ghjaabcc`() {
            assertThat(Day11("ghijklmn").partOne()).isEqualTo("ghjaabcc")
        }

        @Test fun `Has Three Letters Straight`() {
            assertThat(Day11("abccdef").hasThreeLettersStraight()).isTrue
            assertThat(Day11("abfcdcf").hasThreeLettersStraight()).isFalse
        }

        @Test fun `Has No Letter IOL`() {
            assertThat(Day11("abccdef").hasNoLetterIOL()).isTrue
            assertThat(Day11("abcidef").hasNoLetterIOL()).isFalse
            assertThat(Day11("abcodef").hasNoLetterIOL()).isFalse
            assertThat(Day11("abcldef").hasNoLetterIOL()).isFalse
            assertThat(Day11("iolioli").hasNoLetterIOL()).isFalse
        }

        @Test fun `Has Two Exclusive Pairs`() {
            assertThat(Day11("aaabcdef").hasTwoExclusivePairs()).isFalse
            assertThat(Day11("abcdeeef").hasTwoExclusivePairs()).isFalse
            assertThat(Day11("abcdefgh").hasTwoExclusivePairs()).isFalse
            assertThat(Day11("aaaacdef").hasTwoExclusivePairs()).isTrue
            assertThat(Day11("aabbcdef").hasTwoExclusivePairs()).isTrue
            assertThat(Day11("aabccdef").hasTwoExclusivePairs()).isTrue
            assertThat(Day11("aaabbbbf").hasTwoExclusivePairs()).isTrue
            assertThat(Day11("aabbccdd").hasTwoExclusivePairs()).isTrue
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test fun `Second valid password of abcdefgh is abcdffbb`() {
            assertThat(Day11("abcdefgh").partTwo()).isEqualTo("abcdffbb")
        }

        @Test fun `Second valid password of ghijklmn is ghjaabcc`() {
            assertThat(Day11("ghijklmn").partTwo()).isEqualTo("ghjbbcdd")
        }
    }
}