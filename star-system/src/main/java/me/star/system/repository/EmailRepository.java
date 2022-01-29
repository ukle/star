package me.star.system.repository;

import me.star.system.domain.EmailConfig;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author sida_zhou
 * @description
 * @date 2022/1/28
 */
public interface EmailRepository extends JpaRepository<EmailConfig, Long> {
}
