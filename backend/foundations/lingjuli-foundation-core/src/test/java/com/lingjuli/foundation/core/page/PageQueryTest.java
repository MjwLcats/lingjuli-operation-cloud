package com.lingjuli.foundation.core.page;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class PageQueryTest {
    @Test
    void calculatesZeroBasedOffset() {
        assertEquals(40, new PageQuery(3, 20).offset());
    }

    @Test
    void rejectsInvalidPageSize() {
        assertThrows(IllegalArgumentException.class, () -> new PageQuery(1, 201));
    }
}
