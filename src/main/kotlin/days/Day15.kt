package days

@AdventOfCodePuzzle(
    name = "Science for Hungry People",
    url = "https://adventofcode.com/2015/day/15",
    date = Date(day = 15, year = 2015)
)
class Day15(input: List<String>) : Puzzle {
    private val ingredients = input.map(Ingredient::from)

    override fun partOne(): Int =
        combinations(ingredients.size, 100)
            .map { amounts -> makeCookie(ingredients, amounts) }
            .maxOf(Cookie::score)

    override fun partTwo(): Int =
        combinations(ingredients.size, 100)
            .map { amounts -> makeCookie(ingredients, amounts) }
            .filter { cookie -> cookie.calories() == 500 }
            .maxOf(Cookie::score)

    private fun makeCookie(ingredients: List<Ingredient>, amounts: IntArray) =
        amounts.withIndex()
            .associate { amount -> ingredients[amount.index] to amount.value }
            .let { Cookie(it) }

    internal fun combinations(): Int = combinations(ingredients.size, 100).size

    class Cookie(private val recipe: Map<Ingredient, Int>) {
        fun score(): Int =
            recipe.keys.sumOf { it.capacity * recipe[it]!! }.let { if (it > 0) it else 0 } *
                    recipe.keys.sumOf { it.durability * recipe[it]!! }.let { if (it > 0) it else 0 } *
                    recipe.keys.sumOf { it.flavor * recipe[it]!! }.let { if (it > 0) it else 0 } *
                    recipe.keys.sumOf { it.texture * recipe[it]!! }.let { if (it > 0) it else 0 }

        fun calories() = recipe.keys.sumOf { it.calories * recipe[it]!! }
    }

    data class Ingredient(
        val name: String,
        val capacity: Int,
        val durability: Int,
        val flavor: Int,
        val texture: Int,
        val calories: Int
    ) {

        companion object {
            private val PATTERN =
                Regex("""^(\w+): capacity (-?\d+), durability (-?\d+), flavor (-?\d+), texture (-?\d+), calories (\d+)""")

            fun from(string: String): Ingredient =
                PATTERN.matchEntire(string)?.destructured?.let { (name, capacity, durability, flavor, texture, calories) ->
                    Ingredient(
                        name,
                        capacity.toInt(),
                        durability.toInt(),
                        flavor.toInt(),
                        texture.toInt(),
                        calories.toInt()
                    )
                } ?: error("Invalid format: $string")
        }
    }
}