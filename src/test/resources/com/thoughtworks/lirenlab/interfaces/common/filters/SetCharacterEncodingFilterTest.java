package com.thoughtworks.lirenlab.interfaces.common.filters;

import org.junit.Before;
import org.junit.Test;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SetCharacterEncodingFilterTest {

    private FilterConfig filterConfig;
    private ServletRequest servletRequest;
    private ServletResponse servletResponse;
    private FilterChain filterChain;
    private SetCharacterEncodingFilter setCharacterEncodingFilter;

    @Before
    public void setUp() throws Exception {
        filterConfig = mock(FilterConfig.class);
        servletRequest = mock(ServletRequest.class);
        servletResponse = mock(ServletResponse.class);
        filterChain = mock(FilterChain.class);
        setCharacterEncodingFilter = new SetCharacterEncodingFilter();
    }

    @Test
    public void should_set_encoding_before_chain_called() throws Exception {
        when(filterConfig.getInitParameter("encoding")).thenReturn("UTF-8");

        setCharacterEncodingFilter.init(filterConfig);
        setCharacterEncodingFilter.doFilter(servletRequest, servletResponse, filterChain);

        verify(servletRequest).setCharacterEncoding("UTF-8");
        verify(filterChain).doFilter(servletRequest, servletResponse);
    }
}
