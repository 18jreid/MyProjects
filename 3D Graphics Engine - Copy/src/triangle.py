from vec3d import vec3d


class Triangle:
    def __init__(self, v0=vec3d(0,0,0), v1=vec3d(0,0,0), v2=vec3d(0,0,0)) -> None:
        self.vertice0 = v0
        self.vertice1 = v1
        self.vertice2 = v2

    def getPoints(self):
        return self.vertice0, self.vertice1, self.vertice2

    def getVertice0(self):
        return self.vertice0

    def setVertice0(self, point):
        self.vertice0 = point

    def getVertice1(self):
        return self.vertice1
    
    def setVertice1(self, point):
        self.vertice1 = point

    def getVertice2(self):
        return self.vertice2

    def setVertice2(self, point):
        self.vertice2 = point