# iwf-java-samples

Samples for [iWF Java SDK](https://github.com/indeedeng/iwf-java-sdk) that runs
against [iWF server](https://github.com/indeedeng/iwf)

## Setup

1. Start a iWF server following the [instructions](https://github.com/indeedeng/iwf#how-to-run-this-server)
2. Run this project by using gradle task `bootRun`.

_Note that by default this project will listen on 8803 port

## Product Use case samples

### Subscription workflow

This [Subscription](https://github.com/indeedeng/iwf-java-samples/tree/main/src/main/java/io/iworkflow/workflow/subscription)
with [unit tests](https://github.com/indeedeng/iwf-java-samples/tree/main/src/test/java/io/iworkflow/workflow/subscription)
This use case is also described in:

* [Temporal TypeScript tutorials](https://learn.temporal.io/tutorials/typescript/subscriptions/)
* [Temporal go sample](https://github.com/temporalio/subscription-workflow-project-template-go)
* [Temporal Java Sample](https://github.com/temporalio/subscription-workflow-project-template-java)
* [Cadence Java example](https://cadenceworkflow.io/docs/concepts/workflows/#example)

<<<<<<< HEAD
#### Use case statement
Build an application for a limited time Subscription (eg a 36 month Phone plan) that satisfies these conditions:

1. When the user signs up, send a welcome email and start a free trial for **TrialPeriod**.

2. When the TrialPeriod expires, start the billing process. 
 * If the user cancels during the trial, send a trial cancellation email.

3. Billing Process:
 * As long as you have not exceeded **MaxBillingPeriods**, charge the customer for the **BillingPeriodChargeAmount**.
 * Then wait for the next **BillingPeriod**.
 * If the customer cancels during a billing period, send a subscription cancellation email.
 * If Subscription has ended normally (exceeded MaxBillingPeriods without cancellation), send a subscription ended email.

4. At any point while subscriptions are ongoing, be able to look up and change any customer's amount charged and current status and info. 

Of course, this all has to be fault tolerant, scalable to millions of customers, testable, maintainable, and observable.

#### How to run

To start a subscription workflow:

* Open http://localhost:8803/subscription/start

It will return you a **workflowId**.

The controller is hard coded to start with 20s as trial period, 10s as billing period, $100 as period charge amount for
10 max billing periods

To update the period charge amount :

* Open http://localhost:8803/subscription/updateChargeAmount?workflowId=<TheWorkflowId>&newChargeAmount=<The new amount>

To cancel the subscription:

* Open http://localhost:8803/subscription/cancel?workflowId=<TheWorkflowId>

It's recommended to use an iWF state diagram to visualize the workflow design like this:
![subscription state diagram](https://user-images.githubusercontent.com/4523955/217110240-5dfe1d33-0b7c-49f2-8c12-b0d91c4eb970.png)

## iWF feature samples

1. [Basic IO workflow](https://github.com/indeedeng/iwf-java-samples/tree/main/src/main/java/io/iworkflow/workflow/basic):
   Open http://localhost:8803/basic/start in your browser. This workflow demonstrate:
   * How to start workflow with input and get output
   * How to pass input from a state to a next state
2. [Persistence workflow](https://github.com/indeedeng/iwf-java-samples/tree/main/src/main/java/io/iworkflow/workflow/persistence):
   Open http://localhost:8803/persistence/start in your browser. This workflow demonstrate:
   * How to use data objects to share data across workflows
   * How to use search attributes to share data and also searching for workflows
   * How to use record events API
   * How to use StateLocal to pass data from start to decide API
3. [Signal workflow](https://github.com/indeedeng/iwf-java-samples/tree/main/src/main/java/io/iworkflow/workflow/signal):
   Open http://localhost:8803/signal/start in your browser. This workflow demonstrate:
   * How to use signal
   * How to use AnyCommandCompleted trigger type
   * State1 start API will wait for two signals, when any of them is received, the decide API is trigger
4. [Timer workflow](https://github.com/indeedeng/iwf-java-samples/tree/main/src/main/java/io/iworkflow/workflow/timer):
   Open http://localhost:8803/timer/start in your browser. This workflow demonstrate:
   * How to use a durable timer
   * State1 start API will wait for a timer, when timer fires, the decide API is trigger
5. [InterstateChannel workflow](https://github.com/indeedeng/iwf-java-samples/tree/main/src/main/java/io/iworkflow/workflow/interstatechannel):
   Open http://localhost:8803/interstateChannel/start in your browser. This workflow demonstrate:
   * How to use interstate channel to synchronize multi threading/in parallel workflow execution
   * State0 will go to State1 and State2
   * State1 will wait for a InterStateChannel from State2
   * State2 will send a signal and then finish as a "dead end"
6. WIP(dsl dynamic workflow)

Then watch the workflow in Cadence or Temporal Web UI
||||||| parent of d9ce842 (Upgrade to 1.5.1-beta-3)
To start a subscription workflow:

* Open http://localhost:8803/subscription/start

It will return you a **workflowId**.

The controller is hard coded to start with 20s as trial period, 10s as billing period, $100 as period charge amount for
10 max billing periods

To update the period charge amount :

* Open http://localhost:8803/subscription/updateChargeAmount?workflowId=<TheWorkflowId>&newChargeAmount=<The new amount>

To cancel the subscription:

* Open http://localhost:8803/subscription/cancel?workflowId=<TheWorkflowId>

It's recommended to use an iWF state diagram to visualize the workflow design like this:
![Subscription workflow iWF state diagram](https://user-images.githubusercontent.com/4523955/216396635-1c46df3c-e087-415a-996e-16ce47e7ccb2.png)

## iWF feature samples

1. [Basic IO workflow](https://github.com/indeedeng/iwf-java-samples/tree/main/src/main/java/io/iworkflow/workflow/basic):
   Open http://localhost:8803/basic/start in your browser. This workflow demonstrate:
   * How to start workflow with input and get output
   * How to pass input from a state to a next state
2. [Persistence workflow](https://github.com/indeedeng/iwf-java-samples/tree/main/src/main/java/io/iworkflow/workflow/persistence):
   Open http://localhost:8803/persistence/start in your browser. This workflow demonstrate:
   * How to use data objects to share data across workflows
   * How to use search attributes to share data and also searching for workflows
   * How to use record events API
   * How to use StateLocal to pass data from start to decide API
3. [Signal workflow](https://github.com/indeedeng/iwf-java-samples/tree/main/src/main/java/io/iworkflow/workflow/signal):
   Open http://localhost:8803/signal/start in your browser. This workflow demonstrate:
   * How to use signal
   * How to use AnyCommandCompleted trigger type
   * State1 start API will wait for two signals, when any of them is received, the decide API is trigger
4. [Timer workflow](https://github.com/indeedeng/iwf-java-samples/tree/main/src/main/java/io/iworkflow/workflow/timer):
   Open http://localhost:8803/timer/start in your browser. This workflow demonstrate:
   * How to use a durable timer
   * State1 start API will wait for a timer, when timer fires, the decide API is trigger
5. [InterstateChannel workflow](https://github.com/indeedeng/iwf-java-samples/tree/main/src/main/java/io/iworkflow/workflow/interstatechannel):
   Open http://localhost:8803/interstateChannel/start in your browser. This workflow demonstrate:
   * How to use interstate channel to synchronize multi threading/in parallel workflow execution
   * State0 will go to State1 and State2
   * State1 will wait for a InterStateChannel from State2
   * State2 will send a signal and then finish as a "dead end"
6. WIP(dsl dynamic workflow)

Then watch the workflow in Cadence or Temporal Web UI
=======
In additional, iWF provides "Auto-ContinueAsNew feature to allow running the workflow infinitely 
>>>>>>> d9ce842 (Upgrade to 1.5.1-beta-3)
