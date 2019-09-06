package com.example.sendmeal.Domain;

import java.util.Objects;

public class Usuario {

    private Integer id;
    private String nombre;
    private String clave;
    private String mail;
    private TipoCuenta tipoCuenta;
    private Boolean notificarMail;
    private TarjetaCredito tarjeta;
    private CuentaBancaria cuenta;

    public Usuario(Integer id, String nombre, String clave, String mail, TipoCuenta tipoCuenta, Boolean notificarMail, TarjetaCredito tarjeta, CuentaBancaria cuenta) {
        this.id = id;
        this.nombre = nombre;
        this.clave = clave;
        this.mail = mail;
        this.tipoCuenta = tipoCuenta;
        this.notificarMail = notificarMail;
        this.tarjeta = tarjeta;
        this.cuenta = cuenta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public Boolean getNotificarMail() {
        return notificarMail;
    }

    public void setNotificarMail(Boolean notificarMail) {
        this.notificarMail = notificarMail;
    }

    public TarjetaCredito getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(TarjetaCredito tarjeta) {
        this.tarjeta = tarjeta;
    }

    public CuentaBancaria getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaBancaria cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id.equals(usuario.id) &&
                nombre.equals(usuario.nombre) &&
                clave.equals(usuario.clave) &&
                mail.equals(usuario.mail) &&
                tipoCuenta == usuario.tipoCuenta &&
                notificarMail.equals(usuario.notificarMail) &&
                tarjeta.equals(usuario.tarjeta) &&
                cuenta.equals(usuario.cuenta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, clave, mail, tipoCuenta, notificarMail, tarjeta, cuenta);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", clave='" + clave + '\'' +
                ", mail='" + mail + '\'' +
                ", tipoCuenta=" + tipoCuenta +
                ", notificarMail=" + notificarMail +
                ", tarjeta=" + tarjeta +
                ", cuenta=" + cuenta +
                '}';
    }
}
