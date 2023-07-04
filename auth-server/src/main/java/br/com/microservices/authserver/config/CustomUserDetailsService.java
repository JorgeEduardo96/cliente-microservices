package br.com.microservices.authserver.config;

import br.com.microservices.authserver.model.Cliente;
import br.com.microservices.authserver.model.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Cliente> credential = clienteRepository.findByNome(username);
        return credential.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
    }
}
