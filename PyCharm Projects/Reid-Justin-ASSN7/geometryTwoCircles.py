# Justin Reid
# CS 1400-001
# ASSN 7 Task 2

'''
Software Development Process:

Requirement Specification -
    Write a program that prompts the user to enter the center coordinates and radii of two circles and determines
    whether the second circle is inside the first or overlaps with the first, as shown in Figure 4.11. (Hint: circle2 is
    inside circle1 if the distance between the two centers <= | r1 - r2| and circle2 overlaps circle1 if the distance
    between the two centers <= r1 + r2 .)

System Analysis -
    I need to make sure that when collecting the users input values that they are all in float values, because I can't
    perform math operations between a string and a float.

    Equations needed are: circle2 is inside circle1 if the distance between the two centers <= | r1 - r2|, or circle1
    is inside circle2 if the distance between the two centers is >= | r2 - r1|.

    circle2 overlaps circle1 if the distance between the two centers <= r1 + r2 and vice versa.

    Must use distance formula math.sqrt(math.pow(x2 - x1, 2) + math.pow(y2 - y1, 2))

System Design -
    1. Introduction
    2. Ask user for radius and coordinates of circle 1.
    3. Ask user for radius and coordinates of circle 2.
    4. Calculate whether the circles overlap or one is inside another.
    5. Tell the user what the circles are doing.

Testing -
    Test 1:
        Input = (0.5, 5.1) & the radius is 13
                (1, 1.7) & the radius is 4.5
        Output = circle2 is inside circle1

    Test 2:
        Input = (4.4, 5.7) & the radius is 5.5
                (6.7, 3.5) & the radius is 3
        Output = circle2 overlaps circle1

    Test 3:
        Input = (4.4, 5.5) & the radius is 1
                (5.5, 7.2) & the radius is 1
        Output = circle2 does not overlap circle1
'''
import math

# Introduction:
print("Welcome to Justin's circle interpreter!\n" + "\n")

# User inputs:
import math

# Introduction:
print("Welcome to Justin's circle interpreter!\n" + "\n")

# User inputs:
x1 = float(input("Enter circle 1's x-coordinate: "))
y1 = float(input("Enter circle 1's y-coordinate: "))
r1 = float(input("Enter circle 1's radius: "))
x2 = float(input("Enter circle 2's x-coordinate: "))
y2 = float(input("Enter circle 2's y-coordinate: "))
r2 = float(input("Enter circle 2's radius: "))

distanceBetweenCircles = math.sqrt(math.pow(x2 - x1, 2) + math.pow(y2 - y1, 2))

# Circle interpretation:

if distanceBetweenCircles <= (r1 - r2):
    print("Circle 2 is inside of circle 1.")

elif distanceBetweenCircles <= (r2 - r1):
    print("Circle 1 is inside of circle 2.")

elif distanceBetweenCircles <= (r1 + r2):
    print("Circle 2 overlaps circle 1.")

elif distanceBetweenCircles >= (r1 + r2):
    print("Circle 2 does not overlap circle 1.")