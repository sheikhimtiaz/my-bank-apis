package com.mybank.accountservice.mappers;

import com.mybank.accountservice.models.Balance;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BalanceMapper {
    @Insert("INSERT INTO balance (accountId, currency, amount) VALUES(#{accountId}, #{currency}, #{amount})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Balance balance);

    @Select("SELECT id, accountId FROM balance WHERE accountId = #{accountId}")
    Balance findById(long accountId);
}
