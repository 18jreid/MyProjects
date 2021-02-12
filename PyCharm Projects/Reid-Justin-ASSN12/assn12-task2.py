import account as a

def main():
    account = a.Account()
    account.get_User_Info()
    done = False

    while not done:
        account.displayMenu()

        userInput = int(input("Enter selection: "))

        if userInput == 1:
            print("Your ID:", account.get_id(), "\n")
        elif userInput == 2:
            print("Your balance:", account.get_balance(), "dollars\n")
        elif userInput == 3:
            print("Your Annual Interest Rate:", account.get_annualInterestRate(), "percent\n")
        elif userInput == 4:
            print("Your Monthly Interest Rate:", round(account.getMontlyInterestRate(), 2), "percent\n")
        elif userInput == 5:
            print("Your Monthly Interest:", account.getMonthlyInterest(), "dollars\n")
        elif userInput == 6:
            account.withdraw()
            print("Your balance is now:", account.get_balance(), "dollars\n")
        elif userInput == 7:
            account.deposit()
            print("Your balance is now:", account.get_balance(), "dollars\n")
        elif userInput == 8:
            print("\nHave a good day.")
            done = True
        else:
            print("Not a valid selection.\n")
main()