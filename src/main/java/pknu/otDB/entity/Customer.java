package pknu.otDB.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CUSTOMERS")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer {

    @Id
    @Column(name = "customer_id")
    private long id;

    private String name;
//    추후 어드레스 타입을 만들어서 리전과 컨트리 로캐이션 정보 같이 활용 고려
    private String address;
    private String website;
    private int credit_limit;

//    콘택트 리스트로...
    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Contact> contactList = new ArrayList<>();
//    주문정보.... 리스트로
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Order> orderList = new ArrayList<>();

}
