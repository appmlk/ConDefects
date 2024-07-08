import sympy
N = int(input())
A = set(sympy.factorint(N).keys())
print("Yes" if A == set([2, 3]) or A == set([2]) or A == set([3]) else "No")