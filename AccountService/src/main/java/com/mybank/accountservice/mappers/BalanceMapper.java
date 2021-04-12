package com.mybank.accountservice.mappers;

import com.mybank.accountservice.models.Balance;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BalanceMapper {
    @Insert("INSERT INTO balance (accountId, currency, amount) VALUES(#{accountId}, #{currency}, #{amount})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Balance balance);

//    @Update("UPDATE INTO balance (accountId, currency, amount) VALUES(#{accountId}, #{currency}, #{amount}) " +
//            "WHERE id = #{id}")
    @Update("UPDATE balance SET amount = #{amount} WHERE id = #{id} ")
    int update(Balance balance);

    @Select("SELECT id, accountId, amount, currency FROM balance WHERE accountId = #{accountId}")
    List<Balance> findById(String accountId);
}
