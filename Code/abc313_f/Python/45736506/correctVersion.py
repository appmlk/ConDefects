import numpy as np
import scipy.optimize

n, m = map(int, input().split())
ab = [tuple(map(int, input().split())) for _ in range(n)]
xy = [(x-1, y-1) for _ in range(m) for x, y in [map(int, input().split())]]

for x, y in xy:
    if x == y:
        ab[x] = (max(ab[x]), min(ab[x]))

basis = sum(a for a, b in ab)
c = np.array([-(b-a)/2 for a, b in ab])

A_ub = np.eye(n)
b_ub = np.zeros([n])
for x, y in xy:
    if x != y:
        A_ub[x, y] = -1
        A_ub[y, x] = -1

lp = scipy.optimize.linprog(c, A_ub=A_ub, b_ub=b_ub, bounds=(0, 1), integrality=1)
print(basis - c@lp.x)
