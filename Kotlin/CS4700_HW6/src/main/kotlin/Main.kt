/**
 * The main part of the lists package consists of functions to test the functions you code, you may modify however you like as I will use my own Main.kt to test
 */
package lists

// Functions for composeList testing
fun add1(x: Int) : Int = x + 1
fun add2(x: Int) : Int = x + 2

// Functions for listApply testing
fun add(x : Int, y : Int) : Int {
    return x + y
}

fun main(args: Array<String>) {
    println("Start testing")
    // Comment out tests you don't use
    /* This is a multi-line
       comment
     */
    val n = 12
    val zero = 0
    val nAsNull = null

    // countingNumbers tests
    val countingNumbersUpToN = countingNumbers(n)
    println("countingNumbers to $n are $countingNumbersUpToN")
    val countingNumbersZero = countingNumbers(zero)
    println("countingNumbers to $zero are $countingNumbersZero")
    val countingNumbersNull = countingNumbers(nAsNull)
    println("countingNumbers to $nAsNull are $countingNumbersNull")
    println()

    // evenNumbers tests
    val evenNumbersUpToN = evenNumbers(n)
    println("evenNumbers to $n are $evenNumbersUpToN")
    val evenNumbersZero = evenNumbers(zero)
    println("evenNumbers to $zero are $evenNumbersZero")
    val evenNumbersNull = evenNumbers(nAsNull)
    println("evenNumbers to $nAsNull are $evenNumbersNull")
    println()

    // primeNumbers tests
    val primesUpToN = primeNumbers(n)
    println("primeNumbers to $n are $primesUpToN")
    val primesUpToZero = primeNumbers(zero)
    println("primeNumbers to $zero are $primesUpToZero")
    val primeNumbersNull = primeNumbers(nAsNull)
    println("primeNumbers to $nAsNull are $primeNumbersNull")
    println()

    // subLists tests
    println("subLists of $primesUpToN are ${subLists(primesUpToN)}")
    println("subLists of $evenNumbersZero are ${subLists(evenNumbersZero)}")
    println("subLists of $evenNumbersNull are ${subLists(evenNumbersNull)}")
    println()

    // countElements tests
    println("countElements of subLists of $primesUpToN are ${countElements(subLists(primesUpToN))}")
    println("countElements of subLists of even numbers to $zero are ${countElements(subLists(evenNumbersZero))}")
    println("countElements of subLists of prime numbers to $nAsNull are ${countElements(subLists(primeNumbersNull))}")
    val tempList = listOf(evenNumbersUpToN,evenNumbersZero,evenNumbersNull)
    println("countElements of $tempList is ${countElements(tempList)}")
    println()

    // merge tests
    println("merge of $primesUpToN and $countingNumbersUpToN is ${merge(primesUpToN,countingNumbersUpToN)}")
    println("merge of $primesUpToN and $countingNumbersZero is ${merge(primesUpToN,countingNumbersZero)}")
    println("merge of $primesUpToN and $countingNumbersNull is ${merge(primesUpToN,countingNumbersNull)}")
    println()

    // listApply tests
    println("listApply of ::add to $countingNumbersUpToN is ${listApply(::add,subLists(countingNumbersUpToN))}")
    println("listApply of ::add to $countingNumbersZero is ${listApply(::add,subLists(countingNumbersZero))}")
    println("listApply of ::add to $countingNumbersNull is ${listApply(::add,subLists(countingNumbersNull))}")
    println()

    // composeList test
    val functionList = listOf(::add1,::add2,::add1)
    val composedFunction = composeList(functionList)
    var result = composedFunction(n)
    println("composedFunction of [::add1,::add2,::add1] applied to $n is $result")

}
