import alpaca_trade_api as tradeapi
import webbrowser

APCA_API_KEY_ID = 'PK37B3ZW0MMQ06NU0E70'
APCA_API_SECRET_KEY = 'MhVeRwgGD1WGQrYkurZxjiMtLDMvHGHjh9T7917m'
APCA_API_BASE_URL = 'https://paper-api.alpaca.markets'

api = tradeapi.REST(APCA_API_KEY_ID, APCA_API_SECRET_KEY, APCA_API_BASE_URL)
account = api.get_account()


# Prints programs available functions.
def show_functions():
    print("Program Functions:")
    print("    -account")
    print("    -portfolio")
    print("    -money")
    print("    -buy")
    print("    -website")
    print("    -quit")
    show_buying_power()
    print()


# Refreshes Alpaca account information.
def refresh_account():
    global api
    global account
    api = tradeapi.REST(APCA_API_KEY_ID, APCA_API_SECRET_KEY, APCA_API_BASE_URL)
    account = api.get_account()


# Show all Alpaca account info.
def show_account_info():
    print(account)


# Show all Alpaca assets.
def show_portfolio():
    api.list_assets()


# Show Alpaca remaining buying power.
def show_buying_power():
    print("Buying Power: " + account.buying_power)


# Buy stock.
def buy():
    print("Enter stock name: ")
    symbol = input()

    print("Enter quantity(shares): ")
    qty = input()

    print("Enter type(market, ..., ...): ")
    type = input()

    print("Enter time in force: ")
    time_in_force = input()
    print()

    api.submit_order(symbol, int(qty), 'buy', type, time_in_force)
    refresh_account()


def show_website():
    webbrowser.open('https://app.alpaca.markets/paper/dashboard/overview', new=2)


isTrading = True
print("Alpaca Paper Trading Program")
print("-----------------------------")
show_functions()
while isTrading:
    print("Input: ")
    userInput = input()
    if userInput.__contains__("account"):
        show_account_info()
    if userInput.__contains__("portfolio"):
        show_portfolio()
    if userInput.__contains__("money"):
        show_buying_power()
    if userInput.__contains__("buy"):
        buy()
    if userInput.__contains__("website"):
        show_website()
    if userInput.__contains__("quit"):
        quit()
    print()
    show_functions()
