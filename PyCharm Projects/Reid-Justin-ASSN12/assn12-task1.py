import turtle


class Face:
    def __init__(self):
        self.__smile = True
        self.__happy = True
        self.__dark_eyes = True

    def __draw_head(self):
        if self.__happy:
            turtle.color("yellow")
        else:
            turtle.color("red")
        turtle.penup()

        turtle.goto(0, 0)
        turtle.pendown()
        turtle.dot(200)

    def __draw_eyes(self):
        if self.__dark_eyes:
            turtle.color("black")
        else:
            turtle.color("blue")
        turtle.penup()
        turtle.goto(-30, 45)
        turtle.pendown()
        turtle.dot(30)
        turtle.penup()
        turtle.goto(30, 45)
        turtle.pendown()
        turtle.dot(30)

    def __draw_mouth(self):
        turtle.color("black")
        turtle.width(5)
        if self.__smile:
            turtle.penup()
            turtle.goto(-55, -30)
            turtle.setheading(332)
            turtle.pendown()
            turtle.circle(125, 60)
        else:
            turtle.penup()
            turtle.goto(-55, -30)
            turtle.setheading(28)
            turtle.pendown()
            turtle.circle(-125, 60)

    def draw_face(self):
        turtle.clear()
        self.__draw_head()
        self.__draw_eyes()
        self.__draw_mouth()

    def is_smile(self):
        return self.__smile

    def is_happy(self):
        return self.__happy

    def is_dark_eyes(self):
        return self.__dark_eyes

    def change_mouth(self, mouth):
        if mouth == "frown":
            self.__smile = False
        else:
            self.__smile = True
        self.draw_face()

    def change_emotion(self, emotion):
        if emotion == "angry":
            self.__happy = False
        else:
            self.__happy = True
        self.draw_face()

    def change_eyes(self, eyes):
        if eyes == "blue":
            self.__dark_eyes = False
        else:
            self.__dark_eyes = True
        self.draw_face()


def main():
    face = Face()
    face.draw_face()

    done = False

    while not done:
        print("Change My Face")
        mouth = "frown" if face.is_smile() else "smile"
        emotion = "angry" if face.is_happy() else "happy"
        eyes = "blue" if face.is_dark_eyes() else "black"
        print("1) Make me", mouth)
        print("2) Make me", emotion)
        print("3) Make my eyes", eyes)
        print("0) Quit")

        menu = eval(input("Enter a selection: "))

        if menu == 1:
            face.change_mouth(mouth)
        elif menu == 2:
            face.change_emotion(emotion)
        elif menu == 3:
            face.change_eyes(eyes)
        else:
            break

    print("Thanks for Playing")

    turtle.hideturtle()
    turtle.done()


main()