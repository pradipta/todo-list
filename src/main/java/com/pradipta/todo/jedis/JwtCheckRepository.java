package com.pradipta.todo.jedis;

public interface JwtCheckRepository {
    Boolean putJwtInactive(String jwt);
    Boolean getJwtStatus(String jwt);
}
