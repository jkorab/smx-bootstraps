package com.fusesource.examples;

import org.apache.camel.Exchange;

public class RequestReplyQueueRouter {

	private int numberOfRoutes = 32;
	private String requestQueuePrefix;
	private String responseQueuePrefix;

	public void setNumberOfRoutesMod2(int numberOfRoutes) {
		if (numberOfRoutes % 2 != 0) {
			throw new IllegalArgumentException("Number of routes must be % 2");
		}
		this.numberOfRoutes = numberOfRoutes;
	}

	public void setRequestQueuePrefix(String requestQueuePrefix) {
		this.requestQueuePrefix = requestQueuePrefix;
	}

	public void setResponseQueuePrefix(String responseQueuePrefix) {
		this.responseQueuePrefix = responseQueuePrefix;
	}

	public String selectRoute(Exchange exchange) {
		return selectRoute(exchange.getExchangeId());
	}

	private String selectRoute(String exchangeId) {
		int routeNumber = 1 + (exchangeId.hashCode() & (numberOfRoutes - 1));
		StringBuilder route = new StringBuilder("activemq:");
		route.append(requestQueuePrefix).append(".").append(routeNumber);
		if ((responseQueuePrefix != null) && (!responseQueuePrefix.equals(""))) {
			route.append("?replyTo=").append(responseQueuePrefix).append(".")
					.append(routeNumber);
		}
		return route.toString();
	}

	public static void main(String[] args) {
		for (int i = 0; i < 32; i++) {
			String id = "exchange" + i;
			RequestReplyQueueRouter router = new RequestReplyQueueRouter();
			router.setNumberOfRoutesMod2(32);
			router.setRequestQueuePrefix("ping");
			System.out.println(router.selectRoute(id));
			router.setResponseQueuePrefix("pong");
			System.out.println(router.selectRoute(id));
		}
	}

}
