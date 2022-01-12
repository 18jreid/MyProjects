import pygame
from pygame.version import rev
from Mat4x4 import Mat4x4
from Vec3d import Vec3d
from Triangle import Triangle
from Mesh import Mesh
import math
import time


# Multiplies a vector and matrix together
def multiplyMatrixVector(input:Vec3d, output:Vec3d, matrix:Mat4x4) -> None:
    output.setX((input.getX() * matrix.getIndex(0,0)) + (input.getY() * matrix.getIndex(1,0)) + (input.getZ() * matrix.getIndex(2,0)) + matrix.getIndex(3,0))
    output.setY((input.getX() * matrix.getIndex(0,1)) + (input.getY() * matrix.getIndex(1,1)) + (input.getZ() * matrix.getIndex(2,1)) + matrix.getIndex(3,1))
    output.setZ((input.getX() * matrix.getIndex(0,2)) + (input.getY() * matrix.getIndex(1,2)) + (input.getZ() * matrix.getIndex(2,2)) + matrix.getIndex(3,2))
    
    w = (input.getX() * matrix.getIndex(0,3)) + (input.getY() * matrix.getIndex(1,3)) + (input.getZ() * matrix.getIndex(2,3)) + matrix.getIndex(3,3)

    if w != 0.0:
        output.setX((output.getX()) / (w))
        output.setY((output.getY()) / (w))
        output.setZ((output.getZ()) / (w))


# Draws object specified
def drawObject(mesh, xOffset=0, yOffset=0, zOffset=0, vCamera=Vec3d(0,0,0)):
    trianglesToRaster:list[Triangle] = []
    for tri in mesh.getMesh():
        triTranslated = Triangle(Vec3d(1,1,1), Vec3d(1,1,1), Vec3d(1,1,1))
        triRotatedZ = Triangle(Vec3d(1,1,1), Vec3d(1,1,1), Vec3d(1,1,1))
        triRotatedZX = Triangle(Vec3d(1,1,1), Vec3d(1,1,1), Vec3d(1,1,1))

        # Rotate object
        multiplyMatrixVector(tri.getV0(), triRotatedZ.getV0(), matRotZ)
        multiplyMatrixVector(tri.getV1(), triRotatedZ.getV1(), matRotZ)
        multiplyMatrixVector(tri.getV2(), triRotatedZ.getV2(), matRotZ)
        multiplyMatrixVector(triRotatedZ.getV0(), triRotatedZX.getV0(), matRotY)
        multiplyMatrixVector(triRotatedZ.getV1(), triRotatedZX.getV1(), matRotY)
        multiplyMatrixVector(triRotatedZ.getV2(), triRotatedZX.getV2(), matRotY)

        # Add coordinate offset
        triTranslated = triRotatedZX
        triTranslated.getV0().setZ(triRotatedZX.getV0().getZ() + zOffset + vCamera.getZ())
        triTranslated.getV1().setZ(triRotatedZX.getV1().getZ() + zOffset + vCamera.getZ())
        triTranslated.getV2().setZ(triRotatedZX.getV2().getZ() + zOffset + vCamera.getZ())
        triTranslated.getV0().setY(triRotatedZX.getV0().getY() + yOffset + vCamera.getY())
        triTranslated.getV1().setY(triRotatedZX.getV1().getY() + yOffset + vCamera.getY())
        triTranslated.getV2().setY(triRotatedZX.getV2().getY() + yOffset + vCamera.getY())
        triTranslated.getV0().setX(triRotatedZX.getV0().getX() + xOffset + vCamera.getX())
        triTranslated.getV1().setX(triRotatedZX.getV1().getX() + xOffset + vCamera.getX())
        triTranslated.getV2().setX(triRotatedZX.getV2().getX() + xOffset + vCamera.getX())

        trianglesToRaster.append(triTranslated)

    trianglesToRaster.sort(key=lambda x: ((x.getV0().getZ() + x.getV1().getZ() + x.getV2().getZ()) / 3), reverse=True)
    

    rasterTriangles(trianglesToRaster)


def rasterTriangles(triangles:list[Triangle]):
    for x in triangles:
        triProjected = Triangle(Vec3d(1,1,1), Vec3d(1,1,1), Vec3d(1,1,1))
        
        # Calculate normal vector for drawing of faces
        normal = Vec3d(0,0,0)
        line1 = Vec3d(0,0,0)
        line2 = Vec3d(0,0,0)
        line1.setX(x.getV1().getX() - x.getV0().getX())
        line1.setY(x.getV1().getY() - x.getV0().getY())
        line1.setZ(x.getV1().getZ() - x.getV0().getZ())

        line2.setX(x.getV2().getX() - x.getV0().getX())
        line2.setY(x.getV2().getY() - x.getV0().getY())
        line2.setZ(x.getV2().getZ() - x.getV0().getZ())

        normal.setX(line1.getY() * line2.getZ() - line1.getZ() * line2.getY())
        normal.setY(line1.getZ() * line2.getX() - line1.getX() * line2.getZ())
        normal.setZ(line1.getX() * line2.getY() - line1.getY() * line2.getX())

        length = math.sqrt(math.pow(normal.getX(), 2) + math.pow(normal.getY(), 2) + math.pow(normal.getZ(), 2))
        normal.setX(normal.getX() / length)
        normal.setY(normal.getY() / length)
        normal.setZ(normal.getZ() / length)

        if (normal.getX() * (x.getV0().getX() - vCamera.getX()) + normal.getY() * (x.getV0().getY() - vCamera.getY()) + normal.getZ() * (x.getV0().getZ() - vCamera.getZ()) < 0):
            # Illumination
            light_direction = Vec3d(0,0, -1)
            length = math.sqrt(math.pow(light_direction.getX(), 2) + math.pow(light_direction.getY(), 2) + math.pow(light_direction.getZ(), 2))
            light_direction.setX(light_direction.getX() / length)
            light_direction.setY(light_direction.getY() / length)
            light_direction.setZ(light_direction.getZ() / length)
            dp = normal.getX() * light_direction.getX() + normal.getY() * light_direction.getY() + normal.getZ() * light_direction.getZ()
            triProjected.setDp(dp)

            # Project triangles from 3D --> 2D
            multiplyMatrixVector(x.getV0(), triProjected.getV0(), matProj)
            multiplyMatrixVector(x.getV1(), triProjected.getV1(), matProj)
            multiplyMatrixVector(x.getV2(), triProjected.getV2(), matProj)

            # Scale into view
            triProjected.getV0().setX(triProjected.getV0().getX() + 1.0)
            triProjected.getV0().setY(triProjected.getV0().getY() + 1.0)
            triProjected.getV1().setX(triProjected.getV1().getX() + 1.0)
            triProjected.getV1().setY(triProjected.getV1().getY() + 1.0)
            triProjected.getV2().setX(triProjected.getV2().getX() + 1.0)
            triProjected.getV2().setY(triProjected.getV2().getY() + 1.0)

            triProjected.getV0().setX(triProjected.getV0().getX() * float(0.5) * screen_width)
            triProjected.getV0().setY(triProjected.getV0().getY() * float(0.5) * screen_height)
            triProjected.getV1().setX(triProjected.getV1().getX() * float(0.5) * screen_width)
            triProjected.getV1().setY(triProjected.getV1().getY() * float(0.5) * screen_height)
            triProjected.getV2().setX(triProjected.getV2().getX() * float(0.5) * screen_width)
            triProjected.getV2().setY(triProjected.getV2().getY() * float(0.5) * screen_height)

            pygame.draw.polygon(screen, (255 * triProjected.getDp(), 255 * triProjected.getDp() , 255 * triProjected.getDp()), 
                    [(triProjected.getV0().getX(), triProjected.getV0().getY()), 
                    (triProjected.getV1().getX(), triProjected.getV1().getY()),
                    (triProjected.getV2().getX(), triProjected.getV2().getY()),
                    (triProjected.getV0().getX(), triProjected.getV0().getY())],
                    0)

            # Draw lines of triangles, for debugging
            pygame.draw.line(screen, (0,0,0),
                            (triProjected.getV0().getX(), triProjected.getV0().getY()),
                            (triProjected.getV1().getX(), triProjected.getV1().getY()), 3)
            pygame.draw.line(screen, (0,0,0),
                            (triProjected.getV1().getX(), triProjected.getV1().getY()),
                            (triProjected.getV2().getX(), triProjected.getV2().getY()), 3)
            pygame.draw.line(screen, (0,0,0),
                            (triProjected.getV2().getX(), triProjected.getV2().getY()),
                            (triProjected.getV0().getX(), triProjected.getV0().getY()), 3)


### ON USER UPDATE
pygame.init()
screenSize = pygame.display.Info()
screen_width:float = screenSize.current_w * .75
screen_height:float = screenSize.current_h * .75

# Points for cube
cubeTriangles = [
    # SOUTH FACE
    Triangle(Vec3d(0,0,0), Vec3d(0,1,0), Vec3d(1,1,0)),
    Triangle(Vec3d(0,0,0), Vec3d(1,1,0), Vec3d(1,0,0)),

    # EAST FACE
    Triangle(Vec3d(1,0,0), Vec3d(1,1,0), Vec3d(1,1,1)),
    Triangle(Vec3d(1,0,0), Vec3d(1,1,1), Vec3d(1,0,1)),

    # NORTH FACE
    Triangle(Vec3d(1,0,1), Vec3d(1,1,1), Vec3d(0,1,1)),
    Triangle(Vec3d(1,0,1), Vec3d(0,1,1), Vec3d(0,0,1)),

    # WEST FACE
    Triangle(Vec3d(0,0,1), Vec3d(0,1,1), Vec3d(0,1,0)),
    Triangle(Vec3d(0,0,1), Vec3d(0,1,0), Vec3d(0,0,0)),

    # TOP FACE
    Triangle(Vec3d(0,1,0), Vec3d(0,1,1), Vec3d(1,1,1)),
    Triangle(Vec3d(0,1,0), Vec3d(1,1,1), Vec3d(1,1,0)),

    # BOTTOM FACE
    Triangle(Vec3d(1,0,1), Vec3d(0,0,1), Vec3d(0,0,0)),
    Triangle(Vec3d(1,0,1), Vec3d(0,0,0), Vec3d(1,0,0))
]

myPlane = Mesh()
myPlane.loadFromObjectFile("MyPlane.obj")
meshCube = Mesh(cubeTriangles)
meshCone = Mesh()
meshCone.loadFromObjectFile("cone.obj")
meshSphere = Mesh()
meshSphere.loadFromObjectFile("sphere.obj")
fTheta:float = 0.0
userPositionX:float = 0
userPositionY:float = 0
userPositionZ:float = 64
vCamera:Vec3d = Vec3d(userPositionX, userPositionY, userPositionZ)

# Projection Matrix Constants
fNear:float = 0.1
fFar:float = 1000.0
fFov:float = 90
fAspectRatio:float = (screen_height / screen_width)
fFovRad:float = 1 / math.tan(((fFov * 0.5) / (180.0 * math.pi)))

matProj:Mat4x4 = Mat4x4()
matProj.setIndex(0,0, (fAspectRatio * fFovRad))
matProj.setIndex(0,1, 0)
matProj.setIndex(0,2, 0)
matProj.setIndex(0,3, 0)
matProj.setIndex(1,0, 0)
matProj.setIndex(1,1, fFovRad)
matProj.setIndex(1,2, 0)
matProj.setIndex(1,3, 0)
matProj.setIndex(2,0, 0)
matProj.setIndex(2,1, 0)
matProj.setIndex(2,2, (fFar + fNear) / (fFar - fNear))
matProj.setIndex(2,3, 1)
matProj.setIndex(3,0, 0)
matProj.setIndex(3,1, 0)
matProj.setIndex(3,2, (2 * fNear * fFar) / (fNear - fFar))
matProj.setIndex(3,3, 0)

### ON USER UPDATE
startTime = time.time()
elapsedTime = 0
screen = pygame.display.set_mode([screen_width, screen_height])

# Rotation Matrices
matRotZ:Mat4x4 = Mat4x4()
matRotX:Mat4x4 = Mat4x4()
matRotY:Mat4x4 = Mat4x4()
customRotation:float = 0

running = True
rotation = True
while running:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False
        keys = pygame.key.get_pressed()
        if keys[pygame.K_a]:
            userPositionX += 0.25
        if keys[pygame.K_d]:
            userPositionX -= 0.25
        if keys[pygame.K_w]:
            userPositionZ -= 2
        if keys[pygame.K_s]:
            userPositionZ += 2
        if keys[pygame.K_LCTRL]:
            userPositionY += .25
        if keys[pygame.K_LSHIFT]:
            userPositionY -= .25
        if keys[pygame.K_q]:
            customRotation += .05
        if keys[pygame.K_e]:
            customRotation -= .05
        if event.type == pygame.KEYDOWN:
            if event.key == pygame.K_x:
                rotation = not rotation
                startTime = time.time()
    vCamera = Vec3d(userPositionX, userPositionY, userPositionZ)
    
    fTheta = elapsedTime
    # Z ROT
    matRotZ.setIndex(0,0, math.cos(customRotation))
    matRotZ.setIndex(0,1, math.sin(customRotation))
    matRotZ.setIndex(1,0, -math.sin(customRotation))
    matRotZ.setIndex(1,1, math.cos(customRotation))

    # X ROT
    matRotX.setIndex(1,1, math.cos(fTheta * 0.5))
    matRotX.setIndex(1,2, math.sin(fTheta * 0.5))
    matRotX.setIndex(2,1, -math.sin(fTheta * 0.5))
    matRotX.setIndex(2,2, math.cos(fTheta * 0.5))

    # Y ROT
    matRotY.setIndex(0,0, math.cos(fTheta * 0.5))
    matRotY.setIndex(0,2, -math.sin(fTheta * 0.5))
    matRotY.setIndex(2,0, math.sin(fTheta * 0.5))
    matRotY.setIndex(2,2, math.cos(fTheta * 0.5))
    
    # Drawing Area
    screen.fill((0, 0, 0))
    if rotation == True:
        elapsedTime = time.time() - startTime
    else:
        elapsedTime = elapsedTime

    drawObject(myPlane, 0, 0, 16, vCamera)

    pygame.display.flip()
