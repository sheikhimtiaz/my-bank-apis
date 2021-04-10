package com.mybank.accountservice.mappers;

import com.mybank.accountservice.models.Account;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AccountMapper {
    @Insert("INSERT INTO account (id, customerId, country) VALUES(#{accountId}, #{customerId}, #{country})")
    @Options(useGeneratedKeys = true, keyProperty = "accountId")
    int insert(Account account);

    @Select("SELECT id, customerId, country FROM account WHERE id = #{id}")
    Account findById(String id);
}
