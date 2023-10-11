from fractions import Fraction


inf = float("inf")
N = int(input())
X = []
for _ in range(N):
    x, y = map(int, input().split())
    
    a1 = x
    b1 = y - 1
    t1 = Fraction(b1, a1)
    
    a2 = x-1
    b2 = y
    if a2 == 0:
        t2 = inf
    else:
        t2 = Fraction(b2, a2)
    X.append((t1, t2))

X.sort(key=lambda x:x[1])
ans = 0
R = -inf
for l, r in X:
    if l >= R:
        ans += 1
        R = r
        

print(ans)
    
    