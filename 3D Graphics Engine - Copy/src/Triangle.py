from Vec3D import Vec3D


class Triangle:
    def __init__(self, v0=Vec3D(), v1=Vec3D(), v2=Vec3D()) -> None:
        self.__vertice0 = v0
        self.__vertice1 = v1
        self.__vertice2 = v2

    def getPoints(self):
        return self.__vertice0, self.__vertice1, self.__vertice2

    def getVertice0(self):
        return self.__vertice0

    def setVertice0(self, point):
        self.__vertice0 = point

    def getVertice1(self):
        return self.__vertice1
    
    def setVertice1(self, point):
        self.__vertice1 = point

    def getVertice2(self):
        return self.__vertice2

    def setVertice2(self, point):
        self.__vertice2 = point
