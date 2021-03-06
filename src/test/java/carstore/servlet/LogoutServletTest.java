package carstore.servlet;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class LogoutServletTest {

    @Mock
    ServletContext sContext;
    @Mock
    private HttpServletRequest req;
    @Mock
    private HttpServletResponse resp;
    @Mock
    private HttpSession httpSession;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        when(this.req.getSession(false)).thenReturn(this.httpSession);
        when(this.req.getContextPath()).thenReturn("root");
        when(this.req.getServletContext()).thenReturn(this.sContext);
        when(this.sContext.getContextPath()).thenReturn("root");
    }

    @Test
    public void justForCoverageInitDestroy() throws ServletException {
        var servlet = new LogoutServlet();
        servlet.init();
        servlet.destroy();
    }

    @Test
    public void whenDoPostThenInvalidateCurrentSession() throws IOException {
        var servlet = new LogoutServlet();
        servlet.doPost(this.req, this.resp);
        verify(this.httpSession).invalidate();
    }

}