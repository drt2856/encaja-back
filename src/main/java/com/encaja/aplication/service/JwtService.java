package com.encaja.aplication.service;


import com.encaja.aplication.Iservice.IUserService;
import com.encaja.domain.model.Authority;
import com.encaja.domain.model.Users;
import com.encaja.infraestructure.Exeptions.BadAutentication;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.header.Header;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService implements UserDetailsService {


    private final IUserService usersService;

    @Autowired
    public JwtService(IUserService usersService) {
        this.usersService = usersService;
    }


    private static final String JWT_SECRET_KEY = "hfyeob5dsiGgjjdslksd";

    private static final long JWT_TOKEN_VALIDYTY = 1000 * 60 * 60 * (long) 80000;

    public String extractUsernameByAuthorizationHeader(String authorizationHeader) {
        return extractUsername(authorizationHeader.substring(7));
    }

    public String extractUsername(String token) {
        try{
            return extractClaim(token, Claims::getSubject);
        }catch (ExpiredJwtException e){
            throw new BadAutentication("Detetcatmos que usted lleva tiempo sin autenticarse, por su seguridad debe hacerlo");
        }

    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(extractAllClaims(token));
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String username) {
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDYTY))
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY)
                .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private String[] convertirListaEnArray(List<Authority> list) {
        String[] ed = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ed[i] = list.get(i).getAuthorityPK().getAuthority();
        }
        return ed;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usersname) {

        Users users = usersService.findByUserName(usersname);
        if (usersname != null) {
            User.UserBuilder usersBuilder = User.withUsername(usersname);
            usersBuilder.password("{noop}" + users.getPassword()).roles(convertirListaEnArray(users.getAuthorityList()));

            return usersBuilder.build();
        } else {
            throw new UsernameNotFoundException(usersname);
        }
    }

}