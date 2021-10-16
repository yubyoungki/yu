package com.mongta.myspringboot.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mongta.myspringboot.entity.Account;

@SpringBootTest
public class AccountRepositoryTest {
   @Autowired
   AccountRepository accountRepository;
   
   @Test
   public void account() throws Exception {
      Account account = new Account();
      account.setUsername("test");
      account.setPassword("ab1234");
      //DB에 object를 저장 - insert
      Account savedAcct = accountRepository.save(account);
      System.out.println("ID " + savedAcct.getId() + " Name " + savedAcct.getUsername());

      assertThat(savedAcct).isNotNull();
//    assertRepository 사용해보기
      Optional<Account> optional = accountRepository.findByUsername("mongta");
      if(optional.isPresent()) {
    	  Account mongta = optional.get();
    	  System.out.println(mongta.getUsername() + " " + mongta.getPassword());
      }
      
//      Optional<Account> optional2 = accountRepository.findByUsername("test");
      //orElseThrow의 Argument Supplier의 T get() 메서드를 재정의
//      Account emptyAcct = optional2.orElseThrow(()-> new RuntimeException("Account Not Found"));
      
      
      
   }
}
