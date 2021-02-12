import turtle
from chessboard import Chessboard


def main():
    startX, startY = eval(input("Enter a starting point: "))
    width = input("Input a width: ")
    height = input("Input a height: ")

    if width == "" and height == "":
        chessboard = Chessboard(startX, startY)
    elif height == "":
        chessboard = Chessboard(startX, startY, width=eval(width))
    elif width == "":
        chessboard = Chessboard(startX, startY, height=eval(height))
    else:
        chessboard = Chessboard(startX, startY, eval(width), eval(height))

    chessboard.draw(startX, startY, eval(width), eval(height))

    turtle.hideturtle()
    turtle.done()


main()