package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DisplayName("Day 25")
class Day25Test {
    private val prompt =
        "To continue, please consult the code grid in the manual.  Enter the code at row 2947, column 3029."

    @Test
    fun testParseFailure() {
        assertThrows<java.lang.IllegalStateException> { Day25("Prompt with no numbers in it.") }
    }

    @Test
    fun testRowAndColumn() {
        val day25 = Day25(prompt)
        assertThat(day25.row).isEqualTo(2947)
        assertThat(day25.col).isEqualTo(3029)
    }

    @Test
    fun testTriangularSequence() {
        val input = """
           | 1   2   3   4   5   6  
        ---+---+---+---+---+---+---+
         1 |  1   3   6  10  15  21
         2 |  2   5   9  14  20
         3 |  4   8  13  19
         4 |  7  12  18
         5 | 11  17
         6 | 16"""
            .trimIndent().lines().drop(2)
            .map { it.substringAfter('|').split(" ").mapNotNull(String::toIntOrNull).toIntArray() }
            .toTypedArray()
        for (row in input.indices) for (col in input[row].indices) {
            assertThat(Day25.index(row + 1, col + 1)).isEqualTo(input[row][col])
        }
    }

    @Test
    fun testCodeSequence() {
        val expectedCodes = """
               |    1         2         3         4         5         6
            ---+---------+---------+---------+---------+---------+---------+
             1 | 20151125  18749137  17289845  30943339  10071777  33511524
             2 | 31916031  21629792  16929656   7726640  15514188   4041754
             3 | 16080970   8057251   1601130   7981243  11661866  16474243
             4 | 24592653  32451966  21345942   9380097  10600672  31527494
             5 |    77061  17552253  28094349   6899651   9250759  31663883
             6 | 33071741   6796745  25397450  24659492   1534922  27995004"""
            .trimIndent().lines().drop(2)
            .map { it ->
                it.substringAfter("|")
                    .let {
                        Regex("""\d+""").findAll(it).map(MatchResult::value).map(String::toLong).toList().toLongArray()
                    }
            }
            .toTypedArray()
        val lastIndex = Day25.index(expectedCodes.size, expectedCodes[expectedCodes.lastIndex].size)
        val actualCodes = Day25.codes().take(lastIndex).toList()

        for (row in expectedCodes.indices) for (col in expectedCodes[row].indices) {
            val index = Day25.index(row + 1, col + 1)
            assertThat(actualCodes[index - 1]).isEqualTo(expectedCodes[row][col])
        }
    }
}