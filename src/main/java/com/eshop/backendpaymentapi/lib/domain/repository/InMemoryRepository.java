//package com.eshop.backendpaymentapi.lib.domain.repository;
//
//import com.eshop.backendpaymentapi.lib.domain.Identifier;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import java.util.concurrent.ConcurrentHashMap;
//
//@Component
//public class InMemoryRepository implements InMemoryRepositoryContract{
//	public static final Map<String, String> database = new ConcurrentHashMap<>();
//	private final ObjectMapper mapper;
//
//	public InMemoryRepository(ObjectMapper objectMapper) {
//		this.mapper = objectMapper;
//	}
//
//	@Override
//	public <T> T save(final String key, final T value) throws JsonProcessingException {
//		final var data = this.mapper.writeValueAsString(value);
//		database.put(key, data);
//		sleep(30);
//		return value;
//	}
//
//	@Override
//	public <T> Optional<T> get(final String key, final Class<T> clazz) {
//		final String json = database.get(key);
//		sleep(15);
//		return Optional.ofNullable(json)
//			.map(data -> {
//				try {
//					return mapper.readValue(data, clazz);
//				} catch (JsonProcessingException e) {
//					throw new RuntimeException(e);
//				}
//			});
//	}
//
//	private void sleep(final long millis) {
//		try {
//			Thread.sleep(millis);
//		} catch (InterruptedException i) {
//			i.printStackTrace();
//		}
//	}
//
//  @Override
//  public <E> void save(E e) {
//
//  }
//
//  @Override
//  public <E> void update(E e) {
//
//  }
//
//  @Override
//  public void delete(Identifier id) {
//
//  }
//
//  @Override
//  public <E> Optional<E> findById(Identifier id) {
//    return Optional.empty();
//  }
//
//  @Override
//  public <E> List<E> findAll() {
//    return null;
//  }
//}
