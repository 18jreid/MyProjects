userList = []
done = False

while not done:
    userInput = input("Enter a number for your list: ")
    if userInput == "":
        done = True
    else:
        userList.append(eval(userInput))

sumOfList = sum(userList)
averageValueOfList = sumOfList // len(userList)
print("\nYour list is", userList, "\n")
print("Length of List =", len(userList))
print("Max value of list =", max(userList))
print("Min value of list =", min(userList))
print("Sum of values in list =", sumOfList)
print("Average value in list=", averageValueOfList)