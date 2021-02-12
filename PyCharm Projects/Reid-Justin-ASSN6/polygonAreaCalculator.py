# Justin Reid
# CS1400-001
# ASSN 6 Task 1

'''
Software Development Process:

Requirements Specification -
	Create an area calculator for a regular polygon (A regular polygon is an n-sided polygon in which all
    sides are of the same length and all angles have the same degree). Must ask user for number of sides and the lengths of the sides. Output will be in terms of ft squared.

System Analysis -
	In order to produce the correct output, the input needs to be in float format. The user will input integers or floats, and the code will analylize the inputs
	and produce the correct output in square ft. The math module will need to be used in order to use the formula correctly.

    Area of regular polygon = (numOfSides * sideLength**2) / (4 * tan(π/numOfSides))

System Design -
	1. Introduce the user to the program, and describe what it is (be friendly!).
    2. Ask the user for the number of sides.
    3. Ask the user for the length of the sides in ft(all sides are equal).
    4. Compute the area with the correct formula.
    5. Display the area in terms of ft squared.

Testing -
	Test 1:
    	Input: numOfSides = 5, sideLength = 6.5
        Expected Output: 72.69017 (Book example is wrong, should be 72 not 73.)
    Test 2:
    	Input: numOfSides = 10, sideLength = 15
        Expected Output: 1731.19698
    Problems: None
'''

# implement code:
import math
from math import tan

# Introduce User:
print("Welcome to Justin's regular polygon area calculator!\n")

# Ask for user inputs:
n = numOfSides = float(input("Enter the number of sides: "))
s = sideLength = float(input("Enter side length (in feet): "))

# define formula
area = round((numOfSides * math.pow(sideLength, 2)) / (4 * tan(math.pi / numOfSides)), 5)

# display users area
print("\nThe area of the polygon is", area, "feet squared!")
