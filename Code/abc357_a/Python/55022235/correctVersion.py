N,M = map(int,input().split())
H = list(map(int,input().split()))
s = 0
for i in range(N):
    if H[i] > M:
        break
    else:
        M -= H[i]
        s += 1
print(s)