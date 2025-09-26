package org.syh.demo.springai.mcpserver.remote.figma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.syh.demo.springai.mcpserver.remote.figma.context.McpServerContextHolder;
import reactor.core.scheduler.Schedulers;

@SpringBootApplication
public class FigmaRemoteMcpApplication {
	public static void main(String[] args) {
		Schedulers.onScheduleHook("figma-remote-mcp-token-propagation", runnable -> {
			String tokenAtSubmit = McpServerContextHolder.getFigmaToken();

			return () -> {
				try {
					if (tokenAtSubmit != null) {
						McpServerContextHolder.setFigmaToken(tokenAtSubmit);
					} else {
						McpServerContextHolder.clear();
					}

					runnable.run();
				} finally {
					McpServerContextHolder.clear();
				}
			};
		});

		SpringApplication.run(FigmaRemoteMcpApplication.class, args);
	}
}
