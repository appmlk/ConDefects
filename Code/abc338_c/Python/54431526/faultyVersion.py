
n = int(input())
q = list(map(int, input().split()))
a = list(map(int, input().split()))
b = list(map(int, input().split()))


from ortools.sat.python import cp_model
import sys
model = cp_model.CpModel()

ub = 10**6 + 10
x = model.NewIntVar(0, ub, 'x')
y = model.NewIntVar(0, ub, 'y')

for i in range(n):
    model.Add(a[i] * x + b[i] * y <= q[i])

model.Maximize(x + y)
solver = cp_model.CpSolver()
status = solver.Solve(model)

if status == cp_model.OPTIMAL:
    print(solver.ObjectiveValue())
    print("x, y = ",solver.Value(x),solver.Value(y), file = sys.stderr)
else:
  assert False