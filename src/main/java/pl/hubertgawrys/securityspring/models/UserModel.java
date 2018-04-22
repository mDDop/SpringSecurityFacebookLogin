package pl.hubertgawrys.securityspring.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String login;
    private String password;
    @Column(name = "account_type")
    private String userType;
}
