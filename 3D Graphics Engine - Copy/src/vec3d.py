class vec3d:
    x = None
    y = None
    z = None

    def __init__(self, x, y ,z) -> None:
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
    
    def setZ(self, v):
        self.z = v

    def getVector(self):
        return self.x, self.y, self.z