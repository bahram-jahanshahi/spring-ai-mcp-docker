package se.bahram.ai.spring_ai_mcp_docker.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/youtube")
public class YouTubeController {
    private final ChatClient chat;

    public YouTubeController(ChatClient chat) { this.chat = chat; }

    // Example: GET /summarize?url=https://www.youtube.com/watch?v=JdT78t1Offo
    @GetMapping("/summarize")
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

    @GetMapping("/info")
    public String info(@RequestParam String url) {
        var system = """
        You are a concise assistant. When a YouTube URL is provided, call the MCP tool `get_video_info`
        (from the Docker MCP Gateway) with the exact `url` argument. Then produce:
        - the video title,
        - the channel name,
        - the publish date,
        - the view count,
        - the like count (if available),
        - the description (if available).
        - the chapters (if available).
        return format as mark down.
        If video info retrieval fails or is unavailable, say so briefly.
        """;

        var user = "Please get the video info at this URL: " + url;

        return chat.prompt()
                .system(system)
                .user(user)
                .call()
                .content();
    }
}
