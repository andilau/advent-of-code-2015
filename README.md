# Advent of Code 2015

This project contains my solutions for the [Advent of Code 2015](https://adventofcode.com/2015)
in [Kotlin](https://kotlinlang.org/). Advent of Code is an Advent calendar of small programming puzzles
by [Eric Wastl](http://was.tl/).

## Solutions

- Day 1: [Not Quite Lisp](https://adventofcode.com/2015/day/1)
- Day 2: [I Was Told There Would Be No Math](https://adventofcode.com/2015/day/2)
- Day 3: [Perfectly Spherical Houses in a Vacuum](https://adventofcode.com/2015/day/3)
- Day 4: [The Ideal Stocking Stuffer](https://adventofcode.com/2015/day/4)
- Day 5: [Doesn't He Have Intern-Elves For This?](https://adventofcode.com/2015/day/5)
- Day 6: [Probably a Fire Hazard](https://adventofcode.com/2015/day/6)
- Day 7: [Some Assembly Required](https://adventofcode.com/2015/day/7)
- Day 8: [Matchsticks](https://adventofcode.com/2015/day/8)
- Day 9: [All in a Single Night](https://adventofcode.com/2015/day/9)
- Day 10: [Elves Look, Elves Say](https://adventofcode.com/2015/day/10)
- Day 11: [Corporate Policy](https://adventofcode.com/2015/day/11)
- Day 12: [JSAbacusFramework.io](https://adventofcode.com/2015/day/12)
- Day 13: [Knights of the Dinner Table](https://adventofcode.com/2015/day/13)
- Day 14: [Reindeer Olympics](https://adventofcode.com/2015/day/14)
- Day 15: [Science for Hungry People](https://adventofcode.com/2015/day/15)
- Day 16: [Aunt Sue](https://adventofcode.com/2015/day/16)
- Day 17: [No Such Thing as Too Much](https://adventofcode.com/2015/day/17)
- Day 18: [Like a GIF For Your Yard](https://adventofcode.com/2015/day/18)
- Day 19: [Medicine for Rudolph](https://adventofcode.com/2015/day/19)
- Day 20: [Infinite Elves and Infinite Houses](https://adventofcode.com/2015/day/20)
- Day 21: [RPG Simulator 20XX](https://adventofcode.com/2015/day/21)
- Day 22: [Wizard Simulator 20XX](https://adventofcode.com/2015/day/22)

## Features

* Gradle setup so you can run a specific day or all days on the command line (see **Running**)
* Timings for each part of each day
* Input for each day automatically exposed in String and List form
* [Junit 5](https://junit.org/junit5/) and [AssertJ](https://assertj.github.io/doc/) test libraries included (see **
  Testing**)
* Starter .gitignore

## Running

Project is already setup with gradle. To run the app:

* Navigate to top-level directory on the command line
* Run `./gradlew run` to run all days
* Run `./gradlew run --args $DAY` where `$DAY` is an integer to run a specific day
* Run `./gradlew run --args "$DAY1 $DAY2 $ANOTHERDAY"` to run a subset of days

### Testing

Project includes JUnit and AssertJ and a stub unit test to get you going. To run all tests:

* Navigate to top-level directory on the command line
* Run `./gradlew test`
* Add `--info`, `--debug` or `--stacktrace` flags for more output

By default, instantiations of `Day` classes in tests will use the input files in `src/test/resources`, _not_ those
in `src/main/resources`. This hopefully gives you flexibility - you could either just copy the real input
into `src/test/resources` if you want to test the actual answers, or you could add a file of test data based on the
examples given on the Advent of Code description for the day. The stub `Day1Test` class shows a test of the
functionality of `Day1` where the test input differs from the actual input.

### Architecture

* Inputs go into `src/main/resources` and follow the naming convention `input_day_X.txt`
* Solutions go into `src/main/kotlin/days` and implement the `Puzzle` interface
* Solutions follow the naming convention `DayX`
* It is assumed all solutions will have two parts
* It is assumed that the puzzle input is provided through the primary constructor
* You can use the `InputReader` methods for reading input
* To get started simply replace `src/main/resources/input_day_1.txt` with the real input and the solutions in `Day1` with your own