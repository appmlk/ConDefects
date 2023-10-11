from bisect import bisect_left

tri = [(i * i - i) // 2 for i in range(2000)]

N = int(input())

vals = []

for i in range(7):
    u = bisect_left(tri, N + 1)  - 1

    print(N, u, tri[u])

    vals.extend([i] * u)
    N -= tri[u]

assert N == 0
assert vals[0] == 0

curr = vals.pop(0)
out = []

mult = 1

for v in vals:
    #curr *= 10

    u = 7 - ((mult * (curr - v)) % 7)

    out.append(u)
    mult *= 5
    mult %= 7
    
    curr = v

print(''.join(map(str,out[::-1])))
