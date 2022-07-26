package org.mjtech.tourguide.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mjtech.tourguide.model.Provider;
import org.mjtech.tourguide.model.user.User;
import org.mjtech.tourguide.model.user.UserPreferences;
import org.mjtech.tourguide.web.service.RewardsService;
import org.mjtech.tourguide.web.service.TripDealsService;
import org.mjtech.tourguide.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

@Tag("TripDealsServiceImplTest")
@DisplayName("TripDeals Service implement test logic")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class TripDealsServiceImplTest {

  @Autowired
  private TripDealsService tripDealsService;

  @Autowired
  private UserService userService;

  @Test
  @DisplayName("getTripDeals should return user provider list for given user")
  void getTripDeals_ShouldReturnUserProviderList_ForGivenUser() throws ExecutionException, InterruptedException {
    // GIVEN
    User user = new User(UUID.randomUUID(), "internalUser78", "000", "internalUser78@tourGuide.com", new UserPreferences(2, 7));

    // WHEN
    List<Provider> providers = tripDealsService.getTripDeals(user).get();
    userService.tracker.stopTracking();

    // THEN
    assertEquals(10, providers.size());
  }
}