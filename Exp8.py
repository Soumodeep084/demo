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





Output :
Enter initial state (comma separated, e.g. A-table, B-A): B-table, A-table, C-A Enter goal state (comma separated, e.g. C-B,A-C): C-table, B-C,A-B
Initial State: ['B-table', 'A-table', 'C-A']
Goal State: ['C-table', 'B-C', 'A-B']
Step 1: Achieving goal -> C-table
Action added to plan: Put C on table
Current State: ['B-table', 'A-table', 'C-A', 'C-table']
Step 2: Achieving goal -> B-C
Action added to plan: Stack B on C
Current State: ['B-table', 'A-table', 'C-A', 'C-table', 'B-C']
Step 3: Achieving goal -> A-B
Action added to plan: Stack A on B
Current State: ['B-table', 'A-table', 'C-A', 'C-table', 'B-C', 'A-B']
Final Plan:
Start
Put C on table
Stack B on C
Stack A on B
Finish
