from Vec3d import Vec3d
from Triangle import Triangle


class Mesh:
    def __init__(self, listOfTriangles=[]):
        self.__triangles = []

        for x in listOfTriangles:
            self.__triangles.append(x)

    def getMesh(self):
        return self.__triangles

    def loadFromObjectFile(self, file):
        vertices = []
        triangles = []
        f = open(file)

        lines = f.readlines()
        while len(lines) != 0:
            lineSplit = lines.pop(0).split(" ")
            
            if (lineSplit[0] == 'v'):
                vector = Vec3d(float(lineSplit[1]), float(lineSplit[2]), float(lineSplit[3]))
                vertices.append(vector)

            if (lineSplit[0] == 'f'):
                triangle = Triangle(vertices[int(lineSplit[1]) - 1], vertices[int(lineSplit[2]) - 1], vertices[int(lineSplit[3]) - 1])
                triangles.append(triangle)

        for x in triangles:
            self.__triangles.append(x)