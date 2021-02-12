class Account:
    def __init__(self, user_id=0, balance=100, annualInterestRate=0, withdrawAmount=0, depositAmount=0):
        self.__user_id = user_id
        self.__balance = balance
        self.__annualInterestRate = annualInterestRate
        self.__withdrawAmount = withdrawAmount
        self.__depositAmount = depositAmount

    def get_User_Info(self):
        self.__user_id = int(input("Enter ID: "))
        while self.__user_id < 0:
            self.__user_id = int(input("Enter a valid ID: "))
        self.__balance = int(input("Enter balance: "))
        while self.__balance < 0:
            self.__balance = int(input("Enter a valid balance: "))
        self.__annualInterestRate = float(input("Enter annual interest rate: "))
        while self.__annualInterestRate < 0 or self.__annualInterestRate > 10:
            self.__annualInterestRate = int(input("Enter a valid interest rate(between 1 and 10 percent): "))

    def get_id(self):
        return self.__user_id

    def __set_id(self, user_id):
        self.__user_id = user_id

    def get_balance(self):
        return self.__balance

    def set_balance(self, balance):
        self.__balance = balance

    def get_annualInterestRate(self):
        return self.__annualInterestRate

    def __set_annualInterestRate(self, annualInterestRate):
        self.__annualInterestRate = annualInterestRate

    def getMontlyInterestRate(self):
        return self.__annualInterestRate / 12

    def getMonthlyInterest(self):
        return round(self.__balance * ((self.__annualInterestRate / 12) / 100), 2)

    def withdraw(self):
        self.__withdrawAmount = int(input("How much would you like to withdraw?: "))
        self.__balance = self.__balance - self.__withdrawAmount

    def deposit(self):
        self.__depositAmount = int(input("How much would you like to deposit?: "))
        self.__balance = self.__balance + self.__depositAmount

    def displayMenu(self):
        print("(1): Display ID")
        print("(2): Display Balance")
        print("(3): Display Annual Interest Rate")
        print("(4): Display Monthly Interest Rate")
        print("(5): Display Monthly Interest")
        print("(6): Withdraw Money")
        print("(7): Deposit Money")
        print("(8): Exit")
        print()
