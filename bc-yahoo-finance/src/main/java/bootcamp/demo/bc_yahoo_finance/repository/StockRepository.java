package bootcamp.demo.bc_yahoo_finance.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import bootcamp.demo.bc_yahoo_finance.entity.StockEntity;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, Long> {

  Optional<StockEntity> findBySymbol(String symbol);
}
