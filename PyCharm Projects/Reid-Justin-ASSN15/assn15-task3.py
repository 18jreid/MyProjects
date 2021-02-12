from deck import Deck
from gubbinsutil import convertCardToValue

def selectionSortValue(inputList):
    for i in range(len(inputList) - 1):
        currMinIndex = i

        for j in range(i + 1, len(inputList)):
            if inputList[currMinIndex].getValue() > inputList[j].getValue():
                currMinIndex = j

        if currMinIndex != i:
            inputList[i], inputList[currMinIndex] = inputList[currMinIndex], inputList[i]

def selectionSortCardID(inputList):
    for i in range(len(inputList) - 1):
        currMinIndex = i

        for j in range(i + 1, len(inputList)):
            if inputList[currMinIndex].getCardId() > inputList[j].getCardId():
                currMinIndex = j

        if currMinIndex != i:
            inputList[i], inputList[currMinIndex] = inputList[currMinIndex], inputList[i]

def binary_search(input_list, key):
    low = 0
    high = len(input_list) - 1
    while high >= low:
        mid = (high + low) // 2
        if key == input_list[mid].getValue():
            return True
        elif key < input_list[mid].getValue():
            high = mid - 1
        else:
            low = mid + 1

    return False

def main():
    done = False
    deck = Deck()
    playerHand = []
    for i in range(1, 31):
        playerHand.append(deck.draw())
    handList = ["Rock", "Paper", "Scissors"]
    coinList = ["Heads", "Tails"]
    print("""
    Welcome to Gubbins Deck Checker!

        New Hand""")

    while not done:
        # menu
        print("""
        1) Sort by value
        2) Sort by ID
        3) Find card
        4) New hand
        5) Quit\n""")
        option = eval(input("Enter a selection: "))
        print()

        if option == 1:
            selectionSortValue(playerHand)
            for i in playerHand:
                print(i)
        elif option == 2:
            selectionSortCardID(playerHand)
            for i in playerHand:
                print(i)
        elif option == 3:
            cardValue = int(input("            Enter a value to search for: "))
            cardHand = int(input("""
            1) Rock
            2) Paper
            3) Scissors
            Choose a value: """))
            cardCoin = int(input("""
            1) Heads
            2) Tails
            Choose a coin: 
            """))
            found = convertCardToValue(cardValue, handList[cardHand - 1], coinList[cardCoin - 1])
            sortedHand = playerHand[:]
            selectionSortCardID(sortedHand)
            val = binary_search(sortedHand, found)
            if val:
                print("It is in the deck!")
            else:
                print("It is not in the deck :(")
        elif option == 4:
            deck.shuffle()
            playerHand = []
            for i in range(1, 31):
                playerHand.append(deck.draw())
        elif option == 5:
            print("Thanks for playing!")
            done = True

main()