package bootcamp.demo.bc_yahoo_finance.entity;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tstocks")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  // @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String symbol;

  @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL,
      fetch = FetchType.LAZY)
  private List<StockPriceEntity> prices;
}
