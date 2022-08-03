#ifndef CUBE_CLASS_H
#define CUBE_CLASS_H

#include "Mesh.h"

class Cube {
    public:
        std::vector <Vertex> vertices;
        std::vector <GLuint> indices;
        std::vector <Texture> textures;
        Cube();

        void Draw(Shader& shader, Camera& camera, glm::mat4& matrix, glm::vec3& translation, glm::vec3& rotation, glm::vec3& scale);
        void translate(glm::vec3 vector);
    private:
        Mesh mesh;

        void SetupMesh();
};

#endif