class Password:
    def __init__(self):
        self.validPassword = True
        self.password = ""
        self.errorMessage = ""

    def setPassword(self):
        self.validPassword = True
        self.errorMessage = ""
        self.password = input("\nEnter a password: ")
        if self.isValid():
            print("This password meets all requirements.")
        else:
            print("This password is invalid.")
            print(self.errorMessage)

    def isValid(self):
        self.__validLength()
        self.__validCharacters()
        self.__validNumbers()
        self.__cannotContainPassword()
        self.__cannotEndInOneTwoThree()

        return self.validPassword

    def __cannotContainPassword(self):
        if "password" in self.password:
            self.validPassword = False
            self.errorMessage += "Your password cannot contain the word password.\n"
        elif "Password" in self.password:
            self.validPassword = False
            self.errorMessage += "Your password cannot contain the word password.\n"

    def __cannotEndInOneTwoThree(self):
        if self.password[-3:] == "123":
            self.validPassword = False
            self.errorMessage += "Your password cannot end in 123.\n"

    def __validLength(self):
        if len(self.password) < 8:
            self.validPassword = False
            self.errorMessage += "Your password is under 8 characters.\n"

    def __validCharacters(self):
        if not self.password.isalnum():
            self.validPassword = False
            self.errorMessage += "Your password can only contain letters and numbers.\n"

    def __validNumbers(self):
        passNum = 0
        for i in self.password:
            if i.isnumeric():
                passNum += 1
        if passNum < 2:
            self.validPassword = False
            self.errorMessage += "Your password must contain 2 or more numbers.\n"

    def getErrorMessage(self):
        return self.errorMessage