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
public class Auctions {
  private String id;
  private String startDate;
  private String endDate;
  private float startPrice;
  private final String status = "ACTIVE";
  private User2 seller;
  private Categories category;
  private Items item;
}

