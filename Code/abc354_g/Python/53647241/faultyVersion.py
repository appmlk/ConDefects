n = int(input())
s: list[str] = [input().rstrip() for _ in range(n)]
a = list(map(int, input().split()))

ng = [[s[i].find(s[j]) != -1 or s[j].find(s[i]) != -1 for j in range(n)] for i in range(n)]

import sys
import ortools.linear_solver.pywraplp as lp

solver = lp.Solver('SolveIntegerProblem', lp.Solver.CBC_MIXED_INTEGER_PROGRAMMING)

x = [solver.IntVar(0, 1, 'x[%i]' % i) for i in range(n)]

for i in range(n):
    for j in range(i+1, n):
        if ng[i][j]:
            solver.Add(x[i] + x[j] <= 1)

solver.Maximize(solver.Sum([a[i] * x[i] for i in range(n)]))
solver.set_time_limit(1200)

status = solver.Solve()
print(int(solver.Objective().Value()))

if status == lp.Solver.OPTIMAL:
    print("Optimal", file=sys.stderr)
else:
    print("Not optimal", file=sys.stderr)

