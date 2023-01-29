package org.example.Entities;

import java.util.Objects;

public class Rating {
    private Long rid;
    private Long pid;
    private String email;
    private Integer rating;

    public Rating() {}

    public Rating(Long pid, String email, Integer rating) {
        this.pid=pid;
        this.email=email;
        this.rating=rating;
    }

    public long getRid() {
        return rid;
    }

    public void setRid(long rid) {
        this.rid = rid;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "rid=" + rid +
                ", pid=" + pid +
                ", email='" + email + '\'' +
                ", rating=" + rating +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating1 = (Rating) o;
        return Objects.equals(rid, rating1.rid) && Objects.equals(pid, rating1.pid) && Objects.equals(email, rating1.email) && Objects.equals(rating, rating1.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rid, pid, email, rating);
    }
}
