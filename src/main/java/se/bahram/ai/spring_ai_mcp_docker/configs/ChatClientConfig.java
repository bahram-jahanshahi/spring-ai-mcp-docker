package se.bahram.ai.spring_ai_mcp_docker.configs;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    @Bean
    ChatClient chatClient(ChatModel chatModel, SyncMcpToolCallbackProvider mcpTools) {
        return ChatClient.builder(chatModel)
                .defaultToolCallbacks(mcpTools.getToolCallbacks()) // expose Docker MCP tools
                .build();
    }
}
