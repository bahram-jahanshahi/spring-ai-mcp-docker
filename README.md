# Spring AI + MCP + Docker: YouTube Transcript Summarizer

A Spring Boot demo showing how to connect **Spring AI** with **Docker Desktop’s MCP Toolkit** to retrieve and summarize YouTube video transcripts and metadata. Styled as an agentic AI service: the LLM communicates via MCP (Model Context Protocol) tools running in Docker.


---

## ⭐ Why this matters

Machine learning models, like GPT or Claude, are powerful—but to be **truly agentic**, they need to act on real data. This project demonstrates how **MCP serves as an elegant bridge**, enabling Spring AI-based applications to call external services in a structured, discoverable way. You’ll build an app that:

- Runs MCP tools (e.g., `get_transcript`, `get_video_info`) within Docker.
- Uses Spring AI's `SyncMcpToolCallbackProvider` to inject these tools into the LLM’s context.
- Exposes simple REST endpoints for **summarizing YouTube videos** or retrieving their metadata.

---

##  Requirements

- **Java 21**, **Spring Boot 3.5.5**
- **Spring AI** version **1.0.1** (with `spring-ai-starter-mcp-client` and `spring-ai-starter-model-openai`)
- **Docker Desktop** (>= 4.44) with MCP Toolkit
- A terminal and `curl` or browser to test endpoints
- **OpenAI API Key**, available as `SPRING_AI_OPEN_AI_API_KEY`

---

##  Getting Started

1. **Clone this repository**

   ```bash
   git clone https://github.com/bahram-jahanshahi/spring-ai-mcp-docker.git
   cd spring-ai-mcp-docker
   ```

2. **Start the Docker MCP Gateway** (assuming YouTube MCP tool is installed)

   ```bash
   docker mcp gateway run
   ```

3. **Set your OpenAI API key**

   ```bash
   export SPRING_AI_OPEN_AI_API_KEY=sk-...
   ```

4. **Run the Spring Boot application**

   ```bash
   ./mvnw spring-boot:run
   ```

5. **Use the REST endpoints to interact**

   - **Summarize video transcript:**

     ```
     GET http://localhost:8080/youtube/summarize?url=https://www.youtube.com/watch?v=JdT78t1Offo
     ```

   - **Get video info (title, channel, views, description):**

     ```
     GET http://localhost:8080/youtube/info?url=https://www.youtube.com/watch?v=JdT78t1Offo
     ```

---

##  How It Works

1. The `application.yml` configures:
   - **OpenAI** chat model via Spring AI (`gpt-4o-mini`)
   - **MCP client** using `docker mcp gateway run` as the stdio connection

2. The `ChatClientConfig` class picks up MCP tools automatically and registers them with the `ChatClient`.

3. REST endpoints (`/summarize` and `/info`) construct system prompts that instruct the LLM to call the appropriate MCP tool when provided with a YouTube URL.

4. The MCP tool executes inside Docker, fetches the transcript or metadata, and returns it to the LLM, which then crafts a concise natural language response.

---

##  Project Structure

```
.
├── src
│   ├─ main
│   │   ├─ java/se/bahram/ai/spring_ai_mcp_docker/
│   │   │   ├── configs/ChatClientConfig.java
│   │   │   └── controllers/YouTubeController.java
│   │   └── resources/application.yml
├── pom.xml
└── README.md
```

---


##  Next Steps
Happy coding—and may your LLMs be ever more “agentic”!  
