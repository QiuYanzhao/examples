package com.alibaba.cloud.ai.mcp.samples.client;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/mcp")
public class McpWebfluxClientController {

	private final ChatClient.Builder chatClientBuilder;
	private final ChatClient chatClient;

	@Autowired
	public McpWebfluxClientController(
		ChatClient.Builder chatClientBuilder,
		ToolCallbackProvider tools
	) {

		 this.chatClient = chatClientBuilder
			.defaultToolCallbacks(tools)
			.build();
		this.chatClientBuilder = chatClientBuilder;
	}

	@GetMapping("/getWeather")
	public Flux<String> getWeather(
		@RequestParam(value = "query", defaultValue = "北京的天气如何？") String query) {

		return chatClient.prompt(query).stream().content();
	}

	@GetMapping("/strUppercase")
	public Flux<String> strUppercase(
		@RequestParam(value = "query", defaultValue = "将 user 转为大写") String query) {

		return chatClient.prompt(query).stream().content();
	}
}
