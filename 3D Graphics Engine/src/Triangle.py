from Vec3d import Vec3d

class Triangle:
    def __init__(self, v0:Vec3d, v1:Vec3d, v2:Vec3d):
        self.__v0: Vec3d = v0
        self.__v1: Vec3d = v1
        self.__v2: Vec3d = v2
        self.__dp = 0

    def getV0(self) -> Vec3d:
        return self.__v0
    
    def getV1(self) -> Vec3d:
        return self.__v1

    def getV2(self) -> Vec3d:
        return self.__v2

    def getPoints(self):
        return self.__v0, self.__v1, self.__v2

    def getDp(self):
        return self.__dp

    def setDp(self, value):
        if (value <= 0):
            self.__dp = 0
        else:
            self.__dp = value