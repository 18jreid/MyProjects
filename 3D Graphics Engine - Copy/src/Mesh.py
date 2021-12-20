class Mesh:
    def __init__(self, listOfTriangles=[]):
        self.triangles = []

        self.setTriangleList(listOfTriangles)
    
    def setTriangleList(self, listOfTriangles):
        for x in listOfTriangles:
            self.triangles.append(x)

    def getMesh(self):
        return self.triangles
