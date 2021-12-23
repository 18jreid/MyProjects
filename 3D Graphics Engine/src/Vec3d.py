class Vec3d:
    def __init__(self, x:float, y:float, z:float):
        self.__x : float = x
        self.__y : float = y
        self.__z : float = z

    def getX(self) -> float:
        return self.__x

    def setX(self, value:float) -> None:
        self.__x = value

    def getY(self) -> float:
        return self.__y
    
    def setY(self, value:float) -> None:
        self.__y = value

    def getZ(self) -> float:
        return self.__z

    def setZ(self, value:float) -> None:
        self.__z = value