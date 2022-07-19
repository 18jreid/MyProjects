from Deck import *

class Player:
    def __init__(self, deck:Deck):
        self.__deck = deck
        self.__hand = []
        self.__handTotal = 0

    def drawCard(self):
        card = self.__deck.drawCard()
        self.__hand.append(card)
        card.printCard()
        self.__handTotal += card.getCardNumber()
    
    def printDeck(self):
        self.__deck.printDeck()
    
    def printHand(self):
        for x in self.__hand:
            x.printCard()

    def printDealersFirst(self):
        print(self.__hand[1].printCard())

    
    def getDeckCardCount(self):
        self.__deck.getCardCount()

    def getTotalCount(self):
        return self.__handTotal

    def setBet(self, value):
        self.__bet = value
    
    def getBet(self):
        return self.__bet

    def clearHand(self):
        self.__hand = []
        self.__handTotal = 0

    def setDeck(self, newDeck):
        self.__deck = newDeck