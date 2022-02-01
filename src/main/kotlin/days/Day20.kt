package days

import kotlin.math.sqrt

@AdventOfCodePuzzle(
    name = "Infinite Elves and Infinite Houses",
    url = "https://adventofcode.com/2015/day/20",
    date = Date(day = 20, year = 2015)
)
class Day20(input: List<Int>) : Puzzle {
    private val threshold = input.first()

    override fun partOne(): Int =
        presentsDeliveredToInfiniteHouses().indexOfFirst { it >= threshold } + 1

    override fun partTwo(): Int =
        presentsDeliveredTo50AndExtra().indexOfFirst { it > threshold } + 1

    internal fun presentsDeliveredToInfiniteHouses() = sequence {
        for (house in (1..Int.MAX_VALUE)) {
            val bound = sqrt(house.toDouble()).toInt()
            var presents = 1 + house
            for (elf in 2..bound) if (house % elf == 0) presents += elf + house / elf
            if (bound * bound == house) presents -= bound   // correction for double counting
            yield(presents * 10)
        }
    }

    internal fun presentsDeliveredTo50AndExtra() = sequence {
        (1..Int.MAX_VALUE).forEach { house ->
            val presents = (1..50).fold(0) { presents, elf ->
                presents + if (house % elf == 0) house / elf else 0
            }
            yield(presents * 11)
        }
    }
}