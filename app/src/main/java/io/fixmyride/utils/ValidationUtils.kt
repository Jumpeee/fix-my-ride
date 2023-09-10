package io.fixmyride.utils

object ValidationUtils {
    /** Returns a list of indexes of fields whose values are empty. */
    fun stringEmptyIndexes(vararg requiredData: String): List<Int> {
        val emptyIndexes = ArrayList<Int>()
        for (i in requiredData.indices) {
            if (requiredData[i].isBlank()) {
                emptyIndexes.add(i)
            }
        }

        return emptyIndexes
    }
}