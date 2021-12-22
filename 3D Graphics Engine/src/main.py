import pygame
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
    w:float = (input.getX() * matrix.getIndex(0,3)) + (input.getY() * matrix.getIndex(1,3)) + (input.getZ() * matrix.getIndex(2,3)) + matrix.getIndex(3,3)

    if w != 0.0:
        output.setX(output.getX() / w)
        output.setY(output.getY() / w)
        output.setZ(output.getZ() / w)

# Draws object specified
def drawObject(mesh, xOffset=0, yOffset=0, zOffset=0, applyRotation=True, vCamera=Vec3d()):
    for tri in mesh.getMesh():
        triProjected, triTranslated, triRotatedZ, triRotatedZX, triRotatedZXY, triIdentity = Triangle(), Triangle(), Triangle(), Triangle(), Triangle(), Triangle()

        if applyRotation:
            # Apply rotations
            multiplyMatrixVector(tri.getV0(), triRotatedZ.getV0(), matRotZ)
            multiplyMatrixVector(tri.getV1(), triRotatedZ.getV1(), matRotZ)
            multiplyMatrixVector(tri.getV2(), triRotatedZ.getV2(), matRotZ)
            multiplyMatrixVector(triRotatedZ.getV0(), triRotatedZX.getV0(), matRotX)
            multiplyMatrixVector(triRotatedZ.getV1(), triRotatedZX.getV1(), matRotX)
            multiplyMatrixVector(triRotatedZ.getV2(), triRotatedZX.getV2(), matRotX)
            

            triTranslated = triRotatedZX
            triTranslated.getV0().setZ(triRotatedZX.getV0().getZ() + zOffset)
            triTranslated.getV1().setZ(triRotatedZX.getV1().getZ() + zOffset)
            triTranslated.getV2().setZ(triRotatedZX.getV2().getZ() + zOffset)
            triTranslated.getV0().setY(triRotatedZX.getV0().getY() + yOffset)
            triTranslated.getV1().setY(triRotatedZX.getV1().getY() + yOffset)
            triTranslated.getV2().setY(triRotatedZX.getV2().getY() + yOffset)
            triTranslated.getV0().setX(triRotatedZX.getV0().getX() + xOffset)
            triTranslated.getV1().setX(triRotatedZX.getV1().getX() + xOffset)
            triTranslated.getV2().setX(triRotatedZX.getV2().getX() + xOffset)

            normal, line1, line2 = Vec3d(), Vec3d(), Vec3d() 
            line1.setX(triTranslated.getV1().getX() - triTranslated.getV0().getX())
            line1.setY(triTranslated.getV1().getY() - triTranslated.getV0().getY())
            line1.setZ(triTranslated.getV1().getZ() - triTranslated.getV0().getZ())

            line2.setX(triTranslated.getV2().getX() - triTranslated.getV0().getX())
            line2.setY(triTranslated.getV2().getY() - triTranslated.getV0().getY())
            line2.setZ(triTranslated.getV2().getZ() - triTranslated.getV0().getZ())

            normal.setX(line1.getY() * line2.getZ() - line1.getZ() * line2.getY())
            normal.setY(line1.getZ() * line2.getX() - line1.getX() * line2.getZ())
            normal.setZ(line1.getX() * line2.getY() - line1.getY() * line2.getX())

            length = math.sqrt(math.pow(normal.getX(), 2) + math.pow(normal.getY(), 2) + math.pow(normal.getZ(), 2))
            normal.setX(normal.getX() / length)
            normal.setY(normal.getY() / length)
            normal.setZ(normal.getZ() / length)
        else:
            multiplyMatrixVector(tri.getV0(), triIdentity.getV0(), Mat4x4())
            multiplyMatrixVector(tri.getV1(), triIdentity.getV1(), Mat4x4())
            multiplyMatrixVector(tri.getV2(), triIdentity.getV2(), Mat4x4())
            triTranslated = triIdentity
            triTranslated.getV0().setZ(triIdentity.getV0().getZ() + zOffset)
            triTranslated.getV1().setZ(triIdentity.getV1().getZ() + zOffset)
            triTranslated.getV2().setZ(triIdentity.getV2().getZ() + zOffset)
            triTranslated.getV0().setY(triIdentity.getV0().getY() + yOffset)
            triTranslated.getV1().setY(triIdentity.getV1().getY() + yOffset)
            triTranslated.getV2().setY(triIdentity.getV2().getY() + yOffset)
            triTranslated.getV0().setX(triIdentity.getV0().getX() + xOffset)
            triTranslated.getV1().setX(triIdentity.getV1().getX() + xOffset)
            triTranslated.getV2().setX(triIdentity.getV2().getX() + xOffset)

            normal, line1, line2 = Vec3d(), Vec3d(), Vec3d() 
            line1.setX(triTranslated.getV1().getX() - triTranslated.getV0().getX())
            line1.setY(triTranslated.getV1().getY() - triTranslated.getV0().getY())
            line1.setZ(triTranslated.getV1().getZ() - triTranslated.getV0().getZ())

            line2.setX(triTranslated.getV2().getX() - triTranslated.getV0().getX())
            line2.setY(triTranslated.getV2().getY() - triTranslated.getV0().getY())
            line2.setZ(triTranslated.getV2().getZ() - triTranslated.getV0().getZ())

            normal.setX(line1.getY() * line2.getZ() - line1.getZ() * line2.getY())
            normal.setY(line1.getZ() * line2.getX() - line1.getX() * line2.getZ())
            normal.setZ(line1.getX() * line2.getY() - line1.getY() * line2.getX())

            length = math.sqrt(math.pow(normal.getX(), 2) + math.pow(normal.getY(), 2) + math.pow(normal.getZ(), 2))
            normal.setX(normal.getX() / length)
            normal.setY(normal.getY() / length)
            normal.setZ(normal.getZ() / length)
        
        # if (normal.getZ() < 0):
        if (normal.getX() * (triTranslated.getV0().getX() - vCamera.getX()) + normal.getY() * (triTranslated.getV0().getY() - vCamera.getY()) + normal.getZ() * (triTranslated.getV0().getZ() - vCamera.getZ()) < 0):
            # Illumination
            light_direction = Vec3d(0,0, -1)
            length = math.sqrt(math.pow(light_direction.getX(), 2) + math.pow(light_direction.getY(), 2) + math.pow(light_direction.getZ(), 2))
            light_direction.setX(light_direction.getX() / length)
            light_direction.setY(light_direction.getY() / length)
            light_direction.setZ(light_direction.getZ() / length)
            dp = normal.getX() * light_direction.getX() + normal.getY() * light_direction.getY() + normal.getZ() * light_direction.getZ()

            if (dp > 0):
                # Project triangles from 3D --> 2D
                multiplyMatrixVector(triTranslated.getV0(), triProjected.getV0(), matProj)
                multiplyMatrixVector(triTranslated.getV1(), triProjected.getV1(), matProj)
                multiplyMatrixVector(triTranslated.getV2(), triProjected.getV2(), matProj)
                # Scale into view
                triProjected.getV0().setX(triProjected.getV0().getX() + 1.0)
                triProjected.getV0().setY(triProjected.getV0().getY() + 1.0)
                triProjected.getV1().setX(triProjected.getV1().getX() + 1.0)
                triProjected.getV1().setY(triProjected.getV1().getY() + 1.0)
                triProjected.getV2().setX(triProjected.getV2().getX() + 1.0)
                triProjected.getV2().setY(triProjected.getV2().getY() + 1.0)

                xScale = float(0.5) * screen_width
                yScale = float(0.5) * screen_height
                triProjected.getV0().setX(triProjected.getV0().getX() * xScale)
                triProjected.getV0().setY(triProjected.getV0().getY() * yScale)
                triProjected.getV1().setX(triProjected.getV1().getX() * xScale)
                triProjected.getV1().setY(triProjected.getV1().getY() * yScale)
                triProjected.getV2().setX(triProjected.getV2().getX() * xScale)
                triProjected.getV2().setY(triProjected.getV2().getY() * yScale)

                pygame.draw.polygon(screen, (abs(255 * dp),abs(255 * dp),abs(255 * dp)), 
                [(triProjected.getV0().getX(), triProjected.getV0().getY()), 
                (triProjected.getV1().getX(), triProjected.getV1().getY()),
                (triProjected.getV2().getX(), triProjected.getV2().getY()),
                (triProjected.getV0().getX(), triProjected.getV0().getY())],
                0)

                pygame.draw.line(screen, (0,0,0),
                                (triProjected.getV0().getX(), triProjected.getV0().getY()),
                                (triProjected.getV1().getX(), triProjected.getV1().getY()), 3)
                pygame.draw.line(screen, (0,0,0),
                                (triProjected.getV1().getX(), triProjected.getV1().getY()),
                                (triProjected.getV2().getX(), triProjected.getV2().getY()), 3)
                pygame.draw.line(screen, (0,0,0),
                                (triProjected.getV2().getX(), triProjected.getV2().getY()),
                                (triProjected.getV0().getX(), triProjected.getV0().getY()), 3)
            

### ON USER CREATE
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

meshCube = Mesh(cubeTriangles)
fTheta:float = 0.0
vCamera:Vec3d() = Vec3d()

# Projection Matrix Constants
fNear:float = 0.1
fFar:float = 1000.0
fFov:float = 180
fAspectRatio:float = (screen_height / screen_width)
fFovRad:float = 1 / math.tan(((fFov * 0.5) / (180.0 * 3.14159)))

matProj:Mat4x4 = Mat4x4()
matProj.setIndex(0,0, (fAspectRatio * fFovRad))
matProj.setIndex(1,1, fFovRad)
matProj.setIndex(2,2, (fFar) / (fFar - fNear))
matProj.setIndex(2,3, 1)
matProj.setIndex(3,2, (-fFar * fNear) / (fFar - fNear))
matProj.setIndex(3,3, 0)

### ON USER UPDATE
startTime = time.time()
elapsedTime = 0
screen = pygame.display.set_mode([screen_width, screen_height])
running = True
while running:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False
    
    # Drawing Area
    screen.fill((0, 0, 0))

    # Rotation Matrices
    matRotZ:Mat4x4 = Mat4x4()
    matRotX:Mat4x4 = Mat4x4()
    matRotY:Mat4x4 = Mat4x4()
    fTheta = elapsedTime * 0.5

    # Z ROT
    matRotZ.setIndex(0,0, math.cos(fTheta))
    matRotZ.setIndex(0,1, math.sin(fTheta))
    matRotZ.setIndex(1,0, -math.sin(fTheta))
    matRotZ.setIndex(1,1, math.cos(fTheta))

    # X ROT
    matRotX.setIndex(1,1, math.cos(fTheta))
    matRotX.setIndex(1,2, math.sin(fTheta))
    matRotX.setIndex(2,1, -math.sin(fTheta))
    matRotX.setIndex(2,2, math.cos(fTheta))

    # Y ROT
    matRotY.setIndex(0,0, math.cos(fTheta))
    matRotY.setIndex(0,2, -math.sin(fTheta))
    matRotY.setIndex(2,0, math.sin(fTheta))
    matRotY.setIndex(2,2, math.cos(fTheta))

    # Rotating pillar of cubes
    
    
    drawObject(meshCube, 0, 0, 22)
    pygame.display.flip()
    time.sleep(0.015)
    elapsedTime = time.time() - startTime