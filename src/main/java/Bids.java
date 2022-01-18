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
public class Bids {
  private String id;
  private float bidAmount;
  private String bidDate;
  private Auctions auction;
  private User2 bidder;
}

