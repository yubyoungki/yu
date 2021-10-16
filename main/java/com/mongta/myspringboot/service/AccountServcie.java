package com.mongta.myspringboot.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mongta.myspringboot.entity.Account;
import com.mongta.myspringboot.property.MongtaProperties;
import com.mongta.myspringboot.repository.AccountRepository;

@Service
public class AccountServcie implements UserDetailsService{

	private final AccountRepository repository;
	private final MongtaProperties property;
	private final PasswordEncoder encoder;

	public AccountServcie(AccountRepository repository, MongtaProperties property, PasswordEncoder encoder) {
		this.repository = repository;
		this.property = property;
		this.encoder = encoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Account> optional = repository.findByUsername(username);
		Account account = optional.orElseThrow(() -> new UsernameNotFoundException(username));
		return new User(username, username, authorities());
	}
	// 이 collection에 담을 수 있는 class는 GrantedAuthority도 가능하고 자식도 담을 수 있다
	private Collection<? extends GrantedAuthority> authorities() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}

	// 생성지 호출후 바로 실행
	@PostConstruct
	public void insert() {
		System.out.println("Account  insert " + property.getUsername());
		Optional<Account> optional = repository.findByUsername(property.getUsername()); // mongaProperty에 저장죈 Username 가저옴
		if(optional.isEmpty()) {
			Account account = createAccount(property.getUsername(), property.getPassword());
			System.out.println("Account " + account);
		}
		
	}
	// Account 레코드 추가
	public Account createAccount(String username, String password) {
		Account account = new Account();
		account.setUsername(username);
		account.setPassword(password);
		System.out.println("Account created ============");
		account.setPassword(encoder.encode(password));
		return repository.save(account);
	}

}
