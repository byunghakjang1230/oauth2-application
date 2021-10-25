package kr.co.kumsung.oauth2provider.api;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.provider.ClientRegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import kr.co.kumsung.oauth2provider.client.ClientType;
import kr.co.kumsung.oauth2provider.domain.Application;
import kr.co.kumsung.oauth2provider.domain.BasicClientInfo;

@Controller
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientRegistrationService clientRegistrationService;

    @GetMapping("/register")
    public ModelAndView register(ModelAndView mv) {
        mv.setViewName("client/register");
        mv.addObject("registry", new BasicClientInfo());
        return mv;
    }

    @PostMapping("/save")
    public ModelAndView save(BasicClientInfo basicClientInfo, BindingResult bindingResult) {
        if ((bindingResult.hasErrors())) {
            return new ModelAndView("client/register");
        }
        Application app = new Application();
        app.setName(basicClientInfo.getName());
        app.addRedirectUri(basicClientInfo.getRedirectUri());
        app.setClientType(ClientType.valueOf(basicClientInfo.getClientType()));
        app.setClientId(UUID.randomUUID().toString());
        app.setClientSecret(UUID.randomUUID().toString());
        app.setAccessTokenValidity(300);
        app.addScope("read_profile");
        app.addScope("read_contacts");
        clientRegistrationService.addClientDetails(app);
        ModelAndView mv = new ModelAndView("redirect:/client/dashboard");
        mv.addObject("applications", clientRegistrationService.listClientDetails());
        return mv;
    }

    @GetMapping("/remove")
    public ModelAndView remove(@RequestParam(value = "client_id", required = false) String clientId) {
        clientRegistrationService.removeClientDetails(clientId);
        ModelAndView mv = new ModelAndView("redirect:/client/dashboard");
        mv.addObject("applications", clientRegistrationService.listClientDetails());
        return mv;
    }

    @GetMapping("/dashboard")
    public ModelAndView dashboard(ModelAndView mv) {
        mv.addObject("applications", clientRegistrationService.listClientDetails());
        return mv;
    }
}
