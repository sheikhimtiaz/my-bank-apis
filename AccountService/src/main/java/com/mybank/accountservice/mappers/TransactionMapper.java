package com.mybank.accountservice.mappers;

import com.mybank.accountservice.models.Transaction;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TransactionMapper {
    @Insert("INSERT INTO transaction (id, customerId, country) VALUES(#{accountId}, #{customerId}, #{country})")
    @Options(useGeneratedKeys = true, keyProperty = "accountId")
    int insert(Transaction transaction);

    @Select("SELECT id, customerId FROM transaction WHERE id = #{id}")
    Transaction findById(long id);

}