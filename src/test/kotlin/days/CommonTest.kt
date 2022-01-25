package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class CommonTest {
    @Nested
    @DisplayName("Permutations")
    inner class Permutations {

        @Test
        internal fun `Permutation of empty list() should set of empty list`() {
            assertThat(emptyList<Int>().permutations())
                .hasSize(1)
                .isEqualTo(setOf(emptyList<Int>()))
        }

        @Test
        @DisplayName("Permutation 1 Element")
        internal fun permutationsWithOneElement() {
            assertThat(listOf(1).permutations())
                .hasSize(1)
                .isEqualTo(setOf(listOf(1)))
        }

        @Test
        @DisplayName("Permutation 2 Elements")
        internal fun permutationsWithTwoElements() {
            assertThat(listOf(1, 2).permutations())
                .hasSize(2)
                .isEqualTo(setOf(listOf(1, 2), listOf(2, 1)))
        }

        @Test
        @DisplayName("Permutation 3 Elements")
        internal fun permutationsWithThreeElements() {
            assertThat(listOf(1, 2, 3).permutations())
                .hasSize(6)
        }
    }

    @Nested
    @DisplayName("Arrangements")
    inner class Arrangements {
        @Test
        internal fun arrangementsWithNoElement() {
            assertThat(emptyList<Int>().arrangements())
                .hasSize(1)
                .isEqualTo(setOf(emptyList<Int>()))
        }

        @Test
        @DisplayName("Permutation 1 Element")
        internal fun permutationWithOneElement() {
            assertThat(listOf(1).arrangements())
                .hasSize(1)
                .isEqualTo(setOf(listOf(1)))
        }

        @Test
        internal fun `Arrangements of 3 Elements`() {
            val arrange = listOf(1, 2, 3)
            assertThat(arrange.arrangements())
                .hasSize(1)
                .isEqualTo(setOf(arrange))
        }

        @Test
        internal fun `Arrangements of 4 Elements`() {
            val arrange0 = listOf(1, 2, 3, 4)
            val arrange1 = listOf(2, 1, 3, 4)
            val arrange2 = listOf(2, 3, 1, 4)
            assertThat(arrange0.arrangements())
                .hasSize(3)
                .isEqualTo(setOf(arrange0, arrange1, arrange2))
        }

        @Test
        internal fun `Arrangements of 5 Elements`() {
            val arrange0 = listOf(1, 2, 3, 4, 5)
            assertThat(arrange0.arrangements())
                .hasSize(12)
        }

        @Test
        internal fun `Arrangements of 6 Elements`() {
            val arrange0 = listOf(1, 2, 3, 4, 5,6)
            assertThat(arrange0.arrangements())
                .hasSize(60)
        }
    }

    @Nested
    @DisplayName("Combinations")
    inner class Combinations {
        @Test
        fun testCombinations() {
            val combinations = listOf(1, 2, 3).combinations()
            combinations.forEach { println(it) }
            assertThat(combinations).hasSize(8)
        }

        @Test
        internal fun `Combination of 100 Elements in 4 Slots`() {
            val combinations = combinations(4, 100)
            assertThat(combinations)
                .hasSize(176851)
        }
    }
/*
linear permutation      4! -> 4*3*2*1 = 24
circular p.         (4-1)! -> 3*2*1 = 6
turned circular p.(4-1)!/2 -> 3*2*1/2 = 3
                   (3-1)!/2 -> 1
                   (4-1)!/2 -> 3
                   (5-1)!/2 -> 12
                   (6-1)!/2 -> 60
                   (7-1)!/2 -> 360
                   (8-1)!/2 -> 2520
 */
}