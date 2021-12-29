from Player import *
player = Player()
isPlaying = True

while isPlaying:
    print("1) Draw Card")
    print("2) Get Deck Length")
    print("3) Print Deck")
    print("4) Exit Program")
    userInput = int(input("What would you like to do: "))
    print()

    if type(userInput) != int:
        print("Invalid Input!")
    else:
        if userInput == 1:
            player.drawCard()
        elif userInput == 2:
            player.getDeckCardCount()
        elif userInput == 3:
            player.printDeck()
        elif userInput == 4:
            print("Exiting Program")
            isPlaying = False
        else:
            print("Invalid number input!")

    print()
