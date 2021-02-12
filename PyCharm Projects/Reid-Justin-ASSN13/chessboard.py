import turtle

class Chessboard:

    def __init__(self, startX, startY, width=250, height=250):
        self.__startX = startX
        self.__startY = startY
        self.__width = width
        self.__height = height

    def __drawRectangle(self, __startX, __startY, __width, __height):
        turtle.speed(0)
        turtle.penup()
        turtle.goto(__startX, __startY)
        turtle.pendown()
        turtle.begin_fill()
        turtle.setheading(90)
        turtle.forward(__height / 8)
        turtle.setheading(0)
        turtle.forward(__width / 8)
        turtle.setheading(270)
        turtle.forward(__height / 8)
        turtle.setheading(180)
        turtle.forward(__width / 8)
        turtle.end_fill()

    def __drawAllRectangles(self, __startX, __startY, __width, __height):
        for i in range(1, 5):
            self.__drawRectangle(__startX, __startY, __width, __height)
            self.__drawRectangle(__startX + 2 * (__width / 8), __startY, __width, __height)
            self.__drawRectangle(__startX + 4 * (__width / 8), __startY, __width, __height)
            self.__drawRectangle(__startX + 6 * (__width / 8), __startY, __width, __height)
            self.__drawRectangle(__startX + (__width / 8), __startY + (__height / 8), __width, __height)
            self.__drawRectangle(__startX + 3 * (__width / 8), __startY + (__height / 8), __width, __height)
            self.__drawRectangle(__startX + 5 * (__width / 8), __startY + (__height / 8), __width, __height)
            self.__drawRectangle(__startX + 7 * (__width / 8), __startY + (__height / 8), __width, __height)
            __startY = __startY + 2 * (__height / 8)

    def __drawChessboard(self, __startX, __startY, __width=250, __height=250):
        turtle.speed(0)
        turtle.penup()
        turtle.goto(__startX, __startY)
        turtle.pendown()
        turtle.forward(__width)
        turtle.left(90)
        turtle.forward(__height)
        turtle.left(90)
        turtle.forward(__width)
        turtle.left(90)
        turtle.forward(__height)
        turtle.left(90)

    def draw(self, __startX, __startY, __width, __height):
        self.__drawChessboard(__startX, __startY, __width, __height)
        self.__drawAllRectangles(__startX, __startY, __width, __height)