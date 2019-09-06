package com.example.sendmeal.Domain;

import java.util.Objects;

public class CuentaBancaria {

        private Integer id;
        private String alias;
        private String cbu;

    public CuentaBancaria(Integer id, String alias, String cbu) {
        this.id = id;
        this.alias = alias;
        this.cbu = cbu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CuentaBancaria that = (CuentaBancaria) o;
        return id.equals(that.id) &&
                alias.equals(that.alias) &&
                cbu.equals(that.cbu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, alias, cbu);
    }

    @Override
    public String toString() {
        return "CuentaBancaria{" +
                "id=" + id +
                ", alias='" + alias + '\'' +
                ", cbu='" + cbu + '\'' +
                '}';
    }
}
