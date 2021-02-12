# ASGN5 Task 1
# Justin Reid
# CS1400-001
# Proogram a future investment amount calculator.

# Introduction:
print("Welcome to Justin's investment amount calculator!\n")

# User input:
investmentAmount = float(input("Enter investment amount: $"))
annualInterestRate = float(input("Enter annual interest rate: "))
numberOfYears = float(input("Enter number of years: "))

# Variables:
monthlyInterestValue = annualInterestRate / 1200
numberOfMonths = numberOfYears * 12
futureInvestmentValue = float(investmentAmount * (1 + monthlyInterestValue) ** numberOfMonths)

# Display investment value:
print("\nYour accumulated amount is", round(futureInvestmentValue, 2), "dollars!\n")