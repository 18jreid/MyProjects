'''
    # Justin Reid
    # CS-1400-001
    # ASSN11
'''

import turtle
import random

def setup():
    turtle.speed(0)
    turtle.setup(1000, 800)

def reset():
    turtle.clearscreen()
    turtle.speed(0)

def done():
    turtle.done()

def setRandomColor():
    randColor = random.randint(1,5)
    if randColor == 1:
        return "green"
    elif randColor == 2:
        return "blue"
    elif randColor == 3:
        return "red"
    elif randColor == 4:
        return "purple"
    elif(randColor) == 5:
        return "black"

def drawRectangle(height, width, rotation):
    turtle.setheading(rotation)
    turtle.forward(height)
    turtle.right(90)
    turtle.forward(width)
    turtle.right(90)
    turtle.forward(height)
    turtle.right(90)
    turtle.forward(width)


def drawRectanglePattern(centerX, centerY, offset, height, width, count, rotation):
    rectNum = 0
    for i in range(count):
        turtle.pencolor(setRandomColor())
        turtle.penup()
        turtle.goto(centerX, centerY)
        turtle.forward(offset)
        turtle.pendown()
        drawRectangle(height, width, rotation)
        rectNum += 360 / count
        rotation += 360 / count
        turtle.penup()
        turtle.goto(centerX, centerY)
        turtle.setheading(rectNum)
        turtle.penup()

def drawCirclePattern(centerX, centerY, offset, radius, count):
    circNumber = 0
    for i in range(count):
        turtle.pencolor(setRandomColor())
        turtle.penup()
        turtle.goto(centerX, centerY)
        turtle.forward(offset)
        turtle.pendown()
        turtle.circle(radius)
        circNumber += 360 / count
        turtle.penup()
        turtle.goto(centerX, centerY)
        turtle.setheading(circNumber)
        turtle.penup()

def drawSuperPattern(num):
    for i in range(num):
        randPattern = random.randint(1, 2)
        if randPattern == 1:
            centerX = random.randint(-500, 500)
            centerY = random.randint(-400, 400)
            offset = random.randint(-100, 100)
            height = random.randint(-250, 250)
            width = random.randint(-250, 250)
            count = random.randint(1, 200)
            rotation = random.randint(-360, 360)
            drawRectanglePattern(centerX, centerY, offset, height, width, count, rotation)
        elif randPattern == 2:
            centerX = random.randint(-500, 500)
            centerY = random.randint(-400, 400)
            offset = random.randint(-100, 100)
            radius = random.randint(-50, 50)
            count = random.randint(1, 200)
            drawCirclePattern(centerX, centerY, offset, radius, count)
