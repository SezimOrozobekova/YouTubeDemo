**YouTubeDemo Application**

**Description:**

YouTubeDemo is a web application that simulates basic functionalities of a video-sharing platform like YouTube. It allows users to create channels, upload videos, leave comments on videos, and interact with videos by liking or disliking them.

**Features:**

1. **Channel Management:**
   - Users can create channels with a unique name.
   - Each channel can have multiple videos associated with it.

2. **Video Upload:**
   - Users can upload videos with titles, descriptions, durations, and views.
   - Uploaded videos are associated with the creator's channel.

3. **Commenting:**
   - Users can leave comments on videos.
   - Comments are associated with the video they belong to.

4. **Interaction:**
   - Users can interact with videos by liking or disliking them.

**Technologies Used:**

- Java
- Spring Boot
- Spring Data JPA
- Spring MVC
- Hibernate
- MapStruct
- H2 Database (for testing)
- Maven
- JUnit
- Mockito
- Lombok

**Project Structure:**

The project follows the MVC (Model-View-Controller) architecture pattern.

- **Controller:** Handles incoming HTTP requests, processes them, and returns an appropriate response.
- **Service:** Contains business logic, interacts with repositories, and performs operations.
- **Repository:** Communicates with the database.
- **Entity:** Represents database tables and their relationships.
- **DTO (Data Transfer Object):** Used for transferring data between layers and to/from the client.

**How to Run:**

1. Clone the repository to your local machine.
2. Open the project in your preferred IDE.
3. Build the project using Maven.
4. Run the application.
5. Access the endpoints using a REST client like Postman or via a web browser.

**Endpoints:**

- `/api/v1/channel`: CRUD operations for channels.
- `/api/v1/video`: CRUD operations for videos.
- `/api/v1/comment`: CRUD operations for comments.
- `/interaction`: CRUD operations for interactions.

**Testing:**

- Unit tests: Utilize JUnit and Mockito for testing individual components.
- Integration tests: Verify the interaction between different components and simulate real-world scenarios.
