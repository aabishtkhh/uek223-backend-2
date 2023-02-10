package com.example.demo.domain.user;

import com.example.demo.core.generic.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends AbstractServiceImpl<User> implements UserService {

  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
    super(repository);
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return ((UserRepository) repository).findByEmail(email)
                                        .map(UserDetailsImpl::new)
                                        .orElseThrow(() -> new UsernameNotFoundException(email));
  }

  @Override
  public User register(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return save(user);
  }
}
