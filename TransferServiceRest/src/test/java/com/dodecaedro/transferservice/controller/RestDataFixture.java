package com.dodecaedro.transferservice.controller;

/**
 * Created by juan on 06/09/14.
 */
public class RestDataFixture {
  public static String newCustomerJSON() {
    // notice that hibernate will populate the id. here the id is provided for test purposes only
    return "{\"customerId\":1, \"firstName\":\"josé\",\"lastName\":\"mourinho\",\"phoneNumber\":\"555-543\",\"address\":\"concha espina, 1\"}";
  }
}
