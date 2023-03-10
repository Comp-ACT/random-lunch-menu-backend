package com.TeamSk.JMC.Domain.Member;

import com.TeamSk.JMC.Domain.RoomMember.RoomMember;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Member implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;
    @Column(name = "USER_EMAIL", nullable = false, length = 100, unique = true)
    private String name;
    private String email;
    private String profileImageURL;
    @OneToMany(mappedBy = "member")
    private List<RoomMember> roomUsers;

    @Builder
    public Member(String name, String email, String profileImageURL) {
        this.name = name;
        this.email = email;
        this.profileImageURL = profileImageURL;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
