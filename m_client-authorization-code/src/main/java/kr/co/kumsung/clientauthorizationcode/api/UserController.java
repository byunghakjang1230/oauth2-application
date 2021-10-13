package kr.co.kumsung.clientauthorizationcode.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.kumsung.clientauthorizationcode.domain.UserProfile;

@RestController
public class UserController {
    @GetMapping("/api/profile")
    public ResponseEntity<UserProfile> profile() {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        return ResponseEntity.ok(new UserProfile(user.getUsername(), user.getUsername() + "@ggg.gg"));
    }
}
