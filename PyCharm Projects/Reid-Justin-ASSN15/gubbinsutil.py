hand = ["Rock", "Paper", "Scissors"]
coin = ["Heads", "Tails"]
maxCardValue = 20

# Make sure you understand this to do the opposite conversion!!!
def convertCardToValue(cardValue, cardHand, cardCoin):
    return 2 * ((cardValue - 1) + (maxCardValue * hand.index(cardHand))) + coin.index(cardCoin)