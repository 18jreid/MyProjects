class Card:
    def __init__(self, house:str, num) -> None:
        self.__house = house

        if num % 11 == 0:
            self.__cardNum = "Jack"
        elif num % 12 == 0:
            self.__cardNum = "Queen"
        elif num % 13 == 0:
            self.__cardNum = "King"
        elif num % 14 == 0:
            self.__cardNum = "Ace"
        else:
            self.__cardNum = num
    
    def getHouse(self):
        return self.__house

    def getCardNumber(self):
        return int(self.__cardNum)

    def printCard(self):
        print(self.__cardNum, "of", self.__house)