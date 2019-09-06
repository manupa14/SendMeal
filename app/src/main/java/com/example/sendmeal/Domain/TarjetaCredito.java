package com.example.sendmeal.Domain;

import java.util.Date;
import java.util.Objects;

public class TarjetaCredito {

    private Integer id;
    private String numero;
    private String ccv;
    private Date fechaVencimiento;

    public TarjetaCredito(Integer id, String numero, String ccv, Date fechaVencimiento) {
        this.id = id;
        this.numero = numero;
        this.ccv = ccv;
        this.fechaVencimiento = fechaVencimiento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCcv() {
        return ccv;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TarjetaCredito that = (TarjetaCredito) o;
        return id.equals(that.id) &&
                numero.equals(that.numero) &&
                ccv.equals(that.ccv) &&
                fechaVencimiento.equals(that.fechaVencimiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numero, ccv, fechaVencimiento);
    }

    @Override
    public String toString() {
        return "TarjetaCredito{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", ccv='" + ccv + '\'' +
                ", fechaVencimiento=" + fechaVencimiento +
                '}';
    }
}
