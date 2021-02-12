# ASGN5 Task 2
# Justin Reid
# CS1400-001

# Introduction:
print("Welcome to Justin's archery target generator!\n")

# User inputs:
xCord = float(input("Enter the X coordinate for your target: "))
yCord = float(input("Enter the Y coordinate for your target: "))
DIAMETER = float(input("Enter the diameter for the bulls-eye: "))
userRadius = DIAMETER / 2
RADIUS = DIAMETER / 2

# Construct the user's target:
# Fourth circle:
import turtle
tr = turtle.Turtle()
tr.speed(0)

userRadius *= 4
tr.color("black")
tr.penup()
tr.goto(xCord, yCord)
tr.setheading(270)
tr.forward(RADIUS * 4)
tr.setheading(0)
tr.pendown()
tr.begin_fill()
tr.circle(userRadius)
tr.end_fill()

# Third circle:
userRadius -= RADIUS
tr.color("lightSkyblue")
tr.penup()
tr.setheading(90)
tr.forward(RADIUS)
tr.setheading(0)
tr.pendown()
tr.begin_fill()
tr.circle(userRadius)
tr.end_fill()

# Second circle:
userRadius -= RADIUS
tr.color("orangered")
tr.penup()
tr.setheading(90)
tr.forward(RADIUS)
tr.setheading(0)
tr.pendown()
tr.begin_fill()
tr.circle(userRadius)
tr.end_fill()

# First circle:
tr.color("yellow")

userRadius -= RADIUS
tr.penup()
tr.setheading(90)
tr.forward(RADIUS)
tr.setheading(0)
tr.pendown()
tr.begin_fill()
tr.circle(userRadius)
tr.end_fill()

tr.color("black")
tr.penup()
tr.goto(xCord, yCord)
tr.setheading(90)

turtle.done()