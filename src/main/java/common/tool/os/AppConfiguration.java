package common.tool.os;

import common.tool.os.service.OsInformation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xuejian.sun
 * @date 2019/1/25 14:14
 */
@Configuration
public class AppConfiguration {

    @Bean
    public OsInformation osInformation() {
        return new OsInformation();
    }
}
