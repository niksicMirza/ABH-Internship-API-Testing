import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.*;

public class Mirza {

  @BeforeTest
  static void preCondition() {
    baseURI = Utils.API_BASE_URL;
  }


  public static Categories category = Categories.builder().build();
  public static PriceCount priceCount = PriceCount.builder().build();
  public static Items item = Items.builder().build();
  public static Auctions auctions = Auctions.builder().build();
  public static PaymentDetails paymentDetails = PaymentDetails.builder().expirationDate(Utils.randomFutureDate()).cardNumber(Utils.cardNumber()).verificationCode(Utils.verificationCode()).build();
  public static ShippingDetails shippingDetails = ShippingDetails.builder().state(Utils.cityAndStateName()).city(Utils.cityAndStateName()).streetName(Utils.streetName()).country(Utils.countryName()).zipCode(Utils.zipCode()).build();
  public static Bids bids = Bids.builder().bidDate(Utils.getCurrentDateAndTime()).build();
  public static User1 user1 = User1.builder().email(Utils.randomEmail()).dateOfBirth(Utils.randomPastDate()).paymentDetails(paymentDetails).shippingDetails(shippingDetails).firstName(Utils.randomFirstName()).lastName(Utils.randomLastName()).phoneNum(Utils.randomPhoneNumber()).gender(Utils.randomGender()).build();
  public static User2 user2 = User2.builder().phoneNum(Utils.randomPhoneNumber()).build();
  public static User2 user3 = User2.builder().createdAt(Utils.getCurrentDateAndTime()).updatedAt(Utils.getCurrentDateAndTime()).email(Utils.randomEmail()).dateOfBirth(Utils.randomPastDate()).paymentDetails(paymentDetails).shippingDetails(shippingDetails).firstName(Utils.randomFirstName()).lastName(Utils.randomLastName()).phoneNum(Utils.randomPhoneNumber()).gender(Utils.randomGender()).build();

  @Test(testName = "Smoke test")
  public void apiTest() throws IOException {
    Bodies bodies = new Bodies();


    bodies.register(user1, Utils.API_REGISTER_URL);

    Utils.enterEmailInTxtFile(user1.getEmail());

    bodies.login(user1, user2, Utils.API_LOGIN_URL);

    bodies.getUser(user1, user2, Utils.API_GET_USER_URL);

    bodies.updateUser(user2, user1, shippingDetails, paymentDetails, Utils.API_UPDATE_USER);

    bodies.getAuction(user2, user1, category, auctions, item, Utils.API_GET_LAST_CHANCE);

    bodies.getBidDetails(user1, auctions, priceCount, Utils.API_GET_HIGHEST_BID, Utils.API_GET_NO_OF_BIDS);

    bodies.postNewBid(user1, user3, auctions, bids, priceCount, Utils.API_POST_NEW_BID);
  }
}
