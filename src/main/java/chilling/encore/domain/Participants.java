package chilling.encore.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class Participants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long participantsIdx;
    @ManyToOne
    @JoinColumn(name = "userIdx")
    private User user;
    @ManyToOne
    @JoinColumn(name = "listenIdx")
    private ListenTogether listenTogether;
}
