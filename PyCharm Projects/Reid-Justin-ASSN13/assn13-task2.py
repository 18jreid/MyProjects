from password import Password

def main():
    currPassword = Password()
    currPassword.setPassword()

    done = True
    while done:
        print("\nCheck another password?")
        print("(1): Yes")
        print("(2): No")
        userChoice = int(input("Enter choice: "))

        if userChoice == 1:
            currPassword.setPassword()
        elif userChoice == 2:
            break
        else:
            print("Enter a valid choice.")

main()