N = int(input())
A = list(map(int, input().split()))

s = [0]
for a in A:
    s.append(min(s[-1] + 1, a))
t = [0]
for a in A[::-1]:
    t.append(min(t[-1] + 1, a))

ans = 0
for i in range(N // 2 + 1):
    ans = max(ans, min(s[i+1], t[N-i]))

print(ans)
