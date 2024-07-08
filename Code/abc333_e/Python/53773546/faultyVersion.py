from collections import deque
n = int(input())
a = [list(map(int,input().split())) for i in range(n)]

d = {}

s = [0] * n
b = []

ans = 0

for i in range(n):
    if a[i][0] == 1:
        x = a[i][1]

        if x in d:
            d[x].append(i)

        else:
            d[x] = deque([i])

for i in range(n):
    if a[n-1-i][0] == 2:
        x = a[n-1-i][1]

        if x in d:
            if d:
                while d[x]:
                    j = d[x].pop()
                    if j < n-1-i:
                        s[j] += 1
                        s[n-1-i] -= 1

                        b.append(j)
                        break

                if len(d) == 0 and n-1-i <j:
                    ans = -1

            else:
                ans = -1
                break

        else:
            ans = -1
            break

for i in range(1,n):
    s[i] += s[i-1]

p = set(b)

k = []
for i in range(n):
    if a[i][0] == 1:
        if i in p:
            k.append(1)
        else:
            k.append(0)

if ans == 0:
    ans = max(s)

    print(ans)
    print(*k)

else:
    print(ans)
