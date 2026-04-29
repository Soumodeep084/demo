def partial_order_planner(initial_state, goal_state):
    plan = ["Start"]
    current_state = initial_state.copy()

    print("\nInitial State:", current_state)
    print("Goal State:", goal_state)

    step = 1

    for goal in goal_state:
        if goal not in current_state:
            print("\nStep", step, ": Achieving goal ->", goal)
            block, position = goal.split("-")

            if position == "table":
                action = f"Put {block} on table"
            else:
                action = f"Stack {block} on {position}"

            print("Action added to plan:", action)
            plan.append(action)
            current_state.append(goal)
            print("Current State:", current_state)
            step += 1

    plan.append("Finish")

    print("\nFinal Plan:")
    for p in plan:
        print(p)

initial_input = input("Enter initial state (comma separated, e.g. A-table,B-A): ")
goal_input = input("Enter goal state (comma separated, e.g. C-B,A-C): ")

initial_state = [x.strip() for x in initial_input.split(",")]
goal_state = [x.strip() for x in goal_input.split(",")]
partial_order_planner(initial_state, goal_state)
