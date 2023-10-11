N, P, Q = map(int, input().split())
D = list(map(int, input().split()))

res = P

for d in D:
    if (p := Q + d) < res:
        res = p

print(res)
