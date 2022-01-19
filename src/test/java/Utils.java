import com.github.javafaker.Faker;
import javafx.beans.binding.Bindings;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Utils {

  //URLs
  public final static String HOME_PAGE_URL = "https://bidba.herokuapp.com/";
  public final static String API_BASE_URL = "https://bidba-api.herokuapp.com/";
  public final static String API_REGISTER_URL = "api/auth/register";
  public final static String API_LOGIN_URL = "api/auth/login";
  public final static String REGISTER_MESSAGE = "User registration successful";
  public final static String API_GET_USER_STATUS_URL = "https://bidba-api.herokuapp.com/api/auctions/";
  public final static String API_GET_USER_URL = "https://bidba-api.herokuapp.com/api/users/";
  public final static String API_GET_CATEGORIES_URL = "https://bidba-api.herokuapp.com/api/categories";
  public final static String API_GET_FILTERED_AUCTIONS_URL = "https://bidba-api.herokuapp.com/api/auctions/categories/filter";
  public final static String API_UPDATE_USER = "https://bidba-api.herokuapp.com/api/users/update/";
  public final static String API_GET_LAST_CHANCE = "https://bidba-api.herokuapp.com/api/auctions/last_chance";
  public final static String API_POST_NEW_BID = "https://bidba-api.herokuapp.com/api/bids/newBid";
  public final static String API_GET_HIGHEST_BID = "https://bidba-api.herokuapp.com/api/bids/highestBid/";
  public final static String API_GET_NO_OF_BIDS = "https://bidba-api.herokuapp.com/api/bids/noOfBids/";
  public final static String API_DEACTIVATE_USER = "https://bidba-api.herokuapp.com/api/users/deactivate/";

  public final static String STATUS_ACTIVE = "ACTIVE";
  public final static String STATUS_INACTIVE = "INACTIVE";

  public final static int STATUS_CODE_OK = 200;
  public final static int STATUS_CODE_CREATED = 201;
  public final static int STATUS_CODE_NO_CONTENT = 204;
  public final static int STATUS_CODE_CONFLICT = 409;

  public static String getCurrentDateAndTime(){
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    return sdf2.format(timestamp);
  }

  public static String randomEmail(){
    String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZqazwsxedcrfvtgbyhnujmiklop1234567890";
    StringBuilder email = new StringBuilder();
    Random rnd = new Random();
    while (email.length() < 15) { // length of the random string.
      int index = (int) (rnd.nextFloat() * chars.length());
      email.append(chars.charAt(index));
    }
    String emailAddress = email.toString() + "@gmail.com";
    return emailAddress;
  }

  public static void enterEmailInTxtFile(String emailAddress) throws IOException {
    File file = new File("Emails.txt");
    FileWriter fw = new FileWriter(file, true);
    PrintWriter pw = new PrintWriter(fw);

    pw.println("Email address: " + emailAddress);
    pw.close();
  }

  public static String randomFirstName(){
    Faker faker = new Faker();
    return faker.name().firstName();
  }

  public static String randomLastName(){
    Faker faker = new Faker();
    return faker.name().lastName();
  }

  public static String randomPhoneNumber(){
    Faker faker = new Faker();
    return faker.phoneNumber().cellPhone();
  }

  public static String randomGender(){
    Random rand = new Random();
    List<String> givenList = Arrays.asList("MALE", "FEMALE");
    int randomIndex = rand.nextInt(givenList.size());
    String gender = givenList.get(randomIndex);
    return gender;
  }

  public static String randomPastDate(){
    LocalDate startDate = LocalDate.of(1950, 1, 1); //start date
    long start = startDate.toEpochDay();

    LocalDate endDate = LocalDate.now(); //end date
    long end = endDate.toEpochDay();

    long randomEpochDay = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();
    String date = LocalDate.ofEpochDay(randomEpochDay).toString();
    return date;
  }

  public static String randomFutureDate(){
    LocalDate endDate = LocalDate.of(2035, 1, 1); //end date
    long end = endDate.toEpochDay();

    LocalDate startDate = LocalDate.now(); //start date
    long start = startDate.toEpochDay();

    long randomEpochDay = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();
    String date = LocalDate.ofEpochDay(randomEpochDay).toString();
    return date;
  }

  public static String cardNumber(){
    Faker faker = new Faker();
    return faker.finance().creditCard();
  }

  public static String countryName(){
    Faker faker = new Faker();
    return faker.country().name();
  }

  public static String cityAndStateName(){
    Faker faker = new Faker();
    return faker.country().capital();
  }

  public static String zipCode(){
    Faker faker = new Faker();
    return faker.address().zipCode();
  }

  public static String streetName(){
    Faker faker = new Faker();
    return faker.address().streetAddress();
  }

  public static String verificationCode(){
    Faker faker = new Faker();
    return faker.finance().iban();
  }

  public static int randomCategoryID(List<String> s){
    Random rand = new Random();
    int randomIndex = rand.nextInt(s.size());
    return randomIndex;
  }
}

