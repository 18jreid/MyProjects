import kotlin.reflect.KFunction2

/*
* Extend the List class with a "tail" getter to get the tail of a list.
* Below is an example of how you would use tail
*    val a = listOf(1,2,3)
*    val t = a.tail
*    println("tail of $a is $t") // prints [2,3]
*/
val <T> List<T>.tail: List<T>
    get() = drop(1)

fun add1(x : Int) : Int = x + 1
fun add2(x : Int) : Int = x + 2

fun main() {
    val f = composeList(listOf(::add1,::add2))
    println(f(4)) // returns 7
}
/* The compose function takes as input
*     f - A function that takes as input a value of type T and returns a value of type T
*     g - A function that takes as input a value of type T and returns a value of type T
*  and returns as output the composition of the functions
*     f(g(x))
*/
fun<T> compose(f: (T)->T,  g:(T) -> T) : (T) -> T = { f(g(it)) }

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

fun evenNumbers(n: Int?): List<Int>? {
    val myList = countingNumbers(n)

    if (myList != null) {
        return myList.filter { x -> x % 2 == 0 }
    } else {
        return null
    }
}

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
        var myList: MutableList<T> = mutableListOf<T>()
        myList = a.plus(b) as MutableList<T>

        myList.sorted()
    }
}

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


