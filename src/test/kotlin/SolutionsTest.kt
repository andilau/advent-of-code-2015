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
    )
        .map { (day, answers) ->
            DynamicTest.dynamicTest("${day.javaClass.simpleName} -> ${answers.first} / ${answers.second}") {
                assertThat(day.partOne()).isEqualTo(answers.first)
                assertThat(day.partTwo()).isEqualTo(answers.second)
            }
        }
}