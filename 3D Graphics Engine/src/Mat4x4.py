class Mat4x4:
    def __init__(self):
        self.__matrix = [[1,0,0,0], [0,1,0,0], [0,0,1,0], [0,0,0,1]]

    def getIndex(self, row, column) -> float:
        return self.__matrix[row][column]

    def setIndex(self, row:int, column:int, value:float):
        self.__matrix[row][column] = value

    def printMatrix(self):
        for x in self.__matrix:
            for y in x:
                print(y, end=" ")
            print()