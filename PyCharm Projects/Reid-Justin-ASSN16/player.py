class Player:
    def __init__(self, cash=100):
        self.__cards = []
        self.__cash = cash

    def getCash(self):
        return self.__cash

    def betCash(self, betAmount):
        self.__cash = self.__cash - betAmount
        return self.__cash

    def addCash(self, totalAmountBet):
        self.__cash = self.__cash + totalAmountBet

    def addCard(self, card):
        self.__cards.append(card)

    def showCards(self):
        return self.__cards

    def removeCards(self):
        return self.__cards.clear()

    def showLastCard(self):
        return self.__cards[-1]

    def getCardTotal(self):
        convertedCards = []
        for i in range(len(self.__cards)):
            if self.__cards[i].getCardValue() >= 10 and self.__cards[i].getCardValue() <= 13:
                convertedCards.append(10)
            elif self.__cards[i].getCardValue() == 1:
                if sum(convertedCards) > 21:
                    convertedCards.append(1)
                else:
                    convertedCards.append(11)
            else:
                convertedCards.append(self.__cards[i].getCardValue())
        return sum(convertedCards)