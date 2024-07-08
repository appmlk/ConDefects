from scipy.optimize import minimize
from math import sqrt

N = int(input())
segments = [tuple(map(int, input().split())) for i in range(N)]

def f(param):
    x, y = param
    ret = 0
    for a, b, c, d in segments:
        if (a - c) * (a - x) + (b - d) * (b - y) < 0:
            ret = max(ret, sqrt((a - x) ** 2 + (b - y) ** 2))
        elif (c - a) * (c - x) + (d - b) * (d - y) < 0:
            ret = max(ret, sqrt((c - x) ** 2 + (d - y) ** 2))
        else:
            ret = max(ret, abs((a - c) * (b - y) - (b - d) * (a - x)) / sqrt((a - c) ** 2 + (b - d) ** 2))
    return ret

print(minimize(f, (500, 500), args=(), method='trust-constr', options = {"maxiter": 5000, "gtol":1e-15, "xtol": 1e-15, "barrier_tol": 1e-12}).fun)
