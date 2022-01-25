package days

import java.security.MessageDigest

typealias Point = Pair<Int, Int>

fun String.toMD5(): String {
    val bytes = MessageDigest.getInstance("MD5").digest(this.toByteArray())
    return bytes.toHex()
}

fun ByteArray.toHex(): String {
    return joinToString("") { "%02x".format(it) }
}

fun <T> Collection<T>.permutations(): Set<List<T>> {
    if (isEmpty()) return setOf(emptyList())
    val result: MutableSet<List<T>> = mutableSetOf()
    for (element in this) {
        (this - element).permutations().forEach { item ->
            result.add(item + element)
        }
    }
    return result
}

fun <T> Collection<T>.arrangements(): Set<List<T>> {
    if (size <= 3) return setOf(this.toList())
    val result: MutableSet<List<T>> = mutableSetOf()
    val first = first()
    this.drop(1).arrangements().forEach { list ->
        for (index in list.indices) {
            val new = list.toMutableList()
            new.add(index, first)
            result.add(new)
        }
    }
    return result
}

fun <T> List<T>.combinations(): List<List<T>> {
    if (isEmpty()) return listOf(emptyList())

    val result = mutableListOf<List<T>>()
    dropLast(1).combinations().forEach { result.add(it + last()) }
    dropLast(1).combinations().forEach { result.add(it) }
    return result
}

fun combinations(options: Int = 4, slots: Int = 100): Set<IntArray> {
    val array = IntArray(options) { 0 }

    fun MutableSet<IntArray>.populate(array: IntArray, i: Int) {
        if (array.size - 1 == i) this.add(array.apply { this[size - 1] = slots - (0 until i).sumOf { this[it] } })
        else {
            val remain = slots - (0 until i).sumOf { array[it] }
            (remain downTo 0).forEach {
                array[i] = it
                this.populate(array.copyOf(), i + 1)
            }
        }
    }

    return mutableSetOf<IntArray>().apply { this.populate(array, 0) }
}

// - take the first (diff to 100) eq 9
// - add 0..9
// - add 0..9
// - add 0..9


/*
# Combination (order does not matter)
- with repetition

- number of combinations:
(r + n - 1)!
------------
r! * (n-1)!

r - number of slots
n - number of options
* */