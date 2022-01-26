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
public class ShippingDetails {
  private String id;
  private  String streetName;
  private  String city;
  private  String zipCode;
  private  String state;
  private  String country;
}
