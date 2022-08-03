#include "Model.h"
#include "Texture.h"
#include "Grass.h"

const int WIDTH = 700;
const int HEIGHT = 700;

void framebuffer_size_callback(GLFWwindow* window, int width, int height)
{
    glViewport(0, 0, width, height);
}

int main()
{


//------------------------------------------------------------------------------------------------------
// WINDOW INITIALIZATION AND GLAD CONFIGURATION OF OPENGL
//------------------------------------------------------------------------------------------------------
	// Initialize GLFW
	glfwInit();

	// Tell GLFW what version of OpenGL we are using 
	// In this case we are using OpenGL 3.3
	glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
	glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
	// Tell GLFW we are using the CORE profile
	// So that means we only have the modern functions
	glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

	#ifdef __APPLE__
    glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
    #endif

	// Create a GLFWwindow object of 800 by 800 pixels, naming it "YoutubeOpenGL"
	GLFWwindow* window = glfwCreateWindow(WIDTH, HEIGHT, "LearnOpenGL", NULL, NULL);
	// Error check if the window fails to create
	if (window == NULL)
	{
		std::cout << "Failed to create GLFW window" << std::endl;
		glfwTerminate();
		return -1;
	}
	// Introduce the window into the current context
	glfwMakeContextCurrent(window);
	glfwSetFramebufferSizeCallback(window, framebuffer_size_callback);

	//Load GLAD so it configures OpenGL
	gladLoadGL();
	// Specify the viewport of OpenGL in the Window
	// In this case the viewport goes from x = 0, y = 0, to x = 800, y = 800
	glViewport(0, 0, WIDTH, HEIGHT);
//------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------





//------------------------------------------------------------------------------------------------------
// CREATION OF SHADER, VAO, VBO, & EBO FOR OBJECT
//------------------------------------------------------------------------------------------------------
	// Generates Shader object using shaders default.vert and default.frag
	Shader shaderProgram("../Resources/Shaders/default.vert", "../Resources/Shaders/default.frag");
	
	glm::vec4 lightColor = glm::vec4(1.0f, 1.0f, 1.0f, 1.0f);
	glm::vec3 lightPos = glm::vec3(0.5f, 0.5f, 0.5f);
	glm::mat4 lightModel = glm::mat4(1.0f);
	lightModel = glm::translate(lightModel, lightPos);

	shaderProgram.Activate();
	glUniform4f(glGetUniformLocation(shaderProgram.ID, "lightColor"), lightColor.x, lightColor.y, lightColor.z, lightColor.w);
	glUniform3f(glGetUniformLocation(shaderProgram.ID, "lightPos"), lightPos.x, lightPos.y, lightPos.z);

	// Creates camera object
	Camera camera(WIDTH, HEIGHT, glm::vec3(0.0f, 0.0f, 2.0f));

	Model model("../Resources/sword/scene.gltf");

	Cube testCube = Cube();
	Grass testGrassCube = Grass();


	// Main while loop
	glEnable(GL_DEPTH_TEST);
	glDepthMask(GL_TRUE);
	glDepthFunc(GL_LEQUAL);
	glDepthRange(0.0f, 1.0f);
	glEnable(GL_DEPTH_CLAMP);
	glEnable(GL_CULL_FACE);
	glCullFace(GL_BACK);
	while (!glfwWindowShouldClose(window))
	{
		// Specify the color of the background
		glClearColor(.50f, .50f, 1.0f, 1.0f);
		glClearDepth(1.0f);
		// Clean the back buffer and depth buffer
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		// Handles camera inputs
		camera.Inputs(window);
		// Updates and exports the camera matrix to the Vertex Shader
		camera.updateMatrix(45.0f, 0.1f, 100.0f);

		glm::mat4 matrix = glm::mat4(1);
		glm::vec3 translation = glm::vec3(0, 0, 0);
		glm::vec3 rotation = glm::vec3(0, 0, 0);
		glm::vec3 scale = glm::vec3(1, 1, 1);

		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				translation = glm::vec3(i, -1, j);

				testGrassCube.Draw(shaderProgram, camera, matrix, translation, rotation, scale);
			}
		}

		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				for (int k = 0; k < 64; k++) {
					if (j == 0 || j == 64) {
						translation = glm::vec3(i, k, j);
						testCube.Draw(shaderProgram, camera, matrix, translation, rotation, scale);
					}
				}
			}
		}


		// Swap the back buffer with the front buffer
		glfwSwapBuffers(window);
		// Take care of all GLFW events
		glfwPollEvents();
	}



	// Delete all the objects we've created
	shaderProgram.Delete();
	// Delete window before ending the program
	glfwDestroyWindow(window);
	// Terminate GLFW before ending the program
	glfwTerminate();
	return 0;
//------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------
}