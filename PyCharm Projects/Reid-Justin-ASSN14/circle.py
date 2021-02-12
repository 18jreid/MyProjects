import turtle as t

class Circle:
    def __init__(self, xCor, yCor, radius, circColor):
        self.__xCor = xCor
        self.__yCor = yCor
        self.__radius = radius
        self.__circColor = circColor

    def get_Circ_Color(self):
        return self.__circColor

    def draw(self):
        t.speed(0)
        t.color(str(self.get_Circ_Color()))
        t.penup()
        t.goto(self.__xCor, self.__yCor)
        t.setheading(270)
        t.forward(self.__radius)
        t.setheading(0)
        t.pendown()
        t.circle(self.__radius)
