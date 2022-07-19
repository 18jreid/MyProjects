import sys
from tracemalloc import start
from Player import *
from Deck import *

deck = Deck()
global isPlaying
isPlaying = True
global playerBusted
playerBusted = False
global dealerBusted
dealerBusted = False
global dealerHasDrawn
dealerHasDrawn = False
global totalBet
totalBet = 0
global playersMoney
playersMoney = 1000
global totalCountingCount

player = Player(deck)
dealer = Player(deck)

def whoWon():
    global playersMoney
    if (playerBusted):
        print("You lose")
        print("Bank Account: ", playersMoney)
        return
    if (dealerBusted):
        print("You win!")
        playersMoney += totalBet * 2
        print("Bank Account: ", playersMoney)
        return
    if (player.getTotalCount() > dealer.getTotalCount()):
        print("You win!")
        playersMoney += totalBet * 2
        print("Bank Account: ", playersMoney)
        return
    if (player.getTotalCount() < dealer.getTotalCount()):
        print("You lose")
        print("Bank Account: ", playersMoney)
        return
    if (player.getTotalCount() == dealer.getTotalCount()):
        print("PUSH")
        playersMoney += totalBet
        print("Bank Account: ", playersMoney)
        return

def dealersTurn():
    global dealerHasDrawn
    dealerHasDrawn = True
    while (dealer.getTotalCount() < 17):
        dealer.drawCard()
        if (dealer.getTotalCount() >=22):
            dealer.printHand()
            print("THE DEALER BUSTS AT", dealer.getTotalCount())
            global dealerBusted
            dealerBusted = True
    if (dealer.getTotalCount() <= 21):
        dealer.printHand()
        print("Dealer stands at ", dealer.getTotalCount())
    whoWon()


def playRound():
    global playerBusted
    playerBusted = False
    global dealerBusted
    dealerBusted = False
    global dealerHasDrawn
    dealerHasDrawn = False
    global isPlaying
    isPlaying = True
    global deck
    if deck.getCardCount() == 0:
        deck = Deck()
        player.setDeck(deck)
        dealer.setDeck(deck)
        print("New deck shuffled!")
    player.clearHand()
    dealer.clearHand()
    player.drawCard() # Dealer gives player first card
    dealer.drawCard() # Dealer gets first card
    player.drawCard() # Player gets their last card
    dealer.drawCard() # Dealer gets last card
    
    while isPlaying:
        print("---------------------------")
        print("---------------------------")
        print("YOUR HAND:")
        player.printHand()
        print("\n\nDEALERS HAND")
        if (dealerHasDrawn == False):
            dealer.printDealersFirst()
        else:
            dealer.printHand()

        print("\n\nYour Total: ", player.getTotalCount())

        # OPTIONS FOR PLAYER
        print("Count is: ", deck.getTrueCount())
        print("\n\n1) Draw Card")
        print("2) Stand")
        print("3) Double down")
        print("4) Change Bet")
        userInput = int(input("What would you like to do: "))
        if userInput == 1:
            print("\n\n\n")
            player.drawCard()
        elif userInput == 2:
            dealersTurn()
            isPlaying = False
        
        if (player.getTotalCount() > 21):
            print("Your Total: ", player.getTotalCount())
            playerBusted = True
            isPlaying = False

            whoWon()
        
        print("---------------------------")
        print("---------------------------")

        print()

def startRound():
    userInput = int(input("What's your bet?: "))
    global totalBet
    totalBet = userInput
    global playersMoney
    playersMoney -= userInput
    playRound()

keepPlaying = True
while keepPlaying:
    print("Play? y/n")
    userInput = input()
    if (userInput == "y"):
        startRound()
    else:
        keepPlaying = False