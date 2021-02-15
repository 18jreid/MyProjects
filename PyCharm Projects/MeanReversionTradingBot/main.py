# Description: Attempt at programming the dual moving average crossover trading strategy to determine when
# to buy and sell a stock

import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import sys
import os
plt.style.use('fivethirtyeight')

# Get commandline args
tmp1, tmp2 = 0, 0
if len(sys.argv) == 1:
    print("Program Argument Requirements")
    print("arg1=fileName, arg2=smallMovingAverage (optional), arg3=bigMovingAverage (optional)")
    print(os.listdir('Companies'), "\n")
    quit()
elif len(sys.argv) == 2:
    compTmp = sys.argv[1]
    tmp1 = 30
    tmp2 = 100
else:
    compTmp = sys.argv[1]
    tmp1 = int(sys.argv[2])
    tmp2 = int(sys.argv[3])

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

    for i in range(len(data)):
        if data[smallMovingAverageName][i] > data[bigMovingAverageName][i]:
            if flag != 1:
                sigPriceBuy.append(data[companyName][i])
                sigPriceSell.append(np.nan)
                flag = 1
            else:
                sigPriceBuy.append(np.nan)
                sigPriceSell.append(np.nan)
        elif data[smallMovingAverageName][i] < data[bigMovingAverageName][i]:
            if flag != 0:
                sigPriceBuy.append(np.nan)
                sigPriceSell.append(data[companyName][i])
                flag = 0
            else:
                sigPriceBuy.append(np.nan)
                sigPriceSell.append(np.nan)
        else:
            sigPriceBuy.append(np.nan)
            sigPriceSell.append(np.nan)

    return sigPriceBuy, sigPriceSell


buy_sell = buy_sell(data)
data['Buy_Signal_Price'] = buy_sell[0]
data['Sell_Signal_Price'] = buy_sell[1]

# Visualize data of when to sell and buy stock
plt.figure(figsize=(12.6, 6.8))
plt.plot(data[companyName], label=companyName.strip('Companies/.csv'), alpha=0.35)
plt.plot(data[smallMovingAverageName], label=smallMovingAverageName, alpha=0.35)
plt.plot(data[bigMovingAverageName], label=bigMovingAverageName, alpha=0.35)
plt.scatter(data.index, data['Buy_Signal_Price'], label='Buy', marker='^', color='green')
plt.scatter(data.index, data['Sell_Signal_Price'], label='Sell', marker='v', color='red')
plt.title(companyName.strip("Companies/.csv") + " Adj Close Price History Buy and Sell Signals")
plt.xlabel("Feb. 16, 2016 - Feb. 12, 2021")
plt.ylabel("Adj. Close Price History USD ($)")
plt.legend(loc='upper left')
plt.show()
