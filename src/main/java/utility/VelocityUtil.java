package utility;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.StringWriter;
import java.util.Properties;

/**
 * Created by mi on 12/29/16.
 */
@Component
public class VelocityUtil {
    @Autowired
    String _velocityTemplatePath;
    @PostConstruct
    public void init(){
        VelocityUtil.RESOURCE_PATH = _velocityTemplatePath;
    }
    static String RESOURCE_PATH;
    public static VelocityEngine getVelocityEngine(){

        VelocityEngine ve = new VelocityEngine();
        Properties props = new Properties();
        // THIS PATH CAN BE HARDCODED BUT IDEALLY YOUD GET IT FROM A PROPERTIES FILE
        props.put("file.resource.loader.path", RESOURCE_PATH);
        props.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.NullLogSystem");
        ve.init(props);
        return ve;
    }

    public static String getHtmlByTemplateAndContext(String templateName, VelocityContext context){

        VelocityEngine ve = getVelocityEngine();

        Template template = ve.getTemplate(templateName);

        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        return writer.toString();
    }
}
