package app.entity;

import lombok.Getter;

@Getter
public class ObjectMap {
    private ObjectMap() {}
    private String link, html;

    public static ObjectMap.Builder newBuilder() {
        return new ObjectMap().new Builder();
    }

    public class Builder {
        private Builder() {}

        public ObjectMap.Builder setLink(String link) {
            ObjectMap.this.link = link;
            return this;
        }

        public ObjectMap.Builder setHtml(String html) {
            ObjectMap.this.html = html;
            return this;
        }

        public ObjectMap build() {
            return ObjectMap.this;
        }
    }
}
