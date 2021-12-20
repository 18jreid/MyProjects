class Vec3D:
    def __init__(self, x=0, y=0 ,z=0) -> None:
        self.__x = x
        self.__y = y
        self.__z = z

    def getX(self):
        return self.__x

    def setX(self, v):
        self.__x = v
    
    def getY(self):
        return self.__y

    def setY(self, v):
        self.__y = v
    
    def getZ(self):
        return self.__z

    def addOntoZ(self, num):
        self.__z += num
    
    def setZ(self, v):
        self.__z = v

    def getVector(self):
        return self.__x, self.__y, self.__z
