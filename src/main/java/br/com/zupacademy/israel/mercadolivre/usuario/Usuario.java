package br.com.zupacademy.israel.mercadolivre.usuario;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min = 6)
    private String senha;
    @NotNull
    @PastOrPresent
    private LocalDateTime dataCadastro;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Perfil> perfis = new ArrayList<>();

    @Deprecated
    public Usuario(){}

    /**
     * @param email string e em formato de email
     * @param senhaLimpa string com nenhum hash aplicado
     */
    public Usuario(@NotBlank @Email String email, @Valid @NotNull SenhaLimpa senhaLimpa,
                   @NotNull @PastOrPresent LocalDateTime dataCadastro) {

        Assert.isTrue(StringUtils.hasText(email),"email não pode ser vazio");
        Assert.notNull(senhaLimpa,"o objeto tipo senhaLimpa não pode ser null");
        Assert.isTrue(dataCadastro.compareTo(LocalDateTime.now().plus(1, ChronoUnit.NANOS)) <= 0,
                "dataCadastro deve está no futuro");

        this.email = email;
        this.senha = senhaLimpa.hash();
        this.dataCadastro = dataCadastro;
    }

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfis;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return email.equals(usuario.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    public String getEmail() {
        return email;
    }
}
