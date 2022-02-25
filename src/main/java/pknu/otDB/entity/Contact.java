package pknu.otDB.entity;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "CONTACTS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class Contact {

    @Id
    @Column(name = "contact_id")
    private long id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
//    @Column(name = "customer_id")
    @JoinColumn(name = "customer_id")
    @NotNull
    private Customer customer;

}
