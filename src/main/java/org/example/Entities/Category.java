package org.example.Entities;

import java.util.Objects;

public class Category {
    private Long cid;
    private String cname;

    public Category() {
    }

    public Category(Long cid, String cname) {
        this.cid = cid;
        this.cname = cname;
    }

    public Category(String cname) {
        this.cname=cname;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category that = (Category) o;
        return Objects.equals(cid, that.cid) && Objects.equals(cname, that.cname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid, cname);
    }
}
