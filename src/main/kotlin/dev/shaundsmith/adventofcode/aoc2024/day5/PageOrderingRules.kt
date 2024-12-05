package dev.shaundsmith.adventofcode.aoc2024.day5

import io.github.oshai.kotlinlogging.KotlinLogging

data class PageOrderingRules(private val rules: Map<Int, List<Int>>) {

    private val logger = KotlinLogging.logger {}

    fun makeValid(pages: List<Int>, log: Boolean = true): List<Int> {

        if (pages.size == 1) {
            return pages
        }

        val element = pages.first { isValidFollowingPage(it, pages.minus(it)) }

        val result = listOf(element) + makeValid(pages.minus(element), false)
        if (log) {
            logger.debug { "Made $pages valid as $result" }
        }
        return result
    }

    fun isValid(pages: List<Int>): Boolean {

        logger.debug { "Verifying pages: $pages" }
        for (i in 0..<(pages.size - 1)) {
            if (!isValidFollowingPage(pages[i], pages.subList(i + 1, pages.size))) {
                logger.debug { "Page $i is not valid (${pages[i]})"}
                return false
            }
        }

        logger.debug { "Is Valid" }
        return true
    }

    private fun isValidFollowingPage(page: Int, followingPages: List<Int>): Boolean {

        if (rules[page] == null) {
            return false
        }

        return rules[page]?.containsAll(followingPages)!!
    }

    private fun List<Int>.minus(elementToRemove: Int): List<Int> {

        return this.filter { it != elementToRemove }
    }


    class Builder() {
        private val rules = mutableMapOf<Int, MutableList<Int>>()

        fun addRule(rule: Pair<Int, Int>) {
            if (!rules.containsKey(rule.first)) {
                rules[rule.first] = mutableListOf()
            }
            rules[rule.first]?.add(rule.second)
        }
        fun build() = PageOrderingRules(rules)
    }

}
