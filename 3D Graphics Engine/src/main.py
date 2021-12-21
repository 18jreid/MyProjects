import pygame
from Mat4x4 import Mat4x4
from Vec3d import Vec3d
from Triangle import Triangle
from Mesh import Mesh
import math
import time

# Multiplies a vector and matrix together
def multiplyMatrixVector(input:Vec3d, output:Vec3d, matrix:Mat4x4) -> None:
    output.setX(input.getX() * matrix.getIndex(0,0) + input.getY() * matrix.getIndex(1,0) + input.getZ() * matrix.getIndex(2,0) + matrix.getIndex(3,0))
    output.setY(input.getX() * matrix.getIndex(0,1) + input.getY() * matrix.getIndex(1,1) + input.getZ() * matrix.getIndex(2,1) + matrix.getIndex(3,1))
    output.setZ(input.getX() * matrix.getIndex(0,2) + input.getY() * matrix.getIndex(1,2) + input.getZ() * matrix.getIndex(2,2) + matrix.getIndex(3,2))
    w:float = input.getX() * matrix.getIndex(0,3) + input.getY() * matrix.getIndex(1,3) + input.getZ() * matrix.getIndex(2,3) + matrix.getIndex(3,3)

    if w != 0.0:
        output.setX(output.getX() / w)
        output.setY(output.getY() / w)
        output.setZ(output.getZ() / w)

# Draws object specified
def drawObject(mesh, xOffset=0, yOffset=0, zOffset=0, applyRotation=True):
    for tri in mesh.getMesh():
        triProjected, triTranslated, triRotatedZ, triRotatedZX, triIdentity = Triangle(), Triangle(), Triangle(), Triangle(), Triangle()

        if applyRotation:
            multiplyMatrixVector(tri.getV0(), triRotatedZ.getV0(), matRotY)
            multiplyMatrixVector(tri.getV1(), triRotatedZ.getV1(), matRotY)
            multiplyMatrixVector(tri.getV2(), triRotatedZ.getV2(), matRotY)
            triTranslated = triRotatedZ
            triTranslated.getV0().setZ(triRotatedZ.getV0().getZ() + zOffset)
            triTranslated.getV1().setZ(triRotatedZ.getV1().getZ() + zOffset)
            triTranslated.getV2().setZ(triRotatedZ.getV2().getZ() + zOffset)
            triTranslated.getV0().setY(triRotatedZ.getV0().getY() + yOffset)
            triTranslated.getV1().setY(triRotatedZ.getV1().getY() + yOffset)
            triTranslated.getV2().setY(triRotatedZ.getV2().getY() + yOffset)
            triTranslated.getV0().setX(triRotatedZ.getV0().getX() + xOffset)
            triTranslated.getV1().setX(triRotatedZ.getV1().getX() + xOffset)
            triTranslated.getV2().setX(triRotatedZ.getV2().getX() + xOffset)

            # Project triangles from 3D --> 2D
            multiplyMatrixVector(triTranslated.getV0(), triProjected.getV0(), matProj)
            multiplyMatrixVector(triTranslated.getV1(), triProjected.getV1(), matProj)
            multiplyMatrixVector(triTranslated.getV2(), triProjected.getV2(), matProj)
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

        pygame.draw.line(screen, (255,255,255), (triProjected.getV0().getX(), triProjected.getV0().getY()), (triProjected.getV1().getX(), triProjected.getV1().getY()), 2)
        pygame.draw.line(screen, (255,255,255), (triProjected.getV1().getX(), triProjected.getV1().getY()), (triProjected.getV2().getX(), triProjected.getV2().getY()), 2)
        pygame.draw.line(screen, (255,255,255), (triProjected.getV2().getX(), triProjected.getV2().getY()), (triProjected.getV0().getX(), triProjected.getV0().getY()), 2)

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

# Projection Matrix Constants
fNear:float = 0.1
fFar:float = 1000.0
fFov:float = 90.0
fAspectRatio:float = (screen_height / screen_width)
fFovRad:float = 1/ math.tan(fFov * 0.5 / 180.0 * 3.14159)

matProj:Mat4x4 = Mat4x4()
matProj.setIndex(0,0, fAspectRatio * fFovRad)
matProj.setIndex(0,1, 0)
matProj.setIndex(0,2, 0)
matProj.setIndex(0,1, 0)
matProj.setIndex(1,0, 0)
matProj.setIndex(1,1, fFovRad)
matProj.setIndex(1,2, 0)
matProj.setIndex(1,3, 0)
matProj.setIndex(2,0, 0)
matProj.setIndex(2,1, 0)
matProj.setIndex(2,2, fFar / (fFar - fNear))
matProj.setIndex(2,3, 1)
matProj.setIndex(3,0, 0)
matProj.setIndex(3,1, 0)
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
    fTheta = 1.0 * elapsedTime

    # Z ROT
    matRotZ.setIndex(0,0, math.cos(fTheta))
    matRotZ.setIndex(0,1, math.sin(fTheta))
    matRotZ.setIndex(0,2, 0)
    matRotZ.setIndex(0,3, 0)
    matRotZ.setIndex(1,0, -math.sin(fTheta))
    matRotZ.setIndex(1,1, math.cos(fTheta))
    matRotZ.setIndex(1,2, 0)
    matRotZ.setIndex(1,3, 0)
    matRotZ.setIndex(2,0, 0)
    matRotZ.setIndex(2,1, 0)
    matRotZ.setIndex(2,2, 1)
    matRotZ.setIndex(2,3, 0)
    matRotZ.setIndex(3,0, 0)
    matRotZ.setIndex(3,1, 0)
    matRotZ.setIndex(3,2, 0)
    matRotZ.setIndex(3,3, 1)

    # X ROT
    matRotX.setIndex(0,0, 1)
    matRotX.setIndex(0,1, 0)
    matRotX.setIndex(0,2, 0)
    matRotX.setIndex(0,3, 0)
    matRotX.setIndex(1,0, 0)
    matRotX.setIndex(1,1, math.cos(fTheta * 0.5))
    matRotX.setIndex(1,2, math.sin(fTheta * 0.5))
    matRotX.setIndex(1,3, 0)
    matRotX.setIndex(2,0, 0)
    matRotX.setIndex(2,1, -math.sin(fTheta * 0.5))
    matRotX.setIndex(2,2, math.cos(fTheta * 0.5))
    matRotX.setIndex(2,3, 0)
    matRotX.setIndex(3,0, 0)
    matRotX.setIndex(3,1, 0)
    matRotX.setIndex(3,2, 0)
    matRotX.setIndex(3,3, 1)

    matRotY.setIndex(0,0, math.cos(fTheta))
    matRotY.setIndex(0,1, 0)
    matRotY.setIndex(0,2, math.sin(fTheta))
    matRotY.setIndex(0,3, 0)
    matRotY.setIndex(1,0, 0)
    matRotY.setIndex(1,1, 1)
    matRotY.setIndex(1,2, 0)
    matRotY.setIndex(1,3, 0)
    matRotY.setIndex(2,0, -math.sin(fTheta))
    matRotY.setIndex(2,1, 0)
    matRotY.setIndex(2,2, math.cos(fTheta))
    matRotY.setIndex(2,3, 0)
    matRotY.setIndex(3,0, 0)
    matRotY.setIndex(3,1, 0)
    matRotY.setIndex(3,2, 0)
    matRotY.setIndex(3,3, 1)

    # Right static pillar of cubes
    drawObject(meshCube, 2, -1, 4, False)
    drawObject(meshCube, 2, 0, 4, False)
    drawObject(meshCube, 2, 1, 4, False)
    drawObject(meshCube, 2, 2, 4, False)

    # Left rotating pillar of cubes
    drawObject(meshCube, -2, -1, 6)
    drawObject(meshCube, -2, 0, 6)
    drawObject(meshCube, -2, 1, 6)
    drawObject(meshCube, -2, 2, 6)
    
    pygame.display.flip()
    time.sleep(0.015)
    elapsedTime = time.time() - startTime