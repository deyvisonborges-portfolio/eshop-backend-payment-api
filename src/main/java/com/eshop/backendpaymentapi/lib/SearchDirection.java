package com.eshop.backendpaymentapi.lib;

public enum SearchDirection {
  ASCENDANT,
  DESCENDANT;

  public static SearchDirection from(String direction) {
    if (direction != null) {
      switch (direction.toLowerCase()) {
        case "ascendant":
          return ASCENDANT;
        case "descendant":
          return DESCENDANT;
      }
    }
    throw new IllegalArgumentException("Invalid search direction: " + direction);
  }
}
