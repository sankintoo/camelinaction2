package camelinaction;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
@ConfigurationProperties(prefix = "recommend")
public class RecommendController {

    private static final Logger LOG = LoggerFactory.getLogger(RecommendController.class);

    private final RestTemplate restTemplate = new RestTemplate();

    private String rulesUrl;

    @RequestMapping(value = "recommend", method = RequestMethod.GET, produces = "application/json")
    public List<ItemDto> recommend() {

        // here we can do logic to find out the session/user to pass on the rules engine
        // call the rules backend

        LOG.info("Calling rules backend {}", rulesUrl);

        List<ItemDto> items = restTemplate.getForObject(rulesUrl, List.class);
        return items;
    }

    public String getRulesUrl() {
        return rulesUrl;
    }

    public void setRulesUrl(String rulesUrl) {
        this.rulesUrl = rulesUrl;
    }
}
