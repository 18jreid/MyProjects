from Deck import *

class Player:
    def __init__(self) -> None:
        self.__deck = Deck()

    def drawCard(self):
        self.__deck.drawCard()
    
    def printDeck(self):
        self.__deck.printDeck()
    
    def getDeckCardCount(self):
        self.__deck.getCardCount()