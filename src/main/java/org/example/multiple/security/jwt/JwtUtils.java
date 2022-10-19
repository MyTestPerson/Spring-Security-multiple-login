package org.example.multiple.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.example.multiple.security.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtils {



//  Получаем ключи
  String privateKeyContent = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("key/private.pem").toURI())));
  String publicKeyContent = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("key/public.pem").toURI())));


  public JwtUtils() throws IOException, URISyntaxException {
  }



  public String generateJwtToken(Authentication authentication) {


    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

    List<String> roleList = new ArrayList<>();
    userPrincipal.getAuthorities().forEach(grantedAuthority -> roleList.add(grantedAuthority.getAuthority()));

    try {
      return Jwts.builder()
              .setHeaderParam("typ","JWT")
              .claim("email", userPrincipal.getEmail())
              .claim("roles", roleList)
              .setIssuedAt(new Date())
              .setExpiration(new Date(new Date().getTime() + 3600000))
              .signWith(getPrivateKey(), SignatureAlgorithm.RS512)
              .compact();
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new RuntimeException(e);
    }

  }

  public String getUserEmailFromJwtToken(String jwtString) {

    try {
      return Jwts.parserBuilder().setSigningKey(getPublicKey()).build().parseClaimsJws(jwtString).getBody().get("email").toString();
    } catch (NoSuchAlgorithmException | InvalidKeySpecException | IOException e) {
      throw new RuntimeException(e);
    }

  }

  public Jws<Claims> validateJwtToken(String jwtString) {

    try {
      return Jwts.parserBuilder().setSigningKey(getPublicKey()).build().parseClaimsJws(jwtString);
    } catch (NoSuchAlgorithmException | InvalidKeySpecException | IOException e) {
      throw new RuntimeException(e);
    }

  }


  private PublicKey getPublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {

    String key = publicKeyContent.replaceAll("\\n", "")
            .replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "");

    X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(key));

    KeyFactory kf = KeyFactory.getInstance("RSA");

    return kf.generatePublic(keySpec);
  }


  private PrivateKey getPrivateKey() throws NoSuchAlgorithmException, InvalidKeySpecException {

    String key = privateKeyContent.replaceAll("\\n", "")
            .replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "");

    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(key));

    KeyFactory kf = KeyFactory.getInstance("RSA");

    return kf.generatePrivate(keySpec);
  }

}
