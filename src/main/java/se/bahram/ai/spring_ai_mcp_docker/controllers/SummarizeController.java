package se.bahram.ai.spring_ai_mcp_docker.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/summarize")
public class SummarizeController {
    private final ChatClient chat;

    public SummarizeController(ChatClient chat) { this.chat = chat; }

    // Example: GET /summarize?url=https://www.youtube.com/watch?v=JdT78t1Offo
    @GetMapping
    public String summarize(@RequestParam String url) {
        var system = """
        You are a concise assistant. When a YouTube URL is provided, call the MCP tool `get_transcript`
        (from the Docker MCP Gateway) with the exact `url` argument. Then produce:
        - a tight 2–3 sentence overview,
        - 5–8 key bullet takeaways,
        - notable quotes or timestamps if clear.
        If transcript retrieval fails or is unavailable, say so briefly.
        """;

        var user = "Please summarize the video at this URL: " + url;

        return chat.prompt()
                .system(system)
                .user(user)
                .call()
                .content();
    }
}
