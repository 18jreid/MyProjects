# Justin Reid
# CS-1400-001
# ASSN 8 Task 1

'''

Software Development Plan:

    Requirement Specification -
        (Perfect number) A positive integer is called a perfect number if it is equal to the sum of all of its positive
        divisors, excluding itself. For example, 6 is the first perfect number, because 6 = 3 + 2 + 1. The next is 28 =
        14 + 7 + 4 + 2 + 1. There are four perfect numbers less than 10,000. Write a program to find these four numbers.
        Fewer than 16,000,000 iterations of the inner loop are completed. Print how long the program has run for, and
        print how many iterations of loops have been completed.

    System Analysis -
        When looking for perfect numbers, I need to make sure it's always in terms of an integer and not float. To look
        for these perfect numbers, I realize that the lowest common factor will always be 2. Therefore when searching
        for these numbers, I don't need to look after num // 2. Also, if the combination of the divisors is ever greater
        than the num, then I can also stop looking. I know there are only 4 perfect numbers on the interval of (1,
        10,000), so when I find the fourth number, I can end the loop. To find the time, I can take the time module at
        the beginning of the program, and the end, and subtract the two to get the time in seconds of the program.

    System Design -
        1. Introduction to the program.
        2. Take the beginning time of the program.
        3. Find the perfect numbers on the interval of (1, 10,000).
        4. Take the end time of the program, and subtract the beginning time from the end time to get the programs
        runtime in seconds. Then display the seconds to the second decimal place.
        5. Print how long the program took, and how many iterations of loops there were.

    Test -
        Test 1:
            Input: No user input.
            Output: The assignment tells us that there are four perfect numbers, and that one is 6. Find 6.

        Test 2:
            Input: No user input.
            Output: The assignment tells us that there are four perfect numbers, and that one is 28. Find 28.

'''
import time

# Introduction:

print("Welcome to Justin's perfect numbers generator!\n")
print("\nThe perfect numbers on the interval of (1, 10,000) are:")

# Beginning time of program:

begTime = time.time()

# Find perfect numbers:

iterations = 0
perfectNum = 0

for num in range(1, 10001):
    result = 0
    for i in range(1, num // 2 + 1):
        iterations += 1
        if result > num:
            break
        elif num % i == 0:
            result = result + i

    if num == result:
        perfectNum += 1
        print(num)
    if perfectNum == 4:
        break

endTime = time.time()

# End time of program:

runtime = endTime - begTime

# Display the time of the program, and the number of iterations of the loop.

print("\nThis program was", round(runtime % 60, 2), "seconds long.")
print("There were", iterations, "iterations.")