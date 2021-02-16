# Description: Attempt at programming the dual moving average crossover trading strategy to determine when
# to buy and sell a stock

import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import sys
import os
plt.style.use('fivethirtyeight')

# Get commandline args
tmp1, tmp2, buyAmount, compTmp = 0, 0, 0, ""
if len(sys.argv) <= 2:
    print("\nProgram Argument Requirements")
    print("--------------------------------")
    print("    arg1= sharesToBuy \n"
          "    arg2= companyName \n"
          "    arg3= smallMovingAverage (optional, standard = 30) \n"
          "    arg4= bigMovingAverage (optional, standard = 100)")
    print("   ", os.listdir('Companies'), "\n")
    quit()
elif len(sys.argv) == 3:
    tmpAmount = sys.argv[1]
    compTmp = sys.argv[2]
    tmp1 = 30
    tmp2 = 100
else:
    tmpAmount = sys.argv[1]
    compTmp = sys.argv[2]
    tmp1 = int(sys.argv[3])
    tmp2 = int(sys.argv[4])

# Load and show data
File = "Companies/" + compTmp
Company = pd.read_csv(File)

# Create Simple Moving Average of # of days
smallTimeFrame = tmp1
SmallMovingAverage = pd.DataFrame()
SmallMovingAverage["Adj Close"] = Company['Adj Close'].rolling(window=smallTimeFrame).mean()

# Create Simple Moving Average of # of days
bigTimeFrame = tmp2
BigMovingAverage = pd.DataFrame()
BigMovingAverage['Adj Close'] = Company['Adj Close'].rolling(window=bigTimeFrame).mean()

# Create new data frame to visualize data.
data = pd.DataFrame()
companyName = File
smallMovingAverageName = "SMA " + str(smallTimeFrame) + " Days"
bigMovingAverageName = "SMA " + str(bigTimeFrame) + " Days"
data[File] = Company['Adj Close']
data[smallMovingAverageName] = SmallMovingAverage['Adj Close']
data[bigMovingAverageName] = BigMovingAverage['Adj Close']


def buy_sell(data):
    sigPriceBuy = []
    sigPriceSell = []
    flag = 0
    money = 20000
    numOfShares = int(tmpAmount)

    print()
    for i in range(len(data)):
        if data[smallMovingAverageName][i] > data[bigMovingAverageName][i]:
            if flag != 1:
                sigPriceBuy.append(data[companyName][i])
                sigPriceSell.append(np.nan)
                flag = 1
                money -= numOfShares * data[companyName][i]
                print("Bought " + str(numOfShares) + " shares of " + companyName.strip("Companies/.csv") + " at " + str(data[companyName][i])
                      + ", (Total = $" + str(round(-numOfShares * data[companyName][i], 2)) + ")")
            else:
                sigPriceBuy.append(np.nan)
                sigPriceSell.append(np.nan)
        elif data[smallMovingAverageName][i] < data[bigMovingAverageName][i]:
            if flag != 0:
                sigPriceBuy.append(np.nan)
                sigPriceSell.append(data[companyName][i])
                flag = 0
                money += numOfShares * data[companyName][i]
                print("Sold " + str(numOfShares) + " shares of " + companyName.strip("Companies/.csv") + " at " + str(data[companyName][i])
                      + ", (Total = $" + str(round(numOfShares * data[companyName][i], 2)) + ")")
            else:
                sigPriceBuy.append(np.nan)
                sigPriceSell.append(np.nan)
        else:
            sigPriceBuy.append(np.nan)
            sigPriceSell.append(np.nan)
    if flag == 1:
        money += numOfShares * data[companyName][len(data[companyName]) - 1]
        print("Sold " + str(numOfShares) + " shares of " + companyName.strip("Companies/.csv") + " at " + str(
            data[companyName][len(data[companyName]) - 1]) + ", (Total = $" + str(round(numOfShares * data[companyName][len(data[companyName]) - 1], 2)) + ")")
    print("\nTotal return of $" + str(round(money - 20000, 2)))

    return sigPriceBuy, sigPriceSell, money - 20000


buy_sell = buy_sell(data)
data['Buy_Signal_Price'] = buy_sell[0]
data['Sell_Signal_Price'] = buy_sell[1]
money = buy_sell[2]

# Visualize data of when to sell and buy stock
plt.figure(figsize=(12.6, 6.8))
plt.plot(data[companyName], label=companyName.strip('Companies/.csv'), alpha=0.35)
plt.plot(data[smallMovingAverageName], label=smallMovingAverageName, alpha=0.35)
plt.plot(data[bigMovingAverageName], label=bigMovingAverageName, alpha=0.35)
plt.figtext(0, 0, "Profit = $" + str(round(money, 2)))
plt.scatter(data.index, data['Buy_Signal_Price'], label='Buy', marker='^', color='green')
plt.scatter(data.index, data['Sell_Signal_Price'], label='Sell', marker='v', color='red')
plt.title(companyName.strip("Companies/.csv") + " Adj Close Price History Buy and Sell Signals")
plt.xlabel("Days")
plt.ylabel("Adj. Close Price History USD ($)")
plt.legend(loc='upper left')
plt.show()
