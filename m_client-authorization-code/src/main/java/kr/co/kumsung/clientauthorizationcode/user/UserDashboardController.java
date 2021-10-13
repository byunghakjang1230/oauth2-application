package kr.co.kumsung.clientauthorizationcode.user;

import java.util.Arrays;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

import kr.co.kumsung.clientauthorizationcode.domain.ClientUser;
import kr.co.kumsung.clientauthorizationcode.domain.UserProfile;
import kr.co.kumsung.clientauthorizationcode.security.ClientUserDetails;

@Controller
public class UserDashboardController {
    private final OAuth2RestTemplate oAuth2RestTemplate;

    public UserDashboardController(OAuth2RestTemplate oAuth2RestTemplate) {
        this.oAuth2RestTemplate = oAuth2RestTemplate;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/callback")
    public ModelAndView callback() {
        return new ModelAndView(("forward:/dashboard"));
    }

    @GetMapping("/dashboard")
    public ModelAndView dashboard() {
        ClientUserDetails clientUserDetails = (ClientUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        ClientUser clientUser = clientUserDetails.getClientUser();
        clientUser.setEntries(Arrays.asList(new Entry("entry1"), new Entry("entry2")));
        ModelAndView mv = new ModelAndView("dashboard");
        mv.addObject("user", clientUser);
        tryToGetUserProfile(mv);
        return mv;
    }

    private void tryToGetUserProfile(ModelAndView mv) {
        String endPoint = "http://localhost:8080/api/profile";
        try {
            UserProfile userProfile = oAuth2RestTemplate.getForObject(endPoint, UserProfile.class);
            mv.addObject("profile", userProfile);
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("asdfasdfadfsdfasdfsdf");
        }
    }
}
