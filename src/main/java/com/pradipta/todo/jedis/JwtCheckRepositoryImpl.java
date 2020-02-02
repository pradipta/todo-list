package com.pradipta.todo.jedis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JwtCheckRepositoryImpl implements JwtCheckRepository {
    @Autowired
    private RedisConfiguration redisConfiguration;
    private RedisTemplate<String, Boolean> template;
    private HashOperations hashOperations;

    public JwtCheckRepositoryImpl(RedisConfiguration redisConfiguration) {
        this.template = redisConfiguration.redisTemplate();
        this.hashOperations = template.opsForHash();
    }


    @Override
    public Boolean putJwtInactive(String jwt) {
        try {
            hashOperations.put("JWT", jwt, Boolean.FALSE);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return true;
    }

    @Override
    public Boolean getJwtStatus(String jwt) {
        return (hashOperations.get("JWT", jwt) == null) ? Boolean.TRUE : Boolean.FALSE;
    }
}
