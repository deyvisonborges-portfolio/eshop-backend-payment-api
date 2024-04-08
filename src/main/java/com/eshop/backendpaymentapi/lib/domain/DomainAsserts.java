//package com.mach.machorderrestapi.common.domain;
//
//import java.util.ArrayList;
//
//public final class DomainAsserts {
//
//	private DomainAsserts() {}
//
//	public static <T> DomainAssert<T> begin(T value) {
//		return new DomainAssert<>(value);
//	}
//
//	private static <T> DomainAssert<T> begin(T value, List<DomainError> errors) {
//		return new DomainAssert<>(value, errors);
//	}
//
//	public static class DomainAssert<T> {
//		private final List<DomainError> domainErrors = new ArrayList<>();
//
//		private final T actual;
//
//		private DomainAssert(T value) {
//			this(value, new ArrayList<>());
//		}
//
//		private DomainAssert(T value, List<DomainError> domainErrors) {
//			this.actual = value;
//			this.domainErrors.addAll(domainErrors);
//		}
//
//		public DomainAssert<T> notNull(Supplier<DomainError> error) {
//			isNotNull(error);
//			return this;
//		}
//
//		public DomainAssert<T> notBlank(Supplier<DomainError> error) {
//			final boolean notNull = isNotNull(error);
//
//			if (notNull) {
//				String s = (String) this.actual;
//
//				if (s.isBlank()) {
//					this.domainErrors.add(error.get());
//				}
//			}
//
//			return this;
//		}
//
//		public DomainAssert<T> raiseIf(Predicate<T> predicate, Supplier<DomainError> error) {
//			final boolean notNull = isNotNull(error);
//
//			if (notNull) {
//
//				if (predicate.test(this.actual)) {
//					this.domainErrors.add(error.get());
//				}
//			}
//
//			return this;
//		}
//
//		public void end() {
//			if (this.domainErrors.isEmpty()) {
//				return;
//			}
//			throw new DomainException(this.domainErrors);
//		}
//
//		public <S> DomainAssert<S> switchOn(S value) {
//			return DomainAsserts.begin(value, this.domainErrors);
//		}
//
//		private boolean isNotNull(Supplier<DomainError> error) {
//			if (Objects.isNull(this.actual)) {
//				this.domainErrors.add(error.get());
//				return false;
//			}
//			return true;
//		}
//	}
//}