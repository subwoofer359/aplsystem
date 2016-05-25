import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

beans {    
    xmlns([ctx:'http://www.springframework.org/schema/context', mvc: 'http://www.springframework.org/schema/mvc']);
    ctx.'component-scan'('base-package' : 'org.amc');
    //mvc.'annotations-driven';
    
    viewResolver(UrlBasedViewResolver) {
        viewClass = JstlView.class;
        prefix = '/WEB-INF/JSP/';
        suffix = '.jsp';
    };

    messageSource(ResourceBundleMessageSource) {
        basenames = ['labels'];
    };
};