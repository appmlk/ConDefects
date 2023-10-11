N,M,S = map(int,input().split())
a = list(map(int,input().split()))
b = [0]
for i in range(N-1,-1,-1):
    b.append(b[-1]+a[i])
ans = 0
for j in range(N+1):
    for i in range(N+1):
        if i == j:
            continue
        yi = S - (N-j)*M
        yi /= j-i
        yj = S - (N-i)*M
        yj /= i-j
        if yi >= 0 and yj >= 0:
            tmp = b[N-i]*yi + b[N-j]*yj
        else:
            continue
        if ans < tmp:
            ans = tmp
print(ans)