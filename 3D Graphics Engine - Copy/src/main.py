# Simple pygame program
# Import and initialize the pygame library
import pygame
import sys
from vec3d import vec3d
from triangle import triangle
from mat4x4 import mat4x4
from mesh import mesh
from math import sin, cos, tan, asin, acos, atan
import time

def multiplyMatrixVector(vector, matrix):
    resultVector = vec3d(0,0,0)

    resultVector.setX((vector.getX() * matrix.getIndex(0,0)) + (vector.getY() * matrix.getIndex(1,0)) + (vector.getZ() * matrix.getIndex(2,0)) + (matrix.getIndex(3,0)))
    resultVector.setY((vector.getX() * matrix.getIndex(0,1)) + (vector.getY() * matrix.getIndex(1,1)) + (vector.getZ() * matrix.getIndex(2,1)) + (matrix.getIndex(3,1)))
    resultVector.setZ((vector.getX() * matrix.getIndex(0,2)) + (vector.getY() * matrix.getIndex(1,2)) + (vector.getZ() * matrix.getIndex(2,2)) + (matrix.getIndex(3,2)))
    w = (vector.getX() * matrix.getIndex(0,3)) + (vector.getY() + matrix.getIndex(1,3)) + (vector.getZ() + matrix.getIndex(2,3)) + (matrix.getIndex(3,3))

    if (w != 0):
        resultVector.setX(resultVector.getX() / w)
        resultVector.setY(resultVector.getY() / w)
        resultVector.setZ(resultVector.getZ() / w)
    return resultVector

def drawTriangle(screen, vertice0, vertice1, vertice2):
    pygame.draw.circle(screen, (255,255,255), (vertice0.getX(), vertice0.getY()), 50)
    pygame.draw.circle(screen, (255,255,255), (vertice1.getX(), vertice1.getY()), 50)
    pygame.draw.circle(screen, (255,255,255), (vertice2.getX(), vertice2.getY()), 50)


pygame.init()

# Set up the drawing window
screen = pygame.display.set_mode([1000, 1000])

# Run until the user asks to quit
running = True
v0 = vec3d(0, 0, 0)
v1 = vec3d(0, 1, 0)
v2 = vec3d(1, 1, 0)

cubeTriangles = [
    # SOUTH FACE
    triangle(vec3d(0,0,0), vec3d(0,1,0), vec3d(1,1,0)),
    triangle(vec3d(0,0,0), vec3d(1,1,0), vec3d(1,0,0)),

    # EAST FACE
    triangle(vec3d(1,0,0), vec3d(1,1,0), vec3d(1,1,1)),
    triangle(vec3d(1,0,0), vec3d(1,1,1), vec3d(1,0,1)),

    # NORTH FACE
    triangle(vec3d(1,0,1), vec3d(1,1,1), vec3d(0,1,1)),
    triangle(vec3d(1,0,1), vec3d(0,1,1), vec3d(0,0,1)),

    # WEST FACE
    triangle(vec3d(0,0,1), vec3d(0,1,1), vec3d(0,1,0)),
    triangle(vec3d(0,0,1), vec3d(0,1,0), vec3d(0,0,0)),

    # TOP FACE
    triangle(vec3d(0,1,0), vec3d(0,1,1), vec3d(1,1,1)),
    triangle(vec3d(0,1,0), vec3d(1,1,1), vec3d(1,1,0)),

    # BOTTOM FACE
    triangle(vec3d(1,0,1), vec3d(0,0,1), vec3d(0,0,0)),
    triangle(vec3d(1,0,1), vec3d(0,0,0), vec3d(1,0,0))
]

cubeMesh = mesh(cubeTriangles)
for tri in cubeMesh.getMesh():
    tri.getVertice0().setZ(tri.getVertice0().getZ() + 3)
    tri.getVertice1().setZ(tri.getVertice1().getZ() + 3)
    tri.getVertice2().setZ(tri.getVertice2().getZ() + 3)

square = [
    triangle(vec3d(0,0,0), vec3d(0,1,0), vec3d(1,1,0)),
    triangle(vec3d(0,0,0), vec3d(1,1,0), vec3d(1,0,0))
]

fNear = 0.1
fFar = 1000.0
fFov = 90.0
fAspectRatio = 1000 / 1000
fFovRad = 1.0 / atan(fFov * 0.5 / 180.0 * 3.14159)

matProj = mat4x4()
matProj.setIndex(0, 0, (fAspectRatio * fFovRad))
matProj.setIndex(1, 1, (fFovRad))
matProj.setIndex(2, 2, (fFar / (fFar - fNear)))
matProj.setIndex(3, 2, (-fFar * fNear) / (fFar - fNear))
matProj.setIndex(2,3,1)
matProj.setIndex(3, 3, 0)
elapsedTime = 0

projectedCube = []
fTheta = 0
def getProjectedCube(fTheta):
    projectedCube.clear()
    matRotZ = mat4x4()
    matRotX = mat4x4()
    fTheta += 1.0 * elapsedTime

    # Rotation Z
    

    matRotX.setIndex(0,0, 1)
    matRotX.setIndex(1,1, cos(fTheta * .5))
    matRotX.setIndex(1,2, sin(fTheta * .5))
    matRotX.setIndex(2,1, -1 * sin(fTheta * .5))
    matRotX.setIndex(2,2, cos(fTheta * .5))
    matRotX.setIndex(3,3, 1)
    
    for tri in cubeMesh.getMesh():
        triProjected = triangle(vec3d(0,0,0), vec3d(0,0,0), vec3d(0,0,0))
        triTranslated = tri
        
        triProjected.setVertice0(multiplyMatrixVector(triTranslated.getVertice0(), matProj))
        triProjected.setVertice1(multiplyMatrixVector(triTranslated.getVertice1(), matProj))
        triProjected.setVertice2(multiplyMatrixVector(triTranslated.getVertice2(), matProj))

        triProjected.getVertice0().setX(triProjected.getVertice0().getX() + 1)
        triProjected.getVertice0().setY(triProjected.getVertice0().getY() + 1)
        triProjected.getVertice1().setX(triProjected.getVertice1().getX() + 1)
        triProjected.getVertice1().setY(triProjected.getVertice1().getY() + 1)
        triProjected.getVertice2().setX(triProjected.getVertice2().getX() + 1)
        triProjected.getVertice2().setY(triProjected.getVertice2().getY() + 1)

        triProjected.getVertice0().setX(triProjected.getVertice0().getX() * .5 * 1000)
        triProjected.getVertice0().setY(triProjected.getVertice0().getY() * .5 * 1000)
        triProjected.getVertice1().setX(triProjected.getVertice1().getX() * .5 * 1000)
        triProjected.getVertice1().setY(triProjected.getVertice1().getY() * .5 * 1000)
        triProjected.getVertice2().setX(triProjected.getVertice2().getX() * .5 * 1000)
        triProjected.getVertice2().setY(triProjected.getVertice2().getY() * .5 * 1000)

        projectedCube.append(triProjected)

while running:
    # Did the user click the window close button?
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False
    # Fill the background with white
    screen.fill((255, 255, 255))
    getProjectedCube(fTheta)
    for tri in projectedCube:
        pygame.draw.line(screen, (0,0,0), (tri.getVertice0().getX(), tri.getVertice0().getY()), (tri.getVertice1().getX(), tri.getVertice1().getY()), 3)
        pygame.draw.line(screen, (0,0,0), (tri.getVertice0().getX(), tri.getVertice0().getY()), (tri.getVertice2().getX(), tri.getVertice2().getY()), 3)
        pygame.draw.line(screen, (0,0,0), (tri.getVertice1().getX(), tri.getVertice1().getY()), (tri.getVertice2().getX(), tri.getVertice2().getY()), 3)    

    # Flip the display
    pygame.display.flip()
    time.sleep(.025)
    elapsedTime += .025