from Card import *
import random

class Deck:
    def __init__(self) -> None:
        # Initialize cards
        self.__cards = []
        self.__count = 0
        for house in range(0, 24):
            for num in range(2, 15):
                houseName = ""
                if house == 0:
                    houseName = "Hearts"
                elif house == 1:
                    houseName = "Diamonds"
                elif house == 2:
                    houseName = "Clubs"
                elif house == 3:
                    houseName = "Spades"

                card = Card(houseName, num)
                self.__cards.append(card)
        
        self.shuffleDeck()

    def drawCard(self):
        if len(self.__cards) == 0:
            print("You're out of cards!")
        else:
            card = self.__cards.pop()
            self.__count += card.getCount()
            return card
    
    def getCardCount(self):
        print("Number of cards in deck:", len(self.__cards))
        return len(self.__cards)
    
    def printDeck(self):
        if len(self.__cards) == 0:
            print("Deck is empty!")
        else:
            for card in self.__cards:
                card.printCard()

    def shuffleDeck(self):
        random.shuffle(self.__cards)

    def getTrueCount(self):
        return self.__count / 6
