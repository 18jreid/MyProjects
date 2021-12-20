# Justin Reid
# Utah State University
# jsreid27@gmail.com
import pygame
from vec3d import vec3d
from Triangle import Triangle
from Mat4x4 import Mat4x4
from Mesh import Mesh
from math import pi, radians, sin, cos, tan, asin, acos, atan
import time

width = 750
height = 750

# Points for cube
cubeTriangles = [
    # SOUTH FACE
    Triangle(vec3d(0,0,0), vec3d(0,1,0), vec3d(1,1,0)),
    Triangle(vec3d(0,0,0), vec3d(1,1,0), vec3d(1,0,0)),

    # EAST FACE
    Triangle(vec3d(1,0,0), vec3d(1,1,0), vec3d(1,1,1)),
    Triangle(vec3d(1,0,0), vec3d(1,1,1), vec3d(1,0,1)),

    # NORTH FACE
    Triangle(vec3d(1,0,1), vec3d(1,1,1), vec3d(0,1,1)),
    Triangle(vec3d(1,0,1), vec3d(0,1,1), vec3d(0,0,1)),

    # WEST FACE
    Triangle(vec3d(0,0,1), vec3d(0,1,1), vec3d(0,1,0)),
    Triangle(vec3d(0,0,1), vec3d(0,1,0), vec3d(0,0,0)),

    # TOP FACE
    Triangle(vec3d(0,1,0), vec3d(0,1,1), vec3d(1,1,1)),
    Triangle(vec3d(0,1,0), vec3d(1,1,1), vec3d(1,1,0)),

    # BOTTOM FACE
    Triangle(vec3d(1,0,1), vec3d(0,0,1), vec3d(0,0,0)),
    Triangle(vec3d(1,0,1), vec3d(0,0,0), vec3d(1,0,0))
]

# Create cube object and offset it into screen
cubeMesh = Mesh(cubeTriangles)

# Constants needed for projection matrix
fNear = float(0.1)
fFar = float(1000.0)
fFov = float(90.0)
fAspectRatio = float(height) / float(width)
fFovRad = atan(radians(fFov * float(0.5)))

# Projection matrix created and filled correctly
matProj = Mat4x4()
matProj.setIndex(0, 0, fAspectRatio * fFovRad)
matProj.setIndex(1, 1, fFovRad)
matProj.setIndex(2, 2, fFar / (fFar - fNear))
matProj.setIndex(3, 2, (-fFar * fNear) / (fFar - fNear))
matProj.setIndex(2,3, 1)
matProj.setIndex(3,3, 0)

# Multiplies matrices together
def multiplyMatrixVector(vector, matrix):
    resultVector = vec3d()

    resultVector.setX(vector.getX() * matrix.getIndex(0,0) + vector.getY() * matrix.getIndex(1,0) + vector.getZ() * matrix.getIndex(2,0) + matrix.getIndex(3,0))
    resultVector.setY(vector.getX() * matrix.getIndex(0,1) + vector.getY() * matrix.getIndex(1,1) + vector.getZ() * matrix.getIndex(2,1) + matrix.getIndex(3,1))
    resultVector.setZ(vector.getX() * matrix.getIndex(0,2) + vector.getY() * matrix.getIndex(1,2) + vector.getZ() * matrix.getIndex(2,2) + matrix.getIndex(3,2))
    w = vector.getX() * matrix.getIndex(0,3) + vector.getY() + matrix.getIndex(1,3) + vector.getZ() + matrix.getIndex(2,3) + matrix.getIndex(3,3)

    if (w != 0.0):
        resultVector.setX(resultVector.getX() / w)
        resultVector.setY(resultVector.getY() / w)
        resultVector.setZ(resultVector.getZ() / w)
    return resultVector

# Projects cubes points to correct positions from projection matrix
def getProjectedCube():
    #########################################
    #########################################
    # Here's where errors are being produced
    # If these lines of code are commented out the object stands still and doesn't rotate.
    # I'm not sure how instantiating these objects would change the position of the projection but it does.
    matRotX = Mat4x4()
    matRotZ = Mat4x4()

    matRotZ.setIndex(0,0, cos(fTheta))
    matRotZ.setIndex(0,1, -sin(fTheta))
    matRotZ.setIndex(1,0, sin(fTheta))
    matRotZ.setIndex(1,1, cos(fTheta))

    matRotX.setIndex(1,1, cos(fTheta * float(0.5)))
    matRotX.setIndex(1,2, sin(fTheta * float(0.5)))
    matRotX.setIndex(2,1, -sin(fTheta * float(0.5)))
    matRotX.setIndex(2,2, cos(fTheta * float(0.5)))

    #########################################
    #########################################

    for tri in cubeMesh.getMesh():
        triRotatedZ, triRotatedZX, triProjected= Triangle(), Triangle(), Triangle()

        triRotatedZ.setVertice0(multiplyMatrixVector(tri.getVertice0(), matRotZ))
        triRotatedZ.setVertice1(multiplyMatrixVector(tri.getVertice1(), matRotZ))
        triRotatedZ.setVertice2(multiplyMatrixVector(tri.getVertice2(), matRotZ))

        triRotatedZX.setVertice0(multiplyMatrixVector(triRotatedZ.getVertice0(), matRotX))
        triRotatedZX.setVertice1(multiplyMatrixVector(triRotatedZ.getVertice1(), matRotX))
        triRotatedZX.setVertice2(multiplyMatrixVector(triRotatedZ.getVertice2(), matRotX))

        triTranslated = triRotatedZX
        triTranslated.getVertice0().setZ(triRotatedZX.getVertice0().getZ() + 3)
        triTranslated.getVertice1().setZ(triRotatedZX.getVertice1().getZ() + 3)
        triTranslated.getVertice2().setZ(triRotatedZX.getVertice2().getZ() + 3)
        

        # Project triangles from 3D --> 2D
        triProjected.setVertice0(multiplyMatrixVector(triTranslated.getVertice0(), matProj))
        triProjected.setVertice1(multiplyMatrixVector(triTranslated.getVertice1(), matProj))
        triProjected.setVertice2(multiplyMatrixVector(triTranslated.getVertice2(), matProj))

        # Scale into view
        triProjected.getVertice0().setX(triProjected.getVertice0().getX() + 1)
        triProjected.getVertice0().setY(triProjected.getVertice0().getY() + 1)
        triProjected.getVertice1().setX(triProjected.getVertice1().getX() + 1)
        triProjected.getVertice1().setY(triProjected.getVertice1().getY() + 1)
        triProjected.getVertice2().setX(triProjected.getVertice2().getX() + 1)
        triProjected.getVertice2().setY(triProjected.getVertice2().getY() + 1)
        triProjected.getVertice0().setX(triProjected.getVertice0().getX() * .5 * width)
        triProjected.getVertice0().setY(triProjected.getVertice0().getY() * .5 * height)
        triProjected.getVertice1().setX(triProjected.getVertice1().getX() * .5 * width)
        triProjected.getVertice1().setY(triProjected.getVertice1().getY() * .5 * height)
        triProjected.getVertice2().setX(triProjected.getVertice2().getX() * .5 * width)
        triProjected.getVertice2().setY(triProjected.getVertice2().getY() * .5 * height)

        # Draw Triangle
        pygame.draw.line(screen, (255,255,255), (triProjected.getVertice0().getX(), triProjected.getVertice0().getY()), (triProjected.getVertice1().getX(), triProjected.getVertice1().getY()), 3)
        pygame.draw.line(screen, (255,255,255), (triProjected.getVertice0().getX(), triProjected.getVertice0().getY()), (triProjected.getVertice2().getX(), triProjected.getVertice2().getY()), 3)
        pygame.draw.line(screen, (255,255,255), (triProjected.getVertice1().getX(), triProjected.getVertice1().getY()), (triProjected.getVertice2().getX(), triProjected.getVertice2().getY()), 3)

pygame.init()
screen = pygame.display.set_mode([width, height])
running = True

elapsedTime = 0
fTheta = 0
while running:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False
    
    screen.fill((0, 0, 0))
    getProjectedCube()

    pygame.display.flip()
    time.sleep(.025)
    elapsedTime += .025
    fTheta = 1.0 * elapsedTime