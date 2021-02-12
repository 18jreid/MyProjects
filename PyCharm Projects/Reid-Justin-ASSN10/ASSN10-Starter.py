'''
   # Justin Reid
   # CS-1400-001
   # ASSN10
'''

from chessboard import *

# get user input

def main():
    startX, startY = eval(input("Enter starting positions(x, y): "))
    width = input("Enter width: ")
    height = input("Enter height: ")

    #### End Add Code to get input from user ####
    if width == "" and height == "":
        drawChessboard(startX, startY)
    elif height == "":
        drawChessboard(startX, startY, width=eval(width))
    elif width == "":
        drawChessboard(startX, startY, height=eval(height))
    else:
        drawChessboard(startX, startY, eval(width), eval(height))


main()