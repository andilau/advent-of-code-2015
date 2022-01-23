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