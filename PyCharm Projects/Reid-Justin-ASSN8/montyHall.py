# Justin Reid
# CS-1400-001
# ASSN 2 Task 2

'''
Software Development Plan:

    Requirement Specifications -
        Suppose you're	on a game show, and you're	given the choice of	three doors: Behind one door is a car; behind
        the others, goats. You pick a door, say No. 1, and the host, who knows what's behind the doors, opens another
        door, say No. 3, which has a goat. He then says to you, "Do you want to pick door No. 2?" Is it to your
        advantage to switch your choice?

        Your task is to	write a	program	to simulate	the	game. This means your program will automatically play the game
        100,000 times. The only input	the	user will give is if they want to stay with their original pick, or switch picks
        after the first	goat is	revealed. Their	pick will be the decision for all 100,000 simulations.

    System Analysis -
        Make sure that after the users door is picked, the other two are turned into goat doors.

        When getting the percentage of how many wins you've had from the door, take all wins from the 100,00 simulations
        and divide it by 100,000. Then multiply it by 100 for the percentage.

    System Design -
        1. Introduce the user to the game, and assign them to a door.
        2. Show the user what's behind one of the other two doors.
        3. Ask if they want to switch doors.
        4. Simulate the game 100,000 times based off of the users choice.
        5. List the percentage of wins with 100,000 simulations.
'''
import random

# introduction, and the restart if they want to play again:

startOver = "Y"
while startOver == "Y" or startOver == "y":
    print(format("Welcome to Justin's dream car game show!", "^60s"))
    print(format("Behind one of these three doors is the car of your dreams!", "^60s") + "\n")
    print("The 100,000 simulations of this game will be based off whether you switch doors or not, so choose carefully!\n")
    carDoor = random.randint(1, 3)
    userChoiceDoor = random.randint(1, 3)
    win = 0
    loss = 0
    totalGames = 0

# door winning/losing scenarios, defined early on for scope:

    if carDoor == 1:
        goatDoor1 = 2
        goatDoor2 = 3

    if carDoor == 2:
        goatDoor1 = 1
        goatDoor2 = 3

    if carDoor == 3:
        goatDoor1 = 2
        goatDoor2 = 1

# Tell them what their door is and ask if they want to switch:

    if userChoiceDoor == carDoor:
        print("You have been assigned door:", userChoiceDoor)
        print("There is a goat behind door:", goatDoor1)
        print("Would you like to switch to door", goatDoor2, "?")
        switchDoors = input("Enter Y or N: ")
        if switchDoors == "Y" or switchDoors == "y":
            userChoiceDoor = goatDoor2
    elif userChoiceDoor == goatDoor1:
        print("You have been assigned door:", userChoiceDoor)
        print("There is a goat behind door:", goatDoor2)
        print("Would you like to switch to door:", str(carDoor) + "?")
        switchDoors = input("Enter Y or N: ")
        if switchDoors == "Y" or switchDoors == "y":
            userChoiceDoor = carDoor
    elif userChoiceDoor == goatDoor2:
        print("You have been assigned door:", userChoiceDoor)
        print("There is a goat behind door:", goatDoor1)
        print("Would you like to switch to door:", carDoor, "?")
        switchDoors = input("Enter Y or N: ")
        if switchDoors == "Y" or switchDoors == "y":
            userChoiceDoor = carDoor

# win/lose counter:
    if userChoiceDoor == carDoor:
        win += 1
    else:
        loss += 1
    totalGames += 1

# stop running after 100,000 games
    while totalGames < 100000:
        carDoor = random.randint(1, 3)
        userChoiceDoor = random.randint(1, 3)
        if carDoor == 1:
            goatDoor1 = 2
            goatDoor2 = 3

        if carDoor == 2:
            goatDoor1 = 1
            goatDoor2 = 3

        if carDoor == 3:
            goatDoor1 = 2
            goatDoor2 = 1

        if userChoiceDoor == carDoor:
            if switchDoors == "Y" or switchDoors == "y":
                userChoiceDoor = goatDoor2
        elif userChoiceDoor == goatDoor1:
            if switchDoors == "Y" or switchDoors == "y":
                userChoiceDoor = carDoor
        elif userChoiceDoor == goatDoor2:
            if switchDoors == "Y" or switchDoors == "y":
                userChoiceDoor = carDoor
        if userChoiceDoor == carDoor:
            win += 1
        else:
            loss += 1
        totalGames += 1

# display info of wins/losses, and ask if they want to play again:

    print("Your win count is:", win, "wins and your win percentage is:", (win / 100000) * 100)
    print("Your loss count is:", loss, "losses:(")
    print("Total games played:", totalGames)

    if win > loss:
        print(format("You've won more than you've lost, you win a new car!", ">50s"))
    else:
        print(format("You've lost more than you've won, you get to take home this goat!", ">50s"))
    startOver = input("\nWould you like to play again?(Y or N): ")

print("That's too bad :(")