class Card:
    def __init__(self, cardID):
        self.__cardID = cardID
        self.__coin = cardID % 2
        self.__value = ((cardID % 40) // 2) + 1
        self.__hand = cardID // 40

    def getCoin(self):
        if self.__coin == 0:
            self.__coin = "Heads"
        else:
            self.__coin = "Tails"
        return self.__coin

    def getValue(self):
        return self.__value

    def getHand(self):
        if self.__hand == 1:
            self.__hand = "Rock"
        elif self.__hand == 2:
            self.__hand = "Paper"
        elif self.__hand == 0:
            self.__hand = "Scissors"
        return self.__hand

    def getCardId(self):
        return self.__cardID

    def __str__(self):
        return str(self.getValue()) + " of " + str(self.getHand()) + " " + str(self.getCoin())