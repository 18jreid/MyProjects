class Card:
    def __init__(self, house:str, num):
        self.__house = house
        self.__count = 0

        if num % 11 == 0:
            self.__cardNum = "Jack"
            self.__count = -1
        elif num % 12 == 0:
            self.__cardNum = "Queen"
            self.__count = -1
        elif num % 13 == 0:
            self.__cardNum = "King"
            self.__count = -1
        elif num % 14 == 0:
            self.__cardNum = "Ace"
            self.__count = -1
        else:
            self.__cardNum = num
            if num == 7:
                self.__count = 0
            elif num == 8:
                self.__count = 0
            elif num == 9:
                self.__count = 0
            elif num == 10:
                self.__count = -1
            else:
                self.__count = 1
    
    def getHouse(self):
        return self.__house

    def getCardNumber(self):
        if (self.__cardNum == "Jack"):
            return 10
        elif (self.__cardNum == "Queen"):
            return 10
        elif (self.__cardNum == "King"):
            return 10
        elif (self.__cardNum == "Ace"):
            return 10
        else:
            return int(self.__cardNum)

    def printCard(self):
        print(self.__cardNum, "of", self.__house)

    def getCount(self):
        return self.__count