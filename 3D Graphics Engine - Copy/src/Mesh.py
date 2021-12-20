class Mesh:
    def __init__(self, listOfTriangles=[]):
        self.__triangles = []

        self.setTriangleList(listOfTriangles)
    
    def setTriangleList(self, listOfTriangles):
        for x in listOfTriangles:
            self.__triangles.append(x)

    def getMesh(self):
        return self.__triangles
