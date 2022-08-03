#ifndef GRASS_CLASS_H
#define GRASS_CLASS_H

#include "Mesh.h"

class Grass {
    public:
        std::vector <Vertex> topVertices;
        std::vector <GLuint> topIndices;
        std::vector <Texture> topTextures;

        std::vector <Vertex> sideVertices;
        std::vector <GLuint> sideIndices;
        std::vector <Texture> sideTextures;

        std::vector <Vertex> bottomVertices;
        std::vector <GLuint> bottomIndices;
        std::vector <Texture> bottomTextures;
        Grass();

        void Draw(Shader& shader, Camera& camera, glm::mat4& matrix, glm::vec3& translation, glm::vec3& rotation, glm::vec3& scale);
        void translate(glm::vec3 vector);
    private:
        Mesh topMesh;
        Mesh sidesMesh;
        Mesh bottomMesh;

        void SetupMesh();
};

#endif