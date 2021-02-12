# Justin Reid
# CS 1400-001
# ASSN 7 Task 1

'''
Software Development Plan:

Requirement Specification -
    Write a program that plays the popular scissor-rock-paper game. (A scissor can cut a paper, a rock can knock a
    scissor, and a paper can wrap a rock.) The program randomly generates a number 0 , 1 , or 2 representing scissor,
    rock, and paper. The program prompts the user to enter a number 0 , 1 , or 2 and displays a message indicating
    whether the user or the computer wins, loses, or draws.

System Analysis -
    I need to make sure that when using the random function, that it will only output integer values from 0, 2. I must
    also evaluate the users input so that it is always an integer value.

    No formulas required.

System Design -
    1. Introduction
    2. Ask user to input their choice of rock(0), paper(1), or scissors(2).
    3. Have computer output a random integer from 0, 2.
    4. Calculate who has won the game.
    5. Tell the user the outcome of the game.

Testing -
    Test 1
        Input: Rock(0)
        Output: Computer choice unknown. As long as the user has won, tied, or lost the game, the program works.
    Test 2
        Input: Paper(1)
        Output: Computer choice unknown. As long as the user has won, tied, or lost the game, the program works.
'''

import random

# Introduce the user to the program:

print("Welcome to Justin's game of Rock, Paper, Scissors!\n" + "\n")

# Ask user for input:
userChoice = int(input("What is your choice? Rock(0), Paper(1), or Scissors(2): "))

# Have the computer input a random integer from 0, 2:
computerChoice = random.randint(0, 2)

# Tell the user the outcome of the game:
if userChoice == 0:
    if computerChoice == 0:
        print("You chose rock, the computer chose rock. It's a tie!")
    if computerChoice == 1:
        print("You chose rock, the computer chose paper. You lose!")
    if computerChoice == 2:
        print("You chose rock, the computer chose scissors. You win!")

if userChoice == 1:
    if computerChoice == 0:
        print("You chose paper, the computer chose rock. You win!")
    if computerChoice == 1:
        print("You chose paper, the computer chose paper. It's a tie!")
    if computerChoice == 2:
        print("You chose paper, the computer chose scissors. You lose!")

if userChoice == 2:
    if computerChoice == 0:
        print("You chose scissors, the computer chose rock. You lose!")
    if computerChoice == 1:
        print("You chose scissors, the computer chose paper. You win!")
    if computerChoice == 2:
        print("You chose scissors, the computer chose scissors. It's a tie!")