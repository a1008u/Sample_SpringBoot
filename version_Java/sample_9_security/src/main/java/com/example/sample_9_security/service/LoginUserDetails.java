package com.example.sample_9_security.service;


import com.example.sample_9_security.domain.User;
import lombok.Data;
import org.springframework.security.core.authority.AuthorityUtils;


/**
 * UserDetailsのデフォルト実装である、org.springframework.security.core.userdetails.Userを拡張する
 */
@Data
public class LoginUserDetails extends org.springframework.security.core.userdetails.User {

    // Spring Securityの認証ユーザから、実際のUserオブジェクトを取得できるようにする
    private final User user;

    public LoginUserDetails(User user) {
        // コンストラクタを使って、　ユーザ名、パスワード、許可用ロールを設定する
        super(user.getUsername(), user.getEncodedPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
        this.user = user;
    }
}