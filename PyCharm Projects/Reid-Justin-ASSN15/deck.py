from card import Card
import random

class Deck:
    def __init__(self):
        self.shuffle()

    def shuffle(self):
        self.__deck = []
        for i in range(1, 121):
            self.__deck.append(Card(i))
        random.shuffle(self.__deck)

    def draw(self):
        return self.__deck.pop()
