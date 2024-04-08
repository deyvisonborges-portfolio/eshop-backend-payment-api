//package com.mach.machorderrestapi.common.events;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public final class InMemoryEventBus {
//
//	private static final InMemoryEventBus instance = new InMemoryEventBus();
//
//	private final Map<String, List<IIntegrationEventHandler<? extends IntegrationEvent>>> handlersDictionary;
//
//	private InMemoryEventBus() {
//		handlersDictionary = new HashMap<>();
//	}
//
//	public static InMemoryEventBus getInstance() {
//		return instance;
//	}
//
//	public <T extends IntegrationEvent> void subscribe(IIntegrationEventHandler<T> handler) {
//		Class<?> eventType = handler.getEventType();
//		if (eventType != null) {
//			String eventTypeFullName = eventType.getName();
//			if (handlersDictionary.containsKey(eventTypeFullName)) {
//				List<IIntegrationEventHandler<? extends IntegrationEvent>> handlers = handlersDictionary.get(eventTypeFullName);
//				handlers.add(handler);
//			} else {
//				List<IIntegrationEventHandler<? extends IntegrationEvent>> newHandlers = new ArrayList<>();
//				newHandlers.add(handler);
//				handlersDictionary.put(eventTypeFullName, newHandlers);
//			}
//		}
//	}
//
//	public <T extends IntegrationEvent> void publish(T event) {
//		String eventTypeFullName = event.getClass().getName();
//		if (!handlersDictionary.containsKey(eventTypeFullName)) {
//			return;
//		}
//
//		List<IIntegrationEventHandler<? extends IntegrationEvent>> integrationEventHandlers = handlersDictionary.get(eventTypeFullName);
//		for (IIntegrationEventHandler<? extends IntegrationEvent> integrationEventHandler : integrationEventHandlers) {
//			if (integrationEventHandler instanceof IIntegrationEventHandler) {
//				IIntegrationEventHandler<T> handler = (IIntegrationEventHandler<T>) integrationEventHandler;
//				handler.handle(event);
//			}
//		}
//	}
//}
