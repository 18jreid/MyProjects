# Justin Red
# CS 1400-001
# ASSN 6 Task 2

'''
Software Development Process:

Requirements Specification -
    I need to create a pay stub calculator. This calculator should ask the user for personal info and data in order to
    determine their pay. It will display the user's information afterwards with specific formatting.

System Analysis -
    I will need multiple types of data for this program. For example: strings, integers, and floats. I multiply the
     users gross pay by the state and federal tax to determine the net pay.

     Formulas: Gross pay = payRate * hoursWorked

System Design -
    1. Introduce user to program.
    2. Ask employee for name.
    3. Ask for hours worked.
    4. Ask for hourly pay rate.
    5. Ask for federal tax rate.
    6. Ask for state tax rate.

    7. Display pay information -
        For example:
        EMPLOYEE PAY INFORMATION
            Pay
        Hours Worked:
        Pay Rate: $
            Gross Pay: $

            Deductions
        Federal Withholding (%): $
        State Withholding (%): $
        Total Deduction: $
            Net Pay: $

Testing -
    Test 1: 
    Input =
        hoursWorked = 40
        payRate = 12.75
        fedTax = 0.11
        stateTax = 0.07
        
    Output =
        grossPay = 510
        fedWitholding = 56.10
        stateWitholding = 35.70
        totalDeductions = 91.80
        netPay = 418.20
    Test 2:
    Input =
        hoursWorked = 12.5
        payRate = 8.50
        fedTax = 0.12
        stateTax = 0.06
    
    Outpout =
        grossPay = 106.25
        fedWitholding = 12.75
        stateWitholding = 6.38
        totalDeductions = 19.12
        netPay = 87.13
'''

# Introduction:
print("Welcome to Justin's pay stub calculator!\n")

# User inputs:
employeeName = input("Enter employee's name: ")
hoursWorked = float(input("Enter number of hours worked in a week: "))
payRate = float(input("Enter hourly pay rate: "))
fedTax = float(input("Enter federal tax withholding rate (ex. 0.12): "))
stateTax = float(input("Enter state tax withholding rate (ex. 0.06): "))

# Display Pay Stub
grossPay = float(payRate * hoursWorked)
fedWitholding = float(grossPay * fedTax)
stateWitholding = float(grossPay * stateTax)
percentFedTax = round(fedTax * 100, 2)
percentStateTax = round(stateTax * 100, 2)
totalDeductions = round(float(fedWitholding + stateWitholding), 2)
netPay = round(float(grossPay - totalDeductions), 2)

payStub = "\n" + format(employeeName.upper() + "'S PAY INFORMATION\n", "^45s")
payStub += "\n" + format("Pay", "^47s")
payStub += "\n" + format("Hours Worked:", ">32s") + format(hoursWorked, ">12.1f")
payStub += "\n" + format("Pay Rate: $", ">34s") + format(payRate, ">10.2f")
payStub += "\n" + format("Gross Pay: $", ">34s") + format(grossPay, ">10.2f")
payStub += "\n" + "\n" + format("Deductions", "^49s")
payStub += "\n" + format("Federal Withholding (" + str(percentFedTax) + "%): $", ">34s") + format(fedWitholding, ">10.2f")
payStub += "\n" + format("State Withholding (" + str(percentStateTax) + "%): $", ">34s") + format(stateWitholding, ">10.2f")
payStub += "\n" + format("Total Deduction: $", ">34s") + format(totalDeductions, ">10.2f")
payStub += "\n" + "\n" + format("Net Pay: $", ">34s") + format(netPay, ">10.2f")

print(payStub)
