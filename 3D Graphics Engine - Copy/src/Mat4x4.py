class Mat4x4:    
    def __init__(self) -> None:
        self.__matrix = [[1,0,0,0], [0,1,0,0], [0,0,1,0], [0,0,0,1]]

    def setIndex(self, row, column, value):
        self.__matrix[row][column] = value

    def getIndex(self, row, column):
        return self.__matrix[row][column]

    def printMatrix(self):
        for x in self.__matrix:
            for y in x:
                print(y, end=" ")
            print()
