package mk.ukim.finki.labsemt2.security;

public class JwtConstants {
    public static final String SECRET_KEY = "88e3731f2306ec88c8ba2a37c8113ba3a2a4d034cbfef212c1965533cae52d98";
    public static final Long EXPIRATION_TIME = 864000000L;
    public static final String HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
}
