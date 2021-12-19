class Mesh:
    triangles = []

    def __init__(self, listOfTriangles) -> None:
        for x in listOfTriangles:
            self.triangles.append(x)

    def getMesh(self):
        return self.triangles
