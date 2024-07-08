s = list(input())
t = list(input())
ans = []
idx = 0
for i in range(len(s)):
    for j in range(idx, len(t)):
        if s[i] == t[j]:
            ans.append(j+1)
            idx = j+1
            break
print(*ans)