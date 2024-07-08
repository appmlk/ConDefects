from ortools.linear_solver import pywraplp
l,r=map(int,input().split())
a=list(map(int,input().split()))
b=list(map(int,input().split()))
c=[list(map(int,input().split())) for i in range(l)]
p=pywraplp.Solver.CreateSolver("PDLP")
x=[p.NumVar(0,p.infinity(),"x_{}".format(i)) for i in range(l)]
y=[p.NumVar(0,p.infinity(),"y_{}".format(i)) for i in range(r)]
for i in range(l):
    for j in range(r):
        p.Add(c[i][j]<=x[i]+y[j])
p.Minimize(sum([a[i]*x[i] for i in range(l)])+sum([b[i]*y[i] for i in range(r)]))
p.Solve()
print(int(p.Objective().Value()))