package org.jargus.api;

import jakarta.servlet.http.HttpServletRequest;
import org.jargus.scheduler.domain.TaskRequestModel;
import org.jargus.scheduler.repository.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

/**
 * @author Kotelnikov D.M.
 */
@RestController
@RequestMapping("/api/v1")
public class PrometheusQueryController {

    private final RestClient defaultClient;
    private final TaskRepository taskRepository;

    public PrometheusQueryController(TaskRepository taskRepository) {
        this.defaultClient = RestClient.create();
        this.taskRepository = taskRepository;
    }

    @RequestMapping(value = "/**", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<String> evaluateInstantQuery(HttpServletRequest request) throws URISyntaxException {
        String query = request.getQueryString() == null ? "" : request.getQueryString();
        Collection<TaskRequestModel> tasks = taskRepository.getAllTasks();
        for (TaskRequestModel taskRequestModel : tasks) {
            return defaultClient.get()
                    .uri(new URI((taskRequestModel.getUri() + request.getRequestURI() + "?" + query).replaceAll("(?<!http:)//", "/")))
                    .retrieve()
                    .toEntity(String.class);
        }
        return null;
    }
}
