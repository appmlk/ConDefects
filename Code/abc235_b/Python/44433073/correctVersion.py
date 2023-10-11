N = int(input())
H = list(map(int,input().split()))

ans=H[0]
for n in range(N-1):
    if H[n+1]>H[n]:
        ans=H[n+1]
    else:
        break
print(ans)
