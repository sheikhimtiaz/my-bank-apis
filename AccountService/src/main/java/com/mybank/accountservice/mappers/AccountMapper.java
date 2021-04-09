package com.mybank.accountservice.mappers;

import com.mybank.accountservice.models.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AccountMapper {
    @Insert("INSERT INTO account (id, customerId, balances) VALUES(#{name}, #{accountId, customerId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Account account);

    @Select("SELECT id, customerId FROM account WHERE id = #{id}")
    Account findById(long id);
}
