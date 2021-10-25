package kr.co.kumsung.oauth2provider.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/api/profile")
    public ResponseEntity<UserProfile> profile() {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        return ResponseEntity.ok(new UserProfile(user.getUsername(), user.getUsername() + "@ggg.gg"));
    }
}
