#include "Grass.h"

Grass::Grass() {
    // Front face
	std::vector<Vertex> verts;
	Vertex vertex0 = Vertex();
	vertex0.position = glm::vec3(1.0, 0.0, 0.0);
	vertex0.normal = glm::vec3(1.0, 1.0, 1.0);
	vertex0.color = glm::vec3(0.0, 0.0, 0.0);
	vertex0.texUV = glm::vec2(0.0, 1.0);
	verts.push_back(vertex0);

	Vertex vertex1 = Vertex();
	vertex1.position = glm::vec3(0.0, 0.0, 0.0);
	vertex1.normal = glm::vec3(1.0, 1.0, 1.0);
	vertex1.color = glm::vec3(0.0, 0.0, 0.0);
	vertex1.texUV = glm::vec2(0.0, 0.0);
	verts.push_back(vertex1);

	Vertex vertex2 = Vertex();
	vertex2.position = glm::vec3(0.0, -1.0, 0.0);
	vertex2.normal = glm::vec3(1.0, 1.0, 1.0);
	vertex2.color = glm::vec3(0.0, 0.0, 0.0);
	vertex2.texUV = glm::vec2(1.0, 0.0);
	verts.push_back(vertex2);

	Vertex vertex3 = Vertex();
	vertex3.position = glm::vec3(1.0, -1.0, 0.0);
	vertex3.normal = glm::vec3(1.0, 1.0, 1.0);
	vertex3.color = glm::vec3(0.0, 0.0, 0.0);
	vertex3.texUV = glm::vec2(1.0, 1.0);
	verts.push_back(vertex3);

	// Back Face
	Vertex vertex4 = Vertex();
	vertex4.position = glm::vec3(1.0, 0.0, 1.0);
	vertex4.normal = glm::vec3(1.0, 1.0, 1.0);
	vertex4.color = glm::vec3(0.0, 0.0, 0.0);
	vertex4.texUV = glm::vec2(0.0, 1.0);
	verts.push_back(vertex4);

	Vertex vertex5 = Vertex();
	vertex5.position = glm::vec3(0.0, 0.0, 1.0);
	vertex5.normal = glm::vec3(1.0, 1.0, 1.0);
	vertex5.color = glm::vec3(0.0, 0.0, 0.0);
	vertex5.texUV = glm::vec2(0.0, 0.0);
	verts.push_back(vertex5);

	Vertex vertex6 = Vertex();
	vertex6.position = glm::vec3(0.0, -1.0, 1.0);
	vertex6.normal = glm::vec3(1.0, 1.0, 1.0);
	vertex6.color = glm::vec3(0.0, 0.0, 0.0);
	vertex6.texUV = glm::vec2(1.0, 0.0);
	verts.push_back(vertex6);

	Vertex vertex7 = Vertex();
	vertex7.position = glm::vec3(1.0, -1.0, 1.0);
	vertex7.normal = glm::vec3(1.0, 1.0, 1.0);
	vertex7.color = glm::vec3(0.0, 0.0, 0.0);
	vertex7.texUV = glm::vec2(1.0, 1.0);
	verts.push_back(vertex7);

	// Left Face
	Vertex vertex8 = Vertex();
	vertex8.position = glm::vec3(0.0, 0.0, 0.0);
	vertex8.normal = glm::vec3(1.0, 1.0, 1.0);
	vertex8.color = glm::vec3(0.0, 0.0, 0.0);
	vertex8.texUV = glm::vec2(0.0, 1.0);
	verts.push_back(vertex8);

	Vertex vertex9 = Vertex();
	vertex9.position = glm::vec3(0.0, 0.0, 1.0);
	vertex9.normal = glm::vec3(1.0, 1.0, 1.0);
	vertex9.color = glm::vec3(0.0, 0.0, 0.0);
	vertex9.texUV = glm::vec2(0.0, 0.0);
	verts.push_back(vertex9);

	Vertex vertex10 = Vertex();
	vertex10.position = glm::vec3(0.0, -1.0, 1.0);
	vertex10.normal = glm::vec3(1.0, 1.0, 1.0);
	vertex10.color = glm::vec3(0.0, 0.0, 0.0);
	vertex10.texUV = glm::vec2(1.0, 0.0);
	verts.push_back(vertex10);

	Vertex vertex11 = Vertex();
	vertex11.position = glm::vec3(0.0, -1.0, 0.0);
	vertex11.normal = glm::vec3(1.0, 1.0, 1.0);
	vertex11.color = glm::vec3(0.0, 0.0, 0.0);
	vertex11.texUV = glm::vec2(1.0, 1.0);
	verts.push_back(vertex11);

	// Right Face
	Vertex vertex12 = Vertex();
	vertex12.position = glm::vec3(1.0, 0.0, 1.0);
	vertex12.normal = glm::vec3(1.0, 1.0, 1.0);
	vertex12.color = glm::vec3(0.0, 0.0, 0.0);
	vertex12.texUV = glm::vec2(0.0, 1.0);
	verts.push_back(vertex12);

	Vertex vertex13 = Vertex();
	vertex13.position = glm::vec3(1.0, 0.0, 0.0);
	vertex13.normal = glm::vec3(1.0, 1.0, 1.0);
	vertex13.color = glm::vec3(0.0, 0.0, 0.0);
	vertex13.texUV = glm::vec2(0.0, 0.0);
	verts.push_back(vertex13);

	Vertex vertex14 = Vertex();
	vertex14.position = glm::vec3(1.0, -1.0, 0.0);
	vertex14.normal = glm::vec3(1.0, 1.0, 1.0);
	vertex14.color = glm::vec3(0.0, 0.0, 0.0);
	vertex14.texUV = glm::vec2(1.0, 0.0);
	verts.push_back(vertex14);

	Vertex vertex15 = Vertex();
	vertex15.position = glm::vec3(1.0, -1.0, 1.0);
	vertex15.normal = glm::vec3(1.0, 1.0, 1.0);
	vertex15.color = glm::vec3(0.0, 0.0, 0.0);
	vertex15.texUV = glm::vec2(1.0, 1.0);
	verts.push_back(vertex15);

	// Top Face
    std::vector<Vertex> topVerts;
	Vertex vertex16 = Vertex();
	vertex16.position = glm::vec3(1.0, -1.0, 0.0);
	vertex16.normal = glm::vec3(1.0, 1.0, 1.0);
	vertex16.color = glm::vec3(0.0, 0.0, 0.0);
	vertex16.texUV = glm::vec2(0.0, 1.0);
	topVerts.push_back(vertex16);

	Vertex vertex17 = Vertex();
	vertex17.position = glm::vec3(0.0, -1.0, 0.0);
	vertex17.normal = glm::vec3(1.0, 1.0, 1.0);
	vertex17.color = glm::vec3(0.0, 0.0, 0.0);
	vertex17.texUV = glm::vec2(0.0, 0.0);
	topVerts.push_back(vertex17);

	Vertex vertex18 = Vertex();
	vertex18.position = glm::vec3(0.0, -1.0, 1.0);
	vertex18.normal = glm::vec3(1.0, 1.0, 1.0);
	vertex18.color = glm::vec3(0.0, 0.0, 0.0);
	vertex18.texUV = glm::vec2(1.0, 0.0);
	topVerts.push_back(vertex18);

	Vertex vertex19 = Vertex();
	vertex19.position = glm::vec3(1.0, -1.0, 1.0);
	vertex19.normal = glm::vec3(1.0, 1.0, 1.0);
	vertex19.color = glm::vec3(0.0, 0.0, 0.0);
	vertex19.texUV = glm::vec2(1.0, 1.0);
	topVerts.push_back(vertex19);

	// Bottom Face
    std::vector<Vertex> bottomVerts;
	Vertex vertex20 = Vertex();
	vertex20.position = glm::vec3(1.0, 0.0, 0.0);
	vertex20.normal = glm::vec3(1.0, 1.0, 1.0);
	vertex20.color = glm::vec3(0.0, 0.0, 0.0);
	vertex20.texUV = glm::vec2(0.0, 1.0);
	bottomVerts.push_back(vertex20);

	Vertex vertex21 = Vertex();
	vertex21.position = glm::vec3(0.0, 0.0, 0.0);
	vertex21.normal = glm::vec3(1.0, 1.0, 1.0);
	vertex21.color = glm::vec3(0.0, 0.0, 0.0);
	vertex21.texUV = glm::vec2(0.0, 0.0);
	bottomVerts.push_back(vertex21);

	Vertex vertex22 = Vertex();
	vertex22.position = glm::vec3(0.0, 0.0, 1.0);
	vertex22.normal = glm::vec3(1.0, 1.0, 1.0);
	vertex22.color = glm::vec3(0.0, 0.0, 0.0);
	vertex22.texUV = glm::vec2(1.0, 0.0);
	bottomVerts.push_back(vertex22);

	Vertex vertex23 = Vertex();
	vertex23.position = glm::vec3(1.0, 0.0, 1.0);
	vertex23.normal = glm::vec3(1.0, 1.0, 1.0);
	vertex23.color = glm::vec3(0.0, 0.0, 0.0);
	vertex23.texUV = glm::vec2(1.0, 1.0);
	bottomVerts.push_back(vertex23);

	std::vector<GLuint> sidesInd = {
		// Front Face
		0, 1, 2,
		0, 2, 3,

		// Back Face
		6, 5, 4,
		7, 6, 4,

		// Left Face
		8, 9, 10,
		8, 10, 11,

		// Right Face
		12, 13, 14,
		12, 14, 15
		};
    std::vector<GLuint> topInd = {
        // Top Face
		0, 1, 2,
		0, 2, 3,
    };
    std::vector<GLuint> bottomInd = {
        // Bottom Face
		2, 1, 0,
		3, 2, 0
    };
	std::vector<Texture> topTex = {
		Texture("../Resources/grass.jpg", "diffuse", 0)
	};

    std::vector<Texture> sideTex = {
		Texture("../Resources/grassside.jpg", "diffuse", 0)
	};

    std::vector<Texture> bottomTex = {
		Texture("../Resources/dirt.jpg", "diffuse", 0)
	};

    Grass::sideVertices = verts;
    Grass::sideIndices = sidesInd;
    Grass::sideTextures = sideTex;

    Grass::topVertices = topVerts;
    Grass::topIndices = topInd;
    Grass::topTextures = topTex;

    Grass::bottomVertices = bottomVerts;
    Grass::bottomIndices = bottomInd;
    Grass::bottomTextures = bottomTex;

    SetupMesh();
}

void Grass::SetupMesh() {
    Grass::sidesMesh = Mesh(Grass::sideVertices, Grass::sideIndices, Grass::sideTextures);
    Grass::topMesh = Mesh(Grass::topVertices, Grass::topIndices, Grass::topTextures);
    Grass::bottomMesh = Mesh(Grass::bottomVertices, Grass::bottomIndices, Grass::bottomTextures);

}

void Grass::Draw(Shader& shader, Camera& camera, glm::mat4& matrix, glm::vec3& translation, glm::vec3& rotation, glm::vec3& scale) {
    Grass::topMesh.Draw(shader, camera, matrix, translation, rotation, scale);
    Grass::sidesMesh.Draw(shader, camera, matrix, translation, rotation, scale);
    Grass::bottomMesh.Draw(shader, camera, matrix, translation, rotation, scale);
}

void Grass::translate(glm::vec3 vector) {
    for (int i = 0; i < sideVertices.size(); i++) {
        sideVertices[i].position.x += vector.x;
        sideVertices[i].position.y += vector.y;
        sideVertices[i].position.z += vector.z;
    }

    for (int i = 0; i < topVertices.size(); i++) {
        topVertices[i].position.x += vector.x;
        topVertices[i].position.y += vector.y;
        topVertices[i].position.z += vector.z;
    }

    for (int i = 0; i < bottomVertices.size(); i++) {
        bottomVertices[i].position.x += vector.x;
        bottomVertices[i].position.y += vector.y;
        bottomVertices[i].position.z += vector.z;
    }

    SetupMesh();
}