package nowjsio.ticketing.domain.user.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import nowjsio.ticketing.domain.common.entity.BaseEntity;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Min(0)
    private int point;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    //@Enumerated(EnumType.ORDINAL) -> 0 || 1  저장
    private UserRoleType role;
}
