class mat4x4:
    matrix = []
    def __init__(self) -> None:
        self.matrix.append([0,0,0,0])
        self.matrix.append([0,0,0,0])
        self.matrix.append([0,0,0,0])
        self.matrix.append([0,0,0,0])

    def setIndex(self, row, column, value):
        self.matrix[row][column] = value

    def getIndex(self, row, column):
        return self.matrix[row][column]

    def printMatrix(self):
        for x in self.matrix:
            for y in x:
                print(y, end=" ")
            print()