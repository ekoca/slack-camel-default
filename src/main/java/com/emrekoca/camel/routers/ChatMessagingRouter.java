package com.emrekoca.camel.routers;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.slack.SlackComponent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * @author ekoca
 *
 */
@Component
public class ChatMessagingRouter extends RouteBuilder {
	@Value("${slack.webhookup}")
	private String webHookUrl;

	@Value("${slack.endpoint}")
	private String slackEndpoint;

	@Override
	public void configure() throws Exception {
		from("timer:hello?period=60000").process(new MessageResolver()).to(getSlackEndpoint());
	}

	private Endpoint getSlackEndpoint() throws Exception {
		SlackComponent component = new SlackComponent();
		component.setWebhookUrl(webHookUrl);
		component.setCamelContext(getContext());
		return component.createEndpoint(slackEndpoint);
	}

	private static class MessageResolver implements Processor {
		@Override
		public void process(Exchange exchange) throws Exception {
			// getIn and getOut in Apache Camel
			// http://camel.apache.org/using-getin-or-getout-methods-on-exchange.html
			exchange.getOut().setBody("Hello Emre :)");
		}
	}
}
