'''
   # Justin Reid
   # CS-1400-001
   # ASSN10
'''

import turtle

# draw chessboard
def drawChessboard(startX, startY, width=250, height=250):
    turtle.speed(0)
    turtle.penup()
    turtle.goto(startX, startY)
    turtle.pendown()
    turtle.forward(width)
    turtle.left(90)
    turtle.forward(height)
    turtle.left(90)
    turtle.forward(width)
    turtle.left(90)
    turtle.forward(height)
    turtle.left(90)
    drawAllRectangles(startX, startY, width, height)

# draw all rectangles
def drawAllRectangles(startX, startY, width, height):
    for i in range(1, 5):
        drawRectangle(startX, startY, width, height)
        drawRectangle(startX + 2 * (width / 8), startY, width, height)
        drawRectangle(startX + 4 * (width / 8), startY, width, height)
        drawRectangle(startX + 6 * (width / 8), startY, width, height)
        drawRectangle(startX + (width / 8), startY + (height / 8), width, height)
        drawRectangle(startX + 3 * (width / 8), startY + (height / 8), width, height)
        drawRectangle(startX + 5 * (width / 8), startY + (height / 8), width, height)
        drawRectangle(startX + 7 * (width / 8), startY + (height / 8), width, height)
        startY = startY + 2 * (height / 8)
    turtle.done()

# draw one rectangle
def drawRectangle(startX, startY, width, height):
    turtle.speed(0)
    turtle.penup()
    turtle.goto(startX, startY)
    turtle.pendown()
    turtle.begin_fill()
    turtle.setheading(90)
    turtle.forward(height / 8)
    turtle.setheading(0)
    turtle.forward(width / 8)
    turtle.setheading(270)
    turtle.forward(height / 8)
    turtle.setheading(180)
    turtle.forward(width / 8)
    turtle.end_fill()
