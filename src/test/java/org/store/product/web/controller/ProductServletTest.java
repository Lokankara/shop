//package org.store.product.web.controller;
//
//import org.junit.jupiter.api.Test;
//import org.store.product.dao.ProductDao;
//import org.store.product.dao.jdbc.JdbcProductDao;
//import org.store.product.service.ProductService;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import static org.mockito.Mockito.*;
//
//class ProductServletTest {
//
//    final ProductDao mockDao = mock(JdbcProductDao.class);;
//    final ProductService productService = new ProductService(mockDao);
//    final ProductServlet servlet = new ProductServlet(productService);
//    final HttpServletRequest request = mock(HttpServletRequest.class);
//    final HttpServletResponse response = mock(HttpServletResponse.class);;
//    final RequestDispatcher dispatcher = mock(RequestDispatcher.class);
//    final String path = "/WEB-INF/view/products.html";
//
//    @Test
//    void doGet()  {
//        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
////        servlet.doGet(request, response);
////        verify(request, times(1)).getRequestDispatcher(path);
//        verify(request, never()).getSession();
////        response.sendRedirect("/products");
////        verify(dispatcher).forward(request, response);
//    }
//
//    @Test
//    void doPost() {
//    }
//
//    @Test
//    void doDelete() {
//    }
//}