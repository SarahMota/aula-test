package com.example.cliente;

//import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Prestador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome não pode ser vazio")
    @NotNull(message = "Name cannot be null")
    private String nome;

    @Email
    private String email;

    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$")
    private String cpf;

    // Aceita número só com traço separando.
    @Pattern(regexp = "^\\d{2}\\s\\d{4,5}-\\d{4}$")
    private String telefone;

    /*
     * A senha precisa ter pelo menos uma letra minúscula e maiúscula, pelo menos um
     * caractere especial entre[$*&@#],
     * mínimo de 8 caracteres e sem caracteres repetidos consecutivos.
     */
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[$*&@#])(?:([0-9a-zA-Z$*&@#])(?!\\1)){8,}$")
    private String senha;

    private String confirmacaoSenha;

    @Pattern(regexp = "^\\d{5}\\-\\d{3}$")
    private String cep;

    @Pattern(regexp = "\\d{1,5}")
    private String numero;

    @Pattern(regexp = "^[a-zA-Z0-9\s]*$")
    private String logradouro;

    @Pattern(regexp = "^[a-zA-Z0-9\\s]*$")
    private String complemento;

    @Pattern(regexp = "^[a-zA-Z\\s]*$")
    private String bairro;

    @Pattern(regexp = "^[a-zA-Z\\s]*$")
    private String cidade;

    @Pattern(regexp = "^(?i)(\\s*(Acre|Alagoas|Amapá|Amazonas|Bahia|Ceará|Distrito Federal|Espírito Santo|Goiás|Maranhão|Mato Grosso|Mato Grosso do Sul|Minas Gerais|Pará|Paraíba|Paraná|Pernambuco|Piauí|Rio de Janeiro|Rio Grande do Norte|Rio Grande do Sul|Rondônia|Roraima|Santa Catarina|São Paulo|Sergipe|Tocantins)?)$")
    private String estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCPF() {
        return cpf;
    }

    public void setCPF(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean senhaConfirmada() {
        if (senha != null && confirmacaoSenha != null) {
            return senha.equals(confirmacaoSenha);
        } else {
            return false;
        }
    }

    public String getConfirmacaoSenha() {
        return confirmacaoSenha;
    }

    public void setConfirmacaoSenha(String confirmacaoSenha) {
        this.confirmacaoSenha = confirmacaoSenha;
    }

    public String getCEP() {
        return cep;
    }

    public void setCEP(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}