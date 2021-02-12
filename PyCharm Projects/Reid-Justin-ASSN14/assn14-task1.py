from polygon import Polygon

def main():
    numberOfSides1 = eval(input("Enter the number of sides of your first polygon: "))
    numberOfSides2 = eval(input("Enter the number of sides of your second polygon: "))
    polygon1 = Polygon(numberOfSides1)
    polygon2 = Polygon(numberOfSides2)

    print("+: ", end=""), print(polygon1.__add__(polygon2))
    print("-: ", end=""), print(polygon1.__sub__(polygon2))
    print("<: ", end=""), print(polygon1.__lt__(polygon2))
    print(">: ", end=""), print(polygon1.__gt__(polygon2))
    print("==: ", end=""), print(polygon1.__eq__(polygon2))
    print("len(polygon1): ", end=""), print(polygon1.__len__())
    print("len(polygon2): ", end=""), print(polygon2.__len__())
    print("str(polygon1): ", end=""), print(polygon1.__str__())
    print("str(polygon2): ", end=""), print(polygon2.__str__())


main()