import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class User1 {
  private String id;
  private String authenticationToken;
  private String email;
  private  String dateOfBirth;
  private final String password = "123123";
  private String firstName;
  private String lastName;
  private String phoneNum;
  private String gender;
  private final String role = "USER";
  private final String status = "ACTIVE";
  private String createdAt;
  private String updatedAt;
  private PaymentDetails paymentDetails;
  private ShippingDetails shippingDetails;
}


