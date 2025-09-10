package com.steam.controller.auth;

import com.steam.controller.auth.dto.AccessJwtAuthResponse;
import com.steam.service.auth.AuthService;
import com.steam.service.user.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequestMapping("/login")
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthService authService;

    @GetMapping
    public void login(HttpServletResponse response) throws IOException {
        String openIdUrl = """
                https://steamcommunity.com/openid/login?\
                openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&\
                openid.mode=checkid_setup&\
                openid.return_to=http://localhost:8080/login/steamCallback&\
                openid.realm=http%3A%2F%2Flocalhost%3A8080&\
                openid.ns.sreg=http%3A%2F%2Fopenid.net%2Fextensions%2Fsreg%2F1.1&\
                openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&\
                openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select""";
        response.sendRedirect(openIdUrl);
    }

    @GetMapping("/steamCallback")
    public AccessJwtAuthResponse signIn(@RequestParam("openid.claimed_id") String claimedId) {
        var steamId = claimedId.replaceAll(".*/id/", "");
        var user = userService.findOrCreateUserBySteamId(steamId);
        return authService.signIn(user);
    }

}
