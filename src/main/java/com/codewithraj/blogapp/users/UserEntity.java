package com.codewithraj.blogapp.users;

import jakarta.persistence.*;
import lombok.*;
import org.apache.tomcat.util.digester.ObjectCreateRule;
import org.hibernate.Hibernate;
import org.springframework.lang.NonNull;

import java.util.Objects;

@Entity(name = "users")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    @NonNull
    private String username;

    @Column(nullable = false)
    @NonNull
    private String email;

    @Column(nullable = true)
    @NonNull
    private String bio;

    @Column(nullable = true)
    @NonNull
    private String image;

}
