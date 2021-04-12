package com.mybank.ReportingService.mappers;

import com.mybank.ReportingService.models.Transaction;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TransactionMapper {
    @Insert("INSERT INTO transaction (id, accountId, currency, direction, description, amount, balanceAfterTransaction) " +
            "VALUES(#{transactionId}, #{accountId}, #{currency}, #{direction}, #{description}, #{amount}, #{balanceAfterTransaction})")
    @Options(useGeneratedKeys = true, keyProperty = "transactionId")
    int insert(Transaction transaction);

    @Select("SELECT id, accountId, currency, direction, description, amount, balanceAfterTransaction FROM transaction WHERE id = #{id}")
    Transaction findById(String id);
}