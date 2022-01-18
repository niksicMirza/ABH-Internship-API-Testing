import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class PaymentDetails {
  private String id;
  private final String paypal = "null";
  private  String cardName = "Name";
  private String  cardNumber;
  private  String expirationDate;
  private  String verificationCode;
}


