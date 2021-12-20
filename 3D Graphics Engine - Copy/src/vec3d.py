class vec3d:
    def __init__(self, x=0, y=0 ,z=0) -> None:
        self.x = x
        self.y = y
        self.z = z

    def getX(self):
        return self.x

    def setX(self, v):
        self.x = v
    
    def getY(self):
        return self.y

    def setY(self, v):
        self.y = v
    
    def getZ(self):
        return self.z

    def addOntoZ(self, num):
        self.z += num
    
    def setZ(self, v):
        self.z = v

    def getVector(self):
        return self.x, self.y, self.z