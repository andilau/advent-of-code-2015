import days.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import util.InputReader

@DisplayName("Solutions")
class SolutionsTest {

    @TestFactory
    fun testAdventOfCode() = listOf(
        Day1(InputReader.getInputAsString(1)) to Pair(138, 1771),
        Day2(InputReader.getInputAsList(2)) to Pair(1588178, 3783758),
        Day3(InputReader.getInputAsString(3)) to Pair(2081, 2341),
        Day4(InputReader.getInputAsString(4)) to Pair(254_575, 1038_736),
        Day5(InputReader.getInputAsList(5)) to Pair(236, 51),
        Day6(InputReader.getInputAsList(6)) to Pair(400_410, 15_343_601),
        Day7(InputReader.getInputAsList(7)) to Pair(16076, 2797),
        Day8(InputReader.getInputAsList(8)) to Pair(1333, 2046),
        Day9(InputReader.getInputAsList(9)) to Pair(117, 909),
        Day10(InputReader.getInputAsString(10)) to Pair(329_356, 4666_278),
        Day11(InputReader.getInputAsString(11)) to Pair("hepxxyzz", "heqaabcc"),
        Day12(InputReader.getInputAsList(12)) to Pair(156_366, 96_852),
        Day13(InputReader.getInputAsList(13)) to Pair(709, 668),
        Day14(InputReader.getInputAsList(14)) to Pair(2660, 1256),
        Day15(InputReader.getInputAsList(15)) to Pair(222_870, 117_936),
        Day16(InputReader.getInputAsList(16)) to Pair(373, 260),
        Day17(InputReader.getInputAsList(17)) to Pair(1638, 17),
        Day18(InputReader.getInputAsList(18)) to Pair(1061, 1006),
    )
        .map { (day, answers) ->
            DynamicTest.dynamicTest("${day.javaClass.simpleName} -> ${answers.first} / ${answers.second}") {
                assertThat(day.partOne()).isEqualTo(answers.first)
                assertThat(day.partTwo()).isEqualTo(answers.second)
            }
        }
}