import turtle as t

class Rectangle:
    def __init__(self, xCor, yCor, height, width, recColor):
        self.__xCor = xCor
        self.__yCor = yCor
        self.__height = height
        self.__width = width
        self.__recColor = recColor

    def get_Rec_Color(self):
        return self.__recColor

    def draw(self):
        t.speed(0)
        t.color(str(self.get_Rec_Color()))
        t.penup()
        t.goto(self.__xCor, self.__yCor)
        t.pendown()
        t.forward(self.__width)
        t.left(90)
        t.forward(self.__height)
        t.left(90)
        t.forward(self.__width)
        t.left(90)
        t.forward(self.__height)
        t.left(90)
