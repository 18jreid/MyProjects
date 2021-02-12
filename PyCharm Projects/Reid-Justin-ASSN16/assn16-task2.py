from player import Player
from deck import Deck
import time
dealer = []
playAgain = True

# functions
def firstRound():
    # deal cards to players and dealer, display dealers second card
    gameDeck = Deck()

    for i in range(playerNum):
        players[i].addCard(gameDeck.draw())
    for j in range(playerNum):
        players[j].addCard(gameDeck.draw())
    dealer.append(gameDeck.draw())
    dealer.append(gameDeck.draw())
    print("Each player has been dealt two cards.")
    print("The dealers second card is the", dealer[-1])

def bettingRound():
    totalAmountBet = 0
    # display balance and bet
    for i in range(playerNum):
        if players[i].getCash() <= 0:
            players[i].addCash(-10)
            continue
        else:
            print("\nCurrent Balance:", players[i].getCash())
            if players[i].getCash() < 5:
                betAmount = int(input("Must bet current balance. "))
                totalAmountBet += betAmount
                players[i].betCash(betAmount)
            else:
                print("Player", str(i + 1) + ",", "what is your bet (Min: 5 dollars)?")
                betAmount = int(input("Enter amount: "))
                if betAmount < 5:
                    print("Player", str(i + 1) + ",", "you must enter a bet higher than 5 dollars.")
                    betAmount = int(input("Enter amount: "))
                    totalAmountBet += betAmount
                else:
                    totalAmountBet += betAmount
                players[i].betCash(betAmount)
            print("New Balance:", players[i].getCash(), "\n")
            print("The cash pool is:", totalAmountBet)
    return totalAmountBet

def playingRound(totalAmountBet):
    # ask player if they want to hit or stand
    winners = []
    losers = []
    gameDeck = Deck()
    for i in range(playerNum):
        if players[i].getCash() == -10:
            continue
        else:
            if players[i].getCardTotal() < 21:
                print("Player", str(i + 1) + ",", "here are your cards.")
                print(players[i].showCards())
                print("Your total is", players[i].getCardTotal())
                print("""
                1) Hit
                2) Stand
                """)
                playerHit = True
                while playerHit:
                    playerChoice = int(input("Choice: "))
                    if playerChoice == 1:
                        drawnCard = gameDeck.draw()
                        print("You drew the", drawnCard)
                        players[i].addCard(drawnCard)
                        if players[i].getCash() == -10:
                            continue
                        else:
                            print("Your new total is", players[i].getCardTotal())
                        if players[i].getCardTotal() > 21:
                            print("You've gone over 21, you bust!")
                            print("")
                            playerHit = False
                        else:
                            print("")
                    else:
                        print("")
                        playerHit = False
            else:
                pass
    # dealers turn
    print("It is the dealer's turn.")
    print("     Dealers cards:")
    for i in dealer:
        print(i)
    print("The dealers total is:", dealerTotal())
    dealerHit = True
    while dealerHit:
        time.sleep(1)
        if dealerTotal() < 17:
            dealerDrawnCard = gameDeck.draw()
            dealer.append(dealerDrawnCard)
            print("The dealer takes a card.")
            print("The dealers new total is:", dealerTotal())
        else:
            print("The dealer holds.")
            dealerHit = False
    if dealerTotal() > 21:
        print("The dealer busts!")
    # winners
    for i in range(playerNum):
        if dealerTotal() > 21 and players[i].getCardTotal() <= 21:
            winners.append(i)
            players[i].addCash(totalAmountBet / len(winners))
        elif dealerTotal() == players[i].getCardTotal():
            print("The dealer and player", str(i + 1), "tied!")
        elif players[i].getCardTotal() < dealerTotal():
            losers.append(i)
        elif players[i].getCardTotal() > dealerTotal() and players[i].getCardTotal() <= 21:
            winners.append(i)
            players[i].addCash(totalAmountBet / len(winners))

    if len(winners) > 0:
        for i in winners:
            print("Player", str(i+1), "wins!")
    elif len(losers) > 0:
        print("The dealer wins!")

    # display users balance
    for i in range(playerNum):
        if players[i].getCash() == -10:
            continue
        else:
            print("")
            print("Player", str(i + 1) + "'s new balance is", players[i].getCash())
            print("")

    # ask to play again
    print("""
    Play again?
    1) Yes
    2) No
    """)
    userChoice = int(input("Choice: "))
    if userChoice == 1:
        for i in players:
            i.removeCards()
        dealer.clear()
        return True
    else:
        print("Thank you for playing!")
        print("This was hard to code haha :(")
        return False

def addPlayer(numOfPlayers):
    players = [Player() for _ in range(numOfPlayers)]
    return players

def dealerTotal():
    dealerConvertedCards = []
    for i in range(len(dealer)):
        if dealer[i].getCardValue() >= 10 and dealer[i].getCardValue() <= 13:
            dealerConvertedCards.append(10)
        elif dealer[i].getCardValue() == 1:
            if sum(dealerConvertedCards) > 21:
                dealerConvertedCards.append(1)
            else:
                dealerConvertedCards.append(11)
        else:
            dealerConvertedCards.append(dealer[i].getCardValue())
    return sum(dealerConvertedCards)
# game play
print("Welcome to Justin's game of BlackJack!")

playerCount = True
while playerCount:
    playerNum = int(input("How many players would you like to play (1-5): "))
    if playerNum > 5:
        print("Too many players :(, try again.")
    else:
        players = addPlayer(playerNum)
        playerCount = False

playAgain = True

while playAgain:
    firstRound()
    totalAmountBet = bettingRound()
    playAgain = playingRound(totalAmountBet)
