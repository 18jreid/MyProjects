from deck import Deck

# Bubble sort
def bubbleSort(inputList):
    didSwap = True
    count = 0

    while didSwap:
        didSwap = False
        for i in range(len(inputList) - 1):
            if inputList[i].getCardValue() > inputList[i + 1].getCardValue():
                print("[", end="")
                for v in inputList:
                    print(v.getNickName(), end=", ")
                print("]")
                inputList[i], inputList[i + 1] = inputList[i + 1], inputList[i]
                didSwap = True
                count += 1
    print("\nThe amount of times swapped is", count)

# Insertion sort
def insertionSort(inputList):
    count = 0
    for i in range(1, len(inputList)):
        currElement = inputList[i]
        j = i - 1
        while j >= 0 and inputList[j].getCardValue() > currElement.getCardValue():
            print("[", end="")
            for v in inputList:
                print(v.getNickName(), end=", ")
            print("]")
            inputList[j + 1] = inputList[j]
            j -= 1
            count += 1
        inputList[j + 1] = currElement
    print("\nThe amount of times swapped is", count)

deck = Deck()
playerHand = []
for i in range(1, 21):
    playerHand.append(deck.draw())
print("Here is your deck:\n")
print(playerHand)
print("""
Select a sort:
    1) Bubble Sort
    2) Insertion Sort
""")
userSelection = int(input("Selection: "))

if userSelection == 1:
    bubbleSort(playerHand)
    print(playerHand)
elif userSelection == 2:
    insertionSort(playerHand)
    print(playerHand)
else:
    print("Invalid Selection")
