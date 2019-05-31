package com.dyvak.entity;

import java.util.Objects;

public class DynamicLink {

    private String kind;
    private String id;
    private String longUrl;

    DynamicLink(String kind, String id, String longUrl) {
        this.kind = kind;
        this.id = id;
        this.longUrl = longUrl;
    }

    public static DynamicLinkBuilder builder() {
        return new DynamicLinkBuilder();
    }

    public static class DynamicLinkBuilder {

        private String kind;
        private String id;
        private String longUrl;

        DynamicLinkBuilder() {
        }

        public DynamicLinkBuilder kind(String kind) {
            this.kind = kind;
            return this;
        }

        public DynamicLinkBuilder id(String id) {
            this.id = id;
            return this;
        }

        public DynamicLinkBuilder longUrl(String longUrl) {
            this.longUrl = longUrl;
            return this;
        }

        public DynamicLink build() {
            return new DynamicLink(this.kind, this.id, this.longUrl);
        }
    }


    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DynamicLink that = (DynamicLink) o;
        return Objects.equals(kind, that.kind) &&
                Objects.equals(id, that.id) &&
                Objects.equals(longUrl, that.longUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kind, id, longUrl);
    }

    @Override
    public String toString() {
        return "DynamicLink{" +
                "kind='" + kind + '\'' +
                ", id='" + id + '\'' +
                ", longUrl='" + longUrl + '\'' +
                '}';
    }


}
