class Polygon:
    def __init__(self, numberOfSides):
        self.__numberOfSides = numberOfSides

    def getSides(self):
        return self.__numberOfSides

    def __add__(self, other):
        return self.__numberOfSides + other.getSides()

    def __sub__(self, other):
        return self.__numberOfSides - other.getSides()

    def __lt__(self, other):
        return self.__numberOfSides < other.getSides()

    def __gt__(self, other):
        return self.__numberOfSides > other.getSides()

    def __eq__(self, other):
        return self.__numberOfSides == other.getSides()

    def __len__(self):
        return len(str(self.__numberOfSides))

    def __str__(self):
        return type(str(self.__numberOfSides))
