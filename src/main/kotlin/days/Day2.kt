package days

@AdventOfCodePuzzle(
    name = "I Was Told There Would Be No Math",
    url = "https://adventofcode.com/2015/day/2",
    date = Date(day = 2, year = 2015)
)
class Day2(dimensions: List<String>) : Puzzle {
    private val boxes = dimensions.map { Box.from(it) }

    override fun partOne() =
        boxes.sumOf { box -> box.areaWithExtra() }

    override fun partTwo() =
        boxes.sumOf { box -> box.ribbonToWrapAndMakeBow() }

    data class Box(val l: Int, val w: Int, val h: Int) {
        init {
            require(l > 0)
            require(w > 0)
            require(h > 0)
        }

        fun areaWithExtra() =
            areas
                .sorted()
                .let { 3 * it[0] + 2 * it[1] + 2 * it[2] }

        fun ribbonToWrapAndMakeBow(): Int {
            return perimeters.sorted().first() + volume
        }

        val perimeters
            get() = listOf(
                l + l + w + w,
                w + w + h + h,
                h + h + l + l
            )

        val areas
            get() = listOf(
                l * w,
                w * h,
                h * l
            )

        val volume get() = l * w * h

        companion object {
            fun from(dimension: String): Box {
                return dimension
                    .split("x".toRegex(), 3)
                    .map(String::toInt)
                    .let { Box(it[0], it[1], it[2]) }
            }
        }
    }

}

