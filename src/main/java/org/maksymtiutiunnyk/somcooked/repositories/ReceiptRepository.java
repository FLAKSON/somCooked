package org.maksymtiutiunnyk.somcooked.repositories;

import org.maksymtiutiunnyk.somcooked.entities.ReceiptEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReceiptRepository extends JpaRepository<ReceiptEntity, Long> {
}
