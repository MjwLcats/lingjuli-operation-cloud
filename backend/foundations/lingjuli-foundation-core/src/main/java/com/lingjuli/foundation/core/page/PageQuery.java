package com.lingjuli.foundation.core.page;

public record PageQuery(int page, int pageSize) {
    public PageQuery {
        if (page < 1) throw new IllegalArgumentException("page must be greater than zero");
        if (pageSize < 1 || pageSize > 200) throw new IllegalArgumentException("pageSize must be between 1 and 200");
    }

    public long offset() {
        return (long) (page - 1) * pageSize;
    }
}
