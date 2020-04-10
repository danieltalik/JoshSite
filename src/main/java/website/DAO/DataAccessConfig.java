package website.DAO;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("application.properties")
public class DataAccessConfig {

    @Bean ConnectionConfiguration configuration(){
        return new ConnectionConfiguration();
    }

    @Bean
    public BlogDAO blogDao(){
        return new BlogDAO(configuration());
    }
    @Bean
    public EventsDAO eventsDao(){
        return new EventsDAO(configuration());
    }
    @Bean
    public UserDAO userDao(){
        return new UserDAO(configuration());
    }
}
