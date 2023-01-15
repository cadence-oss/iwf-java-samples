package io.iworkflow.workflow.interstatechannel;

import io.iworkflow.core.Context;
import io.iworkflow.core.StateDecision;
import io.iworkflow.core.WorkflowState;
import io.iworkflow.core.command.CommandRequest;
import io.iworkflow.core.command.CommandResults;
import io.iworkflow.core.communication.Communication;
import io.iworkflow.core.communication.InterStateChannelCommand;
import io.iworkflow.core.communication.InterStateChannelCommandResult;
import io.iworkflow.core.persistence.Persistence;

import static io.iworkflow.gen.models.ChannelRequestStatus.WAITING;
import static io.iworkflow.workflow.interstatechannel.BasicInterStateChannelWorkflow.INTER_STATE_CHANNEL_NAME_1;
import static io.iworkflow.workflow.interstatechannel.BasicInterStateChannelWorkflow.INTER_STATE_CHANNEL_NAME_2;

public class BasicInterStateChannelWorkflowState1 implements WorkflowState<Integer> {
    public static final String COMMAND_ID = "test-cmd-id";

    @Override
    public Class<Integer> getInputType() {
        return Integer.class;
    }

    @Override
    public CommandRequest start(
            Context context,
            Integer input,
            Persistence persistence,
            final Communication communication) {
        return CommandRequest.forAnyCommandCompleted(
                InterStateChannelCommand.create(COMMAND_ID, INTER_STATE_CHANNEL_NAME_1),
                InterStateChannelCommand.create(COMMAND_ID, INTER_STATE_CHANNEL_NAME_2)
        );
    }

    @Override
    public StateDecision decide(
            Context context,
            Integer input,
            CommandResults commandResults,
            Persistence persistence,
            final Communication communication) {
        final InterStateChannelCommandResult result1 = commandResults.getAllInterStateChannelCommandResult().get(0);
        Integer output = input + (Integer) result1.getValue().get();

        final InterStateChannelCommandResult result2 = commandResults.getAllInterStateChannelCommandResult().get(1);
        if (result2.getRequestStatusEnum() != WAITING) {
            throw new RuntimeException("the second command should be waiting");
        }
        return StateDecision.gracefulCompleteWorkflow(output);
    }
}
