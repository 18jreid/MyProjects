from circle import Circle
from rectangle import Rectangle
import turtle as t

def main():
    notDone = True
    drawingList = []

    while notDone:
        print("(1): Enter Circle")
        print("(2): Enter Rectangle")
        print("(3): Remove Shapes")
        print("(4): Draw Shapes")
        print("(5): Exit")
        userInput = int(input("Enter selection: "))

        if userInput == 1:
            xCor, yCor = eval(input("Enter your coordinates (X, Y): "))
            radius = int(input("Enter your radius: "))
            circColor = input("Enter a color (Red, Yellow, Blue, Green):")
            userCircle = Circle(xCor, yCor, radius, circColor)
            drawingList.append(userCircle)

        if userInput == 2:
            xCor, yCor = eval(input("Enter your coordinates (X, Y): "))
            height = int(input("Enter your height: "))
            width = int(input("Enter your width: "))
            recColor = input("Enter a color (Red, Yellow, Blue, Green):")
            userRectangle = Rectangle(xCor, yCor, height, width, recColor)
            drawingList.append(userRectangle)

        if userInput == 3:
            print("Which item in your list of", len(drawingList), "objects would you like to remove?")
            userRemoval = int(input())
            drawingList.__delitem__(userRemoval - 1)

        if userInput == 4:
            t.clear()
            for i in range(len(drawingList)):
                drawingList[i].draw()

        if userInput == 5:
            t.done()
            notDone = False
    print("Thanks for playing!")
main()