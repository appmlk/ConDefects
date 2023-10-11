n,m = map(int,input().split())
q = True
if n > m:
    n,m = m,n
    q = False
t = m - n
ans = []
for i in range(2 + t):
    ans.append([1,i + 1])
if t == 0:
    for i in range(2):
        ans.append([2,2 + t - i])
else:
    for i in range(3):
        ans.append([2,2 + t - i])
for i in range(n - 2):
    ans.append([2 + i,t + 2 + i + 1])
    ans.append([2 + i + 1,t + 2 + i + 1])
    ans.append([2 + i + 1,t + 2 + i])
    ans.append([2 + i + 1,t + 2 + i - 1])

if n == 1:
    print(m)
    if q:
        for i in range(m):
            print(1,i + 1)
    else:
        for i in range(m):
            print(i + 1,1)
elif n == m and n % 2 == 1:
    print(4 * m - 3)
    for i in range(3):
        for j in range(3):
            print(i + 1,j + 1)
    t = (m - 3) // 2
    for i in range(t):
        for j in range(3):
            for k in range(3):
                if j != 0 or k != 0:
                    print(2 * (i + 1) + j,2 * (i + 1) + k)
else:
    print(len(ans))
    if q:
        for i in ans:
            print(i[0],i[1])
    else:
        for i in ans:
            print(i[1],i[0])