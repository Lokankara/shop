package org.store.products;

import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductMapperTest {
    ProductMapper mapper = new ProductMapper();

    @Test
    public void testMapperRow() throws SQLException {
        LocalDateTime localDateTime = LocalDateTime.of(2022, 2, 22, 22, 2, 20, 22);
        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.getInt("id")).thenReturn(1);
        when(resultSetMock.getString("name")).thenReturn("apple");
        when(resultSetMock.getString("description")).thenReturn("golden");
        when(resultSetMock.getInt("price")).thenReturn(100);
        when(resultSetMock.getTimestamp("date")).thenReturn(Timestamp.valueOf(localDateTime));

        Product actual = mapper.productMapper(resultSetMock);
        assertEquals(1, actual.getId());
        assertEquals("apple", actual.getName());
        assertEquals("golden", actual.getDescription());
        assertEquals(localDateTime, actual.getDate());

    }
}