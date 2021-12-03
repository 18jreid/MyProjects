// CS4700 Assignment 6
// Justin Reid A02276642

// Do not remove or rename the package
package lists

import kotlin.reflect.KFunction2

/***
 * the countingNumbers function takes an integer n as input, and returns a list from 1, 2, 3, .. n
 * INPUT: integer n, desired ceiling number
 * OUTPUT: a list of numbers up to n
 */
fun countingNumbers(n : Int?) : List<Int>? {
    if (n == null)
        return null
    else {
        val myList: MutableList<Int> = mutableListOf<Int>()

        if (n == 0)
            return myList
        else
            for (i in n downTo 1)
                myList.add(i)
        return myList.reversed()
    }
}

/***
 * the evenNumbers function takes an integer n as input, and returns a list of even numbers up until n
 * INPUT: integer n, desired ceiling number up to n
 * OUTPUT: list of even numbers up to n
 */
fun evenNumbers(n: Int?): List<Int>? {
    val myList = countingNumbers(n)

    if (myList != null) {
        return myList.filter { x -> x % 2 == 0 }
    } else {
        return null
    }
}

/***
 * the primeNumbers function takes an integer n as input, and returns a list of prime numbers up to n
 * INPUT: integer n, desired ceiling number
 * OUTPUT: list of prime numbers up to n
 */
fun primeNumbers(n: Int?) : List<Int>? {
    val myList = countingNumbers(n)
    val myFilteredList: MutableList<Int> = mutableListOf<Int>()

    if (myList != null) {
        myList.forEach { x ->
            var flag = false

            for (i in x downTo 1) {
                if (i == 1)
                    continue
                else if (i == x)
                    continue

                else if (x % i == 0) {
                    flag = true
                }
            }

            if (!flag) {
                myFilteredList.add(x)
            }
        }
        return myFilteredList
    } else {
        return null
    }
}

/***
 * the merge function takes two lists, and returns a merged list
 * INPUT: list a and list b to be merged
 * OUTPUT: a sorted merged list of a and b
 */
fun<T : Comparable<T>> merge (a : List<T>?, b : List<T>?) : List<T>? {
    return if (a == null || b == null) {
        null
    }
    else if (a.isEmpty()) {
        b
    }
    else if (b.isEmpty()) {
        a
    }
    else {
        val myList: MutableList<T> = a.plus(b) as MutableList<T>
        myList.sorted()
    }
}

/***
 * the subLists function takes a list, and creates a list of lists from the list
 * INPUT: list desired to turn into list of lists
 * OUTPUT: a list of lists [[1], [1, 2], .. [1, .. n]]
 */
fun subLists(a: List<Int>?) : List<List<Int>>? {
    if (a == null) {
        return null
    }
    else if (a.isEmpty()) {
        return mutableListOf()
    }
    else {
        val myList = mutableListOf<MutableList<Int>>()
        var index = 0

        while (index != a.size) {
            val tmpList: MutableList<Int> = mutableListOf()
            for (i in 0 until (index + 1))
                tmpList.add(a[i])
            index += 1
            myList.add(tmpList)
        }

        return myList
    }
}

/***
 * the countElements function takes a list (can be a list of lists), and returns total number of elements
 * INPUT: desired list to be counted
 * OUTPUT: count of items in list
 */
fun countElements(a: List<List<Int>?>?): Any {
    return if (a == null) {
        0
    } else {
        var count = 0
        a.forEach {
            if (it != null) {
                count += it.size
            }
        }

        count
    }
}

/***
 * the listApply function takes in a function, and applies the function to every list in the list
 * INPUT: desired function to be applied
 * OUTPUT: list of applied outputs from the function for each list
 */
fun listApply(f: KFunction2<Int, Int, Int>, a: List<List<Int>>?): List<Int>? {
    if (a == null) {
        return null
    }

    val myFinalList: MutableList<Int> = mutableListOf()
    for (item in a) {
        var runningTotal = 0
        var myList: MutableList<Int> = item.toMutableList()
        while (myList.isNotEmpty()) {
            if (myList.size == 1) {
                runningTotal += myList[0]
                myList.clear()
            }
            else if (myList.size >= 2) {
                runningTotal += f(myList[0], myList[1])
                myList.removeAt(0)
                myList.removeAt(0)
            }
        }
        myFinalList.add(runningTotal)
    }

    return myFinalList.toList()
}

/***
 * the composeList function takes a list of functions and returns a function for which an integer can be applied
 * INPUT: list of functions to be applied to integer n
 * OUTPUT: a function which takes integer n and applies it to the list of functions one by one
 */
fun composeList(a: List<(Int) -> Int>) : (Int) -> Int {
    var total = 0

    return {result ->
        total = result

        for (item in a) {
            total = item(total)
        }

        total
    }
}