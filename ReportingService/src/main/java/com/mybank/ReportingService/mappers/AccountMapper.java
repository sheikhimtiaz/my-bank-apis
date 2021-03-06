package com.mybank.ReportingService.mappers;

import com.mybank.ReportingService.models.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AccountMapper {
    @Insert("INSERT INTO account (id, customerId, country) VALUES(#{accountId}, #{customerId}, #{country})")
    @Options(useGeneratedKeys = true, keyProperty = "accountId")
    int insert(Account account);

    @Select("SELECT id, customerId, country FROM account WHERE id = #{id}")
    Account findById(String id);
}
