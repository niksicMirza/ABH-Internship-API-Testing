import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.testng.Assert;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class Bodies {


  public void login(User1 user, User2 user1, String API) {
    Response loginResponse = RestAssured.given().
            contentType(ContentType.JSON).
            body(user).
            when().
            post(API).
            then().
            extract().response();

    Assert.assertEquals(loginResponse.getStatusCode(), 200);
    user.setAuthenticationToken(loginResponse.jsonPath().get("authenticationToken"));
    user.setEmail(loginResponse.jsonPath().get("email"));
    user.setId(loginResponse.jsonPath().get("id"));

    user1.setEmail(loginResponse.jsonPath().get("email"));
    user1.setId(loginResponse.jsonPath().get("id"));
  }

  public void register(User1 user, String API) {
    Response registerResponse = RestAssured.given().
            contentType(ContentType.JSON).
            body(user).
            when().
            post(API).
            then().
            extract().response();

    Assert.assertEquals(registerResponse.getStatusCode(), 200);
    Assert.assertEquals(registerResponse.jsonPath().get("message"), Utils.REGISTER_MESSAGE);
  }

  public void getUser(User1 user, User2 user1, String API) {
    Response userEmailResponse = RestAssured.given().
            header("Authorization", "Bearer " + user.getAuthenticationToken()).
            header("Content-Type", ContentType.JSON).
            header("Accept", ContentType.JSON).
            when().
            get(API + "{email}", user.getEmail()).
            then().assertThat().
            statusCode(200).
            and().contentType(ContentType.JSON).and().
            body("status", equalTo("ACTIVE")).
            extract().
            response();

    user1.setFirstName(userEmailResponse.jsonPath().get("firstName"));
    user1.setLastName(userEmailResponse.jsonPath().get("lastName"));
    user1.setGender(userEmailResponse.jsonPath().get("gender"));
    user1.setCreatedAt(userEmailResponse.jsonPath().get("createdAt"));
    user1.setUpdatedAt(userEmailResponse.jsonPath().get("updatedAt"));
  }

  public void updateUser(User2 user, User1 u1, ShippingDetails shippingDetails, PaymentDetails paymentDetails, String API) {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    paymentDetails.setId("54ab18bb-959f-4d85-8865-6d8fd013ac99");
    user.setPaymentDetails(paymentDetails);
    shippingDetails.setId("54ab18bb-959f-4d85-8865-6d8fd013ac99");
    user.setShippingDetails(shippingDetails);
    user.setDateOfBirth(Utils.randomPastDate());
    user.setGender(Utils.randomGender());

    String updateUserBody = gson.toJson(user);

    Response r = RestAssured.given().
            header("Authorization", "Bearer " + u1.getAuthenticationToken()).
            header("Content-Type", ContentType.JSON).
            header("Accept", ContentType.JSON).
            body(updateUserBody).
            when().
            put(API + "{id}", user.getId()).
            then().assertThat().
            statusCode(200).
            and().contentType(ContentType.JSON).
            extract().
            response();
  }

  public void pr(User1 user) {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String updateUserBody = gson.toJson(user);
    System.out.println(updateUserBody);
  }

  public void getCategories(User1 user, Categories category, String API) {
    Response auctionsResponse =
      RestAssured.given().
        header("Authorization", "Bearer " + user.getAuthenticationToken()).
        when().
        get(API).
        then().assertThat().
        statusCode(200).
        extract().response();

    JsonPath idInst = auctionsResponse.jsonPath();
    List<String> ids = idInst.getList("id");
    int index = Utils.randomCategoryID(ids);
    String instrumentsID = ids.get(index);

    JsonPath nameInst = auctionsResponse.jsonPath();
    List<String> names = nameInst.getList("name");
    String name = names.get(index);

    JsonPath instrumentssub = auctionsResponse.jsonPath();
    List<String> instrumentssubs = instrumentssub.getList("subcategoryOf");
    String instrumentssubb = instrumentssubs.get(index);

    category.setId(instrumentsID);
    category.setName(name);
    category.setSubcategoryOf(instrumentssubb);
  }

  public void getBidDetails(User1 user1, Auctions auction, PriceCount priceCount, String API, String API2){

    Response highestBidResponse = RestAssured.given().
            header("Authorization", "Bearer " + user1.getAuthenticationToken()).
            when().
            get(API + "{auctionID}", auction.getId()).
            then().assertThat().
            statusCode(200).
            extract().response();

    //EXTRACT HIGHEST PRICE
    String currentHighestBid = highestBidResponse.getBody().asString();
    float currentHighestBidF = Float.parseFloat(currentHighestBid);
    priceCount.setPrice(currentHighestBidF);

    Response bidCountResponse = RestAssured.given().
            header("Authorization", "Bearer " + user1.getAuthenticationToken()).
            when().
            get(API2 + "{auctionID}", auction.getId()).
            then().assertThat().
            statusCode(200).
            extract().response();
    //SET BID COUNT
    String count = bidCountResponse.getBody().asString();
    int count1 = Integer.parseInt(count);
    priceCount.setCount(count1);
  }

  public void getAuction(User2 user, User1 u2, Categories categories, Auctions auctions, Items items, String API) {
    Response auctionsResponse =
      RestAssured.given().
        header("Authorization", "Bearer " + u2.getAuthenticationToken()).
        when().
        get(API).
        then().assertThat().
        statusCode(200).
        extract().response();

    JsonPath idAuction = auctionsResponse.jsonPath();
    List<String> ids = idAuction.getList("id");
    int index = Utils.randomCategoryID(ids);
    String auctionID = ids.get(index);
    auctions.setId(auctionID);

    JsonPath startDate = auctionsResponse.jsonPath();
    List<String> startDates = startDate.getList("startDate");
    String date = startDates.get(index);
    auctions.setStartDate(date);

    JsonPath ed = auctionsResponse.jsonPath();
    List<String> endDates = ed.getList("endDate");
    String endDate = endDates.get(index);
    auctions.setEndDate(endDate);

    JsonPath sP = auctionsResponse.jsonPath();
    List<Float> startPrices = sP.getList("startPrice");
    float startPrice = startPrices.get(index);
    auctions.setStartPrice(startPrice);

    JsonPath categoryIDS = auctionsResponse.jsonPath();
    List<String> idss = categoryIDS.getList("category.id");
    String catID = idss.get(index);
    categories.setId(catID);

    JsonPath categoryNames = auctionsResponse.jsonPath();
    List<String> names = categoryNames.getList("category.name");
    String name = names.get(index);
    categories.setName(name);

    JsonPath subCategories = auctionsResponse.jsonPath();
    List<String> subs = subCategories.getList("category.subcategory");
    String subCategory = subs.get(index);
    categories.setSubcategoryOf(subCategory);

    JsonPath itemIDS = auctionsResponse.jsonPath();
    List<String> itemidss = itemIDS.getList("item.id");
    String itemID = itemidss.get(index);
    items.setId(itemID);

    JsonPath itemNames = auctionsResponse.jsonPath();
    List<String> namesI = itemNames.getList("item.name");
    String itemName = namesI.get(index);
    items.setName(itemName);

    JsonPath colors = auctionsResponse.jsonPath();
    List<String> itemColors = colors.getList("item.color");
    String itemColor = itemColors.get(index);
    items.setColor(itemColor);

    items.setSize(20);

    JsonPath desc = auctionsResponse.jsonPath();
    List<String> descriptions = desc.getList("item.description");
    String description = descriptions.get(index);
    items.setDescription(description);

    auctions.setCategory(categories);
    auctions.setSeller(user);
    auctions.setItem(items);

    Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
    String updateUserBody = gson.toJson(auctions);
    System.out.println(updateUserBody);
  }

  public void postNewBid(User1 u2, User2 bidder, Auctions auctions,Bids bids, PriceCount priceCount, String API) {

    bids.setId("c38b1c6e-8c73-467d-a96d-61c7fe5719be");
    bidder.setId("ae066e96-cec5-4c8e-abb6-b5fef160f295");

    bids.setBidAmount(priceCount.getPrice() + 1);
    System.out.println("BID  " + bids.getBidAmount());
    bids.setAuction(auctions);
    bids.setBidder(bidder);
    Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
    String newBidBody = gson.toJson(bids);
    System.out.println(newBidBody);

    Response newBidResponse = RestAssured.given().
            header("Authorization", "Bearer " + u2.getAuthenticationToken()).
            contentType("application/json\r\n").
            body(newBidBody).
            when().
            post(API).
            then().assertThat().
            statusCode(200).
            extract().response();
    newBidResponse.prettyPrint();
  }
}
