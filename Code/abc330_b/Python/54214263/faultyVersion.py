N, L, R = map(int, input().split())
a = list(map(int, input().split()))
ans = [] 
for i in a:
    if i < L:
        ans.append(L)
    elif i > L and i < R:
        ans.append(i)
    else:
        ans.append(R)
print(*ans)