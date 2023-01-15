package io.iworkflow.controller;

import io.iworkflow.core.Client;
import io.iworkflow.workflow.timer.BasicTimerWorkflow;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/timer")
public class TimerWorkflowController {

    private final Client client;

    public TimerWorkflowController(
            final Client client
    ) {
        this.client = client;
    }

    @GetMapping("/start")
    public ResponseEntity<String> start() {
        final long startTs = System.currentTimeMillis();
        final String wfId = "basic-timer-test-id" + startTs / 1000;
        final Integer input = 5;

        final String runId = client.startWorkflow(
                BasicTimerWorkflow.class, wfId, 10, input);

        final Integer output = client.getSimpleWorkflowResultWithWait(Integer.class, wfId);

        return ResponseEntity.ok(String.format("runId: %s, output: %d", runId, output));
    }
}