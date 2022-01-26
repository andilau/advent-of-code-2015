package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 4")
class Day4Test {
    @Nested
    inner class Part1 {
        @Test
        @Disabled
        fun `The MD5 hash of abcdef609043 starts with five zeroes`() {
            assertThat(Day4("abcdef").partOne()).isEqualTo(609043)
        }

        @Test
        @Disabled
        fun `The MD5 hash of pqrstuv1048970 starts with five zeros`() {
            assertThat(Day4("pqrstuv").partOne()).isEqualTo(1048970)
        }
    }
}